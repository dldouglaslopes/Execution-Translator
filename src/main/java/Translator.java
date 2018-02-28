import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.json.JSONObject;

public class Translator {
	public void createXMI(JSONObject json){
		String type = json.getString("type");
		
		ResourceSet resourceSet = new ResourceSetImpl();
	}
}
