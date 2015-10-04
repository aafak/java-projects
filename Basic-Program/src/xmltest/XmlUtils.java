package xmltest;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XmlUtils {

	
	public static void main(String[] args) {
		/* String   xmlFilePath = "resource/upgradeNodeCmd.xml";
		 
		NodeList nodes = getChildNodesByParentNodeName(xmlFilePath, "restoreBackup2");
				for(int j=0;j<nodes.getLength();j++)
		        {
        			if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
		        		System.out.println("restoreNodeList name: "+nodes.item(j).getNodeName() );
		              }
		        }*/
	}

	
	public static Node getRootNode(String filePath){
		Node rootNode = null;
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try{
			DocumentBuilder docBuilder = factory.newDocumentBuilder();
			Document doc = docBuilder.parse(filePath);
			rootNode = doc.getDocumentElement();
			
		}catch(ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return rootNode;
	}
	
	
	public static NodeList getChildNodesByParentNodeName(String filePath, String ParentNodeName){
		NodeList childNodes =null;
		Node rootNode = getRootNode(filePath);
		
		if(rootNode != null){
			NodeList nodes = rootNode.getChildNodes();
			childNodes = getFilteredNodesByNodeName(nodes,ParentNodeName);
		}
		return childNodes;
	}
	public static NodeList getChildNodesByRootNodeAndParentNodeName(Node rootNode, String ParentNodeName){
		NodeList childNodes =null;
	
		if(rootNode != null){
			NodeList nodes = rootNode.getChildNodes();

			childNodes = getFilteredNodesByNodeName(nodes,ParentNodeName);
		}
		return childNodes;
	}	
	
	/**
	 * Takes list of Nodes and return the child nodes of given node name
	 * Note: it will consider the node name as unique in node list, so as
	 * it found first one, will get its child and return
	 */
	public static NodeList getFilteredNodesByNodeName(NodeList nodes,String nodeName){
		NodeList filteredNodes =null;
		if(nodes != null){
			for(int i = 0; i< nodes.getLength(); i++){

				if(nodes.item(i).getNodeType() == Node.ELEMENT_NODE && 
						nodes.item(i).getNodeName().equalsIgnoreCase(nodeName)){
					filteredNodes= nodes.item(i).getChildNodes();
					break;
				}
			}
		}
		return filteredNodes;
	}
	/**
	 * Takes an xml element and the tag name, look for the tag and get
	 * the text content
	 * i.e for <employee><name>John</name></employee> xml snippet if
	 * the Element points to employee node and tagName is 'name' I will return John
	 */
	public static String getTextValue(Element ele, String tagName) {
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
	public static int getIntValue(Element ele, String tagName) {
		//in production application you would catch the exception
		return Integer.parseInt(getTextValue(ele,tagName));
	}
}
