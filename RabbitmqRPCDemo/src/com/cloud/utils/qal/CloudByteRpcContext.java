package com.cloud.utils.qal;

import java.util.HashMap;
import java.util.Map;

public class CloudByteRpcContext {
	private String user_id;
	private String project_id;
	private String auth_token;
	private String is_admin;
	private String request_id;

	public CloudByteRpcContext(String user_id, String project_id, String auth_token, String is_admin, String request_id) {
		this.setUser_id(user_id);
		this.setProject_id(project_id);
		this.setAuth_token(auth_token);
		this.setIs_admin(is_admin);
		this.setRequest_id(request_id);
	}

	/**
	 * @param request_id the request_id to set
	 */
	public void setRequest_id(String request_id) {
		this.request_id = request_id;
	}

	/**
	 * @return the request_id
	 */
	public String getRequest_id() {
		return request_id;
	}

	/**
	 * @param is_admin the is_admin to set
	 */
	public void setIs_admin(String is_admin) {
		this.is_admin = is_admin;
	}

	/**
	 * @return the is_admin
	 */
	public String getIs_admin() {
		return is_admin;
	}

	/**
	 * @param auth_token the auth_token to set
	 */
	public void setAuth_token(String auth_token) {
		this.auth_token = auth_token;
	}

	/**
	 * @return the auth_token
	 */
	public String getAuth_token() {
		return auth_token;
	}

	/**
	 * @param project_id the project_id to set
	 */
	public void setProject_id(String project_id) {
		this.project_id = project_id;
	}

	/**
	 * @return the project_id
	 */
	public String getProject_id() {
		return project_id;
	}

	/**
	 * @param user_id the user_id to set
	 */
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/**
	 * @return the user_id
	 */
	public String getUser_id() {
		return user_id;
	}
	
	public Map<String, String> toRpcContextMap() {
		Map<String, String> rpcContext = new HashMap<String, String> ();
		rpcContext.put("user_id", this.user_id);
		rpcContext.put("project_id", this.project_id);
		rpcContext.put("auth_token", this.auth_token);
		rpcContext.put("is_admin", this.is_admin);
		rpcContext.put("request_id", this.request_id);
		return rpcContext;
	}
		
	public static Map<String, String> defaultAdminRpcContext() {
		Map<String, String> defaultAdminRpcContext = new HashMap<String, String> ();
		defaultAdminRpcContext.put("user_id", "admin");
		defaultAdminRpcContext.put("project_id", "None");
		defaultAdminRpcContext.put("auth_token", "<SANITIZED>");
		defaultAdminRpcContext.put("is_admin", "True");
		defaultAdminRpcContext.put("request_id", "None");
		return defaultAdminRpcContext;
	}
	
	public static Map<String, String> defaultAdminRpcContext(String admin,String cmdCtxId) {
		Map<String, String> defaultAdminRpcContext = new HashMap<String, String> ();
		defaultAdminRpcContext.put("user_id", admin);
		defaultAdminRpcContext.put("project_id", "None");
		defaultAdminRpcContext.put("auth_token", "<SANITIZED>");
		defaultAdminRpcContext.put("is_admin", "True");
		defaultAdminRpcContext.put("request_id", cmdCtxId);
		return defaultAdminRpcContext;
	}
}
