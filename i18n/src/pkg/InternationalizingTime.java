package pkg;
import java.text.DateFormat;  
import java.util.*;  

public class InternationalizingTime {  

static void printTime(Locale locale){  
DateFormat formatter=DateFormat.getTimeInstance(DateFormat.DEFAULT,locale);  
Date currentDate=new Date();  
String time=formatter.format(currentDate);  
System.out.println(time+" in locale "+locale);  
}  

public static void main(String[] args) {  
printTime(Locale.UK);  
printTime(Locale.US);  
printTime(Locale.FRANCE);  
}  
}  