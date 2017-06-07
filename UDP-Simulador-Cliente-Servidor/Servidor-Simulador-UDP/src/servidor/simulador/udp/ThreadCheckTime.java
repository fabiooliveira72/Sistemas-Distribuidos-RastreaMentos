/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.simulador.udp;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servidor.simulador.udp.ServidorSimuladorUDP.connectedVehicle;

/**
 *
 * @author oliveira
 */
public class ThreadCheckTime extends Thread{
    
    Integer time, interval, maxTimeWait;

    public ThreadCheckTime(Integer time, Integer interval, Integer maxTimeWait) {
        this.time = time*1000;
        this.interval = interval;
        this.maxTimeWait = maxTimeWait;
    }
    
    
    public void run(){
        Long currentTime;
        Long timePast;
        
        try {
            while(true){
                currentTime = System.currentTimeMillis();
                for(Integer valor : connectedVehicle.keySet()){

                    timePast = (currentTime - connectedVehicle.get(valor));

                    if(timePast <= time){
                        String s = new Date()+" Veiculo codigo: "+valor+" STATUS: OK";
                        System.out.println(s);
                        LOG.Logs.LogMessage(s, "ServidorSimuladorUDP");
                    } else if(timePast > time){
                        if(timePast <= maxTimeWait){
                            String s = new Date()+" Veiculo codigo: "+valor+" STATUS: Suspeito fora de cobertura";
                            System.out.println(s);
                            LOG.Logs.LogMessage(s, "ServidorSimuladorUDP");
                        }
                        else if(timePast > maxTimeWait){
                            String s = new Date()+" Veiculo codigo: "+valor+" STATUS: Fora da area de cobertura";
                            System.out.println(s);
                            LOG.Logs.LogMessage(s, "ServidorSimuladorUDP");
                        }

                    }

                }
            sleep(time);
            } 
        }catch (InterruptedException ex) {
            LOG.Logs.LogMessage(ex.getMessage(), "ServidorSimuladorUDP");
        }
    }
}
