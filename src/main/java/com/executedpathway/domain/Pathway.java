package com.executedpathway.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pathway implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;

	private String code;
	private String name;
	private Integer version;
	private Boolean completed;
	private Integer idRepository;
	
	public Pathway() {}

	public Pathway(Integer id, String code, String name, Integer version, Boolean completed,
			Integer idRepository) {
		super();
		this.id = id;
		this.code = code;
		this.name = name;
		this.version = version;
		this.completed = completed;
		this.idRepository = idRepository;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Integer getIdRepository() {
		return idRepository;
	}

	public void setIdRepository(Integer idRepository) {
		this.idRepository = idRepository;
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
		Pathway other = (Pathway) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
