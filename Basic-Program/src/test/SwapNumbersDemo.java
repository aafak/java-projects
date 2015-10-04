package test;

public class SwapNumbersDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
int a = 20; 
int b= 10;

//method 1: swaping without using third var
//a = a^b;
//b = a^b;
//a = a^b;

//method 2: swaping without using third var

a = a+b;
b = a-b;
a = a-b;
System.out.println("a: "+a+", b:"+b);
	}

}
