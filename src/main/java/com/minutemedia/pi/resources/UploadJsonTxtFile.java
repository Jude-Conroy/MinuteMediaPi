package com.minutemedia.pi.resources;

import java.io.InputStream;
import java.io.StringWriter;
import java.net.UnknownHostException;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.glassfish.jersey.media.multipart.FormDataParam;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.minutemedia.pi.PiConfiguration;
import com.mongodb.DB;

@Path("uploadFile")
@Produces(MediaType.TEXT_PLAIN)
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class UploadJsonTxtFile {

	private MongoDBInsert mongo;
	private StringWriter writer;
	private String jsonString;
	private ObjectMapper mapper;
	
	public UploadJsonTxtFile(DB db, PiConfiguration configuration) {
		try {
			mongo = new MongoDBInsert(db);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		writer = new StringWriter();
	}

	@POST
	@Timed
	public Response updateRecord(@FormDataParam("filename") final InputStream inputStream) throws Exception {
		
		IOUtils.copy(inputStream, writer);
		jsonString = writer.toString();
		
		mapper = new ObjectMapper();
		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		
		for(String jsonrow : jsonString.split("},"))
			mongo.Insert(jsonrow.substring(jsonrow.indexOf(":{")+1) + "}}");
		
		return Response.ok("Upload successful!").build();
	}
}
