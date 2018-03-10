import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONArray;
import org.json.JSONObject;

import MetamodelExecution.Answer;
import MetamodelExecution.Creator;
import MetamodelExecution.EAuxiliaryConduct;
import MetamodelExecution.EDischarge;
import MetamodelExecution.EInformation;
import MetamodelExecution.EPrescription;
import MetamodelExecution.EReferral;
import MetamodelExecution.ETreatment;
import MetamodelExecution.Execution_metamodelFactory;
import MetamodelExecution.Executor;
import MetamodelExecution.Justification;
import MetamodelExecution.Numeric;
import MetamodelExecution.Question;
import MetamodelExecution.Step;
import MetamodelExecution.YesOrNo;
import pathwayMetamodel.Category;
import pathwayMetamodel.PathwayMetamodelFactory;

public class ExecutedStep {
	public EAuxiliaryConduct addEAuxiliaryConduct(JSONObject json, Resource resource) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSXXX", Locale.getDefault());
				
		//Set executed auxiliary conduct
		EAuxiliaryConduct eAuxiliaryConduct = Execution_metamodelFactory.eINSTANCE.createEAuxiliaryConduct();
		eAuxiliaryConduct.setId(json.getInt("id"));
		eAuxiliaryConduct.setType(json.getString("type"));
		eAuxiliaryConduct.setTypeVerbose(json.getString("type_verbose"));
		eAuxiliaryConduct.setUrl(json.getString("url"));
		eAuxiliaryConduct.setIsCurrent(json.getBoolean("is_current"));
		eAuxiliaryConduct.setReworked(json.getBoolean("reworked"));
		eAuxiliaryConduct.setExecuted(json.getBoolean("executado"));		
		eAuxiliaryConduct.setCreatedById(json.getInt("criado_por_id"));
		eAuxiliaryConduct.setExecutedById(json.getInt("executado_por_id"));
		eAuxiliaryConduct.setIdStep(json.getInt("passo_id"));		
		eAuxiliaryConduct.setStep(addStep(json));
		eAuxiliaryConduct.setCreator(addCreator(json));
		eAuxiliaryConduct.setJustification(addJustification(json));
		eAuxiliaryConduct.setExecutor(addExecutor(json));
		eAuxiliaryConduct.setName(json.getString(eAuxiliaryConduct.getStep().getName()));
		
		String creationStr = json.getString("data_criacao");
		Date creationDate = dateFormat.parse(creationStr);
		eAuxiliaryConduct.setCreationDate(creationDate);
		
		String modificationStr = json.getString("data_modificacao");
		Date modificationDate = dateFormat.parse(modificationStr);
		eAuxiliaryConduct.setModificationDate(modificationDate);
		
		String executionStr = json.getString("data_execucao");
		Date executionDate = dateFormat.parse(executionStr);
		eAuxiliaryConduct.setExecutionDate(executionDate);
		
		//Set answers
		JSONArray answers = json.getJSONArray("respostas");
		
		for (int i = 0; i < answers.length(); i++) {
			JSONObject answerJson = answers.getJSONObject(i);
			Answer answer = Execution_metamodelFactory.eINSTANCE.createAnswer();
			
			answer.setId(json.getInt("id"));
			answer.setType(json.getString("type"));
			answer.setTypeVerbose(json.getString("type_verbose"));
			
			String type = json.getString("type");
			
			if (type.equals("RespostaSimOuNao")) {
				YesOrNo value = Execution_metamodelFactory.eINSTANCE.createYesOrNo();
				value.setValue(answerJson.getBoolean("valor"));
				
				answer.setValue(value);
			}
			else if (type.equals("RespostaNumerica")) {
				Numeric value = Execution_metamodelFactory.eINSTANCE.createNumeric();
				value.setValue(answerJson.getDouble("valor"));
				
				answer.setValue(value);
			}	
			
			JSONObject questionJson = answerJson.getJSONObject("pergunta");
			Question question = Execution_metamodelFactory.eINSTANCE.createQuestion();
			
			question.setId(questionJson.getInt("id"));
			question.setUrl(questionJson.getString("url"));
			question.setText(questionJson.getString("texto"));
			
			JSONObject categoryJson = questionJson.getJSONObject("categoria");
			Category category = PathwayMetamodelFactory.eINSTANCE.createCategory();
			
			if (categoryJson.length() != 0) {				
				category.setName(categoryJson.getString("nome"));
				
				question.setCategory(category);
			}
			else {
				category = null;
				
				question.setCategory(category);
			}
			
			JSONObject variableJson = questionJson.getJSONObject("variavel");
			
			if (type.equals("RespostaSimOuNao")) {			
				pathwayMetamodel.YesOrNo variable = PathwayMetamodelFactory.eINSTANCE.createYesOrNo();
				variable.setId(variableJson.getInt("id"));	
				variable.setName(variableJson.getString("nome"));
				variable.setValue(variableJson.getBoolean("valor"));
				variable.setWeight(variableJson.getInt("peso"));		
				
				//save variable
				question.getVariable().add(variable);
			}
			else if (type.equals("RespostaNumerica")) {
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
	
	public ETreatment addETreatment(JSONObject json, Resource resource){
		return null;
	}
	
	public EPrescription addEPrescription(JSONObject json, Resource resource){
		return null;
	}
	
	public EInformation addEInformation(JSONObject json, Resource resource){
		return null;
	}
	
	public EReferral addEReferral(JSONObject json, Resource resource){
		return null;
	}
	
	public EDischarge addEDischarge(JSONObject json, Resource resource){
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
