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
    public Jaxb2Marshaller marshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        // this package must match the package in the <generatePackage> specified in
        // pom.xml
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

    @Bean
    CommandLineRunner lookup(CalculatorClient quoteClient) {
        return args -> {
            AddResponse response = quoteClient.getAddResponse(1,2);
            System.err.println(response.getAddResult());
        };
    }
}
