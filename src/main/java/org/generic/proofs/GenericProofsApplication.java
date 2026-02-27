package org.generic.proofs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class GenericProofsApplication {
    public static void main(String[] args) {
        SpringApplication.run(GenericProofsApplication.class, args);
    }
}
