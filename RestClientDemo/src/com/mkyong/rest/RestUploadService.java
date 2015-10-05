package com.mkyong.rest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.core.header.FormDataContentDisposition;
import com.sun.jersey.multipart.FormDataParam;
//import com.sun.jersey.multipart.FormDataParam;
 // upload a file using Jersey API
//In order to implement file upload feature, include jersey-multipart dependency 
//http://java2novice.com/restful-web-services/upload-file-jersey/
@Path("/test")
public class RestUploadService {

	private static final String FOLDER_PATH = "I:"+File.separator+"java-study"+
	File.separator+"code"+File.separator+"RestClientDemo"+
			File.separator+"download"+File.separator;
	// private static final String FOLDER_PATH = "/root/";
	//private static final String FOLDER_PATH = ".."+File.separator+"download"+File.separator;

	
	@POST
	@Path("/upload")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	@Produces(MediaType.APPLICATION_JSON)
	public String uploadFile(@FormDataParam("file") InputStream fis,
			@FormDataParam("file") FormDataContentDisposition fdcd) {

		OutputStream outpuStream = null;
		String fileName = fdcd.getFileName();
		System.out.println("File Name: " + fdcd.getFileName());
		String filePath = FOLDER_PATH + fileName;

		try {
			int read = 0;
			byte[] bytes = new byte[1024];
			outpuStream = new FileOutputStream(new File(filePath));
			while ((read = fis.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
			outpuStream.close();
		} catch(IOException iox){
			iox.printStackTrace();
		} finally {
			if(outpuStream != null){
				try{outpuStream.close();} catch(Exception ex){}
			}
		}
		return "File Upload Successfully to " +filePath;
	}
}
