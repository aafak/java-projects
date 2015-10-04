package xmltest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlReaserDemo2 {

	/**
	 * @param args
	 */
	static Document dom = null;
	static List<Employee>myEmpls =new ArrayList<Employee>();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		parseXmlFile();
		parseDocument();
		printData();
		
	}
	
	private static void parseXmlFile(){
		//get the factory
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {

			//Using factory get an instance of document builder
			DocumentBuilder db = dbf.newDocumentBuilder();

			//parse using builder to get DOM representation of the XML file
			 dom = db.parse("resource/employee.xml");
		       // System.out.println("root element: "+dom.getDocumentElement().getNodeName());
			
		        Node rootNode=dom.getDocumentElement(); //saving root node in a variable.
		        //System.out.println("root: "+rootNode.getNodeName());
		        NodeList nList=rootNode.getChildNodes(); //to store the child nodes as node list.
		        for(int i=0;i<nList.getLength();i++)
		        {
		        	if (nList.item(i).getNodeType() == Node.ELEMENT_NODE) {
		        		//System.out.println("node name: "+nList.item(i).getNodeName() );
		              }
		        	if (nList.item(i).getNodeType() == Node.ELEMENT_NODE && nList.item(i).getNodeName().equalsIgnoreCase("restoreBackup2")) {
		        		Node restoreNode = nList.item(i);
		        		System.out.println("restoreNode "+restoreNode );

		        		NodeList restoreNodeList =restoreNode.getChildNodes();
		        		for(int j=0;j<restoreNodeList.getLength();j++)
				        {
		        			if (restoreNodeList.item(j).getNodeType() == Node.ELEMENT_NODE) {
				        		System.out.println("restoreNodeList name: "+restoreNodeList.item(j).getNodeName() );
				              }
				        }
		              }
		            
		        }
		        // Node root = dom.getDocumentElement();
			// System.out.println(root.getChildNodes().getLength());
			 //XPath xpath = XPathFactory.newInstance().newXPath();
			 //xpath.e

		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		}catch(SAXException se) {
			se.printStackTrace();
		}catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	

	
	private static void parseDocument(){
		//get the root element
		Element docEle = dom.getDocumentElement();
//dom.getElementById(elementId)
		//docEle=dom.
		//get a nodelist of 
		//System.out.println(docEle);
		NodeList nl = docEle.getElementsByTagName("Employee");
		//System.out.println(nl.getLength());
		if(nl != null && nl.getLength() > 0) {
			for(int i = 0 ; i < nl.getLength();i++) {

				//get the employee element
				Element el = (Element)nl.item(i);

				//get the Employee object
				Employee e = getEmployee(el);

				//add it to list
				myEmpls.add(e);
			}
		}
	}
	
	

	/**
	 * I take an employee element and read the values in, create
	 * an Employee object and return it
	 */
	private static Employee getEmployee(Element empEl) {

		//for each <employee> element get text or int values of
		//name ,id, age and name
		String name = getTextValue(empEl,"Name");
		int id = getIntValue(empEl,"Id");
		int age = getIntValue(empEl,"Age");

		String type = empEl.getAttribute("type");

		//Create a new Employee with the value read from the xml nodes
		Employee e = new Employee(name,id,age,type);

		return e;
	}


	/**
	 * I take a xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	private static String getTextValue(Element ele, String tagName) {
		String textVal = null;
		NodeList nl = ele.getElementsByTagName(tagName);
		if(nl != null && nl.getLength() > 0) {
			Element el = (Element)nl.item(0);
			textVal = el.getFirstChild().getNodeValue();
		}

		return textVal;
	}


	/**
	 * Calls getTextValue and returns a int value
	 */
	private static int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}
	
	private static void printData(){

		System.out.println("No of Employees '" + myEmpls.size() + "'.");

		Iterator<Employee> it = myEmpls.iterator();
		while(it.hasNext()) {
			System.out.println(it.next().toString());
		}
	}
}
