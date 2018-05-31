package domain;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

import javax.persistence.Id;

public abstract class EElement implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private String type;
	private String name;
	private URL url;
	private Boolean isCurrent;
	private Boolean reworked;
	private Boolean executed;
	private Date creationDate;
	private Date modificationDate;
	private Date executionDate;
	private Integer createdById;
	private Integer executedById;
	
	//--------------------
	private Step step;
	
	//--------------------
	private Justification justification;
	
	//--------------------
	private Sequence previous;
	
	//--------------------
	private Sequence next;

	public EElement() {}	

	public EElement(Integer id, String type, String name, URL url, Boolean isCurrent, Boolean reworked,
			Boolean executed, Date creationDate, Date modificationDate, Date executionDate, Integer createdById,
			Integer executedById, Step step, Justification justification, Sequence previous, Sequence next) {
		super();
		this.id = id;
		this.type = type;
		this.name = name;
		this.url = url;
		this.isCurrent = isCurrent;
		this.reworked = reworked;
		this.executed = executed;
		this.creationDate = creationDate;
		this.modificationDate = modificationDate;
		this.executionDate = executionDate;
		this.createdById = createdById;
		this.executedById = executedById;
		this.step = step;
		this.justification = justification;
		this.previous = previous;
		this.next = next;
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

	public URL getUrl() {
		return url;
	}

	public void setUrl(URL url) {
		this.url = url;
	}

	public Boolean getIsCurrent() {
		return isCurrent;
	}

	public void setIsCurrent(Boolean isCurrent) {
		this.isCurrent = isCurrent;
	}

	public Boolean getReworked() {
		return reworked;
	}

	public void setReworked(Boolean reworked) {
		this.reworked = reworked;
	}

	public Boolean getExecuted() {
		return executed;
	}

	public void setExecuted(Boolean executed) {
		this.executed = executed;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getModificationDate() {
		return modificationDate;
	}

	public void setModificationDate(Date modificationDate) {
		this.modificationDate = modificationDate;
	}

	public Date getExecutionDate() {
		return executionDate;
	}

	public void setExecutionDate(Date executionDate) {
		this.executionDate = executionDate;
	}

	public Integer getCreatedById() {
		return createdById;
	}

	public void setCreatedById(Integer createdById) {
		this.createdById = createdById;
	}

	public Integer getExecutedById() {
		return executedById;
	}

	public void setExecutedById(Integer executedById) {
		this.executedById = executedById;
	}

	public Step getStep() {
		return step;
	}

	public void setStep(Step step) {
		this.step = step;
	}

	public Justification getJustification() {
		return justification;
	}

	public void setJustification(Justification justification) {
		this.justification = justification;
	}

	public Sequence getPrevious() {
		return previous;
	}

	public void setPrevious(Sequence previous) {
		this.previous = previous;
	}

	public Sequence getNext() {
		return next;
	}

	public void setNext(Sequence next) {
		this.next = next;
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
		EElement other = (EElement) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
