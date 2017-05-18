/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.simulador.udp;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 *
 * @author oliveira
 */
public class ServidorSimuladorUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ClassNotFoundException, IOException, InterruptedException {
        // TODO code application logic here
        Integer timeWait = 3000;//Integer.parseInt(args[0]);
        Integer messageFail = 2;//Integer.parseInt(args[1]);
        
        System.out.println(timeWait+ " "+messageFail);
        
        
        int serverPort = 2006;
        DatagramSocket aSocket = null;
        
        
        try {
            aSocket = new DatagramSocket(serverPort);
                
                System.out.println("New Connection");
                ThreadConnection c = new ThreadConnection(aSocket, timeWait, messageFail);
                c.start();                
                
                
                
        } catch (SocketException se){}
        
    }
    
}
