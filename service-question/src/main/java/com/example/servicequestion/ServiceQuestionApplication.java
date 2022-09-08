package com.example.servicequestion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class ServiceQuestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServiceQuestionApplication.class, args);
    }

}
