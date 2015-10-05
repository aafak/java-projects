package com.mkyong.rest;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
 
@Path("/employee2")
public class QueryParamExampleService {
 //@QueryParam and @DefaultValue annotations 
//@QueryParam and @DefaultValue annotations, which injects value from 
//request parameters to your method input parameters. 
	
	
    @GET
    @Path("/query")
    public Response getEmployeeQuery(@QueryParam("dept") String department,
                                    @QueryParam("branch") String branch){
        String resp = "Query parameters are received. 'dept' value is: "
                        +department+" and branch value is: "+branch;
         
        return Response.status(200).entity(resp).build();
        //http://localhost:9080/RestClientDemo/rest/employee2/query?branch=hydrabad&dept=finance
    }
    
    
    /*
     *  if you use "/employee/query?branch=hydrabad&dept=finance" URI pattern with query 
     *  parameters, getEmployeeQuery() method will be invoked, and you will 
     *  get "Query parameters are received. 'dept' value is: finance and branch value 
     *  is: hydrabad" as a response.
     */
    
    
    /*
     * How to assign default values to method input variables if the query parameters are 
     * not available? You can use @DefaultValue annotation to specify default value
     */
    @GET
    @Path("/querywithdefault")
    public Response getEmployeeQueryDefault(
                    @DefaultValue("accounts") @QueryParam("dept") String department,
                    @DefaultValue("bangalore") @QueryParam("branch") String branch){
        String resp = "Query parameters are received. 'dept' value is: "
                        +department+" and branch value is: "+branch;
         
        return Response.status(200).entity(resp).build();
        //http://localhost:9080/RestClientDemo/rest/employee2/querywithdefault?branch=hydrabad&dept=finance
       // http://localhost:9080/RestClientDemo/rest/employee2/querywithdefault
    }
}
//- See more at: http://java2novice.com/restful-web-services/jax-rs-queryparam/#sthash.3sFLhHL3.dpuf