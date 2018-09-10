package com.executedpathway.translator.model;
import java.text.ParseException;

import org.json.JSONArray;
import org.json.JSONObject;

import com.executedpathway.translator.domain.EPathway;
import com.executedpathway.translator.pathway.ExecutedPathway;
import com.executedpathway.translator.pathway.complementaryconduct.ComplementaryConduct;
import com.executedpathway.translator.pathway.step.ExecutedStep;

public class Translator {
	private EPathway ePathway;
	
	public EPathway getePathway() {
		return ePathway;
	}

	//Constructor
	public Translator(){		
		this.ePathway = new EPathway();	
	}
	
	//convert JSON files in one XMI file
	public void toXMI(JSONObject json) throws ParseException{	
		ExecutedStep executedStep = new ExecutedStep();
		ComplementaryConduct complementaryConduct = new ComplementaryConduct();
		ExecutedPathway executedPathway = new ExecutedPathway();
		ePathway = executedPathway.addEPathway(json, ePathway);
		
		//set executed steps
		JSONArray executedStepsJson = json.getJSONArray("passos_executados");	
	
		for (int i = 0; i < executedStepsJson.length(); i++) {
			JSONObject executedStepJson = executedStepsJson.getJSONObject(i);
			String type = executedStepJson.getString("type");
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
		case "AuxilioCondutaExecutado":					
			ePathway.getElement().add(executedStep.createEAuxiliaryConduct(json));
			break;
			
		case "TratamentoExecutado":	
			ePathway.getElement().add(executedStep.createETreatment(json));
			break;
					
		case "ReceitaExecutado":	
			ePathway.getElement().add(executedStep.createEPrescription(json));
			break;
			
		case "EncaminhamentoExecutado":	
			ePathway.getElement().add(executedStep.createEReferral(json));
			break;
			
		case "InformacaoExecutado":
			ePathway.getElement().add(executedStep.createEInformation(json));
			break;
			
		case "AltaExecutado":
			ePathway.getElement().add(executedStep.createEDischarge(json));
			break;
			
		case "PausaExecutado":
			//ePathway.getElement().add(executedStep.createEDischarge(json));
			break;
			
		case "AcaoExecutado":
			//ePathway.getElement().add(executedStep.createEDischarge(json));
			break;
			
		case "ProcessoExecutado":
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
