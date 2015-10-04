package test;

public class ReverseArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int [] a= {1,2,3,4,5,6,7,8,9,10};
		int size = a.length;
		for(int i=0; i<size/2; i++){
			//int temp = a[size-i+1];//10-0+1=11 "main" java.lang.ArrayIndexOutOfBoundsException: 11
			int temp = a[size-(i+1)];
			a[size-(i+1)] = a[i];
			a[i] = temp;
		}
		
		for(int j=0; j<size;j++){
			System.out.println(a[j]);
		}
		

		/*
		 * int j = size - 1;
		  for (int i=0; i<size/2 ; i++)
		  {
			int temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			j--;
			} 

		 */
	}

}
