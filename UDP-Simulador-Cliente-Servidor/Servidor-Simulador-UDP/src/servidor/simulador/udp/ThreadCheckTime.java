/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor.simulador.udp;

import java.util.Date;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static servidor.simulador.udp.ServidorSimuladorUDP.connectedVehicle;

/**
 *
 * @author oliveira
 */
public class ThreadCheckTime extends Thread{
    
    Integer timewait;

    public ThreadCheckTime(Integer timewait) {
        this.timewait = timewait;
    }
    
    
    public void run(){
        Long currentTime;
        Long interval;
        while(true){
            currentTime = System.currentTimeMillis();
            for(Integer valor : connectedVehicle.keySet()){
                
                interval = (currentTime - connectedVehicle.get(valor));
                if(interval > timewait){
                    System.out.println("Veiculo codigo = "+ valor + " esta fora da area de cobertura");
                    connectedVehicle.remove(valor);
                }
                
            }
        }
    }
}
