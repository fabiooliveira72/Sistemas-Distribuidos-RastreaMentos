/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package WS;

import Banco.Conexao;
import Banco.Operacoes;
import Entidades.Posicao;
import Entidades.Veiculo;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.jms.Message;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
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
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getVeiculo/{codigo}")
    public String getVeiculo(@PathParam("codigo") Integer codigo) throws SQLException {
        Veiculo v = new Veiculo();
        //v.setCodigo(codigo);
        //Operacoes cx = new Operacoes();
        v = Operacoes.consultaVeiculo(codigo);
        Gson g = new Gson();
        return g.toJson(v);
//TODO return proper representation object
        //throw new UnsupportedOperationException();   
    }
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getListTypeVeiculo/{tipo}")
    public String listTipoVeiculo(@PathParam("tipo") Integer tipo) throws SQLException {
         List<Veiculo> lista = new ArrayList();
        lista = Operacoes.listaTipo(tipo);
        Gson g = new Gson();
        return g.toJson(lista);
    } 
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("getListPosicao/{codigo}/{datahora}")
     public String listTipoVeiculo(@PathParam("tipo") Integer tipo,@PathParam("datahora") Date datahora) throws SQLException {
         List<Posicao> lista = new ArrayList();
        lista = Operacoes.listaPosicaoVeiculo(tipo, datahora);
        Gson g = new Gson();
        return g.toJson(lista);
    }
     
    //@POST
    //@Consumes(MediaType.APPLICATION_JSON)
    //@Produces(MediaType.APPLICATION_JSON)
    //@Path("postVeiculo")
    //public String adicionaVeiculo(Gson paramJsonVeiculo) throws Exception{  
     //   Gson JsonVeiculo = new Gson();
    //     System.out.println(JsonVeiculo);
    //    JsonVeiculo.toJson(paramJsonVeiculo);
    //return JsonVeiculo.toString();
    //public String adicionaVeiculo(@PathParam("tipo") Integer codigo, String placa, Integer tipo, Integer capacidade, String unpac ) throws SQLException {
        //Veiculo v = new Veiculo();
        //v = Operacoes.consultaVeiculo(codigo);
     //se veiculo encontrado gera msg que não será incluido pq já existe, se não inclui veiculo
        //v = new Veiculo(codigo, placa, tipo, capacidade, unpac); 
        //Operacoes.adicionaVeiculo(v);
        //Gson g = new Gson();
        //return g.toJson("Veiculo Alterado");
    //}
    //}
    @POST
    @Path("/postVeiculo")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public String postJson(String parVeiculo) throws Exception {
        JSONObject parJsonVeiculo = new JSONObject(parVeiculo);
        Veiculo v = new Veiculo();        
        v.setCodigo((Integer) parJsonVeiculo.get("codigo"));
        v.setPlaca(parJsonVeiculo.get("placa").toString());
        v.setTipo((Integer) parJsonVeiculo.get("tipo"));
        v.setCapacidade((Integer) parJsonVeiculo.get("capacidade"));
        v.setUnpac(parJsonVeiculo.get("unpac").toString());
        Operacoes.adicionaVeiculo(v);
       Gson r = new Gson();
        return r.toJson(v.getCapacidade());
    }
     
     
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
