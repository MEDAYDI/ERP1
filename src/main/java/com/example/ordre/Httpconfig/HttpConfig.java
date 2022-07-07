package com.example.ordre.Httpconfig;

import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

public class HttpConfig {
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
