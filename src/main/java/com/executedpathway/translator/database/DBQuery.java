package com.executedpathway.translator.database;

import java.text.DecimalFormat;
import java.util.Arrays;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

import com.executedpathway.translator.config.DBConfig;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;

public class DBQuery {
	private DBConfig dbConfig = new DBConfig();
	
	public String avgTime() {
		AggregateIterable<Document> aggregate = dbConfig.getCollection()
				.aggregate(Arrays.asList(
						Aggregates.group("_id", 
								new BsonField("averageTime", 
										new BsonDocument("$avg", 
												new BsonString("$timeExecution"))))));
				
		Document result = aggregate.first();
		double time = result.getDouble("averageTime");
		
		return decimalFormat(time/60);
	}
	
	private String decimalFormat(double number) {
		return new DecimalFormat("#0").format(number);
	}
}
