package com.example.springsoapexample;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.tempuri.AddResponse;

@SpringBootApplication
public class SpringSoapExampleApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSoapExampleApplication.class, args);
    }

    @Bean
    CommandLineRunner lookup(CalculatorClient quoteClient) {
        return args -> {
            AddResponse response = quoteClient.getAddResponse(1,2);
            System.err.println(response.getAddResult());
        };
    }
}
