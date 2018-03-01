

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
		
		translator.toXMI(json);
	}
}
