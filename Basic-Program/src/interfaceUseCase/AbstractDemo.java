package interfaceUseCase;
abstract class AbstractDemo
{
   public   int a=10;
  public abstract void fun1();

  public void fun2()
  {System.out.println("Hello");}


  private void fun3()
  {
	  System.out.println("this is private");

	  }

static void fun4()
{System.out.println("This is static ");}

}


class A extends AbstractDemo
{
	public void fun1()
	{a=20;}


	}


class AbstractDemo1
{

	public static void main(String arg[]){




		}

	}