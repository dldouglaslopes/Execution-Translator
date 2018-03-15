

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;


public class Init {
	public static void main(String[] args) throws JSONException, IOException, ParseException {
		FileConfig fileConfig = new FileConfig();
		Translator translator = new Translator("./xmi/pneumonia_influenza.xmi");
		
		String folder1 = "./json/pneumonia_influenza";
		String folder2 = "./json/itu_cistite";
		String folder3 = "./json/meningite";
		String folder4 = "./json/fratura_exposta";
		String folder5 = "./json/tratamento_sepse";
		
		String path = folder1;
		
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
