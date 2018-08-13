package com.executedpathway.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonTypeName;

import MetamodelExecution.impl.EDischargeImpl;

@Document
@JsonTypeName("eDischarge")
public class EDischarge extends EDischargeImpl {
	
	@Id
	private Integer id;

	public EDischarge() {		
		super();
	}
}
