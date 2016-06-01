package com.minutemedia.pi;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

import java.net.UnknownHostException;

import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.message.internal.FormProvider;

import com.meltmedia.dropwizard.mongo.MongoBundle;
import com.minutemedia.pi.resources.UploadJsonTxtFile;
import com.mongodb.DB;
import com.mongodb.MongoClient;

public class PiApplication extends Application<PiConfiguration> {

	private static final String PI_DATA = "piData";
	private static final String LOCALHOST = "localhost";
	MongoBundle<PiConfiguration> mongoBundle;
	MongoClient client;
	DB db ;
	
    public static void main(final String[] args) throws Exception {
        new PiApplication().run(args);
    }

    @Override
    public void initialize(final Bootstrap<PiConfiguration> bootstrap) {
    	  bootstrap.addBundle(mongoBundle = MongoBundle.<PiConfiguration>builder()
    			    .withConfiguration(PiConfiguration::getMongo)
    			    .build());
    }

    @Override
    public void run(final PiConfiguration configuration, final Environment environment) throws UnknownHostException {
    	    	     	 
    	 MongoClient client = new MongoClient(LOCALHOST, 27017);
    	 DB db = client.getDB(PI_DATA);
    	 
    	 db = mongoBundle.getDB();
    	 
    	 environment.jersey().register(new UploadJsonTxtFile(db, configuration)); 
    	 environment.jersey().register(MultiPartFeature.class); 
    	
    	 environment.jersey().register( FormProvider.class);
    	 
    }

}
