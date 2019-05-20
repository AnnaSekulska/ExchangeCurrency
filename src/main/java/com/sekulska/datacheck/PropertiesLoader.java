package com.sekulska.datacheck;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@Component
public class PropertiesLoader {

    private static Map<String, String> requestedParameters = new HashMap<>();

    @PostConstruct
    public void loadProperties(){
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

    public static void setRequestedParameters(String key, String value) {
        requestedParameters.put(key, value);
    }

    public static Map<String, String> getRequestedParameters() {
        return requestedParameters;
    }
}
