package xmltest;

class Abc{
	public void test(){
		System.out.println("this is test");
	}
	public static void test1(){
		System.out.println("this is test1");
	}
}

public class Test {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
Abc a=null;
a.test1();
a.test();
	}

}
