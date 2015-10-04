package test;
import java.util.*;
class A<T>
{
   T value;
public A(T x)
{value=x;}

public T display()
{return value;}

}
/*public class GenaricDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Integer a= 32;
		A<Integer> intobj=new <Integer>A(10);
		
	}

}*/




class GenaricDemo
{
  public static void main(String ar[])
  {
     //A<Integer> intobj=new <Integer>A(10);
     A<String> strobj=new A<String>("Hello");
    // System.out.println(intobj.display()); 
     System.out.println(strobj.display());
     
    //intobj=strobj;
      String s=strobj.display(); 
     
   }
}
