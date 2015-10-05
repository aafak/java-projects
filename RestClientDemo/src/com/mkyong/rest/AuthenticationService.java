package com.mkyong.rest;
import java.io.IOException;
import java.util.StringTokenizer;
//import sun.misc.BASE64Decoder;

import com.sun.jersey.core.util.Base64;

/*
 * Here in we have standard Java code. We extract the username and password 
 * from the auth string. Then verify it against the existing credentials 
 * and return boolean accordingly.
 */
public class AuthenticationService {
	public boolean authenticate(String authCredentials) {

		if (null == authCredentials)
			return false;
		// header value format will be "Basic encodedstring" for Basic
		// authentication. Example "Basic YWRtaW46YWRtaW4="
		final String encodedUserPassword = authCredentials.replaceFirst("Basic"
				+ " ", "");
		String usernameAndPassword = null;
		try {
			byte[] decodedBytes = Base64.decode(encodedUserPassword);
			
			//byte[] decodedBytes = new BASE64Decoder().decodeBuffer(
				//	encodedUserPassword);
			usernameAndPassword = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(
				usernameAndPassword, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();

		// we have fixed the userid and password as admin
		// call some UserService/LDAP here
		boolean authenticationStatus = "admin".equals(username)
				&& "admin".equals(password);
		return authenticationStatus;
	}
}