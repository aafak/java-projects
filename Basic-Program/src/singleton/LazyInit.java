package singleton;

import java.util.Date;

/**
 * Lazy initialization method to implement
 * Singleton pattern creates the instance in the global access method
 * implementation works fine incase of single threaded environment
 * but when it comes to multithreaded systems, it can cause issues if multiple 
 * threads are inside the if loop at the same time. It will destroy the singleton pattern
 * and both threads will get the different instances of singleton class. 
 * In next section, we will see different ways to create a thread-safe singleton class.
 * 
 * 
 * 
 */
class Logger1{
	private static Logger1 log;
	private Logger1(){
		System.out.println("Initiating Logger...");
	}
	
	public static Logger1 getInstance(){
		if(log == null){
			log = new Logger1();
		}
		return log;
	}
	
	public void info(String log){
		System.out.println("["+new Date()+"]:"+log);
	}
	
}
public class LazyInit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger1 log1 = Logger1.getInstance();
		log1.info("Using Logger11");
		
		Logger1 log2 = Logger1.getInstance();
		log2.info("Using Logger2");
		

	}

}
