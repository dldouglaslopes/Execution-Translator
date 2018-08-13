package com.executedpathway.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonTypeName;

import MetamodelExecution.impl.EAuxiliaryConductImpl;

@Document
@JsonTypeName("eAuxiliaryConduct")
public class EAuxiliaryConduct extends EAuxiliaryConductImpl{
	
	@Id
	private Integer id;
	
	public EAuxiliaryConduct() {		
		super();
	}
}
