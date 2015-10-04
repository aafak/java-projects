package test;

import java.util.Timer;
import java.util.TimerTask;
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
	}
}

public class TImerDemo {
	Timer t;
	public void start(){
		for(int i=1;i<=5;i++){
			 t=new Timer();
			System.out.println("scheduling task:"+i);
			t.schedule(new MyTask(i), (long)5);
		}
	}
	public static void main(String[] args) {

		
		
	
	}

}
