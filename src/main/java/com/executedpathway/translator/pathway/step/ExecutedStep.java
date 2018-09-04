package com.executedpathway.translator.pathway.step;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;

import com.executedpathway.translator.mongo.domain.Access;
import com.executedpathway.translator.mongo.domain.Answer;
import com.executedpathway.translator.mongo.domain.Bond;
import com.executedpathway.translator.mongo.domain.Complement;
import com.executedpathway.translator.mongo.domain.EAuxiliaryConduct;
import com.executedpathway.translator.mongo.domain.EDischarge;
import com.executedpathway.translator.mongo.domain.EInformation;
import com.executedpathway.translator.mongo.domain.EPrescription;
import com.executedpathway.translator.mongo.domain.EReferral;
import com.executedpathway.translator.mongo.domain.ETreatment;
import com.executedpathway.translator.mongo.domain.Exam;
import com.executedpathway.translator.mongo.domain.Examination;
import com.executedpathway.translator.mongo.domain.Justification;
import com.executedpathway.translator.mongo.domain.Medication;
import com.executedpathway.translator.mongo.domain.Medicine;
import com.executedpathway.translator.mongo.domain.PrescribedExamination;
import com.executedpathway.translator.mongo.domain.PrescribedInternment;
import com.executedpathway.translator.mongo.domain.PrescribedMedication;
import com.executedpathway.translator.mongo.domain.PrescribedPrescriptionItem;
import com.executedpathway.translator.mongo.domain.PrescribedProcedure;
import com.executedpathway.translator.mongo.domain.Prescription;
import com.executedpathway.translator.mongo.domain.Question;
import com.executedpathway.translator.mongo.domain.Step;
import com.executedpathway.translator.mongo.domain.Unit;
import com.executedpathway.translator.mongo.domain.Variable;
import com.executedpathway.translator.mongo.repository.JustificationRepository;

import MetamodelExecution.Execution_metamodelFactory;
import MetamodelExecution.Numeric;
import MetamodelExecution.YesOrNo;
import MetamodelExecution.impl.EElementImpl;

public class ExecutedStep {
	@Autowired
	private JustificationRepository justificationRepository;
	
	public EElementImpl createEElement(JSONObject json, EElementImpl eElement) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.getDefault());
		
		//set justification
		if (!json.isNull("justificativa")) {	
			JSONObject justificationJson = json.getJSONObject("justificativa");
			
			Justification justification = new Justification();
			justification.setId(justificationJson.getInt("id"));
			justification.setReason(justificationJson.getString("razao"));
			justification.setReasonDisplay(justificationJson.getString("razao_display"));
			justification.setDescription(justificationJson.getString("descricao"));
			justification.setJustifiedById(justificationJson.getInt("justificado_por_id"));
			justification.setJustifiedBy(justificationJson.getString("justificado_por"));
						
			justificationRepository.save(justification);
			eElement.setJustification(justification);
		}			
		
		//set step
		JSONObject stepJson = json.getJSONObject("passo");
		Step step = new Step();
		step.setId(stepJson.getInt("id"));
		step.setType(stepJson.getString("type"));
		step.setTypeVerbose(stepJson.getString("type_verbose"));
		step.setUrl(stepJson.getString("url"));
		step.setName(stepJson.getString("nome"));
		step.setDescription(stepJson.getString("descricao"));
		step.setIsInitial(stepJson.getBoolean("is_initial"));
		step.setIsTerminal(stepJson.getBoolean("is_terminal"));
		step.setMandatory(stepJson.getBoolean("obrigatoriedade"));	
		
		//Set executed element/step
		eElement.setId(json.getInt("id"));
		eElement.setType(json.getString("type"));
		eElement.setTypeVerbose(json.getString("type_verbose"));
		eElement.setUrl(json.getString("url"));
		eElement.setIsCurrent(json.getBoolean("is_current"));
		eElement.setReworked(json.getBoolean("reworked"));
		eElement.setExecuted(json.getBoolean("executado"));		
		eElement.setCreatedById(json.getInt("criado_por_id"));		
		eElement.setIdStep(json.getInt("passo_id"));		
		eElement.setStep(step);
//		eElement.setCreator(creator);		
//		eElement.setExecutor(executor);
		eElement.setName(eElement.getStep().getName());			       
		
		if (!json.isNull("executado_por_id")) {
			eElement.setExecutedById(json.getInt("executado_por_id"));
		}	
			
		String creationStr = json.getString("data_criacao");
		Date creationDate = dateFormat.parse(creationStr);
		eElement.setCreationDate(creationDate);		
		
		String modificationStr = json.getString("data_modificacao");
		Date modificationDate = dateFormat.parse(modificationStr);
		eElement.setModificationDate(modificationDate);
		
		if (!json.isNull("data_execucao")) {
			String executionStr = json.getString("data_execucao");
			Date executionDate = dateFormat.parse(executionStr);
			eElement.setExecutionDate(executionDate);
		}		
		
		/*
		//set executor
		Executor executor = Execution_metamodelFactory.eINSTANCE.createExecutor();		
		if (!json.isNull("executado_por")) {
			JSONObject executorJson = json.getJSONObject("executado_por");
			executor.setId(executorJson.getInt("id"));
			executor.setUrl(executorJson.getString("url"));
			executor.setCode(executorJson.getInt("codigo"));
			executor.setEmail(executorJson.getString("email"));
			executor.setLogin(executorJson.getString("login"));
			executor.setName(executorJson.getString("nome"));
			executor.setNumberCouncil(executorJson.getInt("numero_conselho"));
			executor.setTypeCouncil(executorJson.getString("tipo_conselho"));
			executor.setState(executorJson.getString("uf"));
		}
		
		//set creator
		JSONObject creatorJson = json.getJSONObject("criado_por");
		Creator creator = Execution_metamodelFactory.eINSTANCE.createCreator();
		creator.setId(creatorJson.getInt("id"));
		creator.setUrl(creatorJson.getString("url"));
		creator.setCode(creatorJson.getInt("codigo"));
		creator.setEmail(creatorJson.getString("email"));
		creator.setLogin(creatorJson.getString("login"));
		creator.setName(creatorJson.getString("nome"));
		creator.setNumberCouncil(creatorJson.getInt("numero_conselho"));
		creator.setTypeCouncil(creatorJson.getString("tipo_conselho"));
		creator.setState(creatorJson.getString("uf"));	
		*/
		
		return eElement;
	}
	
	public EAuxiliaryConduct createEAuxiliaryConduct(JSONObject json, Resource resource) throws ParseException{
		//Set executed auxiliary conduct
		EAuxiliaryConduct eAuxiliaryConduct = new EAuxiliaryConduct();
		eAuxiliaryConduct = (EAuxiliaryConduct) createEElement(json, eAuxiliaryConduct);
		
		//Set answers
		JSONArray answers = json.getJSONArray("respostas");
		
		for (int i = 0; i < answers.length(); i++) {
			//set answer
			JSONObject answerJson = answers.getJSONObject(i);
			Answer answer = new Answer();			
			answer.setId(answerJson.getInt("id"));
			answer.setType(answerJson.getString("type"));
			answer.setTypeVerbose(answerJson.getString("type_verbose"));	
			
			String type = answerJson.getString("type");		
			
			if (type.equals("RespostaSimOuNao")) {
				//set yes or no
				YesOrNo yesOrNo = Execution_metamodelFactory.eINSTANCE.createYesOrNo();
				yesOrNo.setValue(answerJson.getBoolean("valor"));				
				//save value
				answer.setValue(yesOrNo);
			}
			else if (type.equals("RespostaNumerica")) {
				//set numeric
				Numeric numeric = Execution_metamodelFactory.eINSTANCE.createNumeric();
				numeric.setValue(answerJson.getDouble("valor"));				
				//save value
				answer.setValue(numeric);
			}	
			
			//set question
			JSONObject questionJson = answerJson.getJSONObject("pergunta");
			Question question = new Question();			
			question.setId(questionJson.getInt("id"));
			question.setName(questionJson.getString("texto"));
			question.setUrl(questionJson.getString("url"));	
			
			if (!questionJson.isNull("categoria")) {
				question.setCategory(questionJson.getString("categoria"));
			}  
			
			if (!questionJson.isNull("categoria_id")) {
				question.setIdCategory(questionJson.getInt("categoria_id"));
			}	
			
			JSONObject variableJson = questionJson.getJSONObject("variavel");
			Variable variable = new Variable();
			variable.setId(variableJson.getInt("id"));
			variable.setUrl(json.getString("url"));
			variable.setType(json.getString("type"));
			variable.setTypeVerbose(json.getString("type_verbose"));
			variable.setName(variableJson.getString("nome"));
			variable.setWeight(variableJson.getInt("peso"));			
			
			if (type.equals("RespostaSimOuNao")) {			
				//set yes or no
				YesOrNo yesOrNo = Execution_metamodelFactory.eINSTANCE.createYesOrNo();
				yesOrNo.setValue(variableJson.getBoolean("valor"));					
				//save yes or no
				variable.setValue(yesOrNo);			}
			else if (type.equals("RespostaNumerica")) {
				//set numeric
				Numeric numeric = Execution_metamodelFactory.eINSTANCE.createNumeric();
				numeric.setValue(variableJson.getDouble("valor"));				
				//save numeric
				variable.setValue(numeric);
			}				
			
			Bond bond = new Bond();
			if (!variableJson.isNull("vinculo")) {
				JSONObject bondJson = variableJson.getJSONObject("vinculo");
				bond.setId(bondJson.getInt("id"));
				bond.setType(bondJson.getString("type"));
				bond.setTypeVerbose(bondJson.getString("type_verbose"));
				bond.setTruePremise(bondJson.getString("premissa_verdadeira"));
				bond.setTruePremiseDisplay(bondJson.getString("premissa_verdadeira_display"));
			}
			//save bond
			variable.setBond(bond);
			
			//save variable
			question.setVariable(variable);			
			//save question
			answer.setQuestion(question);			
			//save answer
			eAuxiliaryConduct.getAnswer().add(answer);
		}		
		
		return eAuxiliaryConduct;
	}
	
	public ETreatment createETreatment(JSONObject json, Resource resource) throws ParseException{
		//set executed treatment
		ETreatment eTreatment = new ETreatment();		
		eTreatment = (ETreatment) createEElement(json, eTreatment);	
		
		//set prescribed examination
		JSONArray idsPrescribedExaminationJson = json.getJSONArray("exames_prescritos_ids");		
		for (int i = 0; i < idsPrescribedExaminationJson.length(); i++) {
			//save prescribed examination
			eTreatment.getIdsPrescribedExamination().add(idsPrescribedExaminationJson.optInt(i));
		}
		
		//set prescribed  procedure
		JSONArray idsPrescribedProceduresJson = json.getJSONArray("procedimentos_prescritos_ids");		
		for (int i = 0; i < idsPrescribedProceduresJson.length(); i++) {
			//save prescribed  procedure
			eTreatment.getIdsPrescribedProcedure().add(idsPrescribedProceduresJson.optInt(i));
		}
		
		//set prescribed internment
		JSONArray idsPrescribedInternmentJson = json.getJSONArray("internamentos_prescritos_ids");		
		for (int i = 0; i < idsPrescribedInternmentJson.length(); i++) {
			//save prescribed internment
			eTreatment.getIdsPrescribedInternment().add(idsPrescribedInternmentJson.optInt(i));
		}
		
		//set prescribed medication 
		JSONArray idsPrescribedMedicationJson = json.getJSONArray("medicamentos_prescritos_ids");		
		for (int i = 0; i < idsPrescribedMedicationJson.length(); i++) {
			//save prescribed medication 
			eTreatment.getIdsPrescribedMedication().add(idsPrescribedMedicationJson.optInt(i));
		}	
		
		//save prescribed medication
		eTreatment.getPrescribedmedication().addAll(createPrescribedMedication(json));
		
		//set prescribed examination
		JSONArray prescribedExaminations = json.getJSONArray("exames_prescritos");			
		for (int i = 0; i < prescribedExaminations.length(); i++) {			
			PrescribedExamination prescribedExamination = new PrescribedExamination();
			JSONObject prescribedExaminationJson = prescribedExaminations.getJSONObject(i);
			prescribedExamination.setId(prescribedExaminationJson.getInt("id"));
			prescribedExamination.setReport(prescribedExaminationJson.getString("laudo"));
			
			if (!prescribedExaminationJson.isNull("numero_guia")) {
				prescribedExamination.setNumberGuide(prescribedExaminationJson.getInt("numero_guia"));
			}			
			
			if (!prescribedExaminationJson.isNull("resultado")) {
				prescribedExamination.setResult(prescribedExaminationJson.getString("resultado"));
			}						
			
			//save prescription 
			prescribedExamination.setPrescription(createPrescription(prescribedExaminationJson));
			
			//set complement
			Complement complement = new Complement();
			if (!prescribedExaminationJson.isNull("complemento")) {				
				JSONObject complementJson = prescribedExaminationJson.getJSONObject("complemento");
				complement.setId(complementJson.getInt("id"));
				complement.setSideLimb(complementJson.getString("lado_membro"));
				complement.setSideLimbDisplay(complementJson.getString("lado_membro_display"));
				complement.setJustification(complementJson.getString("justificativa"));
				complement.setClinicalIndication(complementJson.getString("indicacao_clinica"));
				
				if (!complementJson.isNull("quantidade")) {
					complement.setQuantity(complementJson.getInt("quantidade"));
				}			
			}
			//save complement	
			prescribedExamination.setComplement(complement);
			
			//set examination
			Examination examination = new Examination();
			JSONObject examinationJson = prescribedExaminationJson.getJSONObject("exame");
			examination.setId(examinationJson.getInt("id"));
			examination.setUrl(examinationJson.getString("url"));
			examination.setIdExamination(examinationJson.getInt("exame_id"));
			examination.setSideLimb(examinationJson.getString("lado_membro"));
			examination.setQuantity(examinationJson.getInt("quantidade"));
			examination.setJustification(examinationJson.getString("justificativa"));
			examination.setClinicalIndication(examinationJson.getString("indicacao_clinica"));
			examination.setSideLimbDisplay(examinationJson.getString("lado_membro_display"));
			
			//set exam
			Exam exam = new Exam();
			JSONObject examJson = examinationJson.getJSONObject("exame");
			exam.setId(examJson.getInt("id"));
			exam.setUrl(examJson.getString("url"));
			exam.setCode(examJson.getInt("codigo"));
			exam.setName(examJson.getString("nome"));
			exam.setDescription(examJson.getString("descricao"));
			exam.setOnlyEmergency(examJson.getBoolean("somente_emergencia"));
			exam.setMemberPeers(examJson.getBoolean("membros_pares"));
			
			//save exam
			examination.setExam(exam);
			
			//save examination
			prescribedExamination.setExamination(examination);
			
			//save prescribed examination
			eTreatment.getPrescribedexam().add(prescribedExamination);
		}	
		
		//set prescribed procedure
		JSONArray prescribedProcedures = json.getJSONArray("procedimentos_prescritos");
		for (int i = 0; i < prescribedProcedures.length(); i++) {			
			PrescribedProcedure prescribedProcedure = new PrescribedProcedure();
			//save prescribed procedure
			eTreatment.getPrescribedprocedure().add(prescribedProcedure);
		}
		
		//set prescribed internment
		JSONArray prescribedInternments = json.getJSONArray("internamentos_prescritos");
		for (int i = 0; i < prescribedInternments.length(); i++) {			
			PrescribedInternment prescribedInternment = new PrescribedInternment();
			//save prescribed internment
			eTreatment.getPrescribedinternment().add(prescribedInternment);
		}		
		
		return eTreatment;
	}

	//set executed precription
	public EPrescription createEPrescription(JSONObject json, Resource resource) throws ParseException{		
		EPrescription ePrescription = new EPrescription();
		ePrescription = (EPrescription) createEElement(json, ePrescription);
		
		ePrescription.setText(json.getString("texto"));
		
		//set ids prescribed medication 
		JSONArray idsPrescribedMedicationJson = json.getJSONArray("medicamentos_prescritos_ids");		
		for (int i = 0; i < idsPrescribedMedicationJson.length(); i++) {
			//save prescribed medication 
			ePrescription.getIdsPrescribedMedication().add(idsPrescribedMedicationJson.optInt(i));
		}
		
		//set ids prescribed prescription item
		JSONArray idsPrescribedPrescriptionItemJson = json.getJSONArray("medicamentos_prescritos_ids");		
		for (int i = 0; i < idsPrescribedPrescriptionItemJson.length(); i++) {
			//save prescribed PrescriptionItem 
			ePrescription.getIdsPrescribedPrescriptionItem().add(idsPrescribedPrescriptionItemJson.optInt(i));
		}
		
		//save prescription info
		ePrescription.getPrescription().add(createPrescription(json));
		
		//set prescription prescription item
		JSONArray prescribedPrescriptionItens = json.getJSONArray("itens_receita_prescritos");
		for (int i = 0; i < prescribedPrescriptionItens.length(); i++) {			
			PrescribedPrescriptionItem prescribedPrescriptionItem = new PrescribedPrescriptionItem();;
			//save prescription prescription item
			ePrescription.getPrescribedprescriptionitem().add(prescribedPrescriptionItem);
		}
		
		//save prescription medication
		List<PrescribedMedication> prescribedMedications = createPrescribedMedication(json);		
		for (int i = 0; i < prescribedMedications.size(); i++) {
			ePrescription.getPrescribedmedication().add(prescribedMedications.get(i));
		}		
		
		return ePrescription;
	}

	//set executed information
	public EInformation createEInformation(JSONObject json, Resource resource) throws ParseException{		
		EInformation eInformation = new EInformation();
		eInformation = (EInformation) createEElement(json, eInformation);
		
		return eInformation;
	}
	
	//set executed referral
	public EReferral createEReferral(JSONObject json, Resource resource) throws ParseException{		
		EReferral eReferral = new EReferral();
		eReferral = (EReferral) createEElement(json, eReferral);
		
		return eReferral;
	}
	
	//set executed discharge
	public EDischarge createEDischarge(JSONObject json, Resource resource) throws ParseException{
		EDischarge eDischarge = new EDischarge();
		eDischarge = (EDischarge) createEElement(json, eDischarge);
		
		return eDischarge;
	}
	
	//set prescribed medication
	private List<PrescribedMedication> createPrescribedMedication(JSONObject json) throws ParseException {
		JSONArray prescribedMedicationsJson = json.getJSONArray("medicamentos_prescritos");
		List<PrescribedMedication> prescribedMedications = new ArrayList<PrescribedMedication>();
		
		for (int i = 0; i < prescribedMedicationsJson.length(); i++) {
			PrescribedMedication prescribedMedication = new PrescribedMedication();
			JSONObject prescribedMedicationJson = prescribedMedicationsJson.getJSONObject(i);
			
			prescribedMedication.setId(prescribedMedicationJson.getInt("id"));
			if (!prescribedMedicationJson.isNull("resultado")) {
				prescribedMedication.setResult(prescribedMedicationJson.getString("resultado"));
			}
			
			//save prescription
			prescribedMedication.setPrescription(createPrescription(prescribedMedicationJson));
			
			//set medicament
			Medication medication = new Medication();
			JSONObject medicationJson = prescribedMedicationJson.getJSONObject("medicamento");
			medication.setId(medicationJson.getInt("id"));
			medication.setName(medicationJson.getString("nome"));
			medication.setUrl(medicationJson.getString("url"));
			medication.setCode(medicationJson.getInt("codigo"));
			medication.setIdMedicament(medicationJson.getInt("medicationo_id"));
			medication.setDescription(medicationJson.getString("descricao"));
			medication.setBrand(medicationJson.getString("marca"));
			medication.setOutpatient(medicationJson.getBoolean("ambulatorial"));
			medication.setIdUnit(medicationJson.getInt("unidade_id"));
			medication.setIdAccess(medicationJson.getInt("via_acesso_id"));					
			medication.setDailyDosage(medicationJson.getInt("dose_diaria"));
			medication.setCycles(medicationJson.getInt("ciclos"));
			medication.setFrequency(medicationJson.getInt("frequencia"));
			medication.setFrequencyDisplay(medicationJson.getString("frequencia_display"));
			medication.setTimeInterval(medicationJson.getInt("dias_intervalo"));
			medication.setTimeTreatement(medicationJson.getInt("dias_tratamento"));
			
			if (!medicationJson.isNull("padrao")) {
				medication.setStandard(medicationJson.getString("padrao"));
			}	
			
			//set medicine
			Medicine medicine = new Medicine();
			JSONObject medicineJson = medicationJson.getJSONObject("medicamento");
			medicine.setId(medicineJson.getInt("id"));
			medicine.setName(medicineJson.getString("nome"));
			medicine.setUrl(medicineJson.getString("url"));
			medicine.setCode(medicineJson.getInt("codigo"));
			medicine.setOutpatient(medicineJson.getBoolean("ambulatorial"));
			medicine.setBrand(medicineJson.getString("marca"));
			medicine.setDescription(medicineJson.getString("descricao"));
			
			//save medicine
			medication.getMedicine().add(medicine);
			
			//set unit
			Unit unit = new Unit();
			JSONObject unitJson = medicationJson.getJSONObject("unidade");
			unit.setId(unitJson.getInt("id"));
			unit.setName(unitJson.getString("nome"));
			unit.setUrl(unitJson.getString("url"));
			unit.setCode(unitJson.getString("codigo"));
			unit.setUnit(unitJson.getString("unidade"));
			
			//save unit
			medication.getUnit().add(unit);
			
			//set access
			Access access = new Access();
			JSONObject accessJson = medicationJson.getJSONObject("via_acesso");
			access.setId(accessJson.getInt("id"));
			access.setName(accessJson.getString("nome"));
			access.setUrl(accessJson.getString("url"));
			access.setCode(accessJson.getInt("codigo"));
			
			//save access
			medication.getAccess().add(access);
			
			prescribedMedication.setMedicament(medication);
			
			//add prescribedMedication
			prescribedMedications.add(prescribedMedication);
		}		
		
		return prescribedMedications;
	}
	
	//set prescription exam
	private Prescription createPrescription(JSONObject json) throws ParseException {
		Prescription prescription = new Prescription();
		
		if (!json.isNull("prescricao")) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.getDefault());
			JSONObject prescriptionJson = json.getJSONObject("prescricao");			
			prescription.setId(prescriptionJson.getInt("id"));
			prescription.setSuccess(prescriptionJson.getBoolean("sucesso"));
			prescription.setMessage(prescriptionJson.getString("mensagem"));			
			String requestStr = prescriptionJson.getString("data_solicitacao");
			Date requestDate = dateFormat.parse(requestStr);			
			prescription.setRequestDate(requestDate);
		}		
		
		return prescription;
	}
}
