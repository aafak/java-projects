package singleton;

import java.util.Date;
/**
 * Eager initialization
 * In eager initialization, the instance of Singleton Class is created at the time of class
 * loading, this is the easiest method to create a singleton class but it has a drawback that 
 * instance is created even though client application might not be using it.
 * If your singleton class is not using a lot of resources, 
 * this is the approach to use. But in most of the scenarios, 
 * Singleton classes are created for resources such as File System, 
 * Database connections etc and we should avoid the instantiation until unless
 * client calls the getInstance method. Also this method doesn’t provide any 
 * options for exception handling
 * @author aafak
 *
 */
class Logger{
	private static final Logger logger = new Logger();
	
	private Logger(){
		System.out.println("Initiating Logger...");

	}
	public static Logger getInstance(){
		return logger;
	}
	
	public void info(String log){
		System.out.println("["+new Date()+"]:"+log);
	}
}

public class EagerInit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger log1 = Logger.getInstance();
		log1.info("Using logger1");
		
		Logger log2 = Logger.getInstance();
		log2.info("Using logger2");
	}

}
