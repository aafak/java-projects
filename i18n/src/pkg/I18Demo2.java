
package pkg;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class I18Demo2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String warnMsg = MessageFormat.format("Node {0} is Down", new Object[]{"node1"} );
		System.out.println(warnMsg);
		
		 Locale locale = new Locale("en", "US");
		 ResourceBundle bundle =   ResourceBundle.getBundle("resource/Messages",Locale.US);
		  System.out.println("Message in "+Locale.US +":"+bundle.getString("pool.create.failed"));  

		 
		 MessageFormat messageFormat = new MessageFormat("");
		 messageFormat.setLocale(locale);

	      String pattern = bundle.getString("pool.create.failed");
	      messageFormat.applyPattern(pattern);
	     // Object[] messageArguments = {"test","test2"};
	      String errMsg = messageFormat.format(new Object[]{"pool1","node1"});
	         System.out.println(errMsg);
	         
	       System.out.println(MessageUtils.getMessage("pool.create.failed",new Object[]{"pool1","node1"}));  
	       System.out.println(MessageUtils.getMessage("internal.error"));  
	         
	}
	
	

}
