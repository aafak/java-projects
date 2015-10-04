package test;

public class ReverseNumberDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
int n = 1234;
int rn = 0;
while(n>0){
	int mod = n%10;
	System.out.println(mod);
	rn = mod+rn*10;
	n =n/10;
}
System.out.println(rn);
	}

}
