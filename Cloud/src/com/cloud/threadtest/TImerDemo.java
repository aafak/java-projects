package com.cloud.threadtest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;


public class TImerDemo {
		
	Map<Long, Timer> tlist;
	class MyTask extends TimerTask{
		
		long taskid;
		public MyTask(long taskId){
			this.taskid=taskId;
		}
		public void run(){
			int sleepTime=1000;
			for(int i=1;i<=5;i++){
			System.out.println(i+"Executing task:"+taskid);
			try{ 
				if(taskid==1){
					 sleepTime=60000;
				}
				else if(taskid==2){
					 sleepTime=30000;

				}
				else if(taskid==3){
					 sleepTime=10000;

				}
				sleepTime=1;
				Thread.sleep(sleepTime);
			
			
			}//check status again after 5 sec
			 catch ( Exception e) {System.out.println("Thread interupted:"+e.getMessage());}
			}
			Timer currentTimer=tlist.get(taskid);
			System.out.println(currentTimer);
			if(currentTimer!=null)
			   currentTimer.cancel();
	
			
		}
	}
	public  TImerDemo(){
		tlist=new HashMap<Long,Timer>();
		for(int i=1;i<=5;i++){
			Timer  t=new Timer();
			System.out.println(t);

			tlist.put((long)i, t);
			System.out.println("scheduling task:"+i);
			t.schedule(new MyTask(i), (long)5);
		}
	}
	public static void main(String[] args) {

		new TImerDemo();
		
	
	}

}
