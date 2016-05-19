package com.minutemedia.pi;

import io.dropwizard.Configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.meltmedia.dropwizard.mongo.MongoConfiguration;

public class PiConfiguration extends Configuration {
	  
	@JsonProperty
	protected MongoConfiguration mongo;

	public MongoConfiguration getMongo() {
	  return mongo;
	}
}
