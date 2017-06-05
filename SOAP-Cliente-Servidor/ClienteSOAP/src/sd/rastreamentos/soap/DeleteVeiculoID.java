/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sd.rastreamentos.soap;

import ws.soap.ServidorSOAP;
import ws.soap.ServidorSoapSD;

/**
 *
 * @author oliveira
 */
public class DeleteVeiculoID {
    
    public static boolean DeletaVeiculo(Integer codigo){
         ServidorSOAP service = new ServidorSOAP();
         ServidorSoapSD port = service.getServidorSoapSDPort();
         return port.deletaVeiculo(codigo);
        
    }
    
}
