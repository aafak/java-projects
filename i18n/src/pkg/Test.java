package pkg;

import java.text.DateFormat;
import java.util.Locale;

class Emp{
	String name;
	int id;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
public class Test {
public static void setEmpName(Emp e){
	e.setName("A");
}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//Emp e=new Emp();
//e.setId(1);
//setEmpName(e);
//System.out.println(e.getName());
		 Locale list[] = DateFormat.getAvailableLocales();
	      for (Locale aLocale : list) {
	          System.out.println(aLocale.toString());
	      }
		 System.out.println(MessageUtils.getMessage2("pool {0}","p1"));  
		 System.out.println("currentLocale = " + Locale.getDefault());
	       System.out.println(MessageUtils.getMessage("WARN.VALIDATE.DUPLICATE.POOL.NAME","pool2","node1"));  
	      // System.out.println(MessageUtils.getMessage("pool.create.failed",new Object[]{"pool1","node1"}));  
	      // System.out.println(MessageUtils.getMessage("internal.error"));  
	         
		

	}

}
