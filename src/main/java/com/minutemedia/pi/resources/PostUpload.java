package com.minutemedia.pi.resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.media.multipart.BodyPart;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;

import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

@Path("uploadFile")
@Consumes(MediaType.MULTIPART_FORM_DATA)
public class PostUpload {

	static class Entity {
		@JsonProperty String name;
	}
	
	@POST
	@Timed
	public int updateRecord(FormDataMultiPart data) {
	  
		List<BodyPart> bodyParts = data.getBodyParts();
		
		data.messageBodyWorkers.toString();
		
	  return 0;
	}
}
