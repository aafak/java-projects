package com.mkyong.rest;
import javax.ws.rs.GET;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
 //@MatrixParam annotation example
/*
 * The idea of matrix parameters is that they are an arbitrary set of name-value pairs
 *  embedded in a uri path segment. A matrix parameter example is:
   GET http://java2novice.com/spring;name=aop;annotation=@Aspect

The basic idea of matrix parameters is that it represents resources that are 
addressable by their attributes as well as their raw id. The @MatrixParam annotation 
allows you to inject URI matrix paramters into your method invocation

This page gives you an example using JAX-RS @MatrixParam annotation, 
which injects value from request parameters to your method input parameters.

 if you use "/inventory/switch;company=cisco;model=nexus-5596" URI pattern with 
 query parameters, getInventoryDetails() method will be invoked, and you will get 
 below response.

Received request for device: switch, comany: cisco and model: nexus-5596
 */
@Path("/inventory")
public class InventoryService {
 
    @GET
    @Path("{deviceType}")
    public Response getInventoryDetails(@PathParam("deviceType") String deviceType,
            @MatrixParam("company") String company,
            @MatrixParam("model") String model){
         
        String resp = "Received request for device: "+deviceType+
                        ", comany: "+company+" and model: "+model;
        return Response.status(200).entity(resp).build();
    }
}
//http://localhost:9080/RestClientDemo/rest/inventory/switch;company=cisco;model=nexus-5596%22
//- See more at: http://java2novice.com/restful-web-services/jax-rs-matrixparam/#sthash.TaItvICV.dpuf