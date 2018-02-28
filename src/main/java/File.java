import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.JSONException;
import org.json.JSONObject;

public class File {	
	public JSONObject toJSONObject(String path) throws IOException, JSONException{
		String file = "";
        BufferedReader br = new BufferedReader(new FileReader(path));        
        
        while(br.ready()){
           file = file + br.readLine();
        }

        br.close();
		
        return (new JSONObject(file));	
	}
}
