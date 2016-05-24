package com.minutemedia.pi.resources;

import java.util.List;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.codahale.metrics.annotation.Timed;
import com.fasterxml.jackson.annotation.JsonProperty;

@Path("update")
@Consumes(MediaType.APPLICATION_JSON)
public class DataUpload {

	static class Entity {
		  @JsonProperty String name;
	}
	
	@POST
	@Timed
	public int updateRecord(List<Entity> entities) {
	  // Do something with entities...
	  return 0;
	}
}
