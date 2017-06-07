/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.simulador.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.GregorianCalendar;
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
    GregorianCalendar gc = new GregorianCalendar();

    ThreadClientRequest(SimuladorObject so, String address, Integer serverPort) throws SocketException {
        this.so = so;
        this.address = address;
        this.serverPort= serverPort;
        
    }
    
    public void randLatLong(){
        String s;
        double x, newseed;
        
        x= Math.random()* 1000;
        newseed = (x / 100) % 1000000;
        x = newseed * newseed;
        s = String.valueOf(x);
        so.setLat(Float.parseFloat(s));
        
        x= Math.random()* -1000;
        newseed = (x / 100) % 1000000;
        x = newseed * newseed;
        s = String.valueOf(x);
        so.setLon(Float.parseFloat(s));
        
    }
    
    public void run(){
        try {
            if(so.getDataHora().getTime() > gc.getTime().getTime()){
                System.out.println("Vehicle "+so.getCodigo()+" Waiting");
                long twait = so.getDataHora().getTime() - gc.getTime().getTime();
                sleep(twait);
            }
            while(true)
            {
            //SEARIALIZE
                randLatLong();
                so.setDataHora(new Date());
                gc.setTime(so.getDataHora());
                
                String dt = so.getCodigo()+"#"+gc.get(GregorianCalendar.YEAR)+"#"+(gc.get(GregorianCalendar.MONTH)+1)+
                            "#"+ gc.get(GregorianCalendar.DAY_OF_MONTH)+"#"+gc.get(GregorianCalendar.HOUR_OF_DAY)+
                            "#"+ gc.get(GregorianCalendar.MINUTE)+ "#"+gc.get(GregorianCalendar.SECOND)+
                            "#"+so.getLat()+"#"+so.getLon();
                byte[] data = dt.getBytes();
                
                
            //SEND REQUEST
                aSocket = new DatagramSocket();
                InetAddress aHost = InetAddress.getByName(address);
                DatagramPacket request = new DatagramPacket(data, data.length, aHost, serverPort);
                aSocket.send(request);
                System.out.println("Client send request -> "+ so.toString()+ " from -> "+request.getAddress() + " port -> "+ request.getPort());
           
                sleep(so.getSendRequest()*1000);
            }
        } catch (SocketException soe) {
            soe.getMessage();
        }catch(IOException ioe){   
            ioe.getMessage();
        } catch (InterruptedException ex) {
            Logger.getLogger(ThreadClientRequest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
