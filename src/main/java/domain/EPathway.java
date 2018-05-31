package domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EPathway implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;

	private String name;
	private Date creationDate;
	private Date conclusionDate;
	private Boolean aborted;
	private Boolean completed;
	
	//-------------------------
	private Pathway pathway;
	
	//-------------------------
	private Justification justification;
	
	private Integer idLastProfessional;
	private Integer idResponsible;
	
	//-------------------------
	private List<Step> executedSteps = new ArrayList<>();
	
	//-------------------------
	private List<EElement> elements = new ArrayList<>();
	
	public EPathway() {}

	public EPathway(Integer id, String name, Date creationDate, Date conclusionDate, Boolean aborted,
			Boolean completed, Pathway pathway, Integer idLastProfessional, Integer idResponsible, Justification justification) {
		super();
		this.id = id;
		this.name = name;
		this.creationDate = creationDate;
		this.conclusionDate = conclusionDate;
		this.aborted = aborted;
		this.completed = completed;
		this.pathway = pathway;
		this.idLastProfessional = idLastProfessional;
		this.idResponsible = idResponsible;
		this.justification = justification;
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

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getConclusionDate() {
		return conclusionDate;
	}

	public void setConclusionDate(Date conclusionDate) {
		this.conclusionDate = conclusionDate;
	}

	public Boolean getAborted() {
		return aborted;
	}

	public void setAborted(Boolean aborted) {
		this.aborted = aborted;
	}

	public Boolean getCompleted() {
		return completed;
	}

	public void setCompleted(Boolean completed) {
		this.completed = completed;
	}

	public Pathway getPathway() {
		return pathway;
	}

	public void setIdPathway(Pathway pathway) {
		this.pathway = pathway;
	}

	public Integer getIdLastProfessional() {
		return idLastProfessional;
	}

	public void setIdLastProfessional(Integer idLastProfessional) {
		this.idLastProfessional = idLastProfessional;
	}

	public Integer getIdResponsible() {
		return idResponsible;
	}

	public void setIdResponsible(Integer idResponsible) {
		this.idResponsible = idResponsible;
	}

	public List<Step> getExecutedSteps() {
		return executedSteps;
	}

	public void setIdsExecutedStep(List<Step> executedSteps) {
		this.executedSteps = executedSteps;
	}

	public Justification getJustification() {
		return justification;
	}

	public void setJustification(Justification justification) {
		this.justification = justification;
	}

	public List<EElement> getElements() {
		return elements;
	}

	public void setElements(List<EElement> elements) {
		this.elements = elements;
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
		EPathway other = (EPathway) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	
}
