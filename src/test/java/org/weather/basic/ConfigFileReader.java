package org.weather.basic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigFileReader {
  private Properties properties;
	private final String propertyFilePath= ".\\resource\\env.properties";
	
	public ConfigFileReader(){
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}		
	}

	public String getKeyType(){
		String key = properties.getProperty("Key.type");
		if(key != null) return key;
		else throw new RuntimeException("key not specified in the Configuration.properties file.");		
	}
	
	public String getKeyValue(){
		String keyvalue = properties.getProperty("Key.value");
		if(keyvalue!= null) return keyvalue;
		else throw new RuntimeException("key value not specified in the Configuration.properties file.");		
	}
	
	public String getApiBaseUrl() {
		String url = properties.getProperty("Api.baseUrl");
		if(url != null) return url;
		else throw new RuntimeException("base url not specified in the Configuration.properties file.");
	}
	
	public String getContentType() {
		String contentType = properties.getProperty("ContentType");
		if(contentType!= null) return contentType;
		else throw new RuntimeException("Content Type not specified in the Configuration.properties file.");
	}
}
