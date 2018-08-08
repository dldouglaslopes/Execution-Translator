package com.executedpathway.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class ETreatment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private List<PrescribedExamination> prescribedExaminations = new ArrayList<>();
	
	private List<PrescribedProcedure> prescribedProcedures = new ArrayList<>();
	
	private List<PrescribedInternment> prescribedInternments = new ArrayList<>();
	
	private List<PrescribedMedication> prescribedMedications = new ArrayList<>();

	public ETreatment() {}

	public List<PrescribedExamination> getPrescribedExaminations() {
		return prescribedExaminations;
	}

	public void setPrescribedExaminations(List<PrescribedExamination> prescribedExaminations) {
		this.prescribedExaminations = prescribedExaminations;
	}

	public List<PrescribedProcedure> getPrescribedProcedures() {
		return prescribedProcedures;
	}

	public void setPrescribedProcedures(List<PrescribedProcedure> prescribedProcedures) {
		this.prescribedProcedures = prescribedProcedures;
	}

	public List<PrescribedInternment> getPrescribedInternments() {
		return prescribedInternments;
	}

	public void setPrescribedInternments(List<PrescribedInternment> prescribedInternments) {
		this.prescribedInternments = prescribedInternments;
	}

	public List<PrescribedMedication> getPrescribedMedications() {
		return prescribedMedications;
	}

	public void setPrescribedMedications(List<PrescribedMedication> prescribedMedications) {
		this.prescribedMedications = prescribedMedications;
	}
}
