package test;

public class SortingDemo {

	/**
	 * @param args
	 */
	
	public static void sortInAsc(String []a){
		  for(int i=0;i<a.length;i++){
			  for(int j=i+1;j<a.length;j++){
				  if(a[i].compareTo(a[j])>0){
					  String temp = a[i];
					  a[i] = a[j];
					  a[j]=temp;
					  
				  }
			  }
		  }
	}
	
	public static void sortInDesc(String []a){
		  for(int i=0;i<a.length;i++){
			  for(int j=i+1;j<a.length;j++){
				  if(a[i].compareTo(a[j])<0){
					  String temp = a[i];
					  a[i] = a[j];
					  a[j]=temp;
					  
				  }
			  }
		  }
	}
	public static void sortInAsc(int []a){
		  for(int i=0;i<a.length;i++){
			  for(int j=i+1;j<a.length;j++){
				  if(a[i]>a[j]){
					  int temp = a[i];
					  a[i] = a[j];
					  a[j]=temp;
					  
				  }
			  }
		  }
	}
	
	public static void sortInDesc(int []a){
		  for(int i=0;i<a.length;i++){
			  for(int j=i+1;j<a.length;j++){
				  if(a[i]<a[j]){
					  int temp = a[i];
					  a[i] = a[j];
					  a[j]=temp;
					  
				  }
			  }
		  }
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
  int []a = {10,30,50,80,90,40};
  for(int i=0;i<a.length;i++)
	   System.out.println(a[i]);
  sortInAsc(a);
System.out.println("After sorting in asc");
  for(int i=0;i<a.length;i++)
     System.out.println(a[i]);
  
  sortInDesc(a);
  System.out.println("After sorting in desc");
  for(int i=0;i<a.length;i++)
     System.out.println(a[i]);
	

	String s[] = {"d","e","a","b","c"};
	sortInDesc(s);
	  for(int i=0;i<s.length;i++)
		     System.out.println(s[i]);
	}
}
