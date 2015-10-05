package pkg;
import java.util.Locale;  
import java.util.ResourceBundle;  
public class InternationalizationDemo {  
 public static void main(String[] args) {  

  ResourceBundle bundle = ResourceBundle.getBundle("resource/Messages", Locale.US);  
  System.out.println("Message in "+Locale.US +":"+bundle.getString("greeting"));  
  System.out.println("Message in "+Locale.US +":"+bundle.getString("pool.create.failed"));  

  //changing the default locale to indonasian   
  Locale.setDefault(new Locale("in", "ID"));  
  bundle = ResourceBundle.getBundle("resource/Messages");  
  System.out.println("Message in "+Locale.getDefault()+":"+bundle.getString("greeting"));  

 }  
}  