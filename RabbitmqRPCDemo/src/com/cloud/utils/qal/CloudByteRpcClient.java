package com.cloud.utils.qal;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeoutException;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;
import com.rabbitmq.client.StringRpcServer;
import com.rabbitmq.client.AMQP.Queue;
import com.rabbitmq.client.impl.MethodArgumentReader;
import com.rabbitmq.client.impl.MethodArgumentWriter;
import com.rabbitmq.client.impl.ValueReader;
import com.rabbitmq.client.impl.ValueWriter;
import com.rabbitmq.utility.BlockingCell;

/**
* Convenience class which manages a temporary reply queue for simple RPC-style communication.
* The class is agnostic about the format of RPC arguments / return values.
* It simply provides a mechanism for sending a message to an exchange with a given routing key,
* and waiting for a response on a reply queue.
*/
public class CloudByteRpcClient {
	/** Channel we are communicating on */
	private final Channel _channel;
	/** Exchange to send requests to */
	private final String _exchange;
	/** Routing key to use for requests */
	private final String _routingKey;
	/** timeout to use on call responses */
	private final int _timeout;
	/** NO_TIMEOUT value must match convention on {@link BlockingCell#uninterruptibleGet(int)} */
	protected final static int NO_TIMEOUT = -1;
	
	/** Map from request correlation ID to continuation BlockingCell */
	private final Map<String, BlockingCell<Object>> _continuationMap = new HashMap<String, BlockingCell<Object>>();
	/** Contains the most recently-used request correlation ID */
	private int _correlationId;
	
	/** The name of our private reply queue */
	private String _replyQueue;
	/** Consumer attached to our reply queue */
	private QueueingConsumer _consumer;
	
	/**
	 * Construct a new CloudByteRpcClient that will communicate on the given channel, sending
	 * requests to the given exchange with the given routing key.
	 * <p/>
	 * Causes the creation of a temporary private autodelete queue.
	 * @param channel the channel to use for communication
	 * @param exchange the exchange to connect to
	 * @param routingKey the routing key
	 * @param timeout milliseconds before timing out on wait for response
	 * @throws IOException if an error is encountered
	 * @see #setupReplyQueue
	 */
	public CloudByteRpcClient(Channel channel, String exchange, String routingKey, int timeout) throws IOException {
	    _channel = channel;
	    _exchange = exchange;
	    _routingKey = routingKey;
	    if (timeout < NO_TIMEOUT) throw new IllegalArgumentException("Timeout arguument must be NO_TIMEOUT(-1) or non-negative.");
	    _timeout = timeout;
	    _correlationId = 0;
	
	    _replyQueue = setupReplyQueue();
	    _consumer = setupConsumer();
	}
	
	/**
	 * Construct a new CloudByteRpcClient that will communicate on the given channel, sending
	 * requests to the given exchange with the given routing key.
	 * <p/>
	 * Causes the creation of a temporary private autodelete queue.
	 * <p/>
	 * Waits forever for responses (that is, no timeout).
	 * @param channel the channel to use for communication
	 * @param exchange the exchange to connect to
	 * @param routingKey the routing key
	 * @throws IOException if an error is encountered
	 * @see #setupReplyQueue
	 */
	public CloudByteRpcClient(Channel channel, String exchange, String routingKey) throws IOException {
	    this(channel, exchange, routingKey, NO_TIMEOUT);
	}
	
	/**
	 * Private API - ensures the CloudByteRpcClient is correctly open.
	 * @throws IOException if an error is encountered
	 */
	public void checkConsumer() throws IOException {
	    if (_consumer == null) {
	        throw new EOFException("CloudByteRpcClient is closed");
	    }
	}
	
	/**
	 * Public API - cancels the consumer, thus deleting the temporary queue, and marks the CloudByteRpcClient as closed.
	 * @throws IOException if an error is encountered
	 */
	public void close() throws IOException {
	    if (_consumer != null) {
	        _channel.basicCancel(_consumer.getConsumerTag());
	        _consumer = null;
	    }
	}
	
	/**
	 * Creates a server-named exclusive autodelete queue to use for
	 * receiving replies to RPC requests.
	 * @throws IOException if an error is encountered
	 * @return the name of the reply queue
	 */
	protected String setupReplyQueue() throws IOException {
		String key = UUID.randomUUID().toString();
		_channel.exchangeDeclare(key, "direct", false, true, null);
		Queue.DeclareOk res = _channel.queueDeclare(key, false, true, true, null);
		_channel.queueBind(key, key, key);
		return res.getQueue();
	}
	
	/**
	 * Registers a consumer on the reply queue.
	 * @throws IOException if an error is encountered
	 * @return the newly created and registered consumer
	 */
	protected QueueingConsumer setupConsumer() throws IOException {
		QueueingConsumer consumer = new QueueingConsumer(_channel);
		_channel.basicConsume(_replyQueue, true, consumer);
		
	    return consumer;
	}
	
	public void publish(AMQP.BasicProperties props, byte[] message)
	    throws IOException
	{
	    _channel.basicPublish(_exchange, _routingKey, props, message);
	}
	
	public JsonElement mapCloudByteCall(AMQP.BasicProperties props, byte[] message, int RESPONSE_TIMEOUT, int MAX_ATTEMPTS)
	    throws IOException, ShutdownSignalException, TimeoutException, ConsumerCancelledException, InterruptedException
	{
		checkConsumer();
	    publish(null, message);
	    JsonElement response = null;
	    Object reply = null;
	   
	    
	    while (true) {
            QueueingConsumer.Delivery delivery =null;
            for(int i=0;i<MAX_ATTEMPTS;i++)
	    	{
            	delivery = _consumer.nextDelivery(RESPONSE_TIMEOUT);
            	if(delivery!=null)
	    			break;
	    	}
            if (null == delivery) {
	    		throw new TimeoutException(); 
	    	}
            reply = delivery.getBody();
            JsonObject responseUnit = new JsonParser().parse(new String((byte[])reply)).getAsJsonObject();
            if ((responseUnit.get("result") != null) && (!responseUnit.get("result").isJsonNull())) {
            	response = responseUnit.get("result");
            }
            if ((responseUnit.get("ending") != null) && (!responseUnit.get("ending").isJsonNull()) && ((String)responseUnit.get("ending").getAsString() == "true")) {
            	break;
            }
        }
	    
	    return response;
	}
	
	public byte[] primitiveCall(AMQP.BasicProperties props, byte[] message)
    throws IOException, ShutdownSignalException, TimeoutException
	{
	    checkConsumer();
	    BlockingCell<Object> k = new BlockingCell<Object>();
	    synchronized (_continuationMap) {
	        _correlationId++;
	        String replyId = "" + _correlationId;
	        props = ((props==null) ? new AMQP.BasicProperties.Builder() : props.builder())
	                .correlationId(replyId).replyTo(_replyQueue).build();
	        _continuationMap.put(replyId, k);
	    }
	    publish(props, message);
	    Object reply = k.uninterruptibleGet(_timeout);
	    if (reply instanceof ShutdownSignalException) {
	        ShutdownSignalException sig = (ShutdownSignalException) reply;
	        ShutdownSignalException wrapper =
	            new ShutdownSignalException(sig.isHardError(),
	                                        sig.isInitiatedByApplication(),
	                                        sig.getReason(),
	                                        sig.getReference());
	        wrapper.initCause(sig);
	        throw wrapper;
	    } else {
	        return (byte[]) reply;
	    }
	}
	
	/**
	 * Perform a simple byte-array-based RPC roundtrip.
	 * @param message the byte array request message to send
	 * @return the byte array response received
	 * @throws ShutdownSignalException if the connection dies during our wait
	 * @throws IOException if an error is encountered
	 * @throws TimeoutException if a response is not received within the configured timeout
	 * @throws InterruptedException 
	 * @throws ConsumerCancelledException 
	 */
	public byte[] primitiveCall(byte[] message)
	    throws IOException, ShutdownSignalException, TimeoutException, ConsumerCancelledException, InterruptedException {
	    return primitiveCall(null, message);
	}
	
	/**
	 * Perform a simple string-based RPC roundtrip.
	 * @param message the string request message to send
	 * @return the string response received
	 * @throws ShutdownSignalException if the connection dies during our wait
	 * @throws IOException if an error is encountered
	 * @throws TimeoutException if a timeout occurs before the response is received
	 * @throws InterruptedException 
	 * @throws ConsumerCancelledException 
	 */
	public String stringCall(String message)
	    throws IOException, ShutdownSignalException, TimeoutException, ConsumerCancelledException, InterruptedException
	{
	    byte[] request;
	    try {
	        //request = message.getBytes(StringRpcServer.STRING_ENCODING);
	        request = message.getBytes("");

	    } catch (IOException _) {
	        request = message.getBytes();
	    }
	    byte[] reply = primitiveCall(request);
	    try {
	        return new String(reply, "");
	    } catch (IOException _) {
	       return new String(reply);
	    }
	}
	
	/**
	 * Perform an AMQP wire-protocol-table based RPC roundtrip <br><br>
	 *
	 * There are some restrictions on the values appearing in the table: <br>
	 * they must be of type {@link String}, {@link LongString}, {@link Integer}, {@link java.math.BigDecimal}, {@link Date},
	 * or (recursively) a {@link Map} of the enclosing type.
	 *
	 * @param message the table to send
	 * @return the table received
	 * @throws ShutdownSignalException if the connection dies during our wait
	 * @throws IOException if an error is encountered
	 * @throws TimeoutException if a timeout occurs before a response is received
	 * @throws InterruptedException 
	 * @throws ConsumerCancelledException 
	 */
	public Map<String, Object> mapCall(Map<String, Object> message)
	    throws IOException, ShutdownSignalException, TimeoutException, ConsumerCancelledException, InterruptedException
	{
	    ByteArrayOutputStream buffer = new ByteArrayOutputStream();
	    MethodArgumentWriter writer = new MethodArgumentWriter(new ValueWriter(new DataOutputStream(buffer)));
	    writer.writeTable(message);
	    writer.flush();
	    byte[] reply = primitiveCall(buffer.toByteArray());
	    MethodArgumentReader reader =
	        new MethodArgumentReader(new ValueReader(new DataInputStream(new ByteArrayInputStream(reply))));
	    return reader.readTable();
	}
	
	/**
	 * Perform an AMQP wire-protocol-table based RPC roundtrip, first
	 * constructing the table from an array of alternating keys (in
	 * even-numbered elements, starting at zero) and values (in
	 * odd-numbered elements, starting at one) <br>
	 * Restrictions on value arguments apply as in {@link CloudByteRpcClient#mapCall(Map)}.
	 *
	 * @param keyValuePairs alternating {key, value, key, value, ...} data to send
	 * @return the table received
	 * @throws ShutdownSignalException if the connection dies during our wait
	 * @throws IOException if an error is encountered
	 * @throws TimeoutException if a timeout occurs before a response is received
	 * @throws InterruptedException 
	 * @throws ConsumerCancelledException 
	 */
	public Map<String, Object> mapCall(Object[] keyValuePairs)
	    throws IOException, ShutdownSignalException, TimeoutException, ConsumerCancelledException, InterruptedException
	{
	    Map<String, Object> message = new HashMap<String, Object>();
	    for (int i = 0; i < keyValuePairs.length; i += 2) {
	        message.put((String) keyValuePairs[i], keyValuePairs[i + 1]);
	    }
	    return mapCall(message);
	}
	
	/**
	 * Retrieve the channel.
	 * @return the channel to which this client is connected
	 */
	public Channel getChannel() {
	    return _channel;
	}
	
	/**
	 * Retrieve the exchange.
	 * @return the exchange to which this client is connected
	 */
	public String getExchange() {
	    return _exchange;
	}
	
	/**
	 * Retrieve the routing key.
	 * @return the routing key for messages to this client
	 */
	public String getRoutingKey() {
	    return _routingKey;
	}
	
	/**
	 * Retrieve the continuation map.
	 * @return the map of objects to blocking cells for this client
	 */
	public Map<String, BlockingCell<Object>> getContinuationMap() {
	    return _continuationMap;
	}
	
	/**
	 * Retrieve the correlation id.
	 * @return the most recently used correlation id
	 */
	public int getCorrelationId() {
	    return _correlationId;
	}
	
	/**
	 * Retrieve the reply queue.
	 * @return the name of the client's reply queue
	 */
	public String getReplyQueue() {
	    return _replyQueue;
	}
	
	/**
	 * Retrieve the consumer.
	 * @return an interface to the client's consumer object
	 */
	public Consumer getConsumer() {
	    return _consumer;
	}
}