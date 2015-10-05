package com.mkyong.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/message")
public class MessageReceiveService {
 
    @GET
    public Response pingMe(){
     //http://localhost:9080/RestClientDemo/rest/message
        String defaultResp = "Hi... How are you?";
        return Response.status(200).entity(defaultResp).build();
    }
     
    //Simple URI matching:
    @GET
    @Path("/birthday")
    public Response printBdayMessage(){
         //http://localhost:9080/RestClientDemo/rest/message/birthday
        String bDayMessage = "Happy Birthday";
        return Response.status(200).entity(bDayMessage).build();
    }
    
    //Dynamic URI with parameter matching:
    @GET
    @Path("{custMessage}")
    public Response printCustomMessage(@PathParam("custMessage") String message){
         //http://localhost:9080/RestClientDemo/rest/message/hi
        //http://localhost:9080/RestClientDemo/rest/message/hello

        return Response.status(200).entity(message).build();
    }
}
//- See more at: http://java2novice.com/restful-web-services/jax-rs-path-annotation/#sthash.AFJ6FRWe.dpuf