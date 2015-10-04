package test;

import java.util.Timer;
import java.util.TimerTask;


public class JavaReminder {
    Timer timer;

    public JavaReminder(int seconds) {
    	for(int i=1;i<=5;i++){
    		timer=new Timer();
			System.out.println("scheduling task:"+i);
			timer.schedule(new MyTask(i), (long)5);
		}
    }

class MyTask extends TimerTask{
		
		long taskid;
		public MyTask(long taskId){
			this.taskid=taskId;
		}
		public void run(){
			
			for(int i=1;i<=5;i++){
			System.out.println(i+"Executing task:"+taskid);
			try{ Thread.sleep(1000);}//check status again after 5 sec
			 catch ( Exception e) {System.out.println("Thread interupted:"+e.getMessage());}
			}
			timer.cancel();
		}
	}

    public static void main(String args[]) {
        System.out.println("Java timer is about to start");
        JavaReminder reminderBeep = new JavaReminder(5);
        System.out.println("Remindertask is scheduled with Java timer.");
    }
}


