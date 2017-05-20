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
import java.net.DatagramSocket;
import java.net.SocketException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servidor.simulador.udp.ServidorSimuladorUDP.connectedVehicle;

/**
 *
 * @author oliveira
 */
public class ThreadServerRequest extends Thread{
    
    DatagramPacket clientPacket;
    DatagramSocket clientSocket;
    byte[] buffer = new byte[500];
    ThreadServerRequest(DatagramSocket clientSocket, DatagramPacket clientPacket, byte[] buffer) {
        this.buffer = buffer;
        this.clientPacket = clientPacket;
        this.clientSocket = clientSocket;
    }
    

    public void run() {
        try {
                //System.out.println("Thread Connection Run");
                //DESERIALIZE
                
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream oos = new ObjectInputStream(bais);
                SimuladorObject so = (SimuladorObject) oos.readObject();
                oos.close();
                
                System.out.println("Servidor receive object = "+so.getCodigo()+ " "+ so.getDataHora()+ " "+ so.getLat() + " "+so.getLon());
                System.out.println(clientPacket.getAddress()+ " # " + clientPacket.getPort());
                
                //CHECKTIME
                connectedVehicle.put(so.getCodigo(), System.currentTimeMillis());
                
                //SAVE POSITION
                Veiculo v = new Veiculo();
                v.setCodigo(so.getCodigo());
                //Operacoes.adicionaPosicao(new Posicao(so.getDataHora(), so.getLat(), so.getLon(),v));
                
                
//                DatagramPacket reply = new DatagramPacket(clientPacket.getData(), clientPacket.getLength(), 
//                                                          clientPacket.getAddress(), clientPacket.getPort());
//                clientSocket.send(reply);
            
        } catch (SocketException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe){
            System.out.println(ioe.getMessage());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadServerRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
}
