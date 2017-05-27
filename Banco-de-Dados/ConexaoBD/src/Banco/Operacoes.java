/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Entidades.Posicao;
import Entidades.Veiculo;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author eduardo
 */
public class Operacoes {
    
    //OPERAÇÕES REPLICAÇÃO
    public static void beginReplica() throws SQLException{
        String sql = "select f_conecta()";
        PreparedStatement stmt = Conexao.getPreparedStatement(sql);
        stmt.execute();
    }
    
    
    public static void endReplica() throws SQLException{
        String sql = "select f_desconecta()";
        PreparedStatement stmt = Conexao.getPreparedStatement(sql);
        stmt.execute();
    }
    
    // OPERAÇÔES VEÍCULO
    
    
    // Método que adiciona um veículo
    public static void adicionaVeiculo(Veiculo veiculo) throws SQLException {
        
            String sql = "insert into veiculo (codigo, placa, tipo, capacidade, unpac) values (?, ?, ?, ?, ?)";
            PreparedStatement stmt = Conexao.getPreparedStatement(sql);
            stmt.setInt(1, veiculo.getCodigo());
            stmt.setString(2, veiculo.getPlaca());
            stmt.setInt(3, veiculo.getTipo());
            stmt.setInt(4, veiculo.getCapacidade());
            stmt.setString(5, veiculo.getUnpac());
            stmt.executeUpdate();

    }
    
    // Método que exclui um veículo
    public static void deletaVeiculo(Integer codigo) throws SQLException {
  
        String sql = "delete from veiculo where codigo = ?";
        PreparedStatement stmt = Conexao.getPreparedStatement(sql);
        stmt.setInt(1, codigo);
        stmt.executeUpdate();

    }
    
     // Método que altera um veículo
    public static void alteraVeiculo(Veiculo veiculo) throws SQLException {
                  
        String sql = "update veiculo set placa = ?, tipo = ?, capacidade = ?, unpac = ? where codigo = ?";
        PreparedStatement stmt = Conexao.getPreparedStatement(sql);
        stmt.setString(1, veiculo.getPlaca());
        stmt.setInt(2, veiculo.getTipo());
        stmt.setInt(3, veiculo.getCapacidade());
        stmt.setString(4, veiculo.getUnpac());
        stmt.setInt(5, veiculo.getCodigo());
        stmt.executeUpdate();

    }
    
     // Método que faz a pesquisa de um veículo passando o código do veículo a ser buscado
    public static Veiculo consultaVeiculo(Integer cod) throws SQLException {
        Veiculo veiculo = new Veiculo();
        Statement stmt = Conexao.getStatement();
        ResultSet rs = stmt.executeQuery("select * from veiculo where codigo = " + cod);

        rs.next();
        if (!rs.wasNull()) {
            veiculo.setCodigo(rs.getInt("codigo"));
            veiculo.setPlaca(rs.getString("placa"));
            veiculo.setTipo(rs.getInt("tipo"));
            veiculo.setCapacidade(rs.getInt("capacidade"));
            veiculo.setUnpac(rs.getString("unpac"));
        }
        return veiculo;
    }

    // Método que faz a pesquisa de veículos por tipo passando o código do tipo a ser buscado
    public static List<Veiculo> listaTipo(Integer tipo) throws SQLException {

      List<Veiculo> lista = new ArrayList();
      Statement stmt = Conexao.getStatement();
      ResultSet rs = stmt.executeQuery("select * from veiculo where tipo =" + tipo);

      while (rs.next()) {

          Veiculo veiculo = new Veiculo();

          veiculo.setCodigo(rs.getInt("codigo"));
          veiculo.setPlaca(rs.getString("placa"));
          veiculo.setTipo(rs.getInt("tipo"));
          veiculo.setCapacidade(rs.getInt("capacidade"));
          veiculo.setUnpac(rs.getString("unpac"));

          lista.add(veiculo);

      }

      return lista;
    }
      
     // OPERAÇÕES POSIÇÃO  
      
    //Método que adiciona uma posição
    public static void adicionaPosicao(Posicao posicao) throws SQLException {

       String sql = "insert into posicao (codigo, datahora, latitude, longitude) values (?, ?, ?, ?)";
       PreparedStatement stmt = Conexao.getPreparedStatement(sql);
       stmt.setInt(1, posicao.getVeiculo().getCodigo());

       Timestamp datahora = new Timestamp(posicao.getDatahora().getTime());

       stmt.setTimestamp(2, datahora);
       stmt.setFloat(3, posicao.getLatitude());
       stmt.setFloat(4, posicao.getLongitude());
       stmt.executeUpdate();
        
    }
    
    // Método que retorna as posições de um veículo
    public static List<Posicao> listaPosicaoVeiculo(Integer codigo, Date datahora) throws SQLException {

      List<Posicao> lista = new ArrayList();
      Statement stmt = Conexao.getStatement();

      String sql = "";

      if(datahora != null){

          Timestamp datahoraTimestamp = new Timestamp(datahora.getTime());

          sql = "select * from posicao p join veiculo v on v.codigo = p.codigo where v.codigo = " + codigo + " and p.datahora > '" + datahoraTimestamp +"'";
      }else{
          sql = "select * from posicao p join veiculo v on v.codigo = p.codigo where v.codigo = " + codigo;
      }

      ResultSet rs = stmt.executeQuery(sql);

      Veiculo veiculo = new Veiculo();

      veiculo = consultaVeiculo(codigo);

      while (rs.next()) {

          Posicao posicao = new Posicao();

          posicao.setVeiculo(veiculo);
          posicao.setDatahora(rs.getTimestamp("datahora"));
          posicao.setLatitude(rs.getFloat("latitude"));
          posicao.setLatitude(rs.getFloat("longitude"));

          lista.add(posicao);
      }

      return lista;
    }
}
