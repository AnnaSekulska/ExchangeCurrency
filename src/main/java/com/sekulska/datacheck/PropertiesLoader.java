package com.sekulska.datacheck;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

public class PropertiesLoader {

    public static void loadProperties(Map<String, String> requestedParameters){
        try(InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream("urlConfig.properties")) {
            Properties properties = new Properties();
            if(input == null) throw new ResourcesNotFoundException("Unable to find urlConfig.properties");
            properties.load(input);
            requestedParameters.put("function", properties.getProperty("function"));
            requestedParameters.put("apikey", properties.getProperty("apikey"));
        }catch (IOException ex) {
            ex.printStackTrace();
        }

    }
}
