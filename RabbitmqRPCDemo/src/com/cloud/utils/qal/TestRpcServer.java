package com.cloud.utils.qal;

import java.net.UnknownHostException;
import java.util.Map;

import com.google.gson.JsonElement;

public class TestRpcServer implements ListenerService {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = "TestRpcServer";
		TestRpcServer rpcs = new TestRpcServer();
		AMQPServer _server = null;
		try {
			_server = new AMQPServer(rpcs, name);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Thread listener = new Thread(_server);
		listener.start();

	}

	@Override
	public Boolean processDetails(JsonElement reqMethod, JsonElement reqArgs, JsonElement reqMsgId,
			Map<String, Object> status) {
		String method = reqMethod.getAsString();
		System.out.println("Method called "+method);
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

}