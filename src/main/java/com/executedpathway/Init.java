package com.executedpathway;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import com.executedpathway.translator.config.FileConfig;
import com.executedpathway.translator.model.Translator;
import com.executedpathway.translator.mongo.domain.EPathway;
import com.executedpathway.translator.mongo.repository.EPathwayRepository;

public class Init {
	@Autowired
	private static EPathwayRepository ePathwayRepository;
	
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
		
		ePathwayRepository.saveAll(pathways);
		System.out.println("-->XMI(s) updated in MongoDB.");										
	}
}
