package com.redhat.developers.istio;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@Slf4j
public class GreeterApplication {
    public static void main(String[] args) {
        SpringApplication.run(GreeterApplication.class, args);
    }


    @Configuration
    public class AppConfig {

        @Bean
        public RestTemplate restTemplate() {
            return new RestTemplate();
        }
    }
}
