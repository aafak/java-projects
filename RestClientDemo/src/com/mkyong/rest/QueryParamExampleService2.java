package com.mkyong.rest;
import java.util.List;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
//read multiple values of a query parameter
/*
 * http://localhost:8080/RestfulWebServices/employee/query?id=1016&id=1017&id=1415

In the above URL, the id value is same, but with different values, this can be 
easily readable as a list of values using UriInfo object.

 "/employee/query?id=1016&id=1017&id=1415" URI pattern with query parameters, here is the output:

Output:

INFO  [stdout] (http--127.0.0.1-8080-1) Received List: [1016, 1017, 1415]
INFO  [stdout] (http--127.0.0.1-8080-1) First emp record from the request: 1016
 */

@Path("/employee3")
public class QueryParamExampleService2 {
 
    @GET
    @Path("/query")
    public Response getEmployeeQuery(@Context UriInfo uriInfo){
         
        /** read complete employee id list from request query parameter**/
        List<String> empIdList = uriInfo.getQueryParameters().get("id");
        System.out.println("Received List: "+empIdList);
        /** read first employee id from request query parameter **/
        String firstEmpId = uriInfo.getQueryParameters().getFirst("id");
        System.out.println("First emp record from the request: "+firstEmpId);
         
        return Response.status(200).entity("processed request").build();
    }
}
//http://localhost:9080/RestClientDemo/rest/employee3/query?id=1016&id=1017&id=1415

//- See more at: http://java2novice.com/restful-web-services/jax-rs-read-multiple-values/#sthash.PZhFJLlp.dpuf