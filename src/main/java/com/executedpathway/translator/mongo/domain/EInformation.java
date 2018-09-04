package com.executedpathway.translator.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import MetamodelExecution.impl.EInformationImpl;


@Document
@TypeAlias("eInformation")
public class EInformation extends EInformationImpl {

	@Id
	private Integer id;
	
	public EInformation() {
		
		super();
	}
}
