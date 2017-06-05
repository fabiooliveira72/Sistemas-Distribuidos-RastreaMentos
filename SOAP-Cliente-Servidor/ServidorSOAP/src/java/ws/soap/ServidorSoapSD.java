/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws.soap;



import Banco.Operacoes;
import Entidades.Posicao;
import Entidades.Veiculo;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;


/**
 *
 * @author Angelica
 */
@WebService(serviceName = "ServidorSOAP")
public class ServidorSoapSD {
    
    
    /**
     * Operação de Web service
     */
    private String getDataHora(){
        return new Date().toString();
    }
    
    @WebMethod(operationName = "adicionaVeiculo")
    public boolean adicionaVeiculo(@WebParam(name = "veiculo") Veiculo veiculo){
        try {
            Operacoes.adicionaVeiculo(veiculo);
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> Adiciona Veiculo", "ServidorSOAP");
            System.out.println(getDataHora()+" ServidorSOAP -> Adiciona Veiculo");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> SQLEXCPETION adiciona veiculo-> "+ex.getMessage(), "ServidorSOAP");
            return false;
        }
    }
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "alteraVeiculo")
    public boolean alteraVeiculo(@WebParam(name = "veiculo") Veiculo veiculo) {
        try {
            Operacoes.alteraVeiculo(veiculo);
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> Altera Veiculo", "ServidorSOAP");
            System.out.println(getDataHora()+" ServidorSOAP -> Altera Veiculo");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> SQLEXCPETION altera veiculo-> "+ex.getMessage(), "ServidorSOAP");
            return false;
        } 
    }
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "deletaVeiculo")
    public boolean deletaVeiculo(@WebParam(name = "codigo") int codigo) {
        try {
            Operacoes.deletaVeiculo(codigo);
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> Deleta Veiculo", "ServidorSOAP");
            System.out.println(getDataHora()+" ServidorSOAP -> Deleta Veiculo");
            return true;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> SQLEXCPETION deleta veiculo-> "+ex.getMessage(), "ServidorSOAP");
            return false;
        }
    }
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "consultaVeiculo")
    public Veiculo consultaVeiculo(@WebParam(name = "codigo") int codigo) {
       Veiculo v= null;
        try {
            v  = Operacoes.consultaVeiculo(codigo);
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> Consulta Veiculo", "ServidorSOAP");
            System.out.println(getDataHora()+" ServidorSOAP -> Consulta Veiculo");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> SQLEXCPETION consulta veiculo-> "+ex.getMessage(), "ServidorSOAP");
            
        } 
        return v;
    }
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "listaTipo")
    public List<Veiculo> listaTipo(@WebParam(name = "codigo") int codigo) {
        try {
           LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> Lista Tipo Veiculo", "ServidorSOAP");
           System.out.println(getDataHora()+" ServidorSOAP -> Lista Tipo Veiculo");
           return Operacoes.listaTipo(codigo);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> SQLEXCPETION lista tipo-> "+ex.getMessage(), "ServidorSOAP");
            return null;
        } 
    }
    
    
    /**
     * Operação de Web service
     */
    @WebMethod(operationName = "listaPosicaoVeiculo")
    public List<Posicao> listaPosicaoVeiculo(@WebParam(name = "codigo") int codigo, @WebParam(name = "datahora") Date datahora) {
        try {
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> Lista Posicao Veiculo", "ServidorSOAP");
            System.out.println(getDataHora()+" ServidorSOAP -> Lista Posicao Veiculo");
            return Operacoes.listaPosicaoVeiculo(codigo, datahora);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorSOAP -> SQLEXCPETION lista posicao veiculo -> "+ex.getMessage(), "ServidorSOAP");
            return null;
        }
    }
}
