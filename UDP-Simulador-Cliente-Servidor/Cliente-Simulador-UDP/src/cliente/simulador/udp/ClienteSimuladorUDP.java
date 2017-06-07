/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.simulador.udp;

import simulador.object.SimuladorObject;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.List;

/**
 *
 * @author oliveira
 */
public class ClienteSimuladorUDP {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) throws SocketException {
        // TODO code application logic here
        // cod YYY-MM-DD-HH-MM-SS LAT LON INTERVAL
        // 1 2017-05-20-01-20-50 -1.1 -1.1 5 2 2017-07-19-13-31-50 -2.2 -2.2 5 3 2017-10-19-13-31-50 -3.3 -3.3 5
        
        List<String> lsArgs = new ArrayList<>();
        lsArgs.addAll(Arrays.asList(args));
        
        Integer serverPort = 2006;
        String address = "34.211.122.0";
        
        if(args.length%5 == 0){
            
            while(!lsArgs.isEmpty()){
                
                SimuladorObject so = new SimuladorObject();
                
                so.setCodigo(Integer.parseInt(lsArgs.get(0)));
                lsArgs.remove(0);
            
                String[] parts = lsArgs.get(0).split("-");
                GregorianCalendar c = new GregorianCalendar(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])-1, Integer.parseInt(parts[2]), 
                Integer.parseInt(parts[3]), Integer.parseInt(parts[4]), Integer.parseInt(parts[5]));
                so.setDataHora(c.getTime());
                lsArgs.remove(0);
                
                so.setLat(Float.parseFloat(lsArgs.get(0)));
                lsArgs.remove(0);
                
                so.setLon(Float.parseFloat(lsArgs.get(0)));
                lsArgs.remove(0);
                
                so.setSendRequest(Integer.parseInt(lsArgs.get(0)));
                lsArgs.remove(0);
                
                //NEW THREAD CLIENT
                ThreadClientRequest tcr = new ThreadClientRequest(so, address, serverPort);
                tcr.start();
                
            }                 
        }
        else{
            System.out.println("Erro na leitura de argumentos");
        }
            
    }
}