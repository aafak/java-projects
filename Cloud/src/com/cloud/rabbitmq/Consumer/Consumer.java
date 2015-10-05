package com.cloud.rabbitmq.Consumer;

public class Consumer {

	String method;
	String args;
	public Consumer(String method,String ar){
		this.method=method;
		this.args=args;
	}
	public String getMethod() {
		return method;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public String getArgs() {
		return args;
	}
	public void setArgs(String args) {
		this.args = args;
	}

	
}
