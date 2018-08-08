package com.executedpathway.database;

import org.bson.Document;

import com.executedpathway.config.DBConfig;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import MetamodelExecution.EPathway;

public class DBOperations {
	private MongoCollection<Document> collection;
	
	public DBOperations() {
		this.collection = new DBConfig().getCollection();
	}	
	
	public void saveEPathway(String name, EPathway ePathway) {		
		Document ePathwayDoc = new Document("name", ePathway.getName()).append("xmi", ePathway); //send epathway to document
		collection.insertOne(ePathwayDoc);	//insert a document	in a collection
	}
	
	public void updateEPathway(String name, EPathway ePathway) {
		collection.updateOne(Filters.eq("name", name), Updates.set("xmi", ePathway)); 
	}
	
	public void deleteEPathway(String name) {
		collection.deleteOne(Filters.eq("name", name)); 
	}
	
	public boolean hasEPathway(String name) {
		return collection.count(Filters.eq("name", name)) > 0;
	}
}
