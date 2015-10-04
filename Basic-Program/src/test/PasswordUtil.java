package test;
import java.math.*;
import java.security.*;

import sun.misc.BASE64Decoder;
public final class PasswordUtil
{
         private PasswordUtil()
         {
         }
                public static void main(String a[]) throws Exception
                {
                    
                     
                    String toEnc = "123"; // Value to encrypt
                    MessageDigest mdigest = MessageDigest.getInstance("MD5"); // Encryption algorithm
                    mdigest.update(toEnc.getBytes(), 0, toEnc.length());
                    String md5 = new BigInteger(1, mdigest.digest()).toString(16); // Encrypted string
                	System.out.println(md5);
                	
                	byte[] msgBytes = null;
                	byte[] digest = mdigest.digest();
            		BigInteger big = new BigInteger(1,digest);
            		String hash = big.toString(16);
            		System.out.println(hash);
            		while(hash.length() < 32 ){
            		  hash = "0"+hash;
            		}
            		System.out.println("hashtext: "+hash);
            		BASE64Decoder decoder = new BASE64Decoder();
            		msgBytes = decoder.decodeBuffer(hash);
            		System.out.println(msgBytes);
            		String msg = new String(msgBytes,"utf-8");
            // also tried msg = new String(msgBytes);		
                           System.out.println("decoded message is :" + msg);
                	

                	
                 PasswordService ps=new PasswordService();
                 System.out.println(ps.encrypt("srinivaa"));
                 System.out.println(ps.encrypt("123"));
                 }
}