package config.db;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DBConfig {
	private MongoClient mongo;
	private MongoDatabase db;
	
	public MongoDatabase getDb() {
		return db;
	}
	
	public MongoClient getMongo() {
		return mongo;
	}
	
	public void connect() {
		this.mongo = new MongoClient("localhost", 27017);
	}
	
	public void createDatabase(String database) {
		this.db = mongo.getDatabase(database);
	}
	
	public void createCollection(String collection) {
		db.getCollection(collection);
	}
}
