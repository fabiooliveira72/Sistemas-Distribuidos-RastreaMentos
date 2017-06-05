/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.rastreamentos.soap;

import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import ws.soap.Posicao;
import ws.soap.ServidorSOAP;
import ws.soap.ServidorSoapSD;
/**
 *
 * @author oliveira
 */
public class ListaPosicaoVeiculoID {
    
    public static List<Posicao> ListaPosicao(Integer codigo, XMLGregorianCalendar dataHora){
         ServidorSOAP service = new ServidorSOAP();
         ServidorSoapSD port = service.getServidorSoapSDPort();
         return port.listaPosicaoVeiculo(codigo, dataHora);
    }
}
