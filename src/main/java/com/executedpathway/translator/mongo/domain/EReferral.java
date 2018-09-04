package com.executedpathway.translator.mongo.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

import MetamodelExecution.impl.EReferralImpl;

@Document
@TypeAlias("eReferral")
public class EReferral extends EReferralImpl {

	@Id
	private Integer id;
	
	public EReferral() {		
		super();
	}
}
