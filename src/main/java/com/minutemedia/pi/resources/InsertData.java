package com.minutemedia.pi.resources;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.codahale.metrics.annotation.Timed;
import com.minutemedia.pi.PiData;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.WriteResult;

@Path("InsertData")
@Produces(MediaType.APPLICATION_JSON)
public class InsertData {

	public InsertData(DB db) {
		new AtomicLong();
	}

	private static DBObject createDBObject(PiData user) { 
		
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();

		docBuilder.append("id", user.getId());
		docBuilder.append("distance", user.getDistance());
		docBuilder.append("mac_short", user.getMac_short());
		docBuilder.append("mac", user.getMac());
		docBuilder.append("venueid", user.getVenueid());
		docBuilder.append("samples", user.getSamples());
		docBuilder.append("captime", user.getCaptime());
		
		return docBuilder.get();
	}

	private static PiData createUser() {
		PiData u = new PiData();
		u.setId(2);
		u.setDistance("Pankaj");
		u.setMac("");
		u.setVenueid("");
		return u;
	}

	@GET
	@Timed
	public String InsertDataPrime(@QueryParam("upload") String start) throws Exception {

		PiData user = createUser();

		DBObject doc = createDBObject(user);
		MongoClient mongo = new MongoClient("localhost", 27017);
		DB db = mongo.getDB("piData");
		DBCollection col = db.getCollection("venueInfo");

		// create user
		WriteResult result = col.insert(doc);
		System.out.println(result.getN());
		System.out.println(result.getLastConcern());

		// read example
		DBObject query = BasicDBObjectBuilder.start().add("_id", user.getId()).get();
		DBCursor cursor = col.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

		// update example
		user.setVenueid("Pankaj Kumar");
		doc = createDBObject(user);
		result = col.update(query, doc);
		System.out.println(result.getN());
		System.out.println(result.getLastConcern());

		// delete example
		result = col.remove(query);
		System.out.println(result.getN());
		System.out.println(result.getLastConcern());

		// close resources
		mongo.close();

		System.out.println("Done");

		return start;
	}
}
