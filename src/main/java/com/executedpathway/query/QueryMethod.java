package com.executedpathway.query;

import java.text.DecimalFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bson.Document;

import com.mongodb.client.FindIterable;
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
	
	public Map<String, Integer> status() {
		
		//finding all the documents
		FindIterable<Document> status = filterDocuments();	
		
		Map<String, Integer> statusSum = new HashMap<>();		
		statusSum.put( "completed", 0);
		statusSum.put( "aborted", 0);
		statusSum.put( "inProgress", 0);
		
		for (Document doc : status) {
			if (doc.getBoolean("aborted")) {
				int value = statusSum.get( "aborted");
				statusSum.replace( "aborted", value + 1);
			}
			else if (doc.getBoolean( "completed")) {
				int value = statusSum.get( "completed");
				statusSum.replace( "completed", value + 1);
			}
			else{
				int value = statusSum.get( "inProgress");
				statusSum.replace( "inProgress", value + 1);
			}
		}
		
		return statusSum;
	}
	
	///medication in executed step or conduct complementary
	public List<Entry<String, Double>> prescribedMedication() {
				
		//finding all the documents
		FindIterable<Document> medicationComps = filterDocuments();	
		
		Map<String, Double> medicationTimes = new HashMap<>();
		
		//counting how many medication occurences in complementary conducts/executed steps
		for( Document doc : medicationComps) {
			if (carePathway.getConduct().contains(EConduct.MEDICATION)) {
				
				List<Document> complementaryConducts = ( List<Document>) doc.get( "complementaryConducts");

				if( !complementaryConducts.isEmpty()) {				
					for( Document complementaryConduct : complementaryConducts) {
						Document doc2 = ( Document) complementaryConduct.get( "prescribedresource");
												
						if( complementaryConduct.getString( "type").equals( "MedicamentoComplementar") &&
								!doc2.getString( "name").isEmpty()) {
							
							String key = doc2.getString( "name");
							System.out.println(key);
							if (medicationTimes.containsKey( doc2.getString( "name"))) {
								double value = medicationTimes.get(key) + 1;
								medicationTimes.replace( key, value);
							}
							else {
								medicationTimes.put( key, 1.0);
							}
						}	
					}
				}	
			}
			else {
				
				List<Document> executedSteps = ( List<Document>) doc.get( "executedSteps");
				
				if( !executedSteps.isEmpty() &&
					(carePathway.getConduct().contains( EStep.TREATMENT) ||
					carePathway.getConduct().contains( EStep.PRESCRIPTION)) ) {	
					
					for( Document step : executedSteps) {						
						if (doc.get("step.type").equals(EStep.TREATMENT) || 
							doc.get("step.type").equals(EStep.PRESCRIPTION)) {
							
							List<Document> prescribed = ( List<Document>) doc.get( "prescribedmedication");
							
							for (Document document : prescribed) {
								Document medication = ( Document) document.get( "medication");
															
								String key = medication.getString( "name");
								System.out.println(key);
								
								if (medicationTimes.containsKey( medication.getString( "name"))) {
									double value = medicationTimes.get(key) + 1;
									medicationTimes.replace( key, value);
								}
								else {
									medicationTimes.put( key, 1.0);
								}
							}
						}
					}
				}				
			}			
        }
		
		List<Entry<String, Double>> list = new LinkedList<>( medicationTimes.entrySet());

		//sorting the list with a comparator
		sort(list, range.getOrder());
		
		return split(range.getQuantity(), list);				
	}
	
	public String averageByTime() {	
		//quering the average time
		FindIterable<Document> docs = filterDocuments();
		
		int cont = 0;
		double sum = 0; 
		
		for (Document document : docs) {
			cont += 1;
			sum += document.getDouble("timeExecution");
		}
		
		//getting the average time
		double avg = sum / cont;
		
		return decimalFormat( avg / 60);
	}
		
	public int occurrencyExecution() {
		//finding all the documents
		FindIterable<Document> carePathwayDocs = filterDocuments();	
				
		String field = "name";
		String name = carePathway.getName();
		int size = count( field, name, carePathwayDocs);
		
		return size;	
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
	
	private FindIterable<Document> filterDocuments() {
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

/*
 	private long countByName( String field, String name) {
		return dbConfig.getCollection()
						.count( Filters.eq( field,
											new BasicDBObject( "$regex", name)));
	}
*/