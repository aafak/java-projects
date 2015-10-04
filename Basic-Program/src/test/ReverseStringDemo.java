package test;

public class ReverseStringDemo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
String s = "Hello";
char []c = s.toCharArray();
StringBuilder sb = new StringBuilder();
for(int i = c.length-1; i>=0; i--)
	sb.append(c[i]);
System.out.println(sb);	
	}

}
