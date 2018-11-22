package com.executedpathway.init;

import com.executedpathway.query.DBQuery;

public class InitQuery {
	public static void main(String[] args) {
		DBQuery dbQuery = new DBQuery();  
		
		System.err.println("Average Time= " + dbQuery.avgTimeExecution() + " horas");
		//System.err.println(dbQuery.test2());
	}
}
