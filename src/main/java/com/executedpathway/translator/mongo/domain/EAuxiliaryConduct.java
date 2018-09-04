package com.executedpathway.translator.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import MetamodelExecution.impl.EAuxiliaryConductImpl;

@Document
@TypeAlias("eAuxiliaryConduct")
public class EAuxiliaryConduct extends EAuxiliaryConductImpl{
	
	@Id
	private Integer id;
	
	public EAuxiliaryConduct() {		
		super();
	}
}
