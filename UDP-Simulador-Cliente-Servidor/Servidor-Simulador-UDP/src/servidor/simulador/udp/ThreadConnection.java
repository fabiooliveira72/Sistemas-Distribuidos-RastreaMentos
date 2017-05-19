/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.simulador.udp;

import Banco.Operacoes;
import Entidades.Posicao;
import Entidades.Veiculo;
import simulador.object.SimuladorObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servidor.simulador.udp.ServidorSimuladorUDP.connectedVehicle;

/**
 *
 * @author oliveira
 */
public class ThreadConnection extends Thread{
    
    DatagramPacket clientPacket;
    byte[] buffer;
    ThreadConnection(DatagramPacket clientPacket, byte[] buffer) {
        this.clientPacket = clientPacket;
        this.buffer = buffer;
    }
    

    public void run() {
        try {
                System.out.println("Thread Connection Run");
                //DESERIALIZE
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream oos = new ObjectInputStream(bais);
                SimuladorObject so = (SimuladorObject) oos.readObject();
                
                System.out.println(so.getCodigo()+ " "+ so.getDataHora()+ " "+ so.getLat() + " "+so.getLon());
                
                //CHECKTIME
                connectedVehicle.put(so.getCodigo(), System.currentTimeMillis());
                
                //SAVE POSITION
                Veiculo v = new Veiculo();
                v.setCodigo(so.getCodigo());
                Operacoes.adicionaPosicao(new Posicao(so.getDataHora(), so.getLat(), so.getLon(),v));
                
                
//                 DatagramPacket reply = new DatagramPacket(clientPacket.getData(), clientPacket.getLength(), 
//                                                          clientPacket.getAddress(), clientPacket.getPort());
//                clientSocket.send(reply);
            
        } catch (SocketException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException | SQLException e) {
            Logger.getLogger(ThreadConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }    
    
}
