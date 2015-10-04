package xmltest;

import java.util.List;



public class XMLReader2 {
	public static void main(String[] args) {
		List<UpgradeInstructionToRunVO> instructions =new  UpgradeInstructionToRunDaoImpl().listByGroup("restoreBackup2");
		List<UpgradeInstructionToRunVO> instructions2 =new  UpgradeInstructionToRunDaoImpl().listByGroup("restoreBackup1");

		
		System.out.println("Commands#"+instructions.size());
		for(UpgradeInstructionToRunVO instruction : instructions){
			System.out.println("CmdGroup:"+instruction.getGroup()+", Cmd:"+instruction.getCmd()+", " +
					"Server:"+instruction.getServer()+", Sequence:"+instruction.getSequence()+", " +
					"Description:"+instruction.getDescription());
		}
		
		System.out.println("instruction2#"+instructions2.size());
		for(UpgradeInstructionToRunVO instruction2 : instructions2){
			System.out.println("CmdGroup:"+instruction2.getGroup()+", Cmd:"+instruction2.getCmd()+", " +
					"Server:"+instruction2.getServer()+", Sequence:"+instruction2.getSequence()+", " +
					"Description:"+instruction2.getDescription());
		}
	}
	
	
}