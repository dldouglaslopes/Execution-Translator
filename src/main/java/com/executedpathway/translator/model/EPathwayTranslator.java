package com.executedpathway.translator.model;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.executedpathway.translator.pathway.ExecutedPathway;
import com.executedpathway.translator.pathway.complementaryconduct.ComplementaryConduct;
import com.executedpathway.translator.pathway.step.ExecutedStep;

import MetamodelExecution.EPathway;
import MetamodelExecution.Execution_metamodelFactory;

public class EPathwayTranslator {
	private EPathway ePathway;
	
	public EPathway getePathway() {
		return ePathway;
	}

	//Constructor
	public EPathwayTranslator(){		
		this.ePathway = Execution_metamodelFactory.eINSTANCE.createEPathway();	
	}
	
	//convert JSON files in one XMI file
	public void toXMI(JSONObject json) throws ParseException{	
		ExecutedStep executedStep = new ExecutedStep();
		ComplementaryConduct complementaryConduct = new ComplementaryConduct();
		ExecutedPathway executedPathway = new ExecutedPathway();
		ePathway = executedPathway.addEPathway(json, ePathway);
		
		//set executed steps
		JSONArray executedStepJsons = json.getJSONArray("passos_executados");	
	
		for (int i = 0; i < executedStepJsons.length(); i++) {
			JSONObject executedStepJson = executedStepJsons.getJSONObject(i);
			String type = executedStepJson.getJSONObject("passo").getString("type");
			selectEStep(type, executedStepJson, executedStep);
		}
		
		//set complementary conducts
		JSONArray complementaryConductsJson = json.getJSONArray("condutas_complementares");	
		
		for (int i = 0; i < complementaryConductsJson.length(); i++) {
			JSONObject complementaryConductJson = complementaryConductsJson.getJSONObject(i);
			String type = complementaryConductJson.getString("type");
			selectComplementaryConducts(type, complementaryConductJson, complementaryConduct);
		}
	}	

	private void selectEStep(
			String type, 
			JSONObject json, 
			ExecutedStep executedStep) throws ParseException {
		
		switch (type) {
		case "AuxilioConduta":					
			ePathway.getElement().add(executedStep.createEAuxiliaryConduct(json));
			break;
			
		case "Tratamento":	
			ePathway.getElement().add(executedStep.createETreatment(json));
			break;
					
		case "Receita":	
			ePathway.getElement().add(executedStep.createEPrescription(json));
			break;
			
		case "Encaminhamento":	
			ePathway.getElement().add(executedStep.createEReferral(json));
			break;
			
		case "Informacao":
			ePathway.getElement().add(executedStep.createEInformation(json));
			break;
			
		case "Alta":
			ePathway.getElement().add(executedStep.createEDischarge(json));
			break;
			
		case "Pausa":
			//ePathway.getElement().add(executedStep.createEDischarge(json));
			break;
			
		case "Acao":
			//ePathway.getElement().add(executedStep.createEDischarge(json));
			break;
			
		case "Processo":
			//ePathway.getElement().add(executedStep.createEDischarge(json));
			break;

		default:
			System.out.println("UNKNOWN TYPE!");
			break;			
		}
	}
	
	private void selectComplementaryConducts(
			String type, 
			JSONObject complementaryConductJson,
			ComplementaryConduct complementaryConduct) {
		// TODO Auto-generated method stub
		
	}
}
