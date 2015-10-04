package singleton;

import java.util.Date;
/**
 * Static block initialization implementation is similar to eager initialization, 
 * except that instance of class is created in the static block that provides option for 
 * exception handling.
 * Both eager initialization and static block initialization creates the instance 
 * even before it’s being used and that is not the best practice to use. So in further sections, 
 * we will learn how to create Singleton class that supports lazy initialization.
 * @author aafak
 *
 */
class Logger3{
	private static  Logger3 logger;

	static{
		try{
			logger = new Logger3();
		}catch(Exception ex){
			ex.printStackTrace();
		}

	}
	private Logger3(){
		System.out.println("Initiating Logger...");

	}
	public static Logger3 getInstance(){
		return logger;
	}

	public void info(String log){
		System.out.println("["+new Date()+"]:"+log);
	}
}

public class EagerInitWithStaticBlock {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Logger3 log1 = Logger3.getInstance();
		log1.info("Using logger1");
		
		Logger3 log2 = Logger3.getInstance();
		log2.info("Using logger2");
	}

}
