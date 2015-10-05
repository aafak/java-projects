package pkg;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class MessageUtils {
static String baseName="resource/Messages";	
static Locale locale = new Locale("en", "US");
//static Locale locale = new Locale("hi", "IN");
static ResourceBundle bundle=ResourceBundle.getBundle(baseName,locale);
static MessageFormat messageFormat = new MessageFormat("");

public  static String getMessage(String msgKey,Object... messageArguments){
	String errMsg="";
	messageFormat.setLocale(locale);
	String pattern = bundle.getString(msgKey);
	messageFormat.applyPattern(pattern);
	errMsg = messageFormat.format(messageArguments);
	return errMsg;
}

public static String getMessage(String msgKey){
	String errMsg="";
	messageFormat.setLocale(locale);
	errMsg = bundle.getString(msgKey);
	return errMsg;
}
static MessageFormat messageFormat2 = new MessageFormat("");

public  static String getMessage2(String msgKey, Object... messageArguments){
	Locale locale = new Locale("zh", "CN");
	messageFormat2.setLocale(locale);
	messageFormat2.applyPattern(msgKey);
	String errMsg =messageFormat2.format(messageArguments);
	return errMsg;
}
}
