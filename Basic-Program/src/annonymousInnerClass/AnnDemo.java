package annonymousInnerClass;
class Super{
	static private int t=10;
	public void fun1()
	{System.out.println("Fun1");}

}

interface Int
{public void i_fun();}

class AnnDemo{
	static private int a=10;
	public static void main(String arg[]){

		//  Annonymous inner class that extends super class store 
		//its ref in super class
		Super s=new Super(){
			public void fun1(){
				System.out.println("Iam ann:"+a);
		        // System.out.println("Iam ann:"+t);
	    	}
		};
		s.fun1();
		//s.fun(); can't access b'z it can aceess only that member
		//that derived from it

//		Annonymous inner class that implements 
		//Int interface class store its ref in Int
		Int i=new Int(){
			int y=10;
			public void i_fun(){
				System.out.println("Implementation:"+y);
			}
		};
		i.i_fun();

//		Annonymous inner class that extends 
		//Thread class store its ref in super class
		Thread th=new Thread(){
			public void run(){
				try{
					for(int i=1;i<=10;i++){
						System.out.println("Thread one:"+i);
						Thread.sleep(1000);
					}
				} catch(Exception ex){
					ex.printStackTrace();
				}		     }
		};
		th.start();
		
		//Annonymous inner class that implements Runnable interface
		Runnable thread2 = new Runnable(){
			public void run(){
				try{
					for(int i=1;i<=10;i++){
						System.out.println("Thread two:"+i);
						Thread.sleep(1000);
					}
				} catch(Exception ex){
					ex.printStackTrace();
				}
			}
		};
		thread2.run();
		
//		inner class
		class abc	{
			private void fun3(){
			System.out.println("Hello this is inner can access private member " +
					"of outer class:"+a);}
		}
		abc x=new abc();
		x.fun3();
	}

//	abc y=new abc();
}