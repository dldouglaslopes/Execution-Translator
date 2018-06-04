package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Complement implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;

	private String name;
	private String sideLimb;
	private Integer quantity;
	private String justification;
	private String clinicalIndication;
	
	public Complement() {}

	public Complement(Integer id, String name, String sideLimb, Integer quantity, String justification,
			String clinicalIndication) {
		super();
		this.id = id;
		this.name = name;
		this.sideLimb = sideLimb;
		this.quantity = quantity;
		this.justification = justification;
		this.clinicalIndication = clinicalIndication;
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

	public String getSideLimb() {
		return sideLimb;
	}

	public void setSideLimb(String sideLimb) {
		this.sideLimb = sideLimb;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getJustification() {
		return justification;
	}

	public void setJustification(String justification) {
		this.justification = justification;
	}

	public String getClinicalIndication() {
		return clinicalIndication;
	}

	public void setClinicalIndication(String clinicalIndication) {
		this.clinicalIndication = clinicalIndication;
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
		Complement other = (Complement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
