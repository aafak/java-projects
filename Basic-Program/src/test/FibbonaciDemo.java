package test;

public class FibbonaciDemo {

	/**
	 * @param args
	 */
	public static int fibonacci1(int number){
		if(number == 1 || number ==2)
			return 1;
		
		return fibonacci1(number-1)+fibonacci1(number-2);
	}
	public static int fibonacci2(int number){
		if(number == 1 || number ==2)
			return 1;
		int fib1 = 1;
		int fib2 = 1;
		int fibbonaci =1;
		for(int i = 3;i<=number;i++){
			fibbonaci = fib1+fib2;
			fib1 = fib2;
			fib2 = fibbonaci;

		}
		return fibbonaci;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n=10;
		System.out.println("Fibbonaci series up to "+n+" are;");
		for(int i =1;i<=n;i++){
			System.out.println(fibonacci1(i));
		}
	}

}
