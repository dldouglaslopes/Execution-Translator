package com.executedpathway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Exam implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;

	private String name;
	private Integer code;
	private String description;
	private Boolean onlyEmergency;
	private Boolean memberPeers;

	public Exam() {}

	public Exam(Integer id, String name, Integer code, String description, Boolean onlyEmergency, Boolean memberPeers) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.description = description;
		this.onlyEmergency = onlyEmergency;
		this.memberPeers = memberPeers;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getOnlyEmergency() {
		return onlyEmergency;
	}

	public void setOnlyEmergency(Boolean onlyEmergency) {
		this.onlyEmergency = onlyEmergency;
	}

	public Boolean getMemberPeers() {
		return memberPeers;
	}

	public void setMemberPeers(Boolean memberPeers) {
		this.memberPeers = memberPeers;
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
		Exam other = (Exam) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
