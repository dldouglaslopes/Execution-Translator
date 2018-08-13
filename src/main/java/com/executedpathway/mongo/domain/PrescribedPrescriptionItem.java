package com.executedpathway.mongo.domain;

import org.springframework.data.annotation.Id;

import MetamodelExecution.impl.PrescribedPrescriptionItemImpl;

//@Embeddable
public class PrescribedPrescriptionItem extends PrescribedPrescriptionItemImpl{

	@Id
	private Integer id;
	
	public PrescribedPrescriptionItem() {
		super();
	}
}
