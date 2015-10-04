package test;

public class AvoidDeadLockTest
{

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
			synchronized (Integer.class)
			{
				System.out.println(Thread.currentThread().getName() + " - Got lock on String.class");
				synchronized (String.class)
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

///Solution to deadlock

/*
if you have looked above code carefully you may have figured out that real reason for deadlock is not multiple threads but the way they access lock , if you provide an ordered access then problem will be resolved , here is
the fixed version.




public void method1(){
synchronized(Integer.class){
System.out.println("Aquired lock on Integer.class object");

synchronized (String.class) {
System.out.println("Aquired lock on String.class object");
}
}
}

public void method2(){
synchronized(Integer.class){
System.out.println("Aquired lock on Integer.class object");

synchronized (String.class) {
System.out.println("Aquired lock on String.class object");
}
}
}



Now there would not be any deadlock because both method is accessing lock on Integer and String object in same order . so if thread A acquires lock on Integer object , thread B will not proceed until thread A releases Integer lock , same way thread A will not be blocked even if thread B holds String lock because now thread B will not expect thread A to release Integer lock to proceed further

Read more: http://javarevisited.blogspot.com/2010/10/what-is-deadlock-in-java-how-to-fix-it.html#ixzz3XLg4G6Tv






*/