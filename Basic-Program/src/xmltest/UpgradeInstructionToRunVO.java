package xmltest;

public class UpgradeInstructionToRunVO implements UpgradeInstructionToRun {
		
	private String cmd;
	private String group;
	private String server;
	private long sequence;
	private String description;
	
	
	public UpgradeInstructionToRunVO(String cmd, String group, String server,
			long sequence, String description) {
		super();
		this.cmd = cmd;
		this.group = group;
		this.server = server;
		this.sequence = sequence;
		this.description = description;
	}

	/**
	 * @return the cmd
	 */
	@Override
	public String getCmd() {
		return cmd;
	}

	/**
	 * @param cmd the cmd to set
	 */
	@Override
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	/**
	 * @return the group
	 */
	@Override
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	@Override
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the squeence
	 */
	@Override
	public long getSequence() {
		return sequence;
	}

	/**
	 * @param squeence the squeence to set
	 */
	@Override
	public void setSequence(long squeence) {
		this.sequence = squeence;
	}

	/**
	 * @return the description
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the server
	 */
	@Override
	public String getServer() {
		return server;
	}

	/**
	 * @param server the server to set
	 */
	@Override
	public void setServer(String server) {
		this.server = server;
	}

	
}
