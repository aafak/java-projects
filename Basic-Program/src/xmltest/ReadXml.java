package xmltest;
import java.io.File;  
import javax.xml.parsers.DocumentBuilder;  
import javax.xml.parsers.DocumentBuilderFactory;  
import org.w3c.dom.Document;  
import org.w3c.dom.Element;  
import org.w3c.dom.Node;  
import org.w3c.dom.NodeList;  
  
public class ReadXml {  
 public void readXML() {  
  try {  
  
   File xmlFile = new File("resource/student.xml");  
   DocumentBuilderFactory documentFactory = DocumentBuilderFactory  
     .newInstance();  
   DocumentBuilder documentBuilder = documentFactory  
     .newDocumentBuilder();  
   Document doc = documentBuilder.parse(xmlFile);  
  
   doc.getDocumentElement().normalize();  
   NodeList nodeList = doc.getElementsByTagName("student");  
  
   System.out.println("Root element :"  
     + doc.getDocumentElement().getNodeName());  
  
   for (int temp = 0; temp < nodeList.getLength(); temp++) {  
    Node node = nodeList.item(temp);  
  
    System.out.println("\nElement type :" + node.getNodeName());  
  
    if (node.getNodeType() == Node.ELEMENT_NODE) {  
  
     Element student = (Element) node;  
  
     System.out.println("Student id : "  
       + student.getAttribute("id"));  
     System.out.println("First Name : "  
       + student.getElementsByTagName("firstname").item(0)  
         .getTextContent());  
     System.out.println("Last Name : "  
       + student.getElementsByTagName("lastname").item(0)  
         .getTextContent());  
     System.out.println("Email Id : "  
       + student.getElementsByTagName("email").item(0)  
         .getTextContent());  
     System.out.println("Phone No : "  
       + student.getElementsByTagName("phone").item(0)  
         .getTextContent());  
  
    }  
   }  
  } catch (Exception e) {  
   e.printStackTrace();  
  }  
 }  
}  