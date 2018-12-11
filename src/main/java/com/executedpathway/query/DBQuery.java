package com.executedpathway.query;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
		
		//quering the average time
		AggregateIterable<Document> aggregate = dbConfig.getCollection()
				.aggregate(Arrays.asList(
						Aggregates.group("_id", 
								new BsonField("averageTime", 
										new BsonDocument("$avg", 
												new BsonString("$timeExecution"))))));
				
		//getting the average time
		Document result = aggregate.first();
		double time = result.getDouble("averageTime");
		
		return decimalFormat(time/60);
	}
	
	public Entry<String, Integer> mostExecutedCarePathway() {
		Set<String> carePathwayNames = new HashSet<String>();
		
		//finding all the documents
		FindIterable<Document> carePathwayDocs = dbConfig.getCollection().find();		
		
		//storing medicine names in a set
		for (Document doc : carePathwayDocs) {
			carePathwayNames.add(doc.getString("name"));
		}
		
		Map<String, Integer> carePathwayTimes = new HashMap<>();
		
		//counting how long the medicine is stored in complementary conducts
		for(String name : carePathwayNames) {
			long carePathwayTime = dbConfig.getCollection()
										.count(Filters.eq("name",
															new BasicDBObject("$regex", name)));
			
			carePathwayTimes.put(name, (int) carePathwayTime);		
			
		}
				
		List<Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(carePathwayTimes.entrySet());

		//sorting the list with a comparator
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});
		
		return list.get(0);
	}
	
	public List<Entry<String, Integer>> top5MedicineInComplementaryConducts() {
		Set<String> medicineNames = new HashSet<String>();
				
		//finding all the documents
		FindIterable<Document> medicineComps = dbConfig.getCollection().find();	
		
		//storing medicine names in a set
		for (Document doc : medicineComps) {
			@SuppressWarnings("unchecked")
			List<Document> complementaryConducts = (List<Document>) doc.get("complementaryConducts");

			if (!complementaryConducts.isEmpty()) {				
				for (Document complementaryConduct : complementaryConducts) {
					Document doc2 = (Document) complementaryConduct.get("prescribedresource");
											
					if (complementaryConduct.getString("type").equals("MedicamentoComplementar") &&
							!doc2.getString("name").isEmpty()) {
						
						medicineNames.add(doc2.getString("name"));
					}	
				}
			}			
        }
		
		Map<String, Integer> medicineTimes = new HashMap<>();
		
		//counting how long the medicine is stored in complementary conducts
		for(String name : medicineNames) {
			long medicineTime = dbConfig.getCollection()
										.count(Filters.eq("complementaryConducts.prescribedresource.name",
															new BasicDBObject("$regex", name)));
			
			medicineTimes.put(name, (int) medicineTime);			
		}
				
		List<Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(medicineTimes.entrySet());

		//sorting the list with a comparator
		Collections.sort(list, new Comparator<Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o2.getValue()).compareTo(o1.getValue());
			}
		});

		int toIndex = 4;
		if (list.size() < toIndex) {
			return list.subList(0, list.size());
		}	
		
		return list.subList(0, 4);		
	}

	private String decimalFormat(double number) {
		return new DecimalFormat("#0").format(number);
	}
}