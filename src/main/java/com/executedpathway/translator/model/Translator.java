package com.executedpathway.translator.model;
import java.text.ParseException;

import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONObject;

import com.executedpathway.translator.config.FileConfig;
import com.executedpathway.translator.mongo.domain.EPathway;
import com.executedpathway.translator.pathway.ExecutedPathway;
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
		FileConfig fileConfig = new FileConfig();
		Resource resource = fileConfig.getResource();		
		
		if (!json.has("type")) {
			ExecutedPathway executedPathway = new ExecutedPathway();
			ePathway = executedPathway.addEPathway(json, resource, ePathway);
		}
		else{
			ExecutedStep executedStep = new ExecutedStep();	
			String type = json.getString("type");
			
			switch (type) {
			case "AuxilioCondutaExecutado":					
				ePathway.getElement().add(executedStep.createEAuxiliaryConduct(json, resource));
				break;
				
			case "TratamentoExecutado":	
				ePathway.getElement().add(executedStep.createETreatment(json, resource));
				break;
						
			case "ReceitaExecutado":	
				ePathway.getElement().add(executedStep.createEPrescription(json, resource));
				break;
				
			case "EncaminhamentoExecutado":	
				ePathway.getElement().add(executedStep.createEReferral(json, resource));
				break;
				
			case "InformacaoExecutado":
				ePathway.getElement().add(executedStep.createEInformation(json, resource));
				break;
				
			case "AltaExecutado":
				ePathway.getElement().add(executedStep.createEDischarge(json, resource));
				break;
	
			default:
				System.out.println("UNKNOWN TYPE!");
				break;
			}	
		}		
	}
}
