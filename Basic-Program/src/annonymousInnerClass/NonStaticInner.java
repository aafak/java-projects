package annonymousInnerClass;

class Outer1
{
	private int a;
     //Outer1 o=new Outer1(123);
	public Outer1(int a)
	{this.a=a;System.out.println("Outer class is loaded");}

    public void display()
	{ Inner1 x=new Inner1(10);//here allow without object of inner b'z all member of a class directly accessible
	  System.out.println("a="+a);
      System.out.println("b="+x.b);
	}

	//Non static inner class
	class Inner1
	{
		public int b;
		public Inner1(int b)
		{this.b=b;System.out.println("Inner class is loaded");}

		public void display()
		{//Outer1 o=new Outer1(123);
	     System.out.println("a="+a);
		 System.out.println("b="+b);
		}
	}


}

class NonStaticInner
{
	public static void main(String as[])
	{
		//Outer1.Inner1 x=new Outer1.Inner1(20);//Error:instance of outer class required
        Outer1 x=new Outer1(10);
         x.display();// call display of outer
        Outer1.Inner1 y=x.new Inner1(20);
        y.display();
		}
	}