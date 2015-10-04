package test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicates {
	
	private static void findDuplicateNumber(int [] arr) {
	    Set<Integer> hashSet = new HashSet<Integer>();

	    for(int i=0; i < arr.length; i++){
	        boolean unique = hashSet.add(arr[i]);
	        if(unique == false)
	            System.out.println("duplication " + arr[i]);
	    }      

	}
	public void removeDupInIntArray(int[] ints){
	    Set<Integer> setString = new LinkedHashSet<Integer>();
	    for(int i=0;i<ints.length;i++){
	        setString.add(ints[i]);
	    }
	    System.out.println(setString);
	}
	

	public static void exchange(int[] array, int i, int j) {
	    int temp = array[i];
	    array[i] = array[j];
	    array[j] = temp;
	}
	public static int countUnique(int[] A) {
		int count = 0;
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] == A[i + 1]) {
				count++;
			}
		}
		return (A.length - count);
	}
	

	public static void main(String[] args) {
		 //int array[] = { 10, 20, 30, 20, 40, 40, 50, 60, 70, 80 };// array of ten   
		 int array[] = { 80, 20, 30, 20, 40, 40, 50, 60, 70, 10,20 };// array of ten 
         // elements
		 int size = array.length;
		
	 
		 
      /*   
         System.out.println("Size before deletion: " + size);

         for (int i = 0; i < size; i++)
         {

             for (int j = i + 1; j < size; j++)
             {


                 if (array[i] == array[j]) // checking one element with all the
//element
                 {
                     while (j < (size) - 1)
                     {
                         array[j] = array[j + 1];// shifting the values
                         j++;
                     }  
                     size--;
                 }
             }
         }*/
         
        
         
         //2nd method
         System.out.println("by Sorting");
         int[] numbers = { 1, 5, 23, 2, 1, 6, 3, 1, 8, 12, 3 };
         Arrays.sort(numbers);
         int numbersize=numbers.length;
         for(int i = 1; i < numbersize; i++) {
             if(numbers[i] == numbers[i - 1]) {
                 System.out.println("Duplicate: " + numbers[i]);
                 
         }
         }
        
	}

}
