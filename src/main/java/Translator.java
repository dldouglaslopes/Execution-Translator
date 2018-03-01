import java.io.IOException;
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

public class Translator {
	private Resource resource;
	private String output;	
	
	//Constructor
	public Translator(){
		this.resource = createResource(output, new String[] {"xml", "xmi"}, new ResourceSetImpl());
	}
	
	public void toXMI(JSONObject json){
		//Set the same information that contain any execution json		
		if(!json.getString("code").isEmpty()){
			ModelingExecution modeling = new ModelingExecution();
			resource.getContents().add(modeling.createModeling(json, resource));			
		}
		if(!json.getString("type").isEmpty()){
			CompleteStep completeStep = new CompleteStep();
			completeStep.createCompleteStep(json, resource);
			//resource.getContents().add();
		}
		else{
			ShortExecution shortExecution = new ShortExecution();
			shortExecution.createShortExecution(json, resource);
			//resource.getContents().add();
		}
		
		//save the content
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
