package com.mkyong.rest;
import java.io.File;
 
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
/*
 You need to do two steps to download a file from java restful web services.

1) Annotate your service method with @Produces annotation. This annotation should have the 
file MIME type as a value. For example, if you are downloading pdf file 
then MIME type should be "application/pdf", incase if you are downloading png image
file, then MIME type should be "image/png".
2) In the Response header, set “Content-Disposition” details, which helps to 
prompt download box on browser.  
*/
@Path("/download")
public class RestDownloadService {
 
    @GET
    @Path("/service-record")
    @Produces("application/pdf")
    public Response getFile() {
  
        //File file = new File("C:\java2novice\employee_1415.pdf");
        File file = new File("/root/Pictures/Payment.pdf");

        ResponseBuilder response = Response.ok((Object) file);
        response.header("Content-Disposition",
            "attachment; filename=\"employee_1415.pdf\"");
        return response.build();
    }
}
//http://localhost:9080/RestClientDemo/rest/download/service-record
//- See more at: http://java2novice.com/restful-web-services/jax-rs-download-file/#sthash.tTWEVTDo.dpuf