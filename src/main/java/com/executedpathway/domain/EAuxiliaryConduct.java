package com.executedpathway.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class EAuxiliaryConduct extends EElement{
	private static final long serialVersionUID = 1L;
	
	private List<Answer> answers = new ArrayList<>();
	
	public EAuxiliaryConduct() {
		super();
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}
}
