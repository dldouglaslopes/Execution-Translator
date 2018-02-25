

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class ExecutionReader {
	private JSONObject json;
	
	public JSONObject getJson() {
		return json;
	}
	
	public ExecutionReader(String path) throws IOException, JSONException{
		String file = "";
        BufferedReader br = new BufferedReader(new FileReader(path));        
        
        while(br.ready()){
           file = file + br.readLine();
        }

        br.close();
		
        json = new JSONObject(file);	
	}
}
