import java.util.HashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.json.JSONObject;

import MetamodelExecution.Execution;
import MetamodelExecution.Execution_metamodelFactory;

public class Translator {
	private Execution execution;
	private Resource resource;
	private String output;	
	
	public Translator(){
		this.resource = createResource(output, new String[] {"xml", "xmi"}, new ResourceSetImpl());
		this.execution = Execution_metamodelFactory.eINSTANCE.createExecution();
	}
	
	public void toXMI(JSONObject json){
		//Set the same information that contain any execution json
		execution.setId(json.getInt("id"));
		execution.setUrl(json.getString("url"));
		execution.setId(json.getInt("id"));
		execution.setCreationDate("cre");		
	}
	
	public static Resource createResource(String output, String[] fileExtensions, ResourceSet resourceSet) {
	     for (String fileExtension : fileExtensions) {
	        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(fileExtension, new XMLResourceFactoryImpl());
	     }		
	     
	     URI uri = URI.createFileURI(output);
	     Resource resource = resourceSet.createResource(uri);
	     ((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap());
	     
	     return resource;
	}
}
