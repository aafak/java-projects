package com.cloud.threadtest;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class ThreadPoolExample4 {

	
	
	public static void main(String[] args) {
       ExecutorService threadpool =Executors.newFixedThreadPool(5);
	  // ExecutorService service=Executors.newCachedThreadPool();
		
		CompletionService<String>pool=new ExecutorCompletionService<String>(threadpool);
		for(int i=1;i<=2;i++){
			pool.submit(new Task2(i));
		}
		try{

		for(int i=1;i<=2;i++){

			String result=pool.take().get();
			
			System.out.println(result);
		}
		threadpool.shutdown();
		//service.shutdownNow();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		System.out.println("End");

	}
}
final class Task2 implements Callable<String>{
	int taskId;
	public Task2(int taskId){
		this.taskId=taskId;

	}
	@Override
	public String call(){
		for(int i=1;i<=5;i++){
		System.out.println("Executing task:"+taskId+" [part-"+i+"] by thread:"+Thread.currentThread().getName());
		try{
			if(taskId==1)
			Thread.sleep(5000);
			else
				Thread.sleep(1000);

			
		}
		catch(Exception ex){
			System.out.println("Exception caught:"+ex.getMessage());
		}
		}
		return "return result from task:"+taskId;
	}
}