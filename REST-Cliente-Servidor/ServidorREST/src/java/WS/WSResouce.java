package WS;



import Banco.Operacoes;
import Entidades.Posicao;
import Entidades.Veiculo;
import com.google.gson.Gson;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import java.util.Date;

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
    @Path("WSRRastreamento/getVeiculo/{codigo}")
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
    @Path("WSRRastreamento/getListaTipo/{tipo}")
    public String listTipoVeiculo(@PathParam("tipo") Integer tipo) throws SQLException {
         List<Veiculo> lista = new ArrayList();
        lista = Operacoes.listaTipo(tipo);
        Gson g = new Gson();
        return g.toJson(lista);
//TODO return proper representation object
        //throw new UnsupportedOperationException();
    }  
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("WSRRastreamento/getLocalizacao/{codigo}/{datahora}")
    public String listTipoVeiculo(@PathParam("tipo") Integer tipo,Date datahora) throws SQLException {
         List<Posicao> lista = new ArrayList();
        lista = Operacoes.listaPosicaoVeiculo(tipo, datahora);
        Gson g = new Gson();
        return g.toJson(lista);
//TODO return proper representation object
        //throw new UnsupportedOperationException();
    }
    /**
     * PUT method for updating or creating an instance of WSResouce
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
