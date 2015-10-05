package com.cloud.rabbitmq.Producer;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.StringRpcServer;
public class SendAndRecv {

	/**
	 * @param args
	 */
	private final static String QUEUE_NAME = "hello";
	private final static String QUEUE2_NAME = "hello2";

	  public static void main(String[] argv) throws java.io.IOException,  java.lang.InterruptedException {
		  ConnectionFactory factory = new ConnectionFactory();
		    //factory.setHost("10.20.63.80"); //receiver ip
		    factory.setHost("localhost"); //localhost as receiver ip
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
		    
		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    String message = "Hello World111!";
		    Map<String,String>requestMap=new HashMap<String,String>();
		    requestMap.put("method", "test");
		    requestMap.put("args", message);
            Gson gson=new Gson();
		    channel.basicPublish("", QUEUE_NAME, null, gson.toJson(requestMap).getBytes());
		    System.out.println(" [x] Sent '" + gson.toJson(requestMap) + "'");
		       
		    
		    
		    //wating for respnse
		    QueueingConsumer consumer = new QueueingConsumer(channel);
		    channel.basicConsume(QUEUE_NAME, true, consumer);
	        JsonElement response=null;
		    while (true) {
		      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
		      String msg = new String(delivery.getBody());
		      Object reply=delivery.getBody();
		      JsonObject responseUnit = new JsonParser().parse(new String((byte[])reply)).getAsJsonObject();
	         
	          System.out.println("response as ack"+responseUnit);
		      //Consumer request=gson.fromJson(responseUnit, Consumer.class);
		     // System.out.println(" [x]request Methodxx '" + request.getMethod() + "'");
		      //System.out.println(" [x]request Method argsxcx'" +request.getArgs() + "'");

		      //channel.close();
			    //connection.close();
		    }
		   
	  }

}
