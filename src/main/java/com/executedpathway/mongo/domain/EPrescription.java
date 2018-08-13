package com.executedpathway.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonTypeName;

import MetamodelExecution.impl.EPrescriptionImpl;

@Document
@JsonTypeName("ePrescription")
public class EPrescription extends EPrescriptionImpl {	

	@Id
	private Integer id;
	
	public EPrescription() {		
		super();
	}

}
