package com.bidi.users.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestBeanConfig {
    @Bean
    public RestTemplate restTemplateNonCertificate(){
        return new RestTemplate();
    }
}
