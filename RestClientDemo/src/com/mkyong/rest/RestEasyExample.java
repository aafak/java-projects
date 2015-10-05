package com.mkyong.rest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 
@Path("/publish")
public class RestEasyExample {
 
    @GET
    @Path("/{message}")
    public Response publishMessage(@PathParam("message") String msgStr){
         
        String responseStr = "Received message: "+msgStr;
        return Response.status(200).entity(responseStr).build();
    }
}

//http://localhost:9080/RestClientDemo/rest/publish/hello
//- See more at: http://java2novice.com/restful-web-services/jersey-hello-world/#sthash.Vyc8dd8k.dpuf


/*

We need to configure Jersey within our web.xml file. In web.xml file, 
register "com.sun.jersey.spi.container.servlet.ServletContainer" 
and declare your services package path 

*/