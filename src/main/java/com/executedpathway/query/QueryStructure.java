package com.executedpathway.query;

import java.lang.reflect.InvocationTargetException;

import QueryMetamodel.Age;
import QueryMetamodel.Date;
import QueryMetamodel.EAttribute;
import QueryMetamodel.ECarePathway;
import QueryMetamodel.EConduct;
import QueryMetamodel.EMethod;
import QueryMetamodel.EQuery;
import QueryMetamodel.EStep;
import QueryMetamodel.Gender;
import QueryMetamodel.Message;
import QueryMetamodel.Method;
import QueryMetamodel.Order;
import QueryMetamodel.Query_metamodelFactory;
import QueryMetamodel.Range;
import QueryMetamodel.Sex;
import QueryMetamodel.Status;

public class QueryStructure {			
	public void create() {		
		EQuery query = Query_metamodelFactory.eINSTANCE.createEQuery();
		EMethod method = Query_metamodelFactory.eINSTANCE.createEMethod();
		EAttribute attribute = Query_metamodelFactory.eINSTANCE.createEAttribute();	
		Sex sex = Query_metamodelFactory.eINSTANCE.createSex();
		Age age = Query_metamodelFactory.eINSTANCE.createAge();
		Range range = Query_metamodelFactory.eINSTANCE.createRange();
		ECarePathway eCarePathway = Query_metamodelFactory.eINSTANCE.createECarePathway();
		Date date = Query_metamodelFactory.eINSTANCE.createDate();
		Status status = Query_metamodelFactory.eINSTANCE.createStatus();
		
		sex.setSex(Gender.ALL);
		age.setFrom(0);
		age.setTo(0);
		range.setQuantity(5);
		range.setOrder(Order.TOP);
		eCarePathway.getStep().add(EStep.ALL);
		eCarePathway.getConduct().add(EConduct.ALL);
		eCarePathway.setName("Pneumonia & Influenza");
		date.setFrom(null);
		date.setTo(null);
		status.setMessage(Message.ALL);
		status.setValue(true);
		attribute.setRange( range);
		attribute.setSex( sex);
		attribute.setStatus( status);
		attribute.setAge( age);
		attribute.setDate( date);
		attribute.setCarePathway( eCarePathway);
		method.setName(Method.RECURRENCY_FLOW);
		method.setEAttribute(attribute);
		query.setEMethod(method);
		
		call(query);
	}
	
	private void call(EQuery eQuery) {			
		java.lang.reflect.Method method;
		
		try {
			method = QueryMethod.class.getMethod(eQuery.getEMethod().getName() + "");
			method.setAccessible(true);
			try {
				method.invoke(new QueryMethod(eQuery));
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				e.printStackTrace();
			}
		} catch (NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
		}
			
	}
}
