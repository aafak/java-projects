package test;

import java.io.IOException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.xerces.internal.parsers.DOMParser;

public class XmlReader {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) throws SAXException, IOException {
		DOMParser parser = new DOMParser();
		parser.parse("resource/upgradeNodeCmd.xml");
		Document doc = parser.getDocument();
		System.out.print("The elements are: ");
		printElements(doc);
		 
		System.out.println("The attributes of each element are: ");
		printElementAttributes(doc);
	}
	static void printElements(Document doc)
	{
	   NodeList nl = doc.getElementsByTagName("*");
	   Node n;

	   for (int i=0; i<nl.getLength(); i++)
	   {
	      n = nl.item(i);
	      System.out.print(n.getNodeName() + " ");
	   }
	 
	   System.out.println();
	}
	static void printElementAttributes(Document doc)
	{
	   NodeList nl = doc.getElementsByTagName("*");
	   Element e;
	   Node n;
	   NamedNodeMap nnm;
	 
	   String attrname;
	   String attrval;
	   int i, len;
	 
	   len = nl.getLength();

	   for (int j=0; j < len; j++)
	   {
	      e = (Element)nl.item(j);
	      System.out.println(e.getTagName() + ":");
	      nnm = e.getAttributes();
	 
	      if (nnm != null)
	      {
	         for (i=0; i<nnm.getLength(); i++)
	         {
	            n = nnm.item(i);
	            attrname = n.getNodeName();
	            attrval = n.getNodeValue();
	            System.out.print(" " + attrname + " = " + attrval);
	         }
	      }
	      System.out.println();
	   }
	}

}
