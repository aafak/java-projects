package com.cloud.utils.qal;

import java.util.List;

import com.google.gson.annotations.SerializedName;


public class BaseResponse {
	
	@SerializedName("status")
	private String status;
	
	@SerializedName("errorMsg")
	private String errorMsg;

	//TODO : Should errorCode be included?
	@SerializedName("errorCode")
	private long errorCode;
	
	@SerializedName("msgKey")
	private String msgKey;
	
	@SerializedName("msgArgs")
	private List<String> msgArgs;
	
	@SerializedName("jobId")
	private String jobId;
	
	public String getStatus() {
		return status;
	}
	
	public String getErrorMsg() {
		return errorMsg;
	}
	
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public long getErrorCode() {
		return errorCode;
	}
	
	public String getJobId() {
		return jobId;
	}
	public String getMsgKey() {
		return msgKey;
	}

	public List<String> getMsgArgs() {
		return msgArgs;
	}

	public String getMappedErrorMsg() {
		//The Agent/Controller can throw messages that will have to be mapped into the Devman terms.
//		if ( null != errorMsg ) {
//			if ( errorMsg.contains("dataset does not exist")) {
//				//return "Invalid Storage Bucket";
//				return "File system does not exist in the backend.";
//			}
////			if ( errorMsg.contains("dataset already exists")) {
////				return "A snapshot with this name already exists. Please provide unique snapshot name";
////			}			
//		}
		return errorMsg;
	}
	

}