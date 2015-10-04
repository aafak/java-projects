package test;

public class DeadLockTest
{
	/*
	 * "What is deadlock ?"
answer is simple , when two or more threads waiting for each other to release lock and get stuck for infinite time , situation is called deadlock . it will only happen in case of multitasking.

Read more: http://javarevisited.blogspot.com/2010/10/what-is-deadlock-in-java-how-to-fix-it.html#ixzz3XLl130BU

	 * 
	 * 
	 */

	static class ThreadOne implements Runnable {

		public void run()
		{
			synchronized (Integer.class)
			{
				System.out.println(Thread.currentThread().getName() + " - Got lock on Integer.class");
				synchronized (String.class)
				{
					System.out.println(Thread.currentThread().getName() + " - Got lock on String.class");
				}
			}
		}
	}

	static class ThreadTwo implements Runnable {

		public void run()
		{
			synchronized (String.class)
			{
				System.out.println(Thread.currentThread().getName() + " - Got lock on String.class");
				synchronized (Integer.class)
				{
					System.out.println(Thread.currentThread().getName() + " - Got lock on Integer.class");
				}
			}
		}

	}
	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		new Thread(new ThreadOne(), "ThreadOne").start();
		new Thread(new ThreadTwo(), "ThreadTwo").start();
	}

}

