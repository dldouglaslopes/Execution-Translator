package com.executedpathway.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonTypeName;

import MetamodelExecution.impl.EReferralImpl;

@Document
@JsonTypeName("eReferral")
public class EReferral extends EReferralImpl {

	@Id
	private Integer id;
	
	public EReferral() {		
		super();
	}
}
