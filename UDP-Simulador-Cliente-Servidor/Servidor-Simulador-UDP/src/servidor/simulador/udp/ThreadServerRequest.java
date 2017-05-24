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
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.GregorianCalendar;
import static servidor.simulador.udp.ServidorSimuladorUDP.connectedVehicle;

/**
 *
 * @author oliveira
 */
public class ThreadServerRequest extends Thread{
    
    DatagramPacket clientPacket;
    DatagramSocket clientSocket;
    byte[] buffer = new byte[500];
    String s = "ServidorSimuladorUDP";
    ThreadServerRequest(DatagramSocket clientSocket, DatagramPacket clientPacket, byte[] buffer) {
        this.buffer = buffer;
        this.clientPacket = clientPacket;
        this.clientSocket = clientSocket;
    }
    

    public void run() {
        try {
            //SERVER REQUEST STRING -> COD#YYYY#MM#DD#HH#MM#SS#LAT#LONG
            
                String requestReceive = new String(clientPacket.getData()).trim();
                
                String[] parts = requestReceive.split("#");
                
                GregorianCalendar c = new GregorianCalendar(Integer.parseInt(parts[1]), Integer.parseInt(parts[2])-1, Integer.parseInt(parts[3]), 
                Integer.parseInt(parts[4]), Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
                
                SimuladorObject so = new SimuladorObject(Integer.parseInt(parts[0]), c.getTime(), 
                                                        Float.parseFloat(parts[7]), Float.parseFloat(parts[8]));
                
                String info = "Server Receive request -> "+ so.toString() + " from-> "+clientPacket.getAddress()+ " port ->" + clientPacket.getPort();
                System.out.println(info);
                LOG.Logs.LogMessage(info, s);
                
                //CHECKTIME
                connectedVehicle.put(so.getCodigo(), System.currentTimeMillis());
                
                //SAVE POSITION
                Veiculo v = new Veiculo();
                v.setCodigo(so.getCodigo());
                //Operacoes.adicionaPosicao(new Posicao(so.getDataHora(), so.getLat(), so.getLon(),v));
                
                
                DatagramPacket reply = new DatagramPacket(clientPacket.getData(), clientPacket.getLength(), 
                                                          clientPacket.getAddress(), clientPacket.getPort());
                clientSocket.send(reply);
            
        } catch (Exception ioe){
            System.out.println(ioe.getMessage());
        }
    }    
    
}
