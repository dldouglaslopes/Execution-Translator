package com.executedpathway;

import com.executedpathway.translator.database.DBQuery;

public class InitQuery {
	public static void main(String[] args) {
		System.err.println("Average Time= " + new DBQuery().avgTime() + " horas");
	}
}
