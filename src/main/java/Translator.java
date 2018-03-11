import java.text.ParseException;

import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.json.JSONObject;

import MetamodelExecution.EPathway;
import MetamodelExecution.Execution_metamodelFactory;

public class Translator {
	private Resource resource;
	private String output;
	private File file;	
	
	//Constructor
	public Translator(){
		this.file = new File();
		this.resource = file.createResource(output, new String[] {"xml", "xmi"}, new ResourceSetImpl());
	}
	
	//convert JSON files in one XMI file
	public void toXMI(JSONObject json) throws ParseException{
		ExecutedPathway executedPathway = new ExecutedPathway();
		ExecutedStep executedStep = new ExecutedStep();
		EPathway ePathway = Execution_metamodelFactory.eINSTANCE.createEPathway();		
		
		String type = json.getString("type");
		
		switch (type) {
			case "AuxilioCondutaExecutado":					
				ePathway.getElement().add(executedStep.addEAuxiliaryConduct(json, resource));
				break;
				
			case "TratamentoExecutado":	
				ePathway.getElement().add(executedStep.addETreatment(json, resource));
				break;
						
			case "ReceitaExecutado":	
				ePathway.getElement().add(executedStep.addEPrescription(json, resource));
				break;
				
			case "EncaminhamentoExecutado":	
				ePathway.getElement().add(executedStep.addEReferral(json, resource));
				break;
				
			case "InformacaoExecutado":
				ePathway.getElement().add(executedStep.addEInformation(json, resource));
				break;
				
			case "AltaExecutado":
				ePathway.getElement().add(executedStep.addEDischarge(json, resource));
				break;
	
			default:
				ePathway = executedPathway.addEPathway(json, resource, ePathway);
				break;
			}	
		
		//save all contents
		resource.getContents().add(ePathway);		
		file.saveResource(resource);
	}
}
