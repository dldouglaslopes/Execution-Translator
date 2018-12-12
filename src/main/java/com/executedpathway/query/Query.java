package com.executedpathway.query;

import java.util.List;
import java.util.Map.Entry;

public class Query {
	DBQuery dbQuery = new DBQuery();

	public void queries() {
		//#Q1
		/*
		System.err.println("Average Time= " + 
							dbQuery.avgTimeExecution() +
							" horas");
		
		//#Q2
		List<Entry<String, Integer>> medicinesTop5 = dbQuery.top5MedicineInComplementaryConducts();
		for (int i = 0; i < medicinesTop5.size(); i++) {
			System.err.println( medicinesTop5.get(i));
		}		
		
		//#Q3
		System.err.println("The most executed care pathway is " + 
							dbQuery.mostExecutedCarePathway().getKey() +
							" with " +
							dbQuery.mostExecutedCarePathway().getValue() +
							" execution(s).");
		*/
		
		//#Q4
		dbQuery.mostRecurrentFlowInACarePathway("Pneumonia & Influenza");
	}	
}
