package com.sekulska.datacheck;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;

@Component
@Scope("singleton")
public class Properties {

    private String dailyFunction;
    private String realTimeFunction;
    private String apiKey;

    @PostConstruct
    public void loadProperties(){
        try(InputStream input = Properties.class.getClassLoader().getResourceAsStream("urlConfig.properties")) {
            java.util.Properties properties = new java.util.Properties();
            if(input == null) throw new ResourcesNotFoundException("Unable to find urlConfig.properties");
            properties.load(input);
            setRequestedParameters(properties);

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setRequestedParameters(java.util.Properties properties){
        dailyFunction = properties.getProperty("daily_function");
        realTimeFunction = properties.getProperty("real_time_function");
        apiKey = properties.getProperty("api_key");
    }

    public String getDailyFunction() {
        return dailyFunction;
    }

    public String getRealTimeFunction() {
        return realTimeFunction;
    }

    public String getApiKey() {
        return apiKey;
    }
}
