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

import QueryMetamodel.Age;
import QueryMetamodel.Date;
import QueryMetamodel.ECarePathway;
import QueryMetamodel.EConduct;
import QueryMetamodel.EQuery;
import QueryMetamodel.EStep;
import QueryMetamodel.Gender;
import QueryMetamodel.Message;
import QueryMetamodel.Order;
import QueryMetamodel.Range;
import QueryMetamodel.Sex;
import QueryMetamodel.Status;

public class QueryMethod {
	private DBConfig dbConfig;	
	private ECarePathway carePathway;
	private Age age; 
	private Date date;
	private Range range;
	private Sex sex;
	private Status status;
	
	public QueryMethod(EQuery eQuery) {
		dbConfig = new DBConfig();
		this.carePathway = eQuery.getEMethod().getEAttribute().getCarePathway();
		this.age = eQuery.getEMethod().getEAttribute().getAge(); 
		this.date = eQuery.getEMethod().getEAttribute().getDate();
		this.range = eQuery.getEMethod().getEAttribute().getRange();
		this.sex = eQuery.getEMethod().getEAttribute().getSex();
		this.status = eQuery.getEMethod().getEAttribute().getStatus();
	}
	
	public FindIterable<Document> filterDocuments() {
		FindIterable<Document> docs = dbConfig.getCollection()
												.find()
												.filter( Filters.all( "name", 
																	carePathway.getName()));
		
		if (!carePathway.getStep().contains(EStep.ALL) &&
			!carePathway.getConduct().contains(EConduct.ALL)) {
			
			docs.filter( Filters.or( Filters.all( "executedsteps.step.name", 
													carePathway.getStep()),
									Filters.all( "complementaryconducts.type", 
													carePathway.getConduct())));
		}
		else if (carePathway.getStep().contains(EStep.ALL) &&
				!carePathway.getConduct().contains(EConduct.ALL)) {
			
			docs.filter( Filters.all( "complementaryconducts.type", 
										carePathway.getConduct()));
		} 
		else if (!carePathway.getStep().contains(EStep.ALL) &&
				carePathway.getConduct().contains(EConduct.ALL)) {
			
			docs.filter( Filters.all( "executedsteps.step.name", 
										carePathway.getStep()));
		}		
		
		if (!sex.getSex().equals(Gender.ALL)) {
			docs = docs.filter( Filters.eq( "medicalcare.sex", sex.getSex()));
		}												
		
		if (!status.getMessage().equals(Message.ALL)) {
			docs = docs.filter( Filters.eq( status.getMessage().getName(), status.isValue()));
		}
		
		if (age.getFrom() > 0 && age.getTo() == 0) {
			docs.filter( Filters.gte( "medicalcare.age", 
										age.getFrom()));
		}		
		else if (age.getFrom() >= 0 && age.getTo() > 0 && age.getTo() >= age.getFrom()) {
			docs.filter( Filters.and( Filters.gte( "medicalcare.age", 
											age.getFrom()),
									Filters.lte( "medicalcare.age", 
											age.getTo())));
		}
		
		return docs;
	}	
	
	public List<Entry<String, Double>> occurrencyFlow() {
		
		//finding all the documents belonging to the same care pathway
		FindIterable<Document> carePathwayDocs = filterDocuments();
				
		//count how many occurrences of same care pathway name 
		String field = "name";
		String name = carePathway.getName();
		int size = count( field, name, carePathwayDocs);
		
		Map<String, Integer> flowMap = new HashMap<>();
		
		//quering the flows and counting how many flow occurrences
		for( Document carePathwayDoc : carePathwayDocs) {
			if ( carePathway.getName().equals(carePathwayDoc.get("name"))) {
				@SuppressWarnings( "unchecked")
				List<Document> executedStepDocs =  (List<Document>) carePathwayDoc.get( "executedSteps");
				
				String flow = "";
				
				for (Document executedStepDoc : executedStepDocs) {
					Document stepDoc = ( Document) executedStepDoc.get("step");
					flow += stepDoc.getString("type") + "-" + stepDoc.getInteger("_id") + "/";
				}
									
				if (flowMap.containsKey(flow)) {
					int value = flowMap.get(flow) + 1;
					flowMap.replace(flow, value);
				}
				else {
					flowMap.put(flow, 1);
				}					
			}				
		}		

		Map<String, Double> percentMap = new HashMap<>();
		
		//calculating the percent of the flow
		for ( String key : flowMap.keySet()) {
			int dividend = flowMap.get(key);
			int divider = size;
			double percent = rate( dividend, divider);
			percentMap.put( key, percent);	
		}
		
		List<Entry<String, Double>> list = new LinkedList<>( percentMap.entrySet());
		
		//sorting the list following the order
		sort( list, range.getOrder());
		
		return split( range.getQuantity(), list);	
	}
	
	public String averageByTime() {	
		//quering the average time
		AggregateIterable<Document> aggregate = dbConfig.getCollection().aggregate( Arrays.asList(
		Aggregates.group( "_id", new BsonField( "averageTime",	new BsonDocument( "$avg", new BsonString( "$timeExecution"))))));
				
		//getting the average time
		Document result = aggregate.first();
		double time = result.getDouble( "averageTime");
		
		return decimalFormat( time/60);
	}
	
	public List<Entry<String, Double>> occurrenceExecution( String qualify, int quantity) {
		Set<String> carePathwayNames = new HashSet<String>();
		
		//finding all the documents
		FindIterable<Document> carePathwayDocs = allDocuments();	
		
		//storing medication names in a set
		for(Document doc : carePathwayDocs) {
			carePathwayNames.add( doc.getString( "name"));
		}
		
		Map<String, Double> carePathwayTimes = new HashMap<>();
		
		//counting how long the care pathway is stored in complementary conducts
		for( String name : carePathwayNames) {
			double carePathwayTime = countByName( "name", name);
			carePathwayTimes.put( name, carePathwayTime);			
		}
				
		List<Entry<String, Double>> list = new LinkedList<>( carePathwayTimes.entrySet());

		//sorting the list with a comparator
		//sortList(qualify, list);
		
		return split(quantity, list);	
	}
	
	///medication in a care pathway or conduct complementary
	public List<Entry<String, Double>> prescribedMedication( String qualify, int quantity, String field) {
		Set<String> medicationNames = new HashSet<String>();
				
		//finding all the 	documents
		FindIterable<Document> medicationComps = allDocuments();	
		
		//storing medication names in a set
		for( Document doc : medicationComps) {
			@SuppressWarnings( "unchecked")
			List<Document> complementaryConducts = ( List<Document>) doc.get( field);

			if( !complementaryConducts.isEmpty()) {				
				for( Document complementaryConduct : complementaryConducts) {
					Document doc2 = ( Document) complementaryConduct.get( "prescribedresource");
											
					if( complementaryConduct.getString( "type").equals( "MedicamentoComplementar") &&
							!doc2.getString( "name").isEmpty()) {
						
						medicationNames.add( doc2.getString( "name"));
					}	
				}
			}			
        }
		
		Map<String, Double> medicationTimes = new HashMap<>();
		
		//counting how many medication occurences in complementary conducts
		for( String name : medicationNames) {
			double medicationTime = countByName( "complementaryConducts.prescribedresource.name", name);			
			medicationTimes.put( name, medicationTime);			
		}
				
		List<Entry<String, Double>> list = new LinkedList<>( medicationTimes.entrySet());

		//sorting the list with a comparator
		//sortList(qualify, list);
		
		return split(quantity, list);				
	}
	
	private List<Entry<String, Double>> split(int quantity, List<Entry<String, Double>> list) {
		int toIndex = quantity;
		
		if( list.size() < toIndex) {
			return list.subList( 0, list.size());
		}
		
		return list.subList( 0, quantity);
	}
	
	private void sort( List<Entry<String, Double>> list, Order order) {
		if (order.equals(Order.TOP)) {
			descending(list);
		}
		if (order.equals(Order.BOTTOM)) {
			ascending(list);
		}
	}
	
	private double rate( double dividend, double divider) {
		return ( dividend/ divider) * 100;
	}
	
	private FindIterable<Document> allDocuments() {
		return dbConfig.getCollection().find();
	}
	
	private long countByName( String field, String name) {
		return dbConfig.getCollection()
						.count( Filters.eq( field,
											new BasicDBObject( "$regex", name)));
	}
	
	private int count( String field, String name, FindIterable<Document> iterable) {
		int cont = 0;
		
		for (Document document : iterable) {
			if (document.get(field).equals(name)) {
				cont ++; 
			}
		}
		
		return cont; 
	}

	private String decimalFormat( double number) {
		return new DecimalFormat("####0").format( number);
	}
	
	private void descending(final List<Entry<String, Double>> list) {
		//sorting the list with a comparator
		Collections.sort( list, new Comparator<Entry<String, Double>>() {
			public int compare( final Map.Entry<String, Double> o1, final Map.Entry<String, Double> o2) {
				return ( o2.getValue()).compareTo( o1.getValue());
			}
		});
	}
	
	private void ascending(final List<Entry<String, Double>> list) {
		//sorting the list with a comparator
		Collections.sort( list, new Comparator<Entry<String, Double>>() {
			public int compare( final Map.Entry<String, Double> o1, final Map.Entry<String, Double> o2) {
				return ( o1.getValue()).compareTo( o2.getValue());
			}
		});
	}
}