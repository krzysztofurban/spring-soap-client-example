package com.example.springsoapexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.SoapMessage;
import org.springframework.ws.soap.saaj.SaajSoapMessage;
import org.tempuri.Add;
import org.tempuri.AddResponse;

import javax.xml.soap.*;
import javax.xml.transform.TransformerException;
import java.io.IOException;

public class CalculatorClient extends WebServiceGatewaySupport {
    private static final Logger log = LoggerFactory.getLogger(CalculatorClient.class);

    public AddResponse getAddResponse(int a, int b) {
        Add request = new Add();
        request.setIntA(a);
        request.setIntB(b);
        log.info("Requesting addition result for {}", request.toString());

        AddResponse response = (AddResponse) getWebServiceTemplate()
                .marshalSendAndReceive("http://www.dneonline.com/calculator.asmx", request, originMessage -> {
                    SaajSoapMessage message= (SaajSoapMessage) originMessage;
                    try {
                        SOAPMessage saajMessage = message.getSaajMessage();
                        SOAPEnvelope envelope = saajMessage.getSOAPPart().getEnvelope();
                        envelope.removeNamespaceDeclaration(envelope.getPrefix());
                        envelope.addNamespaceDeclaration("soapenv", "http://schemas.xmlsoap.org/soap/envelope/");
                        envelope.addNamespaceDeclaration("tem",  "http://tempuri.org/");
                        envelope.setPrefix("soapenv");

                        SOAPBody soapBody = saajMessage.getSOAPBody();
                        soapBody.removeNamespaceDeclaration(soapBody.getPrefix());
                        soapBody.setPrefix("soapenv");

                        SOAPHeader soapHeader = saajMessage.getSOAPHeader();
                        soapHeader.removeNamespaceDeclaration(soapHeader.getPrefix());
                        soapHeader.setPrefix("soapenv");
                    } catch (SOAPException e) {
                        e.printStackTrace();
                    }
                    ((SaajSoapMessage) originMessage).setSoapAction("http://tempuri.org/Add");
                });

        return response;
    }
}
