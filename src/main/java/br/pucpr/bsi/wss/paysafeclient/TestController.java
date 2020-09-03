package br.pucpr.bsi.wss.paysafeclient;

import br.pucpr.bsi.wss.paysafeclient.ws.CalcPrecoPrazo;
import br.pucpr.bsi.wss.paysafeclient.ws.CalcPrecoPrazoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Controller
public class TestController {
    @Autowired
    private PaysafeClient paysafeClient;
    @GetMapping("/calcPrecoPrazo")
    @ResponseBody
    public  String calcPrecoPrazo(){
        CalcPrecoPrazo request = new CalcPrecoPrazo();
        request.setNCdEmpresa("");
        request.setSDsSenha("");
        request.setNCdServico("04669");
        request.setSCepOrigem("03047000");
        request.setSCepDestino("36834000");
        request.setNVlPeso("2");
        request.setNCdFormato(1);
        request.setNVlComprimento(new BigDecimal(20));
        request.setNVlAltura(new BigDecimal(20));
        request.setNVlLargura(new BigDecimal(20));
        request.setNVlDiametro(new BigDecimal(20));
        request.setSCdMaoPropria("S");
        request.setNVlValorDeclarado(new BigDecimal(30));
        request.setSCdAvisoRecebimento("S");
        CalcPrecoPrazoResponse response = paysafeClient.calcPrecoPrazo(request);
        String msg = "menssagem de erro: "+response.getCalcPrecoPrazoResult().getServicos().getCServico().get(0).getMsgErro()+"<br/>";
        msg= msg + "valor: "+response.getCalcPrecoPrazoResult().getServicos().getCServico().get(0).getValor();
        return  msg;
    }
}
