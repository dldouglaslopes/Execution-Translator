package com.executedpathway.translator.pathway;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONObject;

import com.executedpathway.translator.domain.EPathway;

import MetamodelExecution.Attendance;
import MetamodelExecution.Execution_metamodelFactory;
import MetamodelExecution.Justification;
import MetamodelExecution.Pathway;


public class ExecutedPathway {	
	public EPathway addEPathway(JSONObject json, EPathway ePathway) throws ParseException{
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.ROOT);
		
		//Set pathway
		JSONObject pathwayJson = json.getJSONObject("protocolo");
		Pathway pathway = Execution_metamodelFactory.eINSTANCE.createPathway();
		pathway.setId(pathwayJson.getInt("id"));
		pathway.setCode(pathwayJson.getString("codigo"));		
		pathway.setName(pathwayJson.getString("nome"));				
		pathway.setVersion(pathwayJson.getInt("versao"));
		pathway.setCompleted(pathwayJson.getBoolean("finalizado"));
		pathway.setLastAuditing(pathwayJson.getString("ultima_auditoria"));
		
		//set justification			
		Justification justification = Execution_metamodelFactory.eINSTANCE.createJustification();
		if (!json.isNull("justificativa")) {
			JSONObject justificationJson = json.getJSONObject("justificativa");			
			justification.setId(justificationJson.getInt("id"));
			justification.setReason(justificationJson.getString("razao"));
			justification.setDescription(justificationJson.getString("descricao"));
			justification.setJustifiedById(justificationJson.getInt("justificado_por_id"));	
		}		
		ePathway.setJustification(justification);		
		
		//set dates
		String creationStr = json.getString("data_criacao");
		Date creationDate = dateFormat.parse(creationStr);				
		String conclusionStr = json.getString("data_conclusao");
		Date conclusionDate = dateFormat.parse(conclusionStr);
		
		//set attendance
		JSONObject attendanceJson = json.getJSONObject("atendimento");
		Attendance attendance = Execution_metamodelFactory.eINSTANCE.createAttendance();
		attendance.setCodeAttendance(attendanceJson.getInt("codigo_atendimento"));
		attendance.setCodeBd(attendanceJson.getString("codigo_bd"));
		attendance.setHospitalUnit(attendanceJson.getString("unidade"));
		attendance.setIdProfessional(attendanceJson.getInt("profissional_id"));
		attendance.setPatientRecord(attendanceJson.getString("prontuario"));
				
		//Set executed pathway
		ePathway.setId(json.getInt("id"));		
		ePathway.setName(pathwayJson.getString("nome"));
		ePathway.setCompleted(json.getBoolean("finalizado"));
		ePathway.setAborted(json.getBoolean("abortado"));
		ePathway.setPathway(pathway);	
		ePathway.setConclusionDate(conclusionDate);
		ePathway.setCreationDate(creationDate);
		ePathway.setAttendance(attendance);
		ePathway.setCid(json.getString("cid"));
		ePathway.setTimeExecution(json.getDouble(""));
		
		JSONArray idsExecutionStepJson = json.getJSONArray("passos_executados_ids");		
		for (int i = 0; i < idsExecutionStepJson.length(); i++) {
			ePathway.getIdsExecutedStep().add(idsExecutionStepJson.optInt(i));
		}
		
		return ePathway;
	}	
}
