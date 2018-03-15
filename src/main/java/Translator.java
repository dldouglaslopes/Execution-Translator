import java.text.ParseException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.json.JSONObject;

import MetamodelExecution.EPathway;
import MetamodelExecution.Execution_metamodelFactory;

public class Translator {
	private Resource resource;
	private FileConfig file;	
	private EPathway ePathway;
	private ExecutedPathway executedPathway;
	private ExecutedStep executedStep;	
	
	//Constructor
	public Translator(String output){
		this.file = new FileConfig();
		this.resource = file.createResource(output, new String[] {"xml", "xmi"}, new ResourceSetImpl());
		this.ePathway = Execution_metamodelFactory.eINSTANCE.createEPathway();	
		this.executedPathway = new ExecutedPathway();
		this.executedStep = new ExecutedStep();	
	}
	
	//convert JSON files in one XMI file
	public void toXMI(JSONObject json) throws ParseException{
		if (!json.has("type")) {
			ePathway = executedPathway.addEPathway(json, resource, ePathway);
		}
		else{
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
				break;
			}	
		}		
	}
	
	public void saveContents() {
		//save all contents
		resource.getContents().add(ePathway);		
		file.saveResource(resource);
	}
}
