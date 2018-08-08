package com.executedpathway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Medicine implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String name;
	private Integer code;
	private Boolean outpatient;
	private String brand;	
	private String description;
	
	public Medicine() {}

	public Medicine(Integer id, String name, Integer code, Boolean outpatient, String brand, String description) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.outpatient = outpatient;
		this.brand = brand;
		this.description = description;
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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public Boolean getOutpatient() {
		return outpatient;
	}

	public void setOutpatient(Boolean outpatient) {
		this.outpatient = outpatient;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
		Medicine other = (Medicine) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
