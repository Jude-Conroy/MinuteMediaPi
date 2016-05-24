package com.minutemedia.pi;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.UnknownHostException;

import org.glassfish.jersey.media.multipart.MultiPartFeature;

import com.meltmedia.dropwizard.mongo.MongoBundle;
import com.minutemedia.pi.resources.DataUpload;
import com.minutemedia.pi.resources.InsertData;
import com.minutemedia.pi.resources.PostUpload;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class PiApplication extends Application<PiConfiguration> {

	MongoBundle<PiConfiguration> mongoBundle;
	MongoClient client;
	DB db ;
	
    public static void main(final String[] args) throws Exception {
        new PiApplication().run(args);
    }

    @Override
    public String getName() {
        return "pi";
    }

    @Override
    public void initialize(final Bootstrap<PiConfiguration> bootstrap) {
    	  bootstrap.addBundle(mongoBundle = MongoBundle.<PiConfiguration>builder()
    			    .withConfiguration(PiConfiguration::getMongo)
    			    .build());
    }

    @Override
    public void run(final PiConfiguration configuration, final Environment environment) throws UnknownHostException {
    	    	     	 
    	 MongoClient client = new MongoClient("localhost", 27017);
    	 DB db = client.getDB("piData");
    	 
    	 db = mongoBundle.getDB();
    	 
    	 environment.jersey().register(new InsertData(db));     
    	 environment.jersey().register(new DataUpload());   
    	 environment.jersey().register(new PostUpload()); 
    	 environment.jersey().register(MultiPartFeature.class); 
    }

}
