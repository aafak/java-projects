package test;
import java.util.*;
import java.lang.*;


public class Solution {

	/**
	 * @param args
	 * https://github.com/derekhh/HackerRank
	 * https://github.com/srujalsk/Interview-Street-Problem--K-Difference/blob/master/Solution.java
	 */
	static int N;
	static long K;
	static long result;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner in = new Scanner(System.in);
		Hashtable numbers = new Hashtable();
		
		N = in.nextInt();
		K = in.nextLong();
		
		long[] arr = new long[N];
		for(int i=0;i<N;i++)
		{
			arr[i] = in.nextLong();
			numbers.put(Long.toString(arr[i]), arr[i]);
		}
		
		result = 0;
		
		for(int i=0;i<N;i++)
		{
			long tmp = 0,tmp2 = 0; 
			tmp = arr[i] + K;
			try
			{
				tmp2 = (Long) numbers.get(Long.toString(tmp));
				result++;
			}
			catch(Exception ex)
			{
				
			}

		}
		
		System.out.println(result);

	}
/*
 * int minStringMoves(char* a, char* b) {
    int length, pos, i, j, moves=0;
    char *ptr;
    length = strlen(a);

    for(i=0;i<length;i++) {
        // Find the first occurrence of b[i] in a
        ptr = strchr(a,b[i]);
        pos = ptr - a;

        // If its the last element, swap with the first
        if(i==0 && pos == length-1) {
            swap(&a[0], &a[length-1]);
            moves++;
        }
        // Else swap from current index till pos
        else {
            for(j=pos;j>i;j--) {
                swap(&a[j],&a[j-1]);
                moves++;
            }
        }

        // If equal, break
        if(strcmp(a,b) == 0)
            break;       
   }

   return moves;
}
 * 
 * 
 * 
 */
}