package com.executedpathway.translator.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import MetamodelExecution.impl.EDischargeImpl;

@Document
@TypeAlias("eDischarge")
public class EDischarge extends EDischargeImpl {
	
	@Id
	private Integer id;

	public EDischarge() {		
		super();
	}
}
