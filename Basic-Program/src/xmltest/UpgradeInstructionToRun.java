package xmltest;


public interface UpgradeInstructionToRun {
	String getCmd();
	void setCmd(String cmd);

	String getGroup();
	void setGroup(String group);
	
	String getDescription();
	void setDescription(String description);

	String getServer();
	void setServer(String server);

	long getSequence();
	void setSequence(long squeence);
}
