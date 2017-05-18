/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.simulador.udp;

import simulador.object.SimuladorObject;
import Banco.Operacoes;
import Entidades.Posicao;
import Entidades.Veiculo;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oliveira
 */
public class ThreadSavePosition extends Thread{
        SimuladorObject so = null;
    
    ThreadSavePosition(SimuladorObject so) {
        this.so = so;
    }
    
    public void run(){
        Veiculo v = new Veiculo();
        v.setCodigo(so.getCodigo());
            try {
                Operacoes.adicionaPosicao(new Posicao(so.getDataHora(), so.getLat(), so.getLon(),v));
            } catch (SQLException ex) {
                Logger.getLogger(ThreadSavePosition.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    
}
