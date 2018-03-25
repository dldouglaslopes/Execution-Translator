package init;



import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.json.JSONException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;	


public class Init {
	public static void main(String[] args) throws JSONException, IOException, ParseException, ParserConfigurationException, SAXException {
//		String folderStr = "./json/";	//path of json folder
//		File folder = new File(folderStr);	
//		String[] namesFoldersJson = folder.list(); // names of folders that contain in json folder
//		
//		for (int i = 0; i < namesFoldersJson.length; i++) {
//			String pathStr = folderStr + namesFoldersJson[i]; //create the path of each folder in json folder
//			File path = new File(pathStr);
//			String[] namesJson = path.list(); //names of JSON files	
//			
//			FileConfig fileConfig = new FileConfig(); //configure files
//			Translator translator = new Translator("./xmi/" + namesFoldersJson[i] + ".xmi"); //init constructor		
//			
//			for(String nameJson : namesJson){					
//				JSONObject json = fileConfig.toJSONObject(path + "/" + nameJson); //convert json file to json object							
//				System.out.print(nameJson + " / "); 
//				translator.toXMI(json); //convert json object to xmi file
//			}	
//			
//			translator.saveContents(); //save the generated contents
//			System.out.println("\n" + namesFoldersJson[i] + ".xmi ---> OK \n");
//		}		
				
//		DBConfig executionDB = new DBConfig();//Configure db
//		executionDB.connect();
//		executionDB.createDatabase("teste"); //create a database if it is not exists
//		executionDB.createCollection("table"); //create a table if it is not exists
		
		File xmi = new File("./xmi/fratura_exposta.xmi");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(xmi);
				
		doc.getDocumentElement().normalize();

		System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
		
		System.out.println("id " + doc.getDocumentElement().getAttribute("id"));
		
		NodeList nList = doc.getElementsByTagName("element");
		
		System.out.println("----------------------------");

		for (int temp = 0; temp < nList.getLength(); temp++) {

			Node nNode = nList.item(temp);
					
			System.out.println("\nCurrent Element :" + nNode.getNodeName());
		}
	}
}
