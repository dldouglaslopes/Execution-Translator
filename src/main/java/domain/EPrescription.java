package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class EPrescription extends EElement {
	private static final long serialVersionUID = 1L;

	private String text;

	//---------------
	private List<PrescribedPrescriptionItem> prescribedPrescriptionItens = new ArrayList<>();
	private List<PrescribedMedication> prescribedMedications = new ArrayList<>();
	private List<Prescription> prescription = new ArrayList<>();
	
	public EPrescription() {}
	
	public EPrescription(String text) {
		super();
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<PrescribedPrescriptionItem> getPrescribedPrescriptionItens() {
		return prescribedPrescriptionItens;
	}

	public void setPrescribedPrescriptionItens(List<PrescribedPrescriptionItem> prescribedPrescriptionItens) {
		this.prescribedPrescriptionItens = prescribedPrescriptionItens;
	}

	public List<PrescribedMedication> getPrescribedMedications() {
		return prescribedMedications;
	}

	public void setPrescribedMedications(List<PrescribedMedication> prescribedMedications) {
		this.prescribedMedications = prescribedMedications;
	}

	public List<Prescription> getPrescription() {
		return prescription;
	}

	public void setPrescription(List<Prescription> prescription) {
		this.prescription = prescription;
	}
	

}
