package xmltest;

import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class UpgradeInstructionToRunDaoImpl {
	 Node rootNode;
	static String xmlFilePath = "resource/upgradeNodeCmd.xml";

	public Node getRootNode() {
		if(rootNode == null){
			System.out.println("Comes here......");
			rootNode= XmlUtils.getRootNode(xmlFilePath);
		}
		return rootNode;
	}


	
	/**
	 *Return the list of all the command/instructions by group
	 */	
	public  List<UpgradeInstructionToRunVO> listByGroup(String group) {
		List<UpgradeInstructionToRunVO> instructionlist = new ArrayList<UpgradeInstructionToRunVO>();
		
		NodeList nodes = XmlUtils.getChildNodesByRootNodeAndParentNodeName(getRootNode(), group);
		
		if(nodes != null){
			for(int j=0;j<nodes.getLength();j++){
				if (nodes.item(j).getNodeType() == Node.ELEMENT_NODE) {
					//System.out.println("restoreNodeList name: "+nodes.item(j).getNodeName() );
					//get the employee element
					Element element = (Element)nodes.item(j);

					//get the instruction/command object
					UpgradeInstructionToRunVO instruction = getObjectByXmlElement(element);

					//add it to list
					instructionlist.add(instruction);
				}
			}
		}
		return instructionlist;
	}
	

/**
 *Takes an command element and read the values in, create
 * an UpgradeInstructionToRunVO object and return it
 */	
	private  UpgradeInstructionToRunVO getObjectByXmlElement(Element element){
		//for each <command> element get text or int values of
		//name ,id, age and name
		String group = element.getParentNode().getNodeName();
		String cmd = XmlUtils.getTextValue(element,"cmd");
		String server = XmlUtils.getTextValue(element,"server");
		long sequence = XmlUtils.getIntValue(element,"sequence");
		String description = XmlUtils.getTextValue(element,"description");
		
		UpgradeInstructionToRunVO instruction = new UpgradeInstructionToRunVO(cmd, group, server,
				sequence, description);
		return instruction;
}

}
