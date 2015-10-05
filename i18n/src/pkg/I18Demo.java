package pkg;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18Demo {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 Locale locale = new Locale("en", "US");
		 Locale franceLocale = new Locale("fr", "FR");
		 ResourceBundle  captions= ResourceBundle.getBundle("resource/Messages",franceLocale);

		// ResourceBundle  captions= ResourceBundle.getBundle("resource/Messages",locale);

		 String yesCaption =captions.getString("yesMessage");
		 String noCaption  = captions.getString("noMessage");

		 System.out.println(yesCaption);
		 System.out.println(noCaption);
		
		 

	}

}
