import java.io.IOException;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.json.JSONObject;

import MetamodelExecution.EPathway;
import MetamodelExecution.Execution_metamodelFactory;

public class Translator {
	private Resource resource;
	private String output;	
	
	//Constructor
	public Translator(){
		this.resource = createResource(output, new String[] {"xml", "xmi"}, new ResourceSetImpl());
	}
	
	public void toXMI(JSONObject json) throws ParseException{
		Execution execution = new Execution();
		EPathway ePathway = Execution_metamodelFactory.eINSTANCE.createEPathway();
		
		if (json.getString("type").isEmpty()) {
			ePathway = execution.addEPathway(json, resource, ePathway);
		}
		else{
			String type = json.getString("type");
			
			switch (type) {
				case "AuxilioCondutaExecutado":					
					ePathway.getElement().add(execution.addEAuxiliaryConduct(json, resource));
					break;
				case "TratamentoExecutado":	
					ePathway.getElement().add(execution.addETreatment(json, resource));
					break;
							
				case "ReceitaExecutado":	
					ePathway.getElement().add(execution.addEPrescription(json, resource));
					break;
					
				case "EncaminhamentoExecutado":	
					ePathway.getElement().add(execution.addEReferral(json, resource));
					break;
					
				case "InformacaoExecutado":
					ePathway.getElement().add(execution.addEInformation(json, resource));
					break;
					
				case "AltaExecutado":
					ePathway.getElement().add(execution.addEDischarge(json, resource));
					break;
		
				default:
					break;
				}
		}		
		
		//save all contents
		resource.getContents().add(ePathway);		
		saveResource(resource);
	}
	
	public Resource createResource(String output, String[] fileExtensions, ResourceSet resourceSet) {
	     for (String fileExtension : fileExtensions) {
	        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(fileExtension, new XMLResourceFactoryImpl());
	     }		
	     
	     URI uri = URI.createFileURI(output);
	     Resource resource = resourceSet.createResource(uri);
	     ((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap());
	     
	     return resource;
	}
	
	public void saveResource(Resource resource) {
		Map saveOptions = ((XMLResource)resource).getDefaultSaveOptions();
		saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
		saveOptions.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList());
		try {
			resource.save(saveOptions);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
