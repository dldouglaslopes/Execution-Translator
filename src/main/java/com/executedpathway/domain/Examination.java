package com.executedpathway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Examination implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;

	private String name;
	private Integer quantity;
	private String sideLimb;
 	private String justification;
	private Boolean clinicalIndication;

	private Exam exam;
	
	public Examination() {}

	public Examination(Integer id, String name, Integer quantity, String sideLimb, String justification,
			Boolean clinicalIndication, Exam exam) {
		super();
		this.id = id;
		this.name = name;
		this.quantity = quantity;
		this.sideLimb = sideLimb;
		this.justification = justification;
		this.clinicalIndication = clinicalIndication;
		this.exam = exam;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getSideLimb() {
		return sideLimb;
	}

	public void setSideLimb(String sideLimb) {
		this.sideLimb = sideLimb;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public Boolean getClinicalIndication() {
		return clinicalIndication;
	}

	public void setClinicalIndication(Boolean clinicalIndication) {
		this.clinicalIndication = clinicalIndication;
	}

	public Exam getExam() {
		return exam;
	}

	public void setExam(Exam exam) {
		this.exam = exam;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Examination other = (Examination) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
