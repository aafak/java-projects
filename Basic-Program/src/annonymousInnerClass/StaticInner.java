package annonymousInnerClass;

class Outer
{
	private static int a ;
	static
	{a=10;
	 System.out.println("Outer class is loaded");
	}

	//inner class
	static class Inner
	{ int b;
	   public Inner(int b)
	   {this.b=b;System.out.println("Inner class is loaded");}
	   public void display()
	    {System.out.println("a="+a);//outer class need to be loaded b'z a convert access method call
	     System.out.println("b="+b);
		}


	}

}

class StaticInner
{
	public static void main(String as[])
	{
		Outer.Inner i=new Outer.Inner(20);// after this outer class will not load
		i.display();// when accessing value of a then only outer class will be loaded
		}
	}