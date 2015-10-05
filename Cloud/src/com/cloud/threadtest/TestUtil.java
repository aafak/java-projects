package com.cloud.threadtest;

import java.util.ArrayList;
import java.util.List;

public class TestUtil {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
 String ip="10.20.63.65";
 System.out.println(ip.replace(".", "_"));
 String a[];
 test();
	}
public static void test(String... a ){
	if(a.length>0)
	System.out.println("Test is:"+a[1]);
	List<EMP>pools=new ArrayList<EMP>();
	EMP emp=new EMP();
	emp.setId(1);
	emp.setName("E1");
	pools.add(emp);
	
	for(final EMP pool:pools){
       // System.out.println("creating thread for pool:"+pool);

	Thread t1 = new Thread(new Runnable() {
	     public void run()
	     {  int attemptCount=1;
			while(true){
				if(attemptCount==5){
					pool.setName("E2");
			         System.out.println("running thread for pool:"+pool.getName());

					break;

				}	
				attemptCount++;
		         System.out.println(attemptCount+"running thread for pool:"+pool);

				try{ Thread.sleep(1000);}
				catch ( Exception e) {System.out.println(": Thread interupted:"+e.getMessage());}
			}
	         
	         
	         
	     }});  t1.start();
	}
	
	 System.out.println("Thread has started for checking pool status, go ahead with ");
	
}
}
