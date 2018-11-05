package com.executedpathway.translator.model.pathway.complementaryconduct;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.json.JSONObject;

import MetamodelExecution.ComplementaryConducts;
import MetamodelExecution.ComplementaryExamination;
import MetamodelExecution.ComplementaryItemPrescription;
import MetamodelExecution.ComplementaryMedication;
import MetamodelExecution.ComplementaryProcedure;
import MetamodelExecution.Execution_metamodelFactory;
import MetamodelExecution.MedicationPrescribedResource;
import MetamodelExecution.ProcedurePrescribedResource;
import MetamodelExecution.Suspension;

public class ComplementaryConduct {
	public ComplementaryConducts createComplementaryConducts(JSONObject json, ComplementaryConducts complementaryConducts) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSS", Locale.getDefault());
		
		//set date
		String creationStr = json.getString("data_criacao");
		Date creationDate = dateFormat.parse(creationStr);		
		
		//set complementary conduct		
		complementaryConducts.setId(json.getInt("id"));
		complementaryConducts.setType(json.getString("type"));
		complementaryConducts.setPathway(json.getString("protocolo"));
		complementaryConducts.setCreationDate(creationDate);
		complementaryConducts.setResource(json.getString("recurso"));
		
		//set suspension
		if (json.has("suspensao")) {
			Suspension suspension = Execution_metamodelFactory.eINSTANCE.createSuspension();
			JSONObject suspensionJson = json.getJSONObject("suspensao");
			
			//set date
			String suspensionStr = json.getString("data_solicitacao");
			Date suspensionDate = dateFormat.parse(suspensionStr);
			
			suspension.setId(suspensionJson.getInt("id"));
			suspension.setMessage(suspensionJson.getString("messagem"));
			suspension.setRequestDate(suspensionDate);
			suspension.setSuccess(suspensionJson.getBoolean("sucesso"));
			
			complementaryConducts.setSuspension(suspension);
		}
		
		return complementaryConducts;
	}
	
	public ComplementaryMedication createComplementaryMedicamention(JSONObject json) throws ParseException {
		ComplementaryMedication complementaryMedication = Execution_metamodelFactory.eINSTANCE.createComplementaryMedication();
		complementaryMedication = (ComplementaryMedication) createComplementaryConducts(json, complementaryMedication);
		
		MedicationPrescribedResource medicationPrescribedResource = Execution_metamodelFactory.eINSTANCE.createMedicationPrescribedResource();
		
		if (json.has("recurso_prescrito")) {
			JSONObject resourceJson = json.getJSONObject("recurso_prescrito");
			
			medicationPrescribedResource.setId(resourceJson.getInt("id"));
			medicationPrescribedResource.setIdMedication(resourceJson.getInt("medicamento_id"));
			medicationPrescribedResource.setOutpatient(resourceJson.getBoolean("ambulatorial"));
			medicationPrescribedResource.setName(resourceJson.getString("nome"));
			medicationPrescribedResource.setStandard(resourceJson.getString("padrao"));
			medicationPrescribedResource.setBrand(resourceJson.getString("marca"));
			medicationPrescribedResource.setCode(resourceJson.getInt("codigo"));
			medicationPrescribedResource.setCycles(resourceJson.getInt("ciclos"));
			medicationPrescribedResource.setCategory(resourceJson.getString("categoria"));
			medicationPrescribedResource.setDescription(resourceJson.getString("descricao"));
			medicationPrescribedResource.setTimeInterval(resourceJson.getInt("dias_intervalo"));
			medicationPrescribedResource.setDailyDosage(resourceJson.getInt("dose_diaria"));
			medicationPrescribedResource.setFrequency(resourceJson.getInt("frequencia"));
			medicationPrescribedResource.setTimeTreatement(resourceJson.getInt("dias_tratamento"));
			medicationPrescribedResource.setMedication(resourceJson.getString("medicamento"));
			medicationPrescribedResource.setUnit(resourceJson.getString("unidade"));
			medicationPrescribedResource.setAccess(resourceJson.getString("via_acesso"));
		
			complementaryMedication.setPrescribedresource(medicationPrescribedResource);
		}
		
		return complementaryMedication;
	}
	
	public ComplementaryProcedure createComplementaryProcedure(JSONObject json) throws ParseException {
		ComplementaryProcedure complementaryProcedure = Execution_metamodelFactory.eINSTANCE.createComplementaryProcedure();
		complementaryProcedure = (ComplementaryProcedure) createComplementaryConducts(json, complementaryProcedure);
		
		ProcedurePrescribedResource procedurePrescribedResource = Execution_metamodelFactory.eINSTANCE.createProcedurePrescribedResource();
		
		if (json.has("recurso_prescrito")) {
			JSONObject resourceJson = json.getJSONObject("recurso_prescrito");
			
			procedurePrescribedResource.setId(resourceJson.getInt("id"));
			procedurePrescribedResource.setIdProcedure(resourceJson.getInt("procedimento_id"));
			procedurePrescribedResource.setQuantity(resourceJson.getInt("procedimento"));
			procedurePrescribedResource.setFrequency(resourceJson.getInt("frequencia"));
			procedurePrescribedResource.setProcedure(resourceJson.getString("frequencia_display"));
			procedurePrescribedResource.setCategory(resourceJson.getString("categoria"));
			
			complementaryProcedure.setProcedureprescribedresource(procedurePrescribedResource);
		}
		
		return complementaryProcedure;
	}
	
	public ComplementaryExamination createComplementaryExamination(JSONObject json) throws ParseException {
		ComplementaryExamination complementaryExamination = Execution_metamodelFactory.eINSTANCE.createComplementaryExamination();
		complementaryExamination = (ComplementaryExamination) createComplementaryConducts(json, complementaryExamination);
		
		return complementaryExamination;
	}
	
	public ComplementaryItemPrescription createComplementaryItemPrescription(JSONObject json) throws ParseException {
		ComplementaryItemPrescription itemPrescription = Execution_metamodelFactory.eINSTANCE.createComplementaryItemPrescription();
		itemPrescription = (ComplementaryItemPrescription) createComplementaryConducts(json, itemPrescription);
		
		return itemPrescription;
	}
}
