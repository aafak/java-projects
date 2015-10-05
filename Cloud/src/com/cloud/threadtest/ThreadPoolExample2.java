package com.cloud.threadtest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPoolExample2 {
	ExecutorService service=null;

	
	
	public static void main(String[] args) {
       //ExecutorService service =Executors.newFixedThreadPool(5);
	   ExecutorService service=Executors.newCachedThreadPool();
		
		for(int i=1;i<=100;i++){
			service.submit(new MyTask(i));
		}
		service.shutdown();
		//service.shutdownNow();
		

	}
}
class MyTask implements Runnable{
	int taskId;
	public MyTask(int taskId){
		this.taskId=taskId;

	}
	@Override
	public void run(){
		for(int i=1;i<=5;i++){
		System.out.println("Executing task:"+taskId+" [part-"+i+"] by thread:"+Thread.currentThread().getName());
		try{
			Thread.sleep(1000);
			
		}
		catch(Exception ex){
			System.out.println("Exception caught:"+ex.getMessage());
		}
		}
	}
}