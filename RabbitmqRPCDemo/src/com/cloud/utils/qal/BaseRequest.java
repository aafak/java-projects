package com.cloud.utils.qal;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.annotations.SerializedName;

public class BaseRequest {

	@SerializedName("method")
	private String method;
	
	@SerializedName("args")
	private Map<String, String> args = new HashMap<String, String>();
	
	@SerializedName("args")
	private Map<String, File> fileargs = new HashMap<String, File>();
	
	private int RESPONSE_TIMEOUT = 10000;
	
	private int MAX_ATTEMPTS = 30;

	public BaseRequest(String methodName) {
		this.method = methodName;
	}

	public void addArgs(String name, String value) {
		args.put(name, value);
	}
	protected void addArgs(String name, File file) {
		fileargs.put(name, file);
	}
	public String getMethod() {
		return this.method;
	}
	
	public Map<String, String> getArgs() {
		return this.args;
	}
	
	public String getRoutingKeySuffix() {
		return null;
	}

	public int getResponseTimeout() {
		return RESPONSE_TIMEOUT;
	}

	public void setResponseTimeout(int responseTimeout) {
		RESPONSE_TIMEOUT = responseTimeout;
	}

	public int getMaxAttempts() {
		return MAX_ATTEMPTS;
	}

	public void setMaxAttempts(int maxAttempts) {
		MAX_ATTEMPTS = maxAttempts;
	}

}