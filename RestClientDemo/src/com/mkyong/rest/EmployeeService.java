package com.mkyong.rest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
 
@Path("/employee")
public class EmployeeService {
 //@Path annotation with regular expression match
    @GET
    @Path("{empId: [0-9]+}")
	@Produces(MediaType.APPLICATION_JSON)
    public Emp getEmployeeById(@PathParam("empId") String empId){
    	//http://localhost:9080/RestClientDemo/rest/employee/1
    	Emp e = new Emp();
    	e.setId(Integer.valueOf(empId));
    	return e;
       // return Response.status(200).entity("Got employee with id : " + empId).build();
    }
     
    @GET
    @Path("/name/{empName: [a-zA-Z\\s]+}")
	@Produces(MediaType.APPLICATION_JSON)
    public Emp getEmployeeByName(@PathParam("empName") String name){
         //http://localhost:9080/RestClientDemo/rest/employee/name/aafak
    	Emp e = new Emp();
    	e.setName(name);
    	return e;
    } 
    
    @POST
    @Path("/add")
    @Produces(MediaType.APPLICATION_JSON)
    public Emp addEmp(@FormParam("id") int id, @FormParam("name") String name) {
    	//http://localhost:9080/RestClientDemo/rest/employee/add?id=1&name=aafak
    	System.out.println("Adding emp...");
    	System.out.println("Adding emp: "+id);
    	System.out.println("Adding emp:"+name);

    	Emp e = new Emp();
    	e.setId(id);
    	e.setName(name);
    	return e;
    }
    
   /* @GET
    @Path("/name/{empName: [a-zA-Z\\s]+}")
    public Response getEmployeeByName(@PathParam("empName") String name){
         //http://localhost:9080/RestClientDemo/rest/employee/name/aafak
        return Response.status(200).entity("Got employee with name : " + name).build();
    }*/
    
    
    /*@GET
    @Path("{empId}")
    public Response getEmployeeById(@PathParam("empId") String empId){
         
        return Response.status(200).entity("Got employee with id : " + empId).build();
    }*/
     
    //PathParam annotation example
    //PathParam annotation, which injects value from URI to your method input parameters.
    //Using @PathParam, you can access multiple values as well. Look at getEmployeeList() method in the above example, if you use "/employee/bangalore/accounts" URI pattern, then getEmployeeByName() method will be invoked -
    @GET
    @Path("{branch}/{department}")
	@Produces(MediaType.APPLICATION_JSON)
    public Response getEmployeeList(@PathParam("branch") String branchName,
                                    @PathParam("department") String deptName){
         
        String resp = "Total number of employees in the "+deptName+" department from "
                        +branchName+" is 100";
        return Response.status(200).entity(resp).build();
        //http://localhost:9080/RestClientDemo/rest/employee/bangalore/account
    }
}
//- See more at: http://java2novice.com/restful-web-services/jax-rs-path-regex-match/#sthash.HBWOEis0.dpuf