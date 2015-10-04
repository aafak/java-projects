package test;
import java.util.Scanner;  
    
class AssertionExample{  
 public static void main( String args[] ){  
  
  Scanner scanner = new Scanner( System.in );  
  System.out.print("Enter ur age ");  
    
  int value = scanner.nextInt();  
  assert value>=18:" Not valid";  
  
  System.out.println("value is "+value);  
 }   
}
/**
root@aafak-HP-ProBook-4530s:~# javac AssertionExample.java 
root@aafak-HP-ProBook-4530s:~# java AssertionExample
Enter ur age 11
value is 11
root@aafak-HP-ProBook-4530s:~# java -ea AssertionExample
Enter ur age 11
Exception in thread "main" java.lang.AssertionError:  Not valid
	at AssertionExample.main(AssertionExample.java:10)
root@aafak-HP-ProBook-4530s:~# 



*/