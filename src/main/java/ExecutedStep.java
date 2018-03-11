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
import MetamodelExecution.PrescriptionExam;
import MetamodelExecution.Question;
import MetamodelExecution.Step;
import MetamodelExecution.YesOrNo;
import pathwayMetamodel.Category;
import pathwayMetamodel.PathwayMetamodelFactory;

public class ExecutedStep {
	public EElement addEElement(JSONObject json, EElement eElement) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSXXX", Locale.getDefault());
		
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
		eElement.setJustification(addJustification(json));
		eElement.setExecutor(addExecutor(json));
		eElement.setName(json.getString(eElement.getStep().getName()));
		
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
			
			answer.setId(json.getInt("id"));
			answer.setType(json.getString("type"));
			answer.setTypeVerbose(json.getString("type_verbose"));
			
			String type = json.getString("type");
			
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
			question.setUrl(questionJson.getString("url"));
			question.setText(questionJson.getString("texto"));
			
			//set category
			JSONObject categoryJson = questionJson.getJSONObject("categoria");
			Category category = PathwayMetamodelFactory.eINSTANCE.createCategory();
			
			if (categoryJson.length() > 0) {				
				category.setName(categoryJson.getString("nome"));
				
				//save category
				question.setCategory(category);
			}
			
			JSONObject variableJson = questionJson.getJSONObject("variavel");
			
			if (type.equals("RespostaSimOuNao")) {			
				//set yes or no
				pathwayMetamodel.YesOrNo variable = PathwayMetamodelFactory.eINSTANCE.createYesOrNo();
				variable.setId(variableJson.getInt("id"));	
				variable.setName(variableJson.getString("nome"));
				variable.setValue(variableJson.getBoolean("valor"));
				variable.setWeight(variableJson.getInt("peso"));		
				
				//save variable
				question.getVariable().add(variable);
			}
			else if (type.equals("RespostaNumerica")) {
				//set numeric
				pathwayMetamodel.Numeric variable = PathwayMetamodelFactory.eINSTANCE.createNumeric();
				variable.setId(variableJson.getInt("id"));	
				variable.setName(variableJson.getString("nome"));
				variable.setValue(variableJson.getDouble("valor"));
				variable.setWeight(variableJson.getInt("peso"));
				
				//save variable
				question.getVariable().add(variable);
			}	
			
			//save question
			answer.getQuestion().add(question);
			
			//save answer
			eAuxiliaryConduct.getAnswer().add(answer);
		}		
		
		return eAuxiliaryConduct;
	}
	
	public ETreatment addETreatment(JSONObject json, Resource resource) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSXXX", Locale.getDefault());

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
			
			//set prescription exam
			JSONObject prescriptionExamJson = prescribedExaminationJson.getJSONObject("prescricao");
			PrescriptionExam prescriptionExam = Execution_metamodelFactory.eINSTANCE.createPrescriptionExam();
			prescriptionExam.setId(prescriptionExamJson.getInt("id"));
			prescriptionExam.setSuccess(prescriptionExamJson.getBoolean("sucesso"));
			prescriptionExam.setMessage(prescriptionExamJson.getString("mensagem"));
			
			String requestStr = json.getString("data_solicitacao");
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
			complement.setJustification(complementJson.getString(""));
			complement.setClinicalIndication(complementJson.getString("indicacao_clinica"));
			
			//set quantity
			
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
			
			//set result			
			
			//save prescribed examination
			eTreatment.getPrescribedexam().add(prescribedExamination);
		}
		
		//set prescribed medicament
		//set prescribed procedure
		//set prescribed internment	
		
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
		//set prescription prescription item
		//set prescription medication
		
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
		JSONObject justificationJson = json.getJSONObject("justificativa");
		
		if (justificationJson != null) {
			Justification justification = Execution_metamodelFactory.eINSTANCE.createJustification();
			justification.setId(justificationJson.getInt("id"));
			justification.setReason(justificationJson.getString("razao"));
			justification.setReasonDisplay(justificationJson.getString("razao_display"));
			justification.setDescription(justificationJson.getString("descricao"));
			justification.setJustifiedById(justificationJson.getInt("justificado_por_id"));
			justification.setJustifiedBy(justificationJson.getString("justificado_por"));
			
			return justification;
		}
		
		return null;
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
