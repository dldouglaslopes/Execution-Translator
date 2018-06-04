package domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PrescribedExamination implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	private Integer id;
	
	private String name;
	private String report;
	private String numberGuide;
	private String result;
	
	private Complement complement;
	private Examination examination;
	private Prescription prescription;
	
	public PrescribedExamination() {}

	public PrescribedExamination(Integer id, String name, String report, String numberGuide, String result,
			Complement complement, Examination examination, Prescription prescription) {
		super();
		this.id = id;
		this.name = name;
		this.report = report;
		this.numberGuide = numberGuide;
		this.result = result;
		this.complement = complement;
		this.examination = examination;
		this.prescription = prescription;
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

	public String getReport() {
		return report;
	}

	public void setReport(String report) {
		this.report = report;
	}

	public String getNumberGuide() {
		return numberGuide;
	}

	public void setNumberGuide(String numberGuide) {
		this.numberGuide = numberGuide;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Complement getComplement() {
		return complement;
	}

	public void setComplement(Complement complement) {
		this.complement = complement;
	}

	public Examination getExamination() {
		return examination;
	}

	public void setExamination(Examination examination) {
		this.examination = examination;
	}

	public Prescription getPrescription() {
		return prescription;
	}

	public void setPrescription(Prescription prescription) {
		this.prescription = prescription;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
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
		PrescribedExamination other = (PrescribedExamination) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
}
