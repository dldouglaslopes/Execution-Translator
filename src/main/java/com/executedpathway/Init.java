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
import com.executedpathway.translator.domain.EPathway;
import com.executedpathway.translator.model.Translator;
import com.executedpathway.translator.repository.EPathwayRepository;

public class Init {
	@Autowired
	private static EPathwayRepository ePathwayRepository;
	
	public static void main(String[] args) throws JSONException, IOException, ParseException, ParserConfigurationException, SAXException, InterruptedException {
		List<EPathway> pathways = new ArrayList<EPathway>();
		
		//JSON to XMI
		String folderStr = "C:/Users/dldou/Dropbox/Pesquisa/ArquivosExecucao/Novo/ProtocolosExecutados/";	//path of JSON folder
		File folder = new File(folderStr);	
		String[] namesStr = folder.list(); // names of folders that contain in JSON folder
		
		for (int i = 0; i < namesStr.length; i++) {
			String pathStr = folderStr + namesStr[i]; //create the path of each folder in JSON folder
			System.out.println(namesStr[i]);
			
			int pos = namesStr[i].lastIndexOf(".");
			if (pos > 0 && pos < (namesStr[i].length() - 1)) {
				namesStr[i] = namesStr[i].substring(0, pos);
			}
			
			FileConfig fileConfig = new FileConfig(); //configure files
			fileConfig.setResource("./xmi/" +  namesStr[i] + ".xmi");
			Translator translator = new Translator();				
								
			JSONObject json = fileConfig.toJSONObject(pathStr); //convert JSON file to JSON object
			translator.toXMI(json); //convert JSON object to XMI files				
			
			fileConfig.saveContents(translator.getePathway()); //save the generated contents			
			pathways.add(translator.getePathway());
			System.err.println("\n" + namesStr[i] + ".xmi ---> OK \n");
		}		
		
//		ePathwayRepository.saveAll(pathways);
//		System.out.println("-->XMI(s) updated in MongoDB.");										
	}
}
