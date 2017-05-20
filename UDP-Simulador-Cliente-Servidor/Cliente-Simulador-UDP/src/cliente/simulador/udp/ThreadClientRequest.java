/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.simulador.udp;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.logging.Level;
import java.util.logging.Logger;
import simulador.object.SimuladorObject;

/**
 *
 * @author oliveira
 */
public class ThreadClientRequest extends Thread{

    SimuladorObject so;
    String address;
    Integer serverPort;
    DatagramSocket aSocket = null;

    ThreadClientRequest(SimuladorObject so, String address, Integer serverPort) throws SocketException {
        this.so = so;
        this.address = address;
        this.serverPort= serverPort;
        
    }
    
    public void run(){
        try {
            while(true)
            {
                sleep(so.getSendRequest()*1000);
                
            //SEARIALIZE
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                System.out.println("Client send object= "+so.getCodigo()+ " "+ so.getDataHora()+ " "+ so.getLat() + " "+so.getLon());
                oos.writeObject(so);
                oos.flush();
                byte[] data = baos.toByteArray();
                
            //SEND REQUEST
                aSocket = new DatagramSocket();
                InetAddress aHost = InetAddress.getByName(address);
                DatagramPacket request = new DatagramPacket(data, data.length, aHost, serverPort);
                aSocket.send(request);
            
            //WAIT REPlY
                byte[] buffer = new byte[500];
                DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(reply);
                
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream os = new ObjectInputStream(bais);
                SimuladorObject s1 = (SimuladorObject) os.readObject();
                
                System.out.println("Client receive object= "+s1.getCodigo()+ " "+ s1.getDataHora()+ " "+ s1.getLat() + " "+s1.getLon());
            
            
            }
        } catch (SocketException soe) {
        }catch(IOException ioe){
            
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
