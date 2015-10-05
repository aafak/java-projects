package com.mkyong.rest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
//@FormParam annotation example
//@FormParam to bind html form fields to your method inputs. It works for http method POST
@Path("/user-form")
public class UserRegService {
 
    @POST
    @Path("/register")
    public Response registerUserInfo(@FormParam("name") String name,
                                    @FormParam("address") String address){
         
        String response = "Successfully added user details, name: "+
                            name+" and address: "+address;
        return Response.status(200).entity(response).build();
    }
}
//http://localhost:9080/RestClientDemo/index.html
//http://localhost:9080/RestClientDemo/rest/user-form/register
//- See more at: http://java2novice.com/restful-web-services/jax-rs-formparam/#sthash.jKpPI1Ec.dpuf