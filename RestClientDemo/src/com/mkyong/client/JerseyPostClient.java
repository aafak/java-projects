package com.mkyong.client;
//import sun.misc.BASE64Encoder;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
 
public class JerseyPostClient {
 
    public static void main(String a[]){
         
    	   String name = "java2novice";
           String password = "Simple4u!";
           String authString = name + ":" + password;
           //String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
           String authStringEnc = new Base64().encode(authString.getBytes()).toString();

           System.out.println("Base64 encoded auth string: " + authStringEnc);
        //String url = "http://localhost:8080/RestClientDemo/rest/order-inventory/order";
        String url = "http://localhost:9080/RestClientDemo/rest/order-inventory/order";

        String jsonInput = "{\"customer\":\"Java2novice\",\"address\":\"Bangalore\","+
                "\"amount\":\"$2000\"}";
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);
        ClientResponse resp = webResource.type("application/json").header("Authorization", "Basic " + authStringEnc)
                                    .post(ClientResponse.class, jsonInput);
        System.out.println("Status:"+resp.getStatus());
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        String output = resp.getEntity(String.class);
        System.out.println("response: "+output);
    }
}
//- See more at: http://java2novice.com/restful-web-services/java-client-jersey-api/#sthash.UcuIZPvY.dpuf