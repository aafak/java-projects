package test;

import java.util.Arrays;
import java.util.LinkedHashSet;

public class RemoveDuplicate {
	@SuppressWarnings("unchecked")
	public static <T> T[] removeDuplicates(T[] arr) {
	    return (T[]) new LinkedHashSet<T>(Arrays.asList(arr)).toArray();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] input = new int[]{1, 1, 3, 7, 7, 8, 9, 9, 9, 10,1,3,7,6,9};
	    int current = input[0];
	    boolean found = false;

	    for (int i = 0; i < input.length; i++) {
	        if (current == input[i] && !found) {
	            found = true;
	        } else if (current != input[i]) {
	            System.out.print(" " + current);
	            current = input[i];
	            found = false;
	        }
	    }
	    System.out.print(" " + current);
	}

}
