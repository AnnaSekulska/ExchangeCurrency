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

    private static Map<String, String> requestedParamFxDaily = new HashMap<>();
    private static Map<String, String> requestedParamRealTime = new HashMap<>();

    @PostConstruct
    public void loadProperties(){
        try(InputStream input = PropertiesLoader.class.getClassLoader().getResourceAsStream("urlConfig.properties")) {
            Properties properties = new Properties();
            if(input == null) throw new ResourcesNotFoundException("Unable to find urlConfig.properties");
            properties.load(input);

            setRequestedParameters(properties);

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setRequestedParameters(Properties properties){
        setRequestedParamFxDaily("function", properties.getProperty("daily_function"));
        setRequestedParamRealTime("function", properties.getProperty("real_time_function"));
        setApiKey("apikey", properties.getProperty("api_key"));
    }

    public static Map<String, String> getRequestedParamFxDaily() {
        return requestedParamFxDaily;
    }

    public static void setRequestedParamFxDaily(String key, String value) {
        requestedParamFxDaily.put(key, value);
    }

    public static Map<String, String> getRequestedParamRealTime() {
        return requestedParamRealTime;
    }

    public static void setRequestedParamRealTime(String key, String value) {
        requestedParamRealTime.put(key, value);
    }

    private void setApiKey(String key, String value){
        requestedParamFxDaily.put(key, value);
        requestedParamRealTime.put(key, value);
    }
}
