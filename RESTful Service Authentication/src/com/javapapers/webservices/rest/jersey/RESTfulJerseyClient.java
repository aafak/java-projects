package com.javapapers.webservices.rest.jersey;

import java.net.URI;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.core.util.Base64;


public class RESTfulJerseyClient {

	private static final String webServiceURI = "http://localhost:9080/RESTful_Service_Authentication/rest/helloworld";

	public static void main(String[] args) {
		//ClientConfig clientConfig = new ClientConfig();
		//Client client = ClientBuilder.newClient(clientConfig);
		//URI serviceURI = UriBuilder.fromUri(webServiceURI).build();
		//webTarget webTarget = client.target(serviceURI);

		String name = "admin";
        String password = "admin";
        String authString = name + ":" + password;
       // String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
        String authStringEnc = new Base64().encode(authString.getBytes()).toString();

        System.out.println("Base64 encoded auth string: " + authStringEnc);
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(webServiceURI);
        ClientResponse response = webResource.accept("application/json")
                                         .header("Authorization", "Basic " + authStringEnc)
                                         .get(ClientResponse.class);

		if(response.getStatus() != 200) {
			throw new RuntimeException("Failed : HTTP error code : "
					+ response.getStatus());
		}
		

		String output = response.getEntity(String.class);

		System.out.println("Output from Server .... \n");
		System.out.println(output);
		// response
		/*System.out.println(webTarget.path("rest").path("helloworld").request()
				.accept(MediaType.TEXT_PLAIN).get(Response.class).toString());

		// text
		System.out.println(webTarget.path("rest").path("helloworld").request()
				.accept(MediaType.TEXT_PLAIN).get(String.class));

		// xml
		System.out.println(webTarget.path("rest").path("helloworld").request()
				.accept(MediaType.TEXT_XML).get(String.class));

		// html
		System.out.println(webTarget.path("rest").path("helloworld").request()
				.accept(MediaType.TEXT_HTML).get(String.class));*/
	}
}
