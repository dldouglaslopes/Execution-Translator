package com.executedpathway.translator.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import MetamodelExecution.impl.EPathwayImpl;

@Document
public class EPathway extends EPathwayImpl {	
	
	@Id
	private Integer id;

	public EPathway() {		
		super();
	}	
}
