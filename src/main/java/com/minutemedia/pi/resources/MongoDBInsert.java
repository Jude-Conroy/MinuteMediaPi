package com.minutemedia.pi.resources;

import java.net.UnknownHostException;

import com.minutemedia.pi.PiData;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import com.mongodb.util.JSON;

public class MongoDBInsert {
	private static final String VENUE_INFO = "venueInfo";
	DB db;
	
	public MongoDBInsert(DB db) throws UnknownHostException {
		this.db = db;
	}

	private DBObject createDBObject(PiData user) { 
		
		BasicDBObjectBuilder docBuilder = BasicDBObjectBuilder.start();
		
		docBuilder.append("distance", user.distance);
		docBuilder.append("mac_short", user.mac_short);
		docBuilder.append("mac", user.mac);
		docBuilder.append("venueid", user.venueid);
		docBuilder.append("samples", user.samples);
		docBuilder.append("captime", user.captime);
		
		return docBuilder.get();
	}
	
	private DBObject createDBObject(String data) {
		// convert JSON to DBObject directly
		DBObject dbObject = (DBObject) JSON.parse(data);
		return dbObject;
	}
	
	public boolean Insert(String data) throws Exception {

		DBObject doc = createDBObject(data);
		DBCollection col = db.getCollection("venueinfo");

		// 
		WriteResult result = col.insert(doc);
		
		DBObject query = BasicDBObjectBuilder.start().add("_id", result.getField("_id")).get();
		DBCursor cursor = col.find(query);
		while (cursor.hasNext()) {
			DBObject activeObject = cursor.next();
			//col.remove(activeObject);
			System.out.println(activeObject);
		}
		
		return result.getLastError().ok();
	}

	public String Insert(PiData data) throws Exception {

		DBObject doc = createDBObject(data);
		DBCollection col = db.getCollection(VENUE_INFO);

		// create user
		WriteResult result = col.insert(doc);
		//System.out.println(result.getN());
		//System.out.println(result.getLastConcern());

		// read example
		DBObject query = BasicDBObjectBuilder.start().add("_id", result.getField("_id")).get();
		DBCursor cursor = col.find(query);
		while (cursor.hasNext()) {
			System.out.println(cursor.next());
		}

		// update example
		//result = col.update(query, doc);
		
		// delete example
		//result = col.remove(query);
		
		// close resources
		//mongo.close();

		System.out.println("Done");

		return "Inserted";
	}
}
