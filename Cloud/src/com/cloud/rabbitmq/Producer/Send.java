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
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.StringRpcServer;
public class Send {

	/**
	 * @param args
	 */
	private final static String QUEUE_NAME = "hello";

	  public static void main(String[] argv)
	      throws java.io.IOException {
		  ConnectionFactory factory = new ConnectionFactory();
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
		    
		    channel.close();
		    connection.close();
	  }

}
