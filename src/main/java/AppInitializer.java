

import java.io.IOException;
import org.json.JSONException;


public class AppInitializer {
	public static void main(String[] args) throws JSONException, IOException {
		String file = "";
		ExecutionReader executionReader = new ExecutionReader(file);
		
		new Translator().createXMI(executionReader.getJson());
	}
}
