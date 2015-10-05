package com.cloud.utils.qal;

import java.io.EOFException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;


import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.AMQP.Queue;
import com.rabbitmq.client.ShutdownSignalException;


public class CloudByteRpcServer {

	private static final Logger s_logger = Logger.getLogger(CloudByteRpcServer.class);

	/** Channel we are communicating on */
	private Channel _channel;

	/** queue name to use for requests */
	private String _routingKey;

	private ConnectionFactory cfconn;

	private Connection conn;

	/** The name of our private reply queue */
	private String _queue;

	/** Consumer attached to our reply queue */
	private QueueingConsumer _consumer;

	/*Listener service class */
	ListenerService lst;

	/** Boolean controlling the exit from the mainloop. */
	private boolean _mainloopRunning = true;

	/**Exchange name of the queue**/
	private String exchange;

	/**temporary id sent by the controller in the request - key to which devman should respond**/
	private JsonElement _msg_id;

	/**request unit method**/
	private JsonElement _reqMethod;

	/**request unit args**/
	private JsonElement _reqArgs;

	/** final map to send the ending message to the controller */
	private final Map<String,Boolean> endMap = new HashMap<String,Boolean>();

	/** json string for the end map**/
	private final String endString;

	private int sleepInterval = 1;

	private static int TIME_IN_SECONDS = 1000;
	private static int INTERVAL_MAX=30;
	private static int INTERVAL_STEPPING=2;

	public CloudByteRpcServer(String exchange,String routingKey,ListenerService Obj) throws IOException {
		/**assigning the exchange**/
		this.exchange = exchange;

		_routingKey = routingKey;

		/*
		 * Creating the channel
		 * conn, _channel, _queue and _consumer are initialized in reconnectAMQP() called inside createChannel().
		 */
		createChannel();

		lst = Obj;

		/**fill endMap and convert into a json string**/
		endMap.put("ending", true);
		endMap.put("failure", false);
		endString = new Gson().toJson(endMap);
	}

	protected String setUpListenQueue() throws IOException {
		_channel.exchangeDeclare(exchange, "topic", false, false, null);
		Queue.DeclareOk res = _channel.queueDeclare(_routingKey, false, true, true, null);
		_channel.queueBind(_routingKey, exchange, _routingKey);
		return res.getQueue();
	}

	protected void createChannel() throws IOException {
		//String uri = "amqp://10.20.1.248:5672";
		cfconn = new ConnectionFactory();
		cfconn.setHost("localhost");
		//cfconn.setUri(uri);
		
		reconnectAMQP();
	}

	public void closeChannel() throws IOException {
		conn.close();
	}

	/**
	 * Public API - main server loop. Call this to begin processing
	 * requests. Request processing will continue until the Channel
	 * (or its underlying Connection) is shut down, or until
	 * terminateMainloop() is called.
	 *
	 * Note that if the mainloop is blocked waiting for a request, the
	 * termination flag is not checked until a request is received, so
	 * a good time to call terminateMainloop() is during a request
	 * handler.
	 *
	 * @return the exception that signaled the Channel shutdown, or null for orderly shutdown
	 */

	public void service() {

		while(_mainloopRunning) {
			try{
				if(! conn.isOpen()) {
					reconnectAMQP();
				}

				QueueingConsumer.Delivery delivery = _consumer.nextDelivery();
				Map<String, Object> mStatus = new HashMap<String, Object>();

				Boolean result = handleCall(delivery,mStatus);
				String responseString = new Gson().toJson(mStatus);

				Object details = mStatus.get("result");

				String responseText = "yes";
				if(details == null) {
					responseText = "no";
				}

				if(responseText != "yes") {

					if(result == true) {
						_channel.basicPublish(_msg_id.getAsString(), _msg_id.getAsString(), null, endString.getBytes("UTF-8"));
					}
					else {
						_channel.basicPublish(_msg_id.getAsString(), _msg_id.getAsString(), null, responseString.getBytes("UTF-8"));
						_channel.basicPublish(_msg_id.getAsString(), _msg_id.getAsString(), null, endString.getBytes("UTF-8"));
					}
				}
				else {
					_channel.basicPublish(_msg_id.getAsString(), _msg_id.getAsString(), null, responseString.getBytes("UTF-8"));
					_channel.basicPublish(_msg_id.getAsString(), _msg_id.getAsString(), null, endString.getBytes("UTF-8"));
				}
			}
			catch (ShutdownSignalException e) {
				s_logger.info("AMQP connection received ShutDown signal: "+e.getLocalizedMessage()+ ". Present message_id is "+_msg_id);
				e.printStackTrace();
				if (conn.isOpen()) {
					try {
						conn.close();
					} catch (IOException e1) {
						s_logger.info("Exception while handling the shutDown signal: "+e1.getLocalizedMessage());
						e1.printStackTrace();
					}
				}
			}
			catch(Exception e) {
				s_logger.info("AMQP connection encountered exception: "+e.getLocalizedMessage()+ ". Present message_id is "+_msg_id);
				e.printStackTrace();
			}

		}
	}

	public Boolean reconnectAMQP() {
		while(true) {
			try {
				s_logger.info("Connecting to AMQP server...");
				//try reconnect to amqp server
				conn = cfconn.newConnection();
				if(conn.isOpen()){
					_channel = conn.createChannel();
					_queue = setUpListenQueue();
					_consumer = setupConsumer();

					break;
				}
			}
			catch (IOException e) {
				s_logger.info("IO Exception in reconnectAMQP(). " + e.toString());
			}
			catch (Exception e) {
				s_logger.info("Exception in reconnectAMQP(). " + e.toString());
			}

			try {
				//try to reconnect after 1,3,5,7,9....30,30,30....
				s_logger.info("AMQP server is unreachable. Will try connecting again in "+sleepInterval+" second(s).");
				Thread.sleep(sleepInterval*TIME_IN_SECONDS);

				if(sleepInterval < INTERVAL_MAX) {
					sleepInterval = sleepInterval+INTERVAL_STEPPING;
				}
				else {
					sleepInterval = INTERVAL_MAX;
				}
			}
			catch (InterruptedException e) {
				s_logger.info("AMQP connection thread received Interrupt Signal. " + e.toString());
			}
		}
		sleepInterval = 1;
		return true;
	}

	/**
	 * Lowest-level handler method. Calls
	 * handleCast(AMQP.BasicProperties,byte[]).
	 */
	public Boolean handleCall(QueueingConsumer.Delivery delivery,Map<String, Object> mStatus) {
		Object reply = null;

		//AMQP.BasicProperties requestProperties = delivery.getProperties();
		// _msg_id = requestProperties.getReplyTo();
		reply = delivery.getBody();

		JsonObject requestUnit = new JsonParser().parse(new String((byte[])reply)).getAsJsonObject();
		_msg_id = requestUnit.get("_msg_id");
		_reqMethod = requestUnit.get("method");
		_reqArgs = requestUnit.get("args");
		Boolean status = lst.processDetails(_reqMethod, _reqArgs, _msg_id, mStatus);

		if(status.equals(true))
			mStatus.put("failure", false);
		else
			mStatus.put("failure", true);

		//String element = "\"status\":" + " " +status;
		return status;
	}

	/**
	 * Registers a consumer on the reply queue.
	 * @throws IOException if an error is encountered
	 * @return the newly created and registered consumer
	 */
	protected QueueingConsumer setupConsumer() throws IOException {
		QueueingConsumer consumer = new QueueingConsumer(_channel);
		_channel.basicConsume(_queue, true, consumer);
		return consumer;
	}

	/**
	 * Private API - ensures the CloudByteRpcClient is correctly open.
	 * @throws IOException if an error is encountered
	 */
	public void checkConsumer() throws IOException {
		if (_consumer == null) {
			throw new EOFException("CloudByteRpcServer is closed");
		}
	}

	/**
	 * Call this method to terminate the mainloop.
	 *
	 * Note that if the mainloop is blocked waiting for a request, the
	 * termination flag is not checked until a request is received, so
	 * a good time to call terminateMainloop() is during a request
	 * handler.
	 */
	public void terminateMainloop() {
		_mainloopRunning = false;
	}

	/**
	 * Public API - cancels the consumer, thus deleting the queue, if
	 * it was a temporary queue, and marks the RpcServer as closed.
	 * @throws IOException if an error is encountered
	 */
	public void close() throws IOException {
		if (_consumer != null) {
			_channel.basicCancel(_consumer.getConsumerTag());
			_consumer = null;
		}
		terminateMainloop();
	}


	/**
	 * Retrieve the channel.
	 * @return the channel to which this server is connected
	 */
	public Channel getChannel() {
		return _channel;
	}

	/**
	 * Retrieve the routing key.
	 * @return the routing key for messages to this client
	 */
	public String getRoutingKey() {
		return _routingKey;
	}

	/**
	 * Retrieve the reply queue.
	 * @return the name of the client's reply queue
	 */
	public String getQueue() {
		return _queue;
	}

	/**
	 * Retrieve the consumer.
	 * @return an interface to the client's consumer object
	 */
	public Consumer getConsumer() {
		return _consumer;
	}

	public String getExchange() {
		return exchange;
	}

	public void setExchange(String exchange) {
		this.exchange = exchange;
	}

}
