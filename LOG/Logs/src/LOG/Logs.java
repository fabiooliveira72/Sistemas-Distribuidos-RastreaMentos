/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package LOG;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author oliveira
 */
public class Logs {
    
    public static void LogMessage(String log, String serverName){
       
        try {
            Path path = Paths.get(System.getProperty("user.home"),serverName+".txt");
            File file = new File(path.toString());
            
            if (!file.exists()) {
	     file.createNewFile();
            }
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(log);
            bw.newLine();
            bw.close();

        } catch (Exception e) {
        }
    }
}
