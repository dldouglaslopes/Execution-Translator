package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Justification implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private String reason;
	private String description;
	private Integer justifiedById;
	private String justifiedBy;
	
	public Justification() {}

	public Justification(Integer id, String reason, String description, Integer justifiedById, String justifiedBy) {
		super();
		this.id = id;
		this.reason = reason;
		this.description = description;
		this.justifiedById = justifiedById;
		this.justifiedBy = justifiedBy;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getJustifiedById() {
		return justifiedById;
	}

	public void setJustifiedById(Integer justifiedById) {
		this.justifiedById = justifiedById;
	}

	public String getJustifiedBy() {
		return justifiedBy;
	}

	public void setJustifiedBy(String justifiedBy) {
		this.justifiedBy = justifiedBy;
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
		Justification other = (Justification) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
