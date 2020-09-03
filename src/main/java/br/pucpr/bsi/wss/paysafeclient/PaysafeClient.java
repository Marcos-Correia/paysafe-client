package br.pucpr.bsi.wss.paysafeclient;

import br.pucpr.bsi.wss.paysafeclient.ws.CalcPrecoPrazo;
import br.pucpr.bsi.wss.paysafeclient.ws.CalcPrecoPrazoResponse;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.stereotype.Service;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;

@Service
public class PaysafeClient {
    public CalcPrecoPrazoResponse calcPrecoPrazo (CalcPrecoPrazo request){
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setPackagesToScan("br.pucpr.bsi.wss.paysafeclient.ws");

        WebServiceTemplate ws = new WebServiceTemplate(marshaller);
        CalcPrecoPrazoResponse response = (CalcPrecoPrazoResponse) 
                ws.marshalSendAndReceive("http://ws.correios.com.br/calculador/CalcPrecoPrazo.asmx", request,
                webServiceMessage -> ((SoapMessage) webServiceMessage).setSoapAction("http://tempuri.org/CalcPrecoPrazo"));
        return response;
    }
}
