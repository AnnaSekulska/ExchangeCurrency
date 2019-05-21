package com.sekulska.datacheck;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
@Scope("singleton")
public class ApiProperties {

    private String dailyFunction;
    private String realTimeFunction;
    private String outputSize;
    private String apiKey;

    @PostConstruct
    public void loadProperties(){
        try(InputStream input = ApiProperties.class.getClassLoader().getResourceAsStream("urlConfig.properties")) {
            Properties properties = new Properties();
            if(input == null) throw new ResourcesNotFoundException("Unable to find urlConfig.properties");
            properties.load(input);
            setRequestedParameters(properties);

        }catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void setRequestedParameters(Properties properties){
        dailyFunction = properties.getProperty("daily_function");
        realTimeFunction = properties.getProperty("real_time_function");
        outputSize = properties.getProperty("output_size");
        apiKey = properties.getProperty("api_key");
    }

    public String getDailyFunction() {
        return dailyFunction;
    }

    public String getRealTimeFunction() {
        return realTimeFunction;
    }

    public String getOutputSize() { return outputSize; }

    public String getApiKey() {
        return apiKey;
    }
}
