/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import Banco.Operacoes;
import Entidades.Posicao;
import Entidades.Veiculo;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author Jisselea
 */
@Path("WSRRastreamento")
public class WSResouce {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of WSResouce
     */
    public WSResouce() {
    }

    /**
     * Retrieves representation of an instance of WS.WSResouce
     * @param codigo
     * @return an instance of java.lang.String
     */
    
    private Date getDataHora(){
        return new Date();
    }
     
    @POST
    @Path("/adicionaveiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String adicionaVeiculo(String parVeiculo) {
        JSONObject parJsonVeiculo = new JSONObject(parVeiculo);
        Veiculo v = new Veiculo();        
        v.setCodigo(Integer.parseInt((String) parJsonVeiculo.get("codigo")));
        v.setPlaca(parJsonVeiculo.get("placa").toString());
        v.setTipo(Integer.parseInt((String) parJsonVeiculo.get("tipo")));
        v.setCapacidade(Integer.parseInt((String) parJsonVeiculo.get("capacidade")));
        v.setUnpac(parJsonVeiculo.get("unpac").toString());
        Gson r = new Gson();
        
        try {
            Operacoes.adicionaVeiculo(v);
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> Adiciona Veiculo", "ServidorREST");
            System.out.println(getDataHora()+" ServidorREST -> Adiciona Veiculo");
            return r.toJson(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> SQLEXCPETION Adiciona veiculo-> "+ex.getMessage(), "ServidorREST");
            return r.toJson(0);
        }
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/alteraveiculo")
    public String alteraVeiculo(String parVeiculo) {
        JSONObject parJsonVeiculo = new JSONObject(parVeiculo);
        Gson r = new Gson();
        Veiculo v = new Veiculo();        
        
        try {
        v.setCodigo(Integer.parseInt((String) parJsonVeiculo.get("codigo")));
            Operacoes.consultaVeiculo(v.getCodigo());
            
        v.setPlaca(parJsonVeiculo.get("placa").toString());
        v.setTipo(Integer.parseInt((String) parJsonVeiculo.get("tipo")));
        v.setCapacidade(Integer.parseInt((String) parJsonVeiculo.get("capacidade")));
        v.setUnpac(parJsonVeiculo.get("unpac").toString());
            Operacoes.alteraVeiculo(v);
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> Altera Veiculo", "ServidorREST");
            System.out.println(getDataHora()+" ServidorREST -> Altera Veiculo");
            return r.toJson(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> SQLEXCPETION Altera veiculo-> "+ex.getMessage(), "ServidorREST");
            return r.toJson(0);
        }
    }
    
    @DELETE
    @Path("deleteveiculo/{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteVeiculo(@PathParam("codigo") Integer codigo) {
        
        Gson r = new Gson();
        try {
            Operacoes.consultaVeiculo(codigo);
            Operacoes.deletaVeiculo(codigo);
            
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> Deleta Veiculo", "ServidorREST");
            System.out.println(getDataHora()+" ServidorREST -> Deleta Veiculo");
            return r.toJson(1);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> SQLEXCPETION Deleta veiculo-> "+ex.getMessage(), "ServidorREST");
            return r.toJson(0);
        }
    }
    
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("consultaveiculo/{codigo}")
    public String ConsultaVeiculo(@PathParam("codigo") Integer codigo) {
        Gson g = new Gson();
        try {
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> Consulta Veiculo", "ServidorREST");
            System.out.println(getDataHora()+" ServidorREST -> Consulta Veiculo");
            return g.toJson(Operacoes.consultaVeiculo(codigo));
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> SQLEXCPETION Consulta veiculo-> "+ex.getMessage(), "ServidorREST");
            return g.toJson(0);
        }
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listatipoveiculo/{tipo}")
    public String listTipoVeiculo(@PathParam("tipo") Integer tipo) {
        List<Veiculo> lista = new ArrayList();
        Gson g = new Gson();
        try {
            lista = Operacoes.listaTipo(tipo);
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> Lista Tipo Veiculo", "ServidorREST");
            System.out.println(getDataHora()+" ServidorREST -> Lista Tipo Veiculo");
            return g.toJson(lista);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> SQLEXCPETION Lista Tipo veiculo-> "+ex.getMessage(), "ServidorREST");            
            return null;
        }
    } 
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("listaposicao/{codigo}/{datahora}")
     public String listaPosicao(@PathParam("codigo") Integer codigo,@PathParam("datahora") String datahora) {
         //DATAHORA = YYYY-MM-DD-HH-MM-SS or null
        Gson g = new Gson();
        Date data = null;
         if(datahora.length() > 5){
            String dtstring = new String(datahora.trim());
            String[] parts = dtstring.split("-");
            GregorianCalendar gc = new GregorianCalendar();
            gc.set(Calendar.YEAR, Integer.parseInt(parts[0]));
            gc.set(Calendar.MONTH, Integer.parseInt(parts[1])-1);
            gc.set(Calendar.DAY_OF_MONTH, Integer.parseInt(parts[2]));
            gc.set(Calendar.HOUR_OF_DAY, Integer.parseInt(parts[3]));
            gc.set(Calendar.MINUTE, Integer.parseInt(parts[4]));
            gc.set(Calendar.SECOND, Integer.parseInt(parts[5]));
            data = gc.getTime();
         }
        try {
            List<Posicao> lista = Operacoes.listaPosicaoVeiculo(codigo, data);
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> Lista Posicao Veiculo", "ServidorREST");
            System.out.println(getDataHora()+" ServidorREST -> Lista Posicao Veiculo");
            return g.toJson(lista);
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(getDataHora()+" ServidorREST -> SQLEXCPETION Lista Posicao veiculo-> "+ex.getMessage(), "ServidorREST");
            return null;
        }
    }
    
}
