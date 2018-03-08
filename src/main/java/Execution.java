import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONArray;
import org.json.JSONObject;

import MetamodelExecution.EAuxiliaryConduct;
import MetamodelExecution.EDischarge;
import MetamodelExecution.EInformation;
import MetamodelExecution.EPathway;
import MetamodelExecution.EPrescription;
import MetamodelExecution.EReferral;
import MetamodelExecution.ETreatment;
import MetamodelExecution.Execution_metamodelFactory;
import MetamodelExecution.LastProfessional;
import MetamodelExecution.Pathway;
import MetamodelExecution.Responsible;


public class Execution {	
	public EPathway addEPathway(JSONObject json, Resource resource, EPathway ePathway) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSXXX", Locale.getDefault());
		
		//Set pathway
		JSONObject pathwayJson = json.getJSONObject("protocolo");
		Pathway pathway = Execution_metamodelFactory.eINSTANCE.createPathway();
		pathway.setId(pathwayJson.getInt("id"));
		pathway.setUrl(pathwayJson.getString("url"));
		pathway.setCode(pathwayJson.getString("codigo"));		
		pathway.setName(pathwayJson.getString("nome"));				
		pathway.setVersion(pathwayJson.getInt("versao"));
		pathway.setCompleted(pathwayJson.getBoolean("finalizado"));
		pathway.setIdRepository(pathwayJson.getInt("repositorio_id"));
	
		//Set responsible
		JSONObject responsibleJson = json.getJSONObject("responsavel");
		Responsible responsible = Execution_metamodelFactory.eINSTANCE.createResponsible();
		responsible.setId(responsibleJson.getInt("id"));
		responsible.setUrl(responsibleJson.getString("url"));
		responsible.setCode(responsibleJson.getInt("codigo"));
		responsible.setEmail(responsibleJson.getString("email"));
		responsible.setLogin(responsibleJson.getString("login"));
		responsible.setName(responsibleJson.getString("nome"));
		responsible.setNumberCouncil(responsibleJson.getInt("numero_conselho"));
		responsible.setTypeCouncil(responsibleJson.getString("tipo_conselho"));
		responsible.setState(responsibleJson.getString("uf"));
		
		//Set last professional
		JSONObject lastProfessionalJson = json.getJSONObject("ultimo_profissional");
		LastProfessional lastProfessional = Execution_metamodelFactory.eINSTANCE.createLastProfessional();
		lastProfessional.setId(lastProfessionalJson.getInt("id"));
		lastProfessional.setUrl(lastProfessionalJson.getString("url"));
		lastProfessional.setCode(lastProfessionalJson.getInt("codigo"));
		lastProfessional.setEmail(lastProfessionalJson.getString("email"));
		lastProfessional.setLogin(lastProfessionalJson.getString("login"));
		lastProfessional.setName(lastProfessionalJson.getString("nome"));
		lastProfessional.setNumberCouncil(lastProfessionalJson.getInt("numero_conselho"));
		lastProfessional.setTypeCouncil(lastProfessionalJson.getString("tipo_conselho"));
		lastProfessional.setState(lastProfessionalJson.getString("uf"));		
		
		//Set executed pathway
		ePathway.setId(json.getInt("id"));		
		ePathway.setUrl(json.getString("url"));
		ePathway.setName(pathwayJson.getString("nome"));	
		ePathway.setCompleted(json.getBoolean("finalizado"));
		ePathway.setAborted(json.getBoolean("abortado"));
		ePathway.setLastExecutedStepDate(json.getString("data_ultimo_passo_executado"));
		ePathway.setIdPathway(json.getInt("protocolo_id"));
		ePathway.setIdLastProfessional(json.getInt("ultimo_profissional_id"));		
		ePathway.setIdResponsible(json.getInt("responsavel_id"));
		ePathway.setLastprofessional(lastProfessional);
		ePathway.setResponsible(responsible);
		ePathway.setPathway(pathway);
		
		String creationStr = json.getString("data_criacao");
		Date creationDate = dateFormat.parse(creationStr);
		ePathway.setCreationDate(creationDate);
		
		String conclusionStr = json.getString("data_conclusao");
		Date conclusionDate = dateFormat.parse(conclusionStr);
		ePathway.setConclusionDate(conclusionDate);
		
		JSONArray idsExecutionStepJson = json.getJSONArray("passos_executados_ids");
		
		for (int i = 0; i < idsExecutionStepJson.length(); i++) {
			ePathway.getIdsExecutedStep().add(idsExecutionStepJson.optInt(i));
		}
		
		return ePathway;
	}

	public EAuxiliaryConduct addEAuxiliaryConduct(JSONObject json, Resource resource){
		return null;
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
}
