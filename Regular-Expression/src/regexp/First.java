package regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class First {

	/**
	 * @param args
	 */
	public static final String str = "This is my small example "
		+ "string which I'm going to " + "use for pattern matching.";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(str.matches("\\w.*"));
		String[] splitString = (str.split("\\s+"));
		System.out.println(splitString.length);// should be 14
		for (String string : splitString) {
		System.out.println(string);
		}
		// replace all whitespace with tabs
		System.out.println(str.replaceAll("\\s+", "\t"));
		System.out.println(StringMatcher.isIpAddress("10.20.63.1"));
		System.out.println(StringMatcher.isIpAddress("0.0.3.1"));
		System.out.println(StringMatcher.isMobileNumber("7676532370"));
		System.out.println(StringMatcher.isMobileNumber("76765323sd"));
		System.out.println(StringMatcher.isEmail("aafak.mohammad@cloudbyte.com"));
		System.out.println(StringMatcher.isEmail("aafak.mitsmca09@gmail.com"));
		System.out.println(StringMatcher.isEmail("aafak mitsmca09@gmail.com"));
		System.out.println(StringMatcher.isEmail("aafak.mitsmca09gmail.com"));
		System.out.println(StringMatcher.isAlphaNumric("aB9"));
		System.out.println(StringMatcher.isAlphaNumric("a#2$#@"));
		
		
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]+", Pattern.CASE_INSENSITIVE);
		Matcher matcher = pattern.matcher("Abc2343");
		if(matcher.find()){
			System.out.println("Found: "+ matcher.group());
		} else {
			System.out.println("No match found");
		}
		
		Pattern emailPattern = Pattern.compile("([\\w.-]+)@([\\w.-]+)+");
		Matcher emailMatcher = emailPattern.matcher("aafak.mohammad@cloudbyte.com");
		if(emailMatcher.find()){
			System.out.println("User Name: "+ emailMatcher.group(1));
			System.out.println("Host: "+ emailMatcher.group(2));

		} else {
			System.out.println("No match found");
		}

		String line = "This order was placed for QT3000! OK?";
		// Create a Pattern object
		Pattern r = Pattern.compile( "(.*)(\\d+)(.*)");
		// Now create matcher object.
		Matcher m = r.matcher(line);
		if (m.find( )) {
			System.out.println("Found value: " + m.group(0) );
			System.out.println("Found value: " + m.group(1) );
			System.out.println("Found value: " + m.group(2) );
		} else {
			System.out.println("NO MATCH");
		}
		





	}

}
