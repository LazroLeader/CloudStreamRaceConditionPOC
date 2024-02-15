package com.lazro.cloudstreamracecondition;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@EnableConfigurationProperties
@SpringBootApplication
public class CloudStreamRaceConditionApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudStreamRaceConditionApplication.class, args);
    }

}
