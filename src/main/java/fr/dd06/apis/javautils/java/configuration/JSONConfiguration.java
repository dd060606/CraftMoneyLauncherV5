package fr.dd06.apis.javautils.java.configuration;

import fr.dd06.apis.javautils.org.json.simple.JSONObject;
import fr.dd06.apis.javautils.org.json.simple.parser.JSONParser;
import fr.dd06.apis.javautils.org.json.simple.parser.ParseException;

import java.io.*;

public class JSONConfiguration {
	private File configFile;
	private JSONObject jsonObject;
	public JSONConfiguration(File configFile) {
		this.configFile = configFile;
	}
	public void reloadConfiguration() {
		if (!configFile.exists()) {
			try {
				configFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		JSONParser parser = new JSONParser();

		try {
			FileReader reader = new FileReader(configFile);
			Object readedObject = parser.parse(reader);
			jsonObject = (JSONObject) readedObject;

			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			jsonObject = new JSONObject();

		}
	}

	public void saveConfiguration() {

		try {
			FileWriter writer = new FileWriter(configFile);
			writer.write(jsonObject.toJSONString());
			writer.flush();
			writer.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
	public JSONObject getConfiguration() {
		return jsonObject;
	}
}
