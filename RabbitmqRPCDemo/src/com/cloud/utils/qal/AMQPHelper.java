package com.cloud.utils.qal;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;


import org.apache.log4j.Logger;

public class AMQPHelper {
	private static final Logger s_logger = Logger.getLogger(AMQPHelper.class);
	
	
	public static Object call(String routingKey, BaseRequest request, Object response ) throws TimeoutException {
		//TODO : Find a way to pass a context id, so that each call need not worry about passing them.
		if ( s_logger.isDebugEnabled() ) s_logger.debug( "Initiating an rpc call to : " + routingKey);
		String exchange = "cloudbyte";
		Connection conn = null;
		Map<String, Object> requestMap = new HashMap<String, Object> ();
		try {
	    	
	    	//String uri = "amqp://10.20.1.248:5672";
	
	        ConnectionFactory cfconn = new ConnectionFactory();
	        cfconn.setHost("localhost");
	        //cfconn.setUri(uri);
	        conn = cfconn.newConnection();
	        Channel ch = conn.createChannel();
	        CloudByteRpcClient service = new CloudByteRpcClient(ch, exchange, routingKey);
	        
	        if ( s_logger.isDebugEnabled() ) s_logger.debug( request.getMethod() + " " + request.getArgs() );	        
	        requestMap.put("method", request.getMethod());
	        requestMap.put("args", request.getArgs());
	        requestMap.put("_msg_id", service.getReplyQueue());
	        
	        Map<String, String> defaultAdminRpcContext = CloudByteRpcContext.defaultAdminRpcContext();
	        for (Map.Entry<String, String> e: defaultAdminRpcContext.entrySet()) {
	        	requestMap.put("_context_"+e.getKey(), e.getValue());
	        }    

	        if ( s_logger.isDebugEnabled() ) s_logger.debug( "RequestMap : " + requestMap);
	        Gson gson = new Gson();
	        //System.out.println(gson.toJson(request));
	        JsonElement responseObj = service.mapCloudByteCall(null, gson.toJson(requestMap).getBytes("UTF-8"), request.getResponseTimeout(), request.getMaxAttempts());
	        
	        //System.out.println(responseMap);
	        if ( s_logger.isDebugEnabled() ) s_logger.debug( "Response : " + (null != responseObj ? responseObj.getClass() : "No Response Object"));
	        
	        if ( null != response ) {
		        response = gson.fromJson( responseObj, response.getClass());	        	
	        }
	    } catch (TimeoutException te) {
	    	s_logger.info( "AMQP TimeoutException : " + requestMap);
	    	throw te;
	    } catch (Exception e) {
	        //System.err.println("Main thread caught exception: " + e);
	    	s_logger.info( "AMQP Exception : " + requestMap);
	        e.printStackTrace();
	        //System.exit(1);
	    } finally {
	    	if (conn != null) {
	    		try {
					conn.close();
				} catch (IOException e) {
					s_logger.info( "AMQP Exception : ");
					e.printStackTrace();
				}
	    	}
	    }
		return response;
	}
	
	public static void call(String routingKey, BaseRequest request ) throws TimeoutException {
		if ( s_logger.isDebugEnabled() ) s_logger.debug( "Initiating an rpc call without response : " + routingKey);
		call(routingKey, request, null );
	}	
	
	/**
	 * Overloading this method to pass contextId and admin name to the request
    */
	public static Object call(String routingKey, BaseRequest request, Object response ,String cmdCtxId,String adminName) throws TimeoutException {
		//TODO : Find a way to pass a context id, so that each call need not worry about passing them.
		if ( s_logger.isDebugEnabled() ) s_logger.debug( "Initiating an rpc call to : " + routingKey);
		String exchange = "cloudbyte";
		Connection conn = null;
		Map<String, Object> requestMap = new HashMap<String, Object> ();
		try {
	    	
	    	//String uri = "amqp://10.20.1.248:5672";
	
	        ConnectionFactory cfconn = new ConnectionFactory();
	        cfconn.setHost("localhost");
	        //cfconn.setUri(uri);
	        conn = cfconn.newConnection();
	        Channel ch = conn.createChannel();
	        CloudByteRpcClient service = new CloudByteRpcClient(ch, exchange, routingKey);
	        
	        if ( s_logger.isDebugEnabled() ) s_logger.debug( request.getMethod() + " " + request.getArgs() );	        
	        requestMap.put("method", request.getMethod());
	        requestMap.put("args", request.getArgs());
	        requestMap.put("_msg_id", service.getReplyQueue());
	      
	        
	        Map<String, String> defaultAdminRpcContext = CloudByteRpcContext.defaultAdminRpcContext(adminName,cmdCtxId);
	        for (Map.Entry<String, String> e: defaultAdminRpcContext.entrySet()) {
	        	requestMap.put("_context_"+e.getKey(), e.getValue());
	        }    

	        if ( s_logger.isDebugEnabled() ) s_logger.debug( "RequestMap : " + requestMap);
	        Gson gson = new Gson();
	        //System.out.println(gson.toJson(request));
	        JsonElement responseObj = service.mapCloudByteCall(null, gson.toJson(requestMap).getBytes("UTF-8"), request.getResponseTimeout(), request.getMaxAttempts());
	        
	        //System.out.println(responseMap);
	        if ( s_logger.isDebugEnabled() ) s_logger.debug( "Response : " + (null != responseObj ? responseObj.getClass() : "No Response Object"));
	        
	        if ( null != response ) {
		        response = gson.fromJson( responseObj, response.getClass());	        	
	        }
	    } catch (TimeoutException te) {
	    	s_logger.info( "AMQP TimeoutException : " + requestMap);
	    	throw te;
	    } catch (Exception e) {
	        //System.err.println("Main thread caught exception: " + e);
	    	s_logger.info( "AMQP Exception : " + requestMap);
	        e.printStackTrace();
	        //System.exit(1);
	    } finally {
	    	if (conn != null) {
	    		try {
					conn.close();
				} catch (IOException e) {
					s_logger.info( "AMQP Exception : ");
					e.printStackTrace();
				}
	    	}
	    }
		return response;
	}
	public static void call(String routingKey, BaseRequest request,String cmdCtxId,String adminName ) throws TimeoutException {
		if ( s_logger.isDebugEnabled() ) s_logger.debug( "Initiating an rpc call without response : " + routingKey);
		call(routingKey, request, null,cmdCtxId,adminName );
	}
	
	
	
}
