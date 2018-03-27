package config.db;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import MetamodelExecution.EPathway;
import MetamodelExecution.impl.EPathwayImpl;

public class DBConfig {
	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;
	
	public DBConfig() {		
		this.mongoClient = new MongoClient("localhost"); //creating a Mongo client
		this.mongoDatabase = mongoClient.getDatabase("teste"); //creating a database
	}
	
	public void saveEPathway(EPathway ePathway) {
		MongoCollection<EPathwayImpl> collection =  mongoDatabase.getCollection("testpathway", EPathwayImpl.class);
		//Document ePathwayDoc = new Document().append("test", ePathway);
		collection.insertOne((EPathwayImpl) ePathway);				
	}

	public void close() {
		mongoClient.close();
	}
}
