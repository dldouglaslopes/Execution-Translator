package com.executedpathway.init;

import com.executedpathway.query.DBQuery;

public class InitQuery {
	public static void main(String[] args) {
		DBQuery dbQuery = new DBQuery();  
		
		//Q1
		//System.err.println("Average Time= " + dbQuery.avgTimeExecution() + " horas");
		
		//Q2
		dbQuery.countMedicineInComplementaryConducts();
	}
}
