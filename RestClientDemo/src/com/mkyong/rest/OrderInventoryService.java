package com.mkyong.rest;
//import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.util.Base64;

//import sun.misc.BASE64Decoder;
 
/*
 In the context of a HTTP transaction, basic access authentication is a method for an HTTP user agent to provide a user name and password when making a request.
HTTP Basic authentication implementation is the simplest technique for enforcing access controls to web resources because it doesn't require cookies, session identifier and login pages. Rather, HTTP Basic authentication uses static, standard HTTP headers which means that no handshakes have to be done in anticipation.
When the user agent wants to send the server authentication credentials it may use the Authorization header. The Authorization header is constructed as follows:
1) Username and password are combined into a string "username:password"
2) The resulting string is then encoded using Base64 encoding
3) The authorization method and a space i.e. "Basic " is then put before the encoded string.
For example, if the user agent uses 'Aladdin' as the username and 'open sesame' as the password then the header is formed as follows:
Authorization: Basic QWxhZGRpbjpvcGVuIHNlc2FtZQ==
*/
@Path("/order-inventory")
public class OrderInventoryService {
 
    @GET
    @Path("/order/{orderId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Object getUserById(@PathParam("orderId") Integer orderId,
                            @HeaderParam("authorization") String authString){
         
    	System.out.println("Finding inventary...");
        if(!isUserAuthenticated(authString)){
            return "{\"error\":\"User not authenticated\"}";
        }
        Order ord = new Order();
        ord.setCustomer("Java2Novice");
        ord.setAddress("Bangalore");
        ord.setAmount("$2000");
        return ord;
    }
     
    @POST
    @Path("/order")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Object add(Order order,
                            @HeaderParam("authorization") String authString){
         
    	System.out.println("Adding inventary...");
        if(!isUserAuthenticated(authString)){
            return "{\"error\":\"User not authenticated\"}";
        }
//        Order ord = new Order();
//        ord.setCustomer("Java2Novice");
//        ord.setAddress("Bangalore");
//        ord.setAmount("$2000");
        return order;
    }  
    
    private boolean isUserAuthenticated(String authString){
         
        String decodedAuth = "";
        // Header is in the format "Basic 5tyc0uiDat4"
        // We need to extract data before decoding it back to original string
        String[] authParts = authString.split("\\s+");
        String authInfo = authParts[1];
        // Decode the data back to original string
        byte[] bytes = null;
        try {
            bytes = Base64.decode(authInfo);
            
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        decodedAuth = new String(bytes);
        System.out.println(decodedAuth);
         
        /**
         * here you include your logic to validate user authentication.
         * it can be using ldap, or token exchange mechanism or your
         * custom authentication mechanism.
         */
        // your validation code goes here....
         
        return true;
    }
}



//- See more at: http://java2novice.com/restful-web-services/http-basic-authentication/#sthash.1Iy12pNW.dpuf