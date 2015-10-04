package test;

public class PrimeNumberDemo {

	/**
	 * @param args
	 */
	public static boolean isPrime(int number){
		if(number == 1 || number == 2)
			return true;
		for(int i = 2; i<number;i++){
			if(number%i == 0){
				return false;
			}
		}
		return true;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
for(int i = 1;i<100;i++){
	if(isPrime(i))
	  System.out.println(i +" is prime number");
}
	}

}
