package com.cloud.core.programming;

import java.util.HashMap;
import java.util.Map;

public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String, Object>request=new HashMap<String, Object>();
		request.put("method", "test");
		request.put("args", "hello");
		System.out.println("request method:"+request.get("method"));
	}

}
