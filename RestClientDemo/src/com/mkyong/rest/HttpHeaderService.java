package com.mkyong.rest;
import java.util.Set;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
//pass header parameters as method inputs

@Path("/http-header")
public class HttpHeaderService {
 
    @GET
    @Path("query")
    public Response queryHeaderInfo(@HeaderParam("Cache-Control") String ccControl,
                                        @HeaderParam("User-Agent") String uaStr){
         
        String resp = "Received http headers are Cache-Control: "+ccControl+
                        "<br>User-Agent: "+uaStr;
        return Response.status(200).entity(resp).build();
    }
  //http://localhost:9080/RestClientDemo/rest/http-header/query
    /*
     * Received http headers are Cache-Control: no-cache
    User-Agent: Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.6+ (KHTML, like Gecko) 
    Chromium/17.0.963.56 Chrome/17.0.963.56 Safari/537.6+
     */
    
    
  //read header parameters

    @GET
    @Path("query2")
    public Response queryHeaderInfo(@Context HttpHeaders httpHeaders) {
         
        /** how to get specific header info? **/
        String cacheControl = httpHeaders.getRequestHeader("Cache-Control").get(0);
        System.out.println("Cache-Control: "+cacheControl);
        /** get list of all header parameters from request **/
        Set<String> headerKeys = httpHeaders.getRequestHeaders().keySet();
        for(String header:headerKeys){
            System.out.println(header+":"+httpHeaders.getRequestHeader(header).get(0));
        }
        return Response.status(200).entity("successfully queried header info").build();
    }
    //http://localhost:9080/RestClientDemo/rest/http-header/query2
    /*
		* Cache-Control: max-age=0
		host:localhost:9080
		user-agent:Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.6+ (KHTML, like Gecko) Chromium/17.0.963.56 Chrome/17.0.963.56 Safari/537.6+
		accept:text/html,application/xhtml+xml,application/xml;q=0.9,;q=0.8
		cache-control:max-age=0
		accept-encoding:gzip, deflate
		connection:Keep-Alive
*/
     
}

//- See more at: http://java2novice.com/restful-web-services/jax-rs-headerparam/#sthash.LT2ci9rb.dpuf