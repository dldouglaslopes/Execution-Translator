package config.db;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import MetamodelExecution.EPathway;

public class DBConfig {
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	private CodecRegistry codecRegistry;
	
	public DBConfig() {	
		this.codecRegistry = fromRegistries(MongoClient.getDefaultCodecRegistry(),fromProviders(PojoCodecProvider.builder().automatic(true).build()));
		this.mongoClient = new MongoClient("localhost", MongoClientOptions.builder().codecRegistry(codecRegistry).build()); //creating a Mongo client
		this.mongoDatabase = mongoClient.getDatabase("teste3").withCodecRegistry(codecRegistry);
	}
	
	public void saveEPathway(EPathway ePathway) {
		MongoCollection<Document> collection = mongoDatabase.getCollection("ExecutedPathway"); //create a collection
		collection = collection.withCodecRegistry(codecRegistry); //config codec
		Document ePathwayDoc = new Document(ePathway.getName(), ePathway); //send epathway to document
		collection.insertOne(ePathwayDoc);	//insert a document	in a collection
	}
	
	public void close() {
		mongoClient.close(); //close client
	}
}
