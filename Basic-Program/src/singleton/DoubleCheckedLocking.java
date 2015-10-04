package singleton;

import java.util.Date;
/***
 * To avoid this extra overhead of synchronized every time, double checked locking principle is used. 
 * In this approach, the synchronized block is used inside the if condition with an additional 
 * check to ensure that only one instance of singleton class is created.
 * @author aafak
 *
 */
class Logger6{
	private static Logger6 logger;
	private  Logger6(){
		System.out.println("Initializing logger...");
	}
	
	public static Logger6 getInstance(){
		if(logger == null){
			synchronized(Logger6.class){
				if(logger == null){
					logger = new Logger6();
				}
			}
		}
		return logger;
	}
	public void info(String log){
		System.out.println("["+new Date()+"]:"+log);
	}
}
public class DoubleCheckedLocking {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		for(int i=1; i<=5; i++){
			final int j=i;
			Runnable thread = new Runnable(){
				public void run(){
					Logger6 log = Logger6.getInstance();
					log.info("Loggger using by thread: "+j);
				}
			};
			thread.run();
		}
		
		
	}

}
