package com.executedpathway.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonTypeName;

import MetamodelExecution.impl.ETreatmentImpl;

@Document
@JsonTypeName("eTreatment")
public class ETreatment extends ETreatmentImpl {

	@Id
	private Integer id;
	
	public ETreatment() {		
		super();
	}	
}
