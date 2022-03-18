/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_web_ui_total;

/**
 *
 * @author Asadullah Monsur
 */
import javax.tools.*;
import java.io.*;
import java.util.Timer;
import java.util.TimerTask;

class Copy_Writer{  
    public static void main(String arg[]){  
        try{            
        File file = new File("Green_Load.java");
        file.createNewFile();
        
        FileReader fr = new FileReader("html.html");
        BufferedReader br = new BufferedReader(fr);
        FileWriter fw = new FileWriter("Green_Load.java", false);
        
        String s = br.readLine();
               s = br.readLine();
        
        int count = Integer.parseInt(s);
        
        for(int i=1;i<count;i++) { 
     
           s = br.readLine();
           fw.write("\n"+s); 
           fw.flush();
        }
       fw.close();
        br.close();
        }
        catch(Exception e){
        }
    }
}  