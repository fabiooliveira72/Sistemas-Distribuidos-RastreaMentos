/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.simulador.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author oliveira
 */
public class ServidorSimuladorUDP {

    /**
     * @param args the command line arguments
     */
    static Map<Integer,Long> connectedVehicle = new ConcurrentHashMap<>();
    
    public static void main(String[] args){
        // TODO code application logic here
        Integer time = Integer.parseInt(args[0]);
        Integer interval = Integer.parseInt(args[1]);
        Integer maxTimeWait = (time*interval)*1000;
        String s = "ServidorSimuladorUDP";
        
        ThreadCheckTime checkTime = new ThreadCheckTime(maxTimeWait);
        checkTime.start();
        
        Integer serverPort = 2006;
        DatagramSocket aSocket = null;
        
        try {
            
            aSocket = new DatagramSocket(serverPort);
            
            while(true){
                byte[] buffer = new byte[500];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                
                ThreadServerRequest connection = new ThreadServerRequest(aSocket, request, buffer);
                connection.start();
            }
                           
        } catch (SocketException se){
            System.out.println(se.getMessage());
            LOG.Logs.LogMessage(se.getMessage(), s);
        } 
        catch (IOException ex) {
            System.out.println(ex.getMessage());
            LOG.Logs.LogMessage(ex.getMessage(), s);
        }
       
    }
    
}
