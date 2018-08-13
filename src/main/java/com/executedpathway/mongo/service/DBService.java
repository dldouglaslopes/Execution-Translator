package com.executedpathway.mongo.service;

import java.text.ParseException;

import java.text.SimpleDateFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.executedpathway.mongo.domain.Access;
import com.executedpathway.mongo.domain.EPathway;
import com.executedpathway.mongo.domain.EPrescription;
import com.executedpathway.mongo.domain.Justification;
import com.executedpathway.mongo.domain.Medication;
import com.executedpathway.mongo.domain.Medicine;
import com.executedpathway.mongo.domain.Pathway;
import com.executedpathway.mongo.domain.PrescribedMedication;
import com.executedpathway.mongo.domain.Prescription;
import com.executedpathway.mongo.domain.Step;
import com.executedpathway.mongo.domain.Unit;
import com.executedpathway.mongo.repository.AccessRepository;
import com.executedpathway.mongo.repository.AnswerRepository;
import com.executedpathway.mongo.repository.BondRepository;
import com.executedpathway.mongo.repository.ComplementRepository;
import com.executedpathway.mongo.repository.EPathwayRepository;
import com.executedpathway.mongo.repository.ExElementRepository;
import com.executedpathway.mongo.repository.ExamRepository;
import com.executedpathway.mongo.repository.ExaminationRepository;
import com.executedpathway.mongo.repository.JustificationRepository;
import com.executedpathway.mongo.repository.MedicationRepository;
import com.executedpathway.mongo.repository.MedicineRepository;
import com.executedpathway.mongo.repository.PathwayRepository;
import com.executedpathway.mongo.repository.PrescribedExaminationRepository;
import com.executedpathway.mongo.repository.PrescribedIntermentRepository;
import com.executedpathway.mongo.repository.PrescribedMedicationRepository;
import com.executedpathway.mongo.repository.PrescribedProcedureRepository;
import com.executedpathway.mongo.repository.PrescriptionRepository;
import com.executedpathway.mongo.repository.QuestionRepository;
import com.executedpathway.mongo.repository.StepRepository;
import com.executedpathway.mongo.repository.UnitRepository;
import com.executedpathway.mongo.repository.VariableRepository;

@Service
public class DBService {	

	@Autowired
	private EPathwayRepository ePathwayRepository;	
	@Autowired
	private JustificationRepository justificationRepository;
	@Autowired
	private PathwayRepository pathwayRepository;
	@Autowired
	private AccessRepository accessRepository;
	@Autowired
	private AnswerRepository answerRepository;
	@Autowired
	private BondRepository bondRepository;
	@Autowired
	private ComplementRepository complementRepository;
	@Autowired
	private ExaminationRepository examinationRepository;	
	@Autowired
	private ExamRepository examRepository;	
	@Autowired
	private ExElementRepository elementRepository;	
	@Autowired
	private MedicationRepository medicationRepository;	
	@Autowired
	private MedicineRepository medicineRepository;	
	@Autowired
	private PrescribedExaminationRepository prescribedExaminationRepository;
	@Autowired
	private PrescribedMedicationRepository prescribedMedicationRepository;
	@Autowired
	private PrescribedProcedureRepository procedureRepository;
	@Autowired
	private PrescribedIntermentRepository intermentRepository;
	@Autowired
	private PrescriptionRepository prescriptionRepository;
	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private StepRepository stepRepository;
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private VariableRepository variableRepository;
	
	public void instatiateTestDatabase() throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");		
		
		//ROOT
		/*Justification justification = new Justification(null, 1, "reason", "description", 1);
		Pathway pathway = new Pathway(null, 1, "1", "name", 1, true);		
		EPathway ePathway = new EPathway(null, 1, "name", format.parse("10/11/1942"), format.parse("10/11/1942"), true, true, pathway, 1, 1, justification);
		
		pathway.getePathways().add(ePathway);
		justification.getePathways().add(ePathway);
		
		pathwayRepository.save(pathway);
		justificationRepository.save(justification);
		ePathwayRepository.save(ePathway);
		
		//TREATMENT
		Step step = new Step(null, 1, "type", "name", "description", true, true, true);			
		Access access = new Access();
		access.setId(1);
		access.setCode(1);
		access.setName("ddwd");
		
		Unit unit = new Unit(null, 1, "name", 1, "unit");		
		Medicine medicine = new Medicine(null, 1, "name", 1, true, "brand", "description");		
		Medication medication = new Medication(null, 1, "name", 1, true, "brand", "description", "standard", 1, 1, 1, 1, 1, unit, medicine, access);		
		Prescription prescription = new Prescription(null, 1, true, "message", format.parse("10/11/1942"));		
		Justification justification2 = new Justification(null, 1, "reason", "description", 1);
		EPrescription ePrescription = new EPrescription(null, 1, "type", "name", null, true, true, true, format.parse("10/11/1942"), format.parse("10/11/1942"), format.parse("10/11/1942"), 1, 1, step, justification2, ePathway, "text", prescription);		
		PrescribedMedication prescribedMedication = new PrescribedMedication(null, 1, "result", medication, prescription, ePrescription, null);
		
		step.getElements().add(ePrescription);
		access.getMedications().add(medication);
		unit.getMedications().add(medication);
		medicine.getMedications().add(medication);
		medication.getPrescribedMedications().add(prescribedMedication);
		prescription.getePrescriptions().add(ePrescription);
		prescription.getPrescribedExaminations().add(null);
		prescription.getPrescribedMedications().add(prescribedMedication);
		justification.getElements().add(ePrescription);
		ePrescription.getPrescribedMedications().add(prescribedMedication);
		ePathway.getElements().add(ePrescription);	
		
		stepRepository.save(step);
		accessRepository.save(access);
		unitRepository.save(unit);
		medicineRepository.save(medicine);
		medicationRepository.save(medication);
		prescriptionRepository.save(prescription);		
		justificationRepository.save(justification2);
		elementRepository.save(ePrescription);
		prescribedMedicationRepository.save(prescribedMedication);*/
		
		//AUXILIARY CONDUCT
		//Step step2 = new Step(2, 2, "type", "name", "description", true, true, true);				
		
		//PRESCRIPTION
		//Step step3 = new Step(3, 3, "type", "name", "description", true, true, true);	
	
	}
}

