/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.simulador.udp;

import java.io.IOException;
import static java.lang.Thread.sleep;
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
    
    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
        // TODO code application logic here
        Integer time = 5;//Integer.parseInt(args[0]);
        Integer messageFail = 2;//Integer.parseInt(args[1]);
        Integer timeWait = (time*messageFail)*1000;
        
        
        ThreadCheckTime checkTime = new ThreadCheckTime(timeWait);
        checkTime.start();
        
        int serverPort = 2006;
        DatagramSocket aSocket = null;
        
        
        try {
            aSocket = new DatagramSocket(serverPort);
            byte[] buffer = new byte[500];
            
            while(true){
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                System.out.println("New Connection");
                aSocket.receive(request);
                
                ThreadConnection connection = new ThreadConnection(request, buffer);
                connection.start();                
            }      
                
        } catch (SocketException se){}
        
    }
    
}
