package com.cloud.utils.qal;

import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class TestRpcClient {
	public static void main(String[] args) {
	    try {
	    	Map<String, Object> request = new HashMap<String, Object> ();
	    	String uri = "amqp://10.20.63.100:5672";
	    	String exchange = "cloudbyte";
	    	String queue = "cbc.ecc834a0-0887-3573-9395-736a1d0f8c00.hostdetails";
	    	//String queue = "cbc.10_20_11_1.hostdiscovery";
	
	        ConnectionFactory cfconn = new ConnectionFactory();
	        cfconn.setUri(uri);
	        Connection conn = cfconn.newConnection();
	        Channel ch = conn.createChannel();
	        CloudByteRpcClient service = new CloudByteRpcClient(ch, exchange, queue);
	        
	        /*request.put("method", "discoverHost");
	        Map<String, String> method_args = new HashMap<String, String> ();
	        method_args.put("hostName", "10.20.11.1");
	        request.put("args", method_args);*/
	        //request.put("method", "getNetworkDetails");
	        //request.put("method", "getCPUDetails");
	        //request.put("method", "getMemoryDetails");
	        /*request.put("method", "updateNodeId");
	        Map<String, String> method_args = new HashMap<String, String> ();
	        method_args.put("nodeId", "ecc834a0-0887-3573-9395-736a1d0f8c00");
	        request.put("args", method_args);*/
	        request.put("method", "getNICDetails");
	        request.put("_msg_id", service.getReplyQueue());
	        
	        Map<String, String> defaultAdminRpcContext = CloudByteRpcContext.defaultAdminRpcContext();
	        for (Map.Entry<String, String> e: defaultAdminRpcContext.entrySet()) {
	        	request.put("_context_"+e.getKey(), e.getValue());
	        }	        
	        //System.out.println(request);
	        
	        Gson gson = new Gson();
	        //System.out.println(gson.toJson(request));
	        JsonElement response = service.mapCloudByteCall(null, gson.toJson(request).getBytes("UTF-8"),10000,10);
	        System.out.println(response);
	        conn.close();
	    } catch (Exception e) {
	        System.err.println("Main thread caught exception: " + e);
	        e.printStackTrace();
	        System.exit(1);
	    }
	}
}