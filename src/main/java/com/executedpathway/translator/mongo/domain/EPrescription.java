package com.executedpathway.translator.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import MetamodelExecution.impl.EPrescriptionImpl;

@Document
@TypeAlias("ePrescription")
public class EPrescription extends EPrescriptionImpl {	

	@Id
	private Integer id;
	
	public EPrescription() {		
		super();
	}

}
