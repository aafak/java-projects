package annonymousInnerClass;

class C1
{static int a;
  static{System.out.println("Outer Class C1 is loaded");}

static class inn
   {static{System.out.println("Class inner is loaded");}
   }

}


class LoadingTest
{
	public static void main(String ar[])
	{ C1.inn x=new C1.inn();//Outer Class C1 will not be loaded

		}

	}