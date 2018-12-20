package com.executedpathway.query;

import QueryMetamodel.Age;
import QueryMetamodel.ComplementaryConducts;
import QueryMetamodel.Conduct;
import QueryMetamodel.Date;
import QueryMetamodel.EAttribute;
import QueryMetamodel.EElement;
import QueryMetamodel.EMethod;
import QueryMetamodel.EQuery;
import QueryMetamodel.EStep;
import QueryMetamodel.Field;
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
		EMethod method = Query_metamodelFactory.eINSTANCE.createEMethod();
		EAttribute attribute = Query_metamodelFactory.eINSTANCE.createEAttribute();	
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
		complementaryConducts.setConduct(Conduct.NONE);
		date.setFrom(null);
		date.setTo(null);
		status.setMessage(Message.ALL);
		attribute.setOrder(order);
		attribute.setRange(range);
		attribute.setSex(sex);
		attribute.setStatus(status);
		attribute.getAge().add( age);
		attribute.getDate().add( date);
		attribute.getComplementaryconducts().add(complementaryConducts);
		attribute.getEstep().add(eStep);
		method.setName(Method.AVERAGE_BY_TIME);
		method.setAttribute(attribute);
		query.setMethod(method);
		
		call(query);
	}
	
	private void call(EQuery eQuery) {
		System.out.println(toString(eQuery));
	}
	
	private String toString(EQuery eQuery) {
		String method = ".";
		
		method += eQuery.getMethod().getName() + "(";
		method += eQuery.getMethod().getAttribute().getAge().get(0).getFrom() + ",";
		method += eQuery.getMethod().getAttribute().getAge().get(0).getTo() + ",";
		method += eQuery.getMethod().getAttribute().getDate().get(0).getFrom() + ",";
		method += eQuery.getMethod().getAttribute().getDate().get(0).getTo() + ",";		
		method += "\"" + eQuery.getMethod().getAttribute().getEstep().get(0).getStep() + "\",";
		method += "\"" + eQuery.getMethod().getAttribute().getComplementaryconducts().get(0).getConduct() + "\",";
		method += "\"" + eQuery.getMethod().getAttribute().getOrder().getOrder() + "\",";
		method += eQuery.getMethod().getAttribute().getRange().getFrom() + ",";
		method += eQuery.getMethod().getAttribute().getRange().getQuantity() + ",";
		method += eQuery.getMethod().getAttribute().getRange().getTo() + ",";
		method += "\"" + eQuery.getMethod().getAttribute().getSex().getSex() + "\",";
		method += "\"" + eQuery.getMethod().getAttribute().getStatus().getMessage() + "\")";
		
		return method;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
