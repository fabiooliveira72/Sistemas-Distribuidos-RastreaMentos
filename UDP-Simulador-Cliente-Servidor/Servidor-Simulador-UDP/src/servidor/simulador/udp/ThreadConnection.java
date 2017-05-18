/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.simulador.udp;

import simulador.object.SimuladorObject;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author oliveira
 */
public class ThreadConnection extends Thread{
    
    DatagramSocket clientSocket;
    int timeWait, messageFail;
    
    ThreadConnection(DatagramSocket clientSocket, Integer  timeWait, Integer messageFail) {
        this.clientSocket = clientSocket;
        this.timeWait = timeWait;
        this.messageFail = messageFail;
    }
    

    public void run() {
        try {
            while(true)
            {
                System.out.println("Running Server");
                //RECEIVE REQUEST
                byte[] buffer = new byte[500];
                
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                //clientSocket.setSoTimeout(messageFail*timeWait);
                clientSocket.receive(request);

                System.out.println("Request Receive");
                //DESERIALIZE
                ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
                ObjectInputStream oos = new ObjectInputStream(bais);
                SimuladorObject so = (SimuladorObject) oos.readObject();
                
                System.out.println(so.getCodigo()+ " "+ so.getDataHora()+ " "+ so.getLat() + " "+so.getLon());
                
                //THREAD TO SAVE POSITION
                ThreadSavePosition sp = new ThreadSavePosition(so);
                sp.start();
                
                sleep(timeWait);
                
                 DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), 
                                                          request.getAddress(), request.getPort());
                clientSocket.send(reply);
                
            }
            
        } catch (SocketException se) {
            System.out.println(se.getMessage());
        } catch (IOException ioe){
            System.out.println(ioe.getMessage() +" - Fora da area de cobertura");
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ThreadConnection.class.getName()).log(Level.SEVERE, null, e);
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadConnection.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            if(clientSocket != null)
                clientSocket.close();
        }
    }    
    
}
