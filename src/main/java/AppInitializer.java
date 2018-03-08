

import java.io.IOException;
import java.text.ParseException;

import org.json.JSONException;
import org.json.JSONObject;


public class AppInitializer {
	public static void main(String[] args) throws JSONException, IOException, ParseException {
		String path = "/json/pneumonia_influenza/modelagem.json";
		File file = new File();		
		JSONObject json = file.toJSONObject(path);
		
		Translator translator = new Translator();
		
		for (int i = 0; i < args.length; i++) {
			System.out.println("Tranlate file " + i + "...");
			translator.toXMI(json);
			System.out.print("OK.");
		}
		
		System.out.println("Finish.");
	}
}
