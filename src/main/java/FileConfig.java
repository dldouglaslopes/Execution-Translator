import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.json.JSONException;
import org.json.JSONObject;

public class FileConfig {	
	public JSONObject toJSONObject(String path) throws IOException, JSONException{
		String file = "";
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path), "UTF-8"));        
        
        while(br.ready()){
           file = file + br.readLine();
        }

        br.close();
		
        return (new JSONObject(file));	
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
		saveOptions.put(XMLResource.OPTION_ENCODING, "UTF-8");
		try {
			resource.save(saveOptions);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
