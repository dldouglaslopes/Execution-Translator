

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;


public class Init {
	public static void main(String[] args) throws JSONException, IOException, ParseException {
		FileConfig fileConfig = new FileConfig();
		Translator translator = new Translator("./xmi/pneumonia_influenza.xmi");
		String path = "./json/pneumonia_influenza";		
		File file = new File(path);				
		String[] namesJson = file.list(); //names of JSON files
		
		for(String nameJson : namesJson){					
			JSONObject json = fileConfig.toJSONObject(path + "/" + nameJson);							
			System.out.println(nameJson);
			translator.toXMI(json);
		}	
		
		translator.saveContents();
		System.out.println("OK.");
	}
}
