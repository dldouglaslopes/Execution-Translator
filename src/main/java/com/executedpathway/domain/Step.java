package com.executedpathway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Step implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;

	private String type;
	private String name;
	private String description;
	private Boolean isInitial;
	private Boolean isTerminal;
	private Boolean mandatory;
	
	public Step() {}

	public Step(Integer id, String type, String name, String description, Boolean isInitial,
			Boolean isTerminal, Boolean mandatory) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.description = description;
		this.isInitial = isInitial;
		this.isTerminal = isTerminal;
		this.mandatory = mandatory;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsInitial() {
		return isInitial;
	}

	public void setIsInitial(Boolean isInitial) {
		this.isInitial = isInitial;
	}

	public Boolean getIsTerminal() {
		return isTerminal;
	}

	public void setIsTerminal(Boolean isTerminal) {
		this.isTerminal = isTerminal;
	}

	public Boolean getMandatory() {
		return mandatory;
	}

	public void setMandatory(Boolean mandatory) {
		this.mandatory = mandatory;
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
		Step other = (Step) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
