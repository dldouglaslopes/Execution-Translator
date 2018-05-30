package init;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.xml.sax.SAXException;

import MetamodelExecution.EPathway;
import config.db.DBConfig;
import config.file.FileConfig;
import database.DBOperations;
import translator.Translator;

public class Init {
	public static void main(String[] args) throws JSONException, IOException, ParseException, ParserConfigurationException, SAXException, InterruptedException {
		List<EPathway> pathways = new ArrayList<EPathway>();
		
		//JSON to XMI
		String folderStr = "./json/";	//path of JSON folder
		File folder = new File(folderStr);	
		String[] namesFoldersJson = folder.list(); // names of folders that contain in JSON folder
		
		for (int i = 0; i < namesFoldersJson.length; i++) {
			String pathStr = folderStr + namesFoldersJson[i]; //create the path of each folder in JSON folder
			File path = new File(pathStr);
			String[] namesJson = path.list(); //names of JSON files	
			
			FileConfig fileConfig = new FileConfig(); //configure files
			fileConfig.setResource("./xmi/" + namesFoldersJson[i] + ".xmi");
			Translator translator = new Translator();		
			
			for(String nameJson : namesJson){					
				JSONObject json = fileConfig.toJSONObject(path + "/" + nameJson); //convert JSON file to JSON object							
				System.out.print(nameJson + " / "); 
				translator.toXMI(json); //convert JSON object to XMI file
			}	
			
			fileConfig.saveContents(translator.getePathway()); //save the generated contents			
			pathways.add(translator.getePathway());
			System.err.println("\n" + namesFoldersJson[i] + ".xmi ---> OK \n");
		}	
				
		DBConfig dbConfig = new DBConfig(); //configuring MongoDB
		DBOperations dbOperations = new DBOperations(); //operation of MongoDB
		System.out.println("*Connected to the database --> OK");		
		
		for (int i = 0; i < pathways.size(); i++) {
			if (dbOperations.hasEPathway(pathways.get(i).getName())) {
				dbOperations.updateEPathway(pathways.get(i).getName(), pathways.get(i));
				System.out.println("-->XMI(s) updated in MongoDB.");			
			}
			else {
				dbOperations.saveEPathway(pathways.get(i).getName(), pathways.get(i));	//adding executed pathway in MongoDB
				System.out.println("-->XMI(s) added in MongoDB.");
			}
					
			//dbConfig.deleteEPathway(pathways.get(0).getName()); //deleting the contents of executed pathway
		}
		
		//Adicionar diretamente de pasta com arquivo XMI
					
		dbConfig.close(); //closing MongoDB client
		System.out.println("*Closed to the database --> OK");
	}
}
