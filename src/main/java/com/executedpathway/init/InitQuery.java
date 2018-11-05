package com.executedpathway.init;

import com.executedpathway.translator.database.DBQuery;

public class InitQuery {
	public static void main(String[] args) {
		DBQuery dbQuery = new DBQuery();  
		
		System.err.println("Average Time= " + dbQuery.avgTimeExecution() + " horas");
		//System.err.println(dbQuery.test2());
	}
}
