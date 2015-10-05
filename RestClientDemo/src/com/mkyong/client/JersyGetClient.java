package com.mkyong.client;
//import sun.misc.BASE64Encoder;
 
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
 
public class JersyGetClient {
 
    public static void main(String a[]){
         
        String url = "http://localhost:9080/RestClientDemo/rest/order-inventory/order/1016";
        String name = "admin";
        String password = "admin";
        String authString = name + ":" + password;
       // String authStringEnc = new BASE64Encoder().encode(authString.getBytes());
        String authStringEnc = new Base64().encode(authString.getBytes()).toString();

        System.out.println("Base64 encoded auth string: " + authStringEnc);
        Client restClient = Client.create();
        WebResource webResource = restClient.resource(url);
        ClientResponse resp = webResource.accept("application/json")
                                         .header("Authorization", "Basic " + authStringEnc)
                                         .get(ClientResponse.class);
        if(resp.getStatus() != 200){
            System.err.println("Unable to connect to the server");
        }
        String output = resp.getEntity(String.class);
        System.out.println("response: "+output);
    }
}
//- See more at: http://java2novice.com/restful-web-services/http-basic-authentication/#sthash.1Iy12pNW.dpuf