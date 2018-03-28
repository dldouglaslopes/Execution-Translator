package config.db;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.Iterator;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import MetamodelExecution.EPathway;

public class DBConfig {
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private CodecRegistry codecRegistry;
	private MongoCollection<Document> collection;
	
	public DBConfig() {	
		this.codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build())); //create a codec registry
		this.mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(codecRegistry).build()); //creating a Mongo client
		this.mongoDatabase = mongoClient.getDatabase("teste6").withCodecRegistry(codecRegistry); //create a database
		this.collection = mongoDatabase.getCollection("ExecutedPathway").withCodecRegistry(codecRegistry); //create a collection
	}
	
	public void saveEPathway(EPathway ePathway) {		
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
		return findEPathway(name) != null;
	}
	
	public void close() {
		mongoClient.close(); //close client
	}
	
	private Document findEPathway(String name) {
		if (collection.find() != null) {
			FindIterable<Document> findIterable = collection.find(); // Getting the find iterator 		     
			Iterator<Document> iterator = findIterable.iterator(); // Getting the iterator 
		    
		    while (iterator.hasNext()) {		       
		    	if(iterator.next().get("name").equals(name)) {
		    		return iterator.next();
		    	}
		    }
		}
		
		return null;
	}
}
