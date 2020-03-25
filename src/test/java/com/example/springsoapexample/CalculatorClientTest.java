package com.example.springsoapexample;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.test.client.MockWebServiceServer;


@RunWith(SpringRunner.class)
@SpringBootTest
public class CalculatorClientTest {

    @Autowired
    private CalculatorClient calculatorClient;

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    private MockWebServiceServer mockWebServiceServer;

    @Before
    public void createServer() {
        mockWebServiceServer = MockWebServiceServer.createServer(webServiceTemplate);
    }

    @Test
    void contextLoads() {
        System.out.println("Helo from test");
    }
}
