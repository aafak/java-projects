package singleton;

import java.util.Date;

/***
 * The easier way to create a thread-safe singleton class is to make the global access 
 * method synchronized, 
 * so that only one thread can execute this method at a time.
 * works fine and provides thread-safety but it reduces the performance because of cost 
 * associated with the synchronized method, although we need it only for the first few threads 
 * who might create the separate instances (Read: Java Synchronization). 
 * To avoid this extra overhead every time, double checked locking principle is used
 * @author aafak
 *
 */
class Logger5{
	private static Logger5 logger;
	private Logger5(){
		System.out.println("Initializing logger...");
	}

	public static synchronized Logger5  getInstance(){
		if(logger == null){
			logger = new Logger5();
		}
		return logger;
	}
	public void info(String log){
		System.out.println("["+new Date()+"]:"+log);
	}
}

public class LazyInitThreadSafeVer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=1; i<=5; i++){
			final int j=i;
			Runnable thread = new Runnable(){
				public void run(){
					Logger5 log = Logger5.getInstance();
					log.info("Loggger using by thread: "+j);
				}
			};
			thread.run();
		}
		
		
	}

}
