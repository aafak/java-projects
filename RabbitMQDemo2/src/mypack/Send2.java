package mypack;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;
public class Send2 {

	/**
	 * @param args
	 */
	private final static String QUEUE_NAME = "hello";

	  public static void main(String[] argv)
	      throws java.io.IOException {
		  ConnectionFactory factory = new ConnectionFactory();
		    //factory.setHost("10.20.63.80"); //receiver ip
		    factory.setHost("localhost"); //localhost as receiver ip
		    Connection connection = factory.newConnection();
		    Channel channel = connection.createChannel();
		    
		    channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		    String message = "Hello World2vbnbv222222222!";

		    channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
		    System.out.println(" [x] Sent '" + message + "'");
		    channel.close();
		    connection.close();
	  }

}
