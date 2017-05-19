/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.simulador.udp;

import simulador.object.SimuladorObject;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import static java.lang.Thread.sleep;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author oliveira
 */
public class ClienteSimuladorUDP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws InterruptedException {
        // TODO code application logic here
        GregorianCalendar c = new GregorianCalendar() ;
        Date dt = c.getTime();
        SimuladorObject so = new SimuladorObject(1, dt, -1.0F, -5.0F);
        
        DatagramSocket aSocket = null;
        int serverPort = 2006;
        
        try {
            while(true)
            {
            //SEARIALIZE
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(so);
            oos.flush();
            byte[] data = baos.toByteArray();
            
            sleep(5000);
            //SEND REQUEST
            aSocket = new DatagramSocket();
            InetAddress aHost = InetAddress.getByName("localhost");
            DatagramPacket request = new DatagramPacket(data, data.length, aHost, serverPort);
            aSocket.send(request);
            
            //WAIT REPlY
            byte[] buffer = new byte[500];
            DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
            aSocket.receive(reply);
            
            System.out.println("Reply: "+ new String(reply.getData()));
            
            
            }
        } catch (SocketException soe) {
        }catch(IOException ioe){
            
        }finally{
            if(aSocket!= null)
                aSocket.close();
        }
    }
    
}
