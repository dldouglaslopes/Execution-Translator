import org.eclipse.emf.ecore.resource.Resource;
import org.json.JSONArray;
import org.json.JSONObject;

import MetamodelExecution.Element;
import MetamodelExecution.Execution_metamodelFactory;
import MetamodelExecution.Modeling;

public class ModelingExecution {
	public Modeling createModeling(JSONObject json, Resource resource){
		Modeling modeling = Execution_metamodelFactory.eINSTANCE.createModeling();
		
		modeling.setId(json.getInt("id"));
		modeling.setIdRepository(json.getInt("repositorio_id"));
		modeling.setName(json.getString("nome"));
		modeling.setUrl(json.getString("url"));
		modeling.setVersion(json.getInt("versao"));
		modeling.setCode(json.getString("codigo"));
		modeling.setCompleted(json.getBoolean("finalizado"));
		
		JSONArray elements = json.getJSONArray("elementos");
		
		for (int i = 0; i < elements.length(); i++) {		
			Element element = Execution_metamodelFactory.eINSTANCE.createElement();
			JSONObject elem = elements.getJSONObject(i);

			element.setId(elem.getInt("id"));
			element.setType(elem.getString("type"));
			element.setTypeVerbose(elem.getString("type_verbose"));
			element.setUrl(elem.getString("url"));
			element.setName(elem.getString("nome"));
			element.setDescription(elem.getString("descricao"));
			element.setIsInitial(elem.getBoolean("is_initial"));
			element.setIsTerminal(elem.getBoolean("is_terminal"));
			element.setMandatory(elem.getBoolean("obrigatoriedade"));
			
			modeling.getElement().add(element);
		}
		
		return modeling;
	}
}
