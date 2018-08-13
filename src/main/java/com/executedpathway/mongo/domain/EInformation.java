package com.executedpathway.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonTypeName;

import MetamodelExecution.impl.EInformationImpl;


@Document
@JsonTypeName("eInformation")
public class EInformation extends EInformationImpl {

	@Id
	private Integer id;
	
	public EInformation() {
		
		super();
	}
}
