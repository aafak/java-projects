package test;

import java.util.Collection;

public class ReverseString {

	public static String reverseString(String str){
		StringBuilder reverseStr = new StringBuilder();
		for(int i = str.length()-1; i>=0;i--){
			reverseStr.append(str.charAt(i));
		}
		return reverseStr.toString();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(reverseString("abc"));
		System.out.println(new StringBuffer("abc").reverse());
		System.out.println(new StringBuilder("abc").reverse());

	}

}
