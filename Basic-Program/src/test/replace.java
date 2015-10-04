package test;
/*
 *
 * A free Java sample program 
 * to read a file A and replace occurrence of a String
 * with another String and write the result to another file B
 *
 * @author William Alexander
 * free for use as long as this comment is included 
 * in the program as it is
 * 
 * More Free Java programs available for download 
 * at http://www.java-samples.com
 *
 */

import java.net.*;
import java.io.*;
import java.util.*;

public class replace
{
    public static void main(String args[]) throws Exception
    {
       
	   if (args.length != 4)
           {
		System.err.println ("Invalid command parameters");
		System.exit(0);
           }

String str;	
try{
FileInputStream	fis2=new FileInputStream(args[2]);
DataInputStream   input = new DataInputStream (fis2);
FileOutputStream fos2=new FileOutputStream(args[3]);
DataOutputStream   output = new DataOutputStream (fos2);

while (null != ((str = input.readLine())))
{



String s2=args[0];
String s3=args[1];

int x=0;
int y=0;
String result="";
while ((x=str.indexOf(s2, y))>-1) {
  result+=str.substring(y,x);
  result+=s3;
  y=x+s2.length();
 }
result+=str.substring(y);
str=result;

if(str.indexOf("'',") != -1){
	continue;
}
else{
str=str+"\n";

output.writeBytes(str);
}
        }
       }
        catch (IOException ioe)
        {
            System.err.println ("I/O Error - " + ioe);
        }
    }
}