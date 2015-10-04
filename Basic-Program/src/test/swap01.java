package test;
public class swap01
{
    public static void main(String arg[])
    {
        int[] a = {0,1,1,0,0,1,1,1,0};
        int l =0;
        int r= a.length-1;
        while(l<r)
        {System.out.println("comes");
            if(a[l] == 0)
            {
                l++;
            }
            else if(a[r] == 1)
            {
                r--;
            }
            else {
                swap(a,l,r);
                l++;
                r--;
            }
        }
        for(int i=0;i<a.length;i++)
            System.out.print(a[i] + ",");
    }
    private static void swap(int[] a, int l, int r)
    {System.out.println("swap");
        int tmp = a[l];
        a[l] = a[r];
        a[r]=tmp;
    }
  
    
    static void segregateZerosAndOnes( int A[], int N )
    {

    int countOfZeroes = -1, i = 0;

    while ( i < N ) {

    if ( A[i] != 0 ) {

   // swap ( A[i], A[++countOfZeroes] );

    int temp =A[i];
    A[i] = A[++countOfZeroes] ;
    A[++countOfZeroes]=temp;
    }

    i++;

    }

    }
    
    /**
     * int end=arraysize;
int begin=0;
int temp[arraysize];

for(i=0;i<arraysize;i++)
{

if(arr[i]==0)
temp[begin++]=0;
else
temp[--end]=1;
} 
     */
}