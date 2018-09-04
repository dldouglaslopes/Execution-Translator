package com.executedpathway.translator.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import MetamodelExecution.impl.ETreatmentImpl;

@Document
@TypeAlias("eTreatment")
public class ETreatment extends ETreatmentImpl {

	@Id
	private Integer id;
	
	public ETreatment() {		
		super();
	}	
}
