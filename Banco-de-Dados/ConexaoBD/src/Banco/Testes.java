/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import Entidades.Veiculo;
import Banco.Operacoes;
import Entidades.Posicao;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leandro
 */
public class Testes extends Operacoes{

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        // TODO code application logic here
        
//    ADICIONA CARRO..........
//        
//        Veiculo veiculo = new Veiculo();
//       
//        veiculo.setCodigo(400);
//        veiculo.setPlaca("ILH1124");
//        veiculo.setTipo(1);
//        veiculo.setCapacidade(1);
//        veiculo.setUnpac("unpac");
//           
//        System.out.println("Agora vai adicionar!!");
//       
//        try {
//            Operacoes.adicionaVeiculo(veiculo);
//            System.out.println("INCLUIU COM SUCESSO!!!");
//            } catch (SQLException ex) {
//            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("ERRO AO INCLUIR...");
//        }
//     
//        */
        /*----------------------------------------------------------------------------------------------------*/
            
            /* DELETA CARRO..............*/
            /*   Veiculo veiculo = new Veiculo();
       
            veiculo.setCodigo(1);
            veiculo.setPlaca("ILH1124");
            veiculo.setTipo(1);
            veiculo.setCapacidade(1);
            veiculo.setUnpac("unpac");
            
            
            System.out.println("Agora vai Deletar!!");
            try{
                Operacoes.deletaVeiculo(veiculo);
                System.out.println("DELETOU COM SUCESSO!!!");
            } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO AO DELETAR...");
        }
            
            
          */
          
            
        
        /*------------------------------------------------------------------------------------------------*/
        
        //ALTERAR UM CARRO......
            Veiculo veiculo = new Veiculo();
            
            veiculo.setCodigo(400);
            veiculo.setPlaca("ILH");
            veiculo.setTipo(4);
            veiculo.setCapacidade(1);
            veiculo.setUnpac("unp");

            System.out.println("Agora vai Alterar!!");
            
        try {
            Operacoes.alteraVeiculo(veiculo);
            System.out.println("ALTEROU COM SUCESSO!!!");
            
             } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO AO ALTERAR...");
            }
         
            
      /*---------------------------------------------------------------------------------------------------------*/
        
//            
//            Veiculo veiculo = new Veiculo();
//            
//            System.out.println("Agora vai Consultar!!");
//            try {
//            veiculo = Operacoes.consultaVeiculo(3);
//            System.out.println("CONSULTOU CARRO: " + veiculo.getModelo());
//            } catch (SQLException ex) {
//            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("NAO ACHOU ESSE CARRO!!");
//            }
            
            
            /*--------------------------------------------------------------------------------------*/
            
           /*     LISTA CARROS TIPO......
            List<Veiculo> lista = new ArrayList();
            
            
            System.out.println("Agora vai buscar lista!!");
            try {
            lista = Operacoes.listaTipo(1);
            
            for (Veiculo veiculo : lista) {
            System.out.println("LISTOU O CARRO: " + veiculo.getPlaca());
            }
            } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NAO ACHOU NADA COM ESSE ANO E MODELO!!");
            }
            
            */
           
           
               /*     LISTA CARROS TIPO......
            List<Veiculo> lista = new ArrayList();
            
            
            System.out.println("Agora vai buscar lista!!");
            try {
            lista = Operacoes.listaTipo(1);
            
            for (Veiculo veiculo : lista) {
            System.out.println("LISTOU O CARRO: " + veiculo.getPlaca());
            }
            } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NAO ACHOU NADA COM ESSE ANO E MODELO!!");
            }
            
                    
        /*----------------------------------------------------------------------------------------------------------*/
            
            //LISTA CARRO......
            /*Veiculo veiculo = new Veiculo();
      
            
            System.out.println("Agora vai buscar lista!!");
           
                veiculo = Operacoes.consultaVeiculo(1);
          
                System.out.println("VEÍCULO: " + veiculo);
            
            */
         
            
            
            /*--------------------------------------------------------------------------------------*/
            
            /*LISTA CARROS ANO/MODELO......
            List<Carro> lista = new ArrayList();
            
            
            System.out.println("Agora vai buscar lista!!");
            try {
            lista = OperacoesBD.listaAnoModelo(2006, "Polo");
            
            for (Carro car : lista) {
            System.out.println("LISTOU O CARRO: " + car.getModelo());
            }
            } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NAO ACHOU NADA COM ESSE ANO E MODELO!!");
            }
            */
            
                    
        /*----------------------------------------------------------------------------------------------------------*/
            
            /*LISTA CARROS......
            Carro car = new Carro();
            List<Carro> lista = new ArrayList();
            
            
            System.out.println("Agora vai buscar lista!!");
            try {
            lista = OperacoesBD.listaCarro();
            System.out.println("TAMANHO DA LISTA: " + lista.size());
            for(int i=0; i<lista.size(); i++){
            car = null;
            car = lista.get(i);
            System.out.println("PERCORRENDO A LISTA: " + lista.get(i).getModelo());
            }
            
            } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NAO ACHOU NADA COM ESSE ANO E MODELO!!");
            }
            */
       
        
   /*
            
        Veiculo veiculo = new Veiculo();
           
        veiculo.setCodigo(1);
        
        Posicao posicao = new Posicao();
   
        posicao.setDatahora(new Date());
        posicao.setVeiculo(veiculo);
        posicao.setLatitude(1F);
        posicao.setLongitude(2F);
  
        System.out.println("Agora vai adicionar!!");
       
        try {
            Operacoes.adicionaPosicao(posicao);
            System.out.println("INCLUIU COM SUCESSO!!!");
            } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRO AO INCLUIR...");
        }
           
     */ 
     
       /*LISTA CARROS ANO/MODELO......
            List<Carro> lista = new ArrayList();
            
            
            System.out.println("Agora vai buscar lista!!");
            try {
            lista = OperacoesBD.listaAnoModelo(2006, "Polo");
            
            for (Carro car : lista) {
            System.out.println("LISTOU O CARRO: " + car.getModelo());
            }
            } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NAO ACHOU NADA COM ESSE ANO E MODELO!!");
            }
            */
            
                    
        /*----------------------------------------------------------------------------------------------------------*/
            
        // Listar posições de um veículo
        
       /*
         
            List<Posicao> lista = new ArrayList();
            
            
            System.out.println("Agora vai buscar lista!!");
            try {
                
                lista = Operacoes.listaPosicaoVeiculo(1, null);
                
                System.out.println("TAMANHO DA LISTA: " + lista.size());
                
                for(int i=0; i<lista.size(); i++){
                    System.out.println("Código: " + lista.get(i).getVeiculo());
                }
            
            } catch (SQLException ex) {
            Logger.getLogger(Testes.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("NAO ACHOU NADA COM ESSE ANO E MODELO!!");
            }
          
    */
     
    }
}
