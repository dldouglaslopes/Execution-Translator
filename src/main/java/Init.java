

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;


public class Init {
	public static void main(String[] args) throws JSONException, IOException, ParseException {
		String folderStr = "./json/";	//path of json folder
		File folder = new File(folderStr);	
		String[] namesFoldersJson = folder.list(); // names of folders that contain in json folder
		
		for (int i = 0; i < namesFoldersJson.length; i++) {
			String pathStr = folderStr + namesFoldersJson[i]; //create the path of each folder in json folder
			File path = new File(pathStr);
			String[] namesJson = path.list(); //names of JSON files	
			
			FileConfig fileConfig = new FileConfig(); //configure files
			Translator translator = new Translator("./xmi/" + namesFoldersJson[i] + ".xmi"); //init constructor		
			
			for(String nameJson : namesJson){					
				JSONObject json = fileConfig.toJSONObject(path + "/" + nameJson); //convert json file to json object							
				System.out.print(nameJson + " / "); 
				translator.toXMI(json); //convert json object to xmi file
			}	
			
			//save the generated contents
			translator.saveContents();
			System.out.println("\n" + namesFoldersJson[i] + ".xmi ---> OK \n");
		}		
	}
}
