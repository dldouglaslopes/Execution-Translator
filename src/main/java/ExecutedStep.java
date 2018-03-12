import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONArray;
import org.json.JSONObject;

import MetamodelExecution.Answer;
import MetamodelExecution.Complement;
import MetamodelExecution.Creator;
import MetamodelExecution.EAuxiliaryConduct;
import MetamodelExecution.EDischarge;
import MetamodelExecution.EElement;
import MetamodelExecution.EInformation;
import MetamodelExecution.EPrescription;
import MetamodelExecution.EReferral;
import MetamodelExecution.ETreatment;
import MetamodelExecution.Exam;
import MetamodelExecution.Examination;
import MetamodelExecution.Execution_metamodelFactory;
import MetamodelExecution.Executor;
import MetamodelExecution.Justification;
import MetamodelExecution.Numeric;
import MetamodelExecution.PrescribedExamination;
import MetamodelExecution.PrescribedInternment;
import MetamodelExecution.PrescribedMedicament;
import MetamodelExecution.PrescribedMedication;
import MetamodelExecution.PrescribedPrescriptionItem;
import MetamodelExecution.PrescribedProcedure;
import MetamodelExecution.PrescriptionExam;
import MetamodelExecution.PrescriptionInfo;
import MetamodelExecution.Question;
import MetamodelExecution.Step;
import MetamodelExecution.Variable;
import MetamodelExecution.YesOrNo;

public class ExecutedStep {
	public EElement addEElement(JSONObject json, EElement eElement) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.getDefault());
		
		//Set executed element/step
		eElement.setId(json.getInt("id"));
		eElement.setType(json.getString("type"));
		eElement.setTypeVerbose(json.getString("type_verbose"));
		eElement.setUrl(json.getString("url"));
		eElement.setIsCurrent(json.getBoolean("is_current"));
		eElement.setReworked(json.getBoolean("reworked"));
		eElement.setExecuted(json.getBoolean("executado"));		
		eElement.setCreatedById(json.getInt("criado_por_id"));
		eElement.setExecutedById(json.getInt("executado_por_id"));
		eElement.setIdStep(json.getInt("passo_id"));		
		eElement.setStep(addStep(json));
		eElement.setCreator(addCreator(json));		
		eElement.setExecutor(addExecutor(json));
		eElement.setName(eElement.getStep().getName());
		
		if (addJustification(json) != null) {
			eElement.setJustification(addJustification(json));
		}		
		
		String creationStr = json.getString("data_criacao");
		Date creationDate = dateFormat.parse(creationStr);
		eElement.setCreationDate(creationDate);
		
		String modificationStr = json.getString("data_modificacao");
		Date modificationDate = dateFormat.parse(modificationStr);
		eElement.setModificationDate(modificationDate);
		
		String executionStr = json.getString("data_execucao");
		Date executionDate = dateFormat.parse(executionStr);
		eElement.setExecutionDate(executionDate);
		
		return eElement;
	}
	
	public EAuxiliaryConduct addEAuxiliaryConduct(JSONObject json, Resource resource) throws ParseException{
		//Set executed auxiliary conduct
		EAuxiliaryConduct eAuxiliaryConduct = Execution_metamodelFactory.eINSTANCE.createEAuxiliaryConduct();
		eAuxiliaryConduct = (EAuxiliaryConduct) addEElement(json, eAuxiliaryConduct);
		
		//Set answers
		JSONArray answers = json.getJSONArray("respostas");
		
		for (int i = 0; i < answers.length(); i++) {
			//set answer
			JSONObject answerJson = answers.getJSONObject(i);
			Answer answer = Execution_metamodelFactory.eINSTANCE.createAnswer();
			
			answer.setId(answerJson.getInt("id"));
			answer.setType(answerJson.getString("type"));
			answer.setTypeVerbose(answerJson.getString("type_verbose"));
			
			String type = answerJson.getString("type");
			
			if (type.equals("RespostaSimOuNao")) {
				//set yes or no
				YesOrNo value = Execution_metamodelFactory.eINSTANCE.createYesOrNo();
				value.setValue(answerJson.getBoolean("valor"));
				
				//save value
				answer.setValue(value);
			}
			else if (type.equals("RespostaNumerica")) {
				//set numeric
				Numeric value = Execution_metamodelFactory.eINSTANCE.createNumeric();
				value.setValue(answerJson.getDouble("valor"));
				
				//save value
				answer.setValue(value);
			}	
			
			//set question
			JSONObject questionJson = answerJson.getJSONObject("pergunta");
			Question question = Execution_metamodelFactory.eINSTANCE.createQuestion();
			
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
			Variable variable = Execution_metamodelFactory.eINSTANCE.createVariable();
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
				variable.setValue(yesOrNo);
			}
			else if (type.equals("RespostaNumerica")) {
				//set numeric
				Numeric numeric = Execution_metamodelFactory.eINSTANCE.createNumeric();
				numeric.setValue(variableJson.getDouble("valor"));
				
				//save numeric
				variable.setValue(numeric);
			}	
			
			//save variable
			question.setVariable(variable);
			
			//save question
			answer.setQuestion(question);
			
			//save answer
			eAuxiliaryConduct.getAnswer().add(answer);
		}		
		
		return eAuxiliaryConduct;
	}
	
	public ETreatment addETreatment(JSONObject json, Resource resource) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.getDefault());

		//set executed treatment
		ETreatment eTreatment = Execution_metamodelFactory.eINSTANCE.createETreatment();		
		eTreatment = (ETreatment) addEElement(json, eTreatment);	
		
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
		
		//set prescribed examination
		JSONArray prescribedExaminations = json.getJSONArray("exames_prescritos");			
		for (int i = 0; i < prescribedExaminations.length(); i++) {			
			PrescribedExamination prescribedExamination = Execution_metamodelFactory.eINSTANCE.createPrescribedExamination();
			JSONObject prescribedExaminationJson = prescribedExaminations.getJSONObject(i);
			prescribedExamination.setId(prescribedExaminationJson.getInt("id"));
			prescribedExamination.setReport(prescribedExaminationJson.getString("laudo"));
			prescribedExamination.setNumberGuide(prescribedExaminationJson.getInt("numero_guia"));
			if (!prescribedExaminationJson.isNull("resultado")) {
				prescribedExamination.setResult(prescribedExaminationJson.getString("resultado"));
			}			
			
			//set prescription exam
			JSONObject prescriptionExamJson = prescribedExaminationJson.getJSONObject("prescricao");
			PrescriptionExam prescriptionExam = Execution_metamodelFactory.eINSTANCE.createPrescriptionExam();
			prescriptionExam.setId(prescriptionExamJson.getInt("id"));
			prescriptionExam.setSuccess(prescriptionExamJson.getBoolean("sucesso"));
			prescriptionExam.setMessage(prescriptionExamJson.getString("mensagem"));			
			String requestStr = prescriptionExamJson.getString("data_solicitacao");
			Date requestDate = dateFormat.parse(requestStr);			
			prescriptionExam.setRequestDate(requestDate);
			//save prescription exam
			prescribedExamination.setPrescriptionexam(prescriptionExam);
			
			//set complement
			JSONObject complementJson = prescribedExaminationJson.getJSONObject("complemento");
			Complement complement = Execution_metamodelFactory.eINSTANCE.createComplement();
			complement.setId(complementJson.getInt("id"));
			complement.setSideLimb(complementJson.getString("lado_membro"));
			complement.setSideLimbDisplay(complementJson.getString("lado_membro_display"));
			complement.setJustification(complementJson.getString("justificativa"));
			complement.setClinicalIndication(complementJson.getString("indicacao_clinica"));
			if (!complementJson.isNull("quantidade")) {
				complement.setQuantity(complementJson.getInt("quantidade"));
			}
			//save complement
			prescribedExamination.setComplement(complement);
			
			//set examination
			Examination examination = Execution_metamodelFactory.eINSTANCE.createExamination();
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
			Exam exam = Execution_metamodelFactory.eINSTANCE.createExam();
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
		
		//set prescribed medicamention
		JSONArray prescribedMedications = json.getJSONArray("medicamentos_prescritos");		
		for (int i = 0; i < prescribedMedications.length(); i++) {			
			PrescribedMedication prescribedMedicamention = Execution_metamodelFactory.eINSTANCE.createPrescribedMedication();
			//save prescribed medicamention
			eTreatment.getPrescribedmedication().add(prescribedMedicamention);
		}
		
		//set prescribed procedure
		JSONArray prescribedProcedures = json.getJSONArray("procedimentos_prescritos");
		for (int i = 0; i < prescribedProcedures.length(); i++) {			
			PrescribedProcedure prescribedProcedure = Execution_metamodelFactory.eINSTANCE.createPrescribedProcedure();
			//save prescribed procedure
			eTreatment.getPrescribedprocedure().add(prescribedProcedure);
		}
		
		//set prescribed internment
		JSONArray prescribedInternments = json.getJSONArray("internamentos_prescritos");
		for (int i = 0; i < prescribedInternments.length(); i++) {			
			PrescribedInternment prescribedInternment = Execution_metamodelFactory.eINSTANCE.createPrescribedInternment();
			//save prescribed internment
			eTreatment.getPrescribedinternment().add(prescribedInternment);
		}		
		
		return eTreatment;
	}
	
	public EPrescription addEPrescription(JSONObject json, Resource resource) throws ParseException{
		//set executed precription
		EPrescription ePrescription = Execution_metamodelFactory.eINSTANCE.createEPrescription();
		ePrescription = (EPrescription) addEElement(json, ePrescription);
		
		ePrescription.setText(json.getString("texto"));
		
		//set prescribed medication 
		JSONArray idsPrescribedMedicationJson = json.getJSONArray("medicamentos_prescritos_ids");		
		for (int i = 0; i < idsPrescribedMedicationJson.length(); i++) {
			//save prescribed medication 
			ePrescription.getIdsPrescribedMedication().add(idsPrescribedMedicationJson.optInt(i));
		}
		
		//set prescribed prescription item
		JSONArray idsPrescribedPrescriptionItemJson = json.getJSONArray("medicamentos_prescritos_ids");		
		for (int i = 0; i < idsPrescribedPrescriptionItemJson.length(); i++) {
			//save prescribed PrescriptionItem 
			ePrescription.getIdsPrescribedPrescriptionItem().add(idsPrescribedPrescriptionItemJson.optInt(i));
		}
		
		//set prescription info
		PrescriptionInfo prescriptionInfo = Execution_metamodelFactory.eINSTANCE.createPrescriptionInfo();
		//save prescription info
		ePrescription.getPrescriptioninfo().add(prescriptionInfo);
		
		//set prescription prescription item
		PrescribedPrescriptionItem prescribedPrescriptionItem = Execution_metamodelFactory.eINSTANCE.createPrescribedPrescriptionItem();
		//save prescription prescription item
		ePrescription.getPrescribedprescriptionitem().add(prescribedPrescriptionItem);
		
		//set prescription medication
		PrescribedMedicament prescribedMedicament = Execution_metamodelFactory.eINSTANCE.createPrescribedMedicament();
		//save prescription medication
		ePrescription.getPrescribedmedicament().add(prescribedMedicament);
		
		return ePrescription;
	}
	
	public EInformation addEInformation(JSONObject json, Resource resource) throws ParseException{
		//set executed information
		EInformation eInformation = Execution_metamodelFactory.eINSTANCE.createEInformation();
		eInformation = (EInformation) addEElement(json, eInformation);
		
		return eInformation;
	}
	
	public EReferral addEReferral(JSONObject json, Resource resource) throws ParseException{
		//set executed referral
		EReferral eReferral = Execution_metamodelFactory.eINSTANCE.createEReferral();
		eReferral = (EReferral) addEElement(json, eReferral);
		
		return eReferral;
	}
	
	public EDischarge addEDischarge(JSONObject json, Resource resource) throws ParseException{
		//set executed discharge
		EDischarge eDischarge = Execution_metamodelFactory.eINSTANCE.createEDischarge();
		eDischarge = (EDischarge) addEElement(json, eDischarge);
		
		return eDischarge;
	}
	
	public String getNameStep() {
		return null;
	}
	
	//Set step
	public Step addStep(JSONObject json) {
		JSONObject stepJson = json.getJSONObject("passo");
		Step step = Execution_metamodelFactory.eINSTANCE.createStep();
		step.setId(stepJson.getInt("id"));
		step.setType(stepJson.getString("type"));
		step.setTypeVerbose(stepJson.getString("type_verbose"));
		step.setUrl(stepJson.getString("url"));
		step.setName(stepJson.getString("nome"));
		step.setDescription(stepJson.getString("descricao"));
		step.setIsInitial(stepJson.getBoolean("is_initial"));
		step.setIsTerminal(stepJson.getBoolean("is_terminal"));
		step.setMandatory(stepJson.getBoolean("obrigatoriedade"));
		
		return step;
	}
	
	//Set justification
	public Justification addJustification(JSONObject json) {			
		Justification justification = Execution_metamodelFactory.eINSTANCE.createJustification();

		if (!json.isNull("justificativa")) {	
			JSONObject justificationJson = json.getJSONObject("justificativa");
			justification.setId(justificationJson.getInt("id"));
			justification.setReason(justificationJson.getString("razao"));
			justification.setReasonDisplay(justificationJson.getString("razao_display"));
			justification.setDescription(justificationJson.getString("descricao"));
			justification.setJustifiedById(justificationJson.getInt("justificado_por_id"));
			justification.setJustifiedBy(justificationJson.getString("justificado_por"));
		}
		
		return justification;
	}
	
	//Set creator
	public Creator addCreator(JSONObject json) {
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
		
		return creator;
	}
	
	//Set executor
	public Executor addExecutor(JSONObject json) {
		JSONObject executorJson = json.getJSONObject("executado_por");
		Executor executor = Execution_metamodelFactory.eINSTANCE.createExecutor();
		executor.setId(executorJson.getInt("id"));
		executor.setUrl(executorJson.getString("url"));
		executor.setCode(executorJson.getInt("codigo"));
		executor.setEmail(executorJson.getString("email"));
		executor.setLogin(executorJson.getString("login"));
		executor.setName(executorJson.getString("nome"));
		executor.setNumberCouncil(executorJson.getInt("numero_conselho"));
		executor.setTypeCouncil(executorJson.getString("tipo_conselho"));
		executor.setState(executorJson.getString("uf"));
		
		return executor;
	}
}
