package com.example.springsoapexample;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.core.WebServiceTemplate;

@Configuration
public class SoapConfiguration {

    @Bean
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in gradle/pom
        // I moved them manually into src directory
        marshaller.setContextPath("org.tempuri");
        return marshaller;
    }

    @Bean
    public CalculatorClient countryClient(Jaxb2Marshaller marshaller) {
        CalculatorClient client = new CalculatorClient();
        client.setDefaultUri("http://www.dneonline.com/calculator.asmx");
        client.setWebServiceTemplate(new WebServiceTemplate());
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }
}
