package com.cloud.rabbitmq.Consumer;
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
  
public class RecvAndSend {

	private final static String QUEUE_NAME = "hello";
	private final static String QUEUE2_NAME = "hello2";

	  public static void main(String[] argv)
	      throws java.io.IOException,
	             java.lang.InterruptedException {

	    ConnectionFactory factory = new ConnectionFactory();
	    factory.setHost("localhost");
	    Connection connection = factory.newConnection();
	    Channel channel = connection.createChannel();
        Gson gson=new Gson();
	    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
	    System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
	    
	    QueueingConsumer consumer = new QueueingConsumer(channel);
	    channel.basicConsume(QUEUE_NAME, true, consumer);
JsonElement response=null;
	    while (true) {
	      QueueingConsumer.Delivery delivery = consumer.nextDelivery();
	      String message = new String(delivery.getBody());
	      Object reply=delivery.getBody();
	      JsonObject responseUnit = new JsonParser().parse(new String((byte[])reply)).getAsJsonObject();
         
System.out.println("response"+responseUnit);
	      Consumer request=gson.fromJson(responseUnit, Consumer.class);
	      System.out.println(" [x]request Methodxx '" + request.getMethod() + "'");
	      System.out.println(" [x]request Method argsxcx'" +request.getArgs() + "'");

	      
	      
	      //sending ack
	      
	      channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    String msg = "Msg received, sending acknowledegement!";
		    Map<String,String>requestMap=new HashMap<String,String>();
		    requestMap.put("method", "test");
		    requestMap.put("args", msg);
           gson=new Gson();
		    channel.basicPublish("", QUEUE_NAME, null, gson.toJson(requestMap).getBytes());
	      
	    }
	    }

}
