package com.executedpathway.query;

import QueryMetamodel.Age;
import QueryMetamodel.Attribute;
import QueryMetamodel.ComplementaryConducts;
import QueryMetamodel.Date;
import QueryMetamodel.EElement;
import QueryMetamodel.EQuery;
import QueryMetamodel.EStep;
import QueryMetamodel.Function;
import QueryMetamodel.Gender;
import QueryMetamodel.Message;
import QueryMetamodel.Method;
import QueryMetamodel.Order;
import QueryMetamodel.Qualifier;
import QueryMetamodel.Query_metamodelFactory;
import QueryMetamodel.Range;
import QueryMetamodel.Sex;
import QueryMetamodel.Status;

public class QueryStructure {		
	private QueryMethod queryMethod = new QueryMethod();
	
	public void create() {		
		EQuery query = Query_metamodelFactory.eINSTANCE.createEQuery();
		Method method = Query_metamodelFactory.eINSTANCE.createMethod();
		Attribute attribute = Query_metamodelFactory.eINSTANCE.createAttribute();	
		Sex sex = Query_metamodelFactory.eINSTANCE.createSex();
		Age age = Query_metamodelFactory.eINSTANCE.createAge();
		Order order = Query_metamodelFactory.eINSTANCE.createOrder();
		Range range = Query_metamodelFactory.eINSTANCE.createRange();
		EStep eStep = Query_metamodelFactory.eINSTANCE.createEStep();
		ComplementaryConducts complementaryConducts = Query_metamodelFactory.eINSTANCE.createComplementaryConducts();
		Date date = Query_metamodelFactory.eINSTANCE.createDate();
		Status status = Query_metamodelFactory.eINSTANCE.createStatus();
		
		sex.setSex(Gender.ALL);
		age.setFrom(0);
		age.setTo(0);
		order.setOrder(Qualifier.RANDOM);
		range.setFrom(0);
		range.setTo(5);
		range.setQuantity(5);
		eStep.setStep(EElement.ALL);
		date.setFrom(null);
		date.setTo(null);
		status.setMessage(Message.ALL);
		attribute.setOrder(order);
		attribute.setRange(range);
		attribute.setSex(sex);
		attribute.setStatus(status);
		attribute.getAge().set(0, age);
		attribute.getDate().set(0, date);
		attribute.getField().set(0, eStep);
		attribute.getField().set(1, complementaryConducts);
		method.setName(Function.AVERAGE_TIME);
		method.setAttribute(attribute);
		query.setMethod(method);
		
		call(query);
	}
	
	private void call(EQuery eQuery) {
	
	}
}
