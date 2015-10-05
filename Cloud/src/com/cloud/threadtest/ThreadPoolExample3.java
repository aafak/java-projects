package com.cloud.threadtest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
public class ThreadPoolExample3 {
	ExecutorService service=null;

	
	
	public static void main(String[] args) {
       ExecutorService service =Executors.newFixedThreadPool(5);
	  // ExecutorService service=Executors.newCachedThreadPool();
		List<Future<String>>futures=new ArrayList<Future<String>>(10);
		for(int i=1;i<=2;i++){
			futures.add(service.submit(new Task1(i)));
		}
		try{

		for(Future<String> future:futures){

			String result=future.get();
			System.out.println(result);
		}
		service.shutdown();
		//service.shutdownNow();
		}catch(Exception ex){
			System.out.println(ex.getMessage());
		}
		
		//But this code is a bit complicated. And there is a disadvantage. If the first task takes a long time to compute and all the other tasks ends before the first, the current thread cannot compute the result before the first task ends. Once again, Java has the solution for you, CompletionService.

		//A CompletionService is a service that make easier to wait for result of submitted task to an executor. The implementation is ExecutorCompletionService who’s based on an ExecutorService to work. So let’s try :

		System.out.println("End");

	}
}
final class Task1 implements Callable<String>{
	int taskId;
	public Task1(int taskId){
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