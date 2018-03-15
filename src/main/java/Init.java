

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;


public class Init {
	public static void main(String[] args) throws JSONException, IOException, ParseException {
		FileConfig fileConfig = new FileConfig();
		
		//pneumonia_influenza
		Translator translator1 = new Translator("./xmi/pneumonia_influenza.xmi");		
		String folder1 = "./json/pneumonia_influenza";

		//itu_cistite
		Translator translator2 = new Translator("./xmi/itu_cistite.xmi");
		String folder2 = "./json/itu_cistite";
		
		//meningite
		Translator translator3 = new Translator("./xmi/meningite.xmi");
		String folder3 = "./json/meningite";
		
		//fratura_exposta
		Translator translator4 = new Translator("./xmi/fratura_exposta.xmi");
		String folder4 = "./json/fratura_exposta";
		
		//tratamento_sepse
		Translator translator5 = new Translator("./xmi/tratamento_sepse.xmi");
		String folder5 = "./json/tratamento_sepse";
		
		String path = folder5;
		
		File file = new File(path);				
		String[] namesJson = file.list(); //names of JSON files		
		
		for(String nameJson : namesJson){					
			JSONObject json = fileConfig.toJSONObject(path + "/" + nameJson);							
			System.out.println(nameJson);
			translator5.toXMI(json);
		}	
		
		translator5.saveContents();
		System.out.println("OK.");
	}
}
