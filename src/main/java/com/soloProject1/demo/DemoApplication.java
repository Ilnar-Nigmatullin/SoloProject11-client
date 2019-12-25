package com.soloProject1.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
//@ComponentScan("com.soloProject1.demo")
public class DemoApplication {

    @Bean
    public RestTemplate getRestTemplate(RestTemplateBuilder builder) {
        return builder.basicAuthentication("a", "a").build();
    }

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

}
