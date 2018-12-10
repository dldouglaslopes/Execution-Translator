package com.executedpathway.query;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bson.BsonDocument;
import org.bson.BsonString;
import org.bson.Document;

import com.mongodb.BasicDBObject;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.FindIterable;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.BsonField;
import com.mongodb.client.model.Filters;

public class DBQuery {
	private DBConfig dbConfig = new DBConfig();
	
	public String avgTimeExecution() {
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
	
	public void countMedicineInComplementaryConducts() {
		Set<String> medicineNames = new HashSet<String>();
				
		FindIterable<Document> medicineComps = dbConfig.getCollection().find();		
		
		for (Document doc : medicineComps) {
			@SuppressWarnings("unchecked")
			List<Document> courses = (List<Document>) doc.get("complementaryConducts");

			if (!courses.isEmpty()) {				
				for (Document course : courses) {
					Document doc2 = (Document) course.get("prescribedresource");
											
					if (course.getString("type").equals("MedicamentoComplementar") &&
							!doc2.getString("name").isEmpty()) {
						System.out.println(doc2.getString("name"));
						medicineNames.add(doc2.getString("name"));
					}	
				}
			}			
        }
			
		for(String name : medicineNames) {
			long medicineTimes = dbConfig.getCollection().count(Filters.eq("complementaryConducts.prescribedresource.name",
																			new BasicDBObject("$regex", name)));
			System.out.println(medicineTimes);
		}		
	}

	private String decimalFormat(double number) {
		return new DecimalFormat("#0").format(number);
	}
}