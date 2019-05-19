package com.sekulska.configuration;

import com.sekulska.http.HttpCaller;
import com.sekulska.http.RetrofitBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
public class HttpCallerConfiguration {

    @Bean
    public RetrofitBuilder getRetrofitBuilder(){
        return new RetrofitBuilder();
    }

    @Bean
    public HttpCaller getHttpCaller(){
        return getRetrofitBuilder().getRetrofit().create(HttpCaller.class);
    }
}
