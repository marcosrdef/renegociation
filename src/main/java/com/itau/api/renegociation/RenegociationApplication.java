package com.itau.api.renegociation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class RenegociationApplication {
    public static void main(String[] args) {
        SpringApplication.run(RenegociationApplication.class, args);
    }
}
