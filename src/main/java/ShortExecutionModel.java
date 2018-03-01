import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONObject;

import MetamodelExecution.Execution_metamodelFactory;
import MetamodelExecution.ShortExecution;

public class ShortExecutionModel {
	public ShortExecution createShortExecution(JSONObject json, Resource resource) throws ParseException{
		ShortExecution shortExecution = Execution_metamodelFactory.eINSTANCE.createShortExecution();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		shortExecution.setId(json.getInt("id"));
		shortExecution.setUrl(json.getString("url"));
		
		String creationStr = json.getString("data_criacao");
		Date creationDate = dateFormat.parse(creationStr);
		shortExecution.setCreationDate(creationDate);
		
		String conclusionStr = json.getString("data_conclusao");
		Date conclusionDate = dateFormat.parse(conclusionStr);
		shortExecution.setConclusionDate(conclusionDate);
		
		shortExecution.setCompleted(json.getBoolean("finalizado"));
		shortExecution.setAborted(json.getBoolean("abortado"));
		
		//justify
		
		shortExecution.setLastExecutedStepDate(json.getString("data_ultimo_passo_executado"));
		shortExecution.setIdPathway(json.getInt("protocolo_id"));
		
		//protocolo		
		
		shortExecution.setIdResponsible(json.getInt("responsavel_id"));
		
		//resposavel
		
		shortExecution.setIdLastProfessional(json.getInt("ultimo_profissional_id"));
		
		//lastprofessional
		//passos ids
		//passos exxecutados
		
		return null;
	}
}
