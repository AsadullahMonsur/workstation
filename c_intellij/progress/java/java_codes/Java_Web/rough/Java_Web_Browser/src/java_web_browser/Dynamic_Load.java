/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_web_browser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author Asadullah Monsur
 */
public class Dynamic_Load {
    
    boolean test = false;
    
    synchronized void first(String url_text){
    try{            
        File file = new File("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\server_ui.fxml");
        file.createNewFile();
        
        File file2 = new File("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\Server_UI_Controller.java");
        file2.createNewFile();
        
        
        URL url = new URL("http://localhost/java/test_ui.html");
        BufferedReader  br = new BufferedReader(
                new InputStreamReader(url.openStream()));

        FileWriter fw = new FileWriter("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\server_ui.fxml", false);
        FileWriter fw2 = new FileWriter("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\Server_UI_Controller.java", false);
        
        
        String s = br.readLine();
               s = br.readLine();
        
        int count = Integer.parseInt(s);
        
        for(int i=1;i<count;i++) { 
     
           s = br.readLine();
           fw2.write(s+"\n"); 
           fw2.flush();
        }
        fw2.close();
       
                 
        s = br.readLine();
        count = Integer.parseInt(s);
       
                for(int i=1;i<count;i++) { 
     
           s = br.readLine();
           fw.write(s+"\n"); 
           fw.flush();
        }
        fw.close();

        br.close();
        test = true;
        Thread.sleep(10000);
        notifyAll();        
        }
        catch(Exception e){
            System.out.println("Exception in coping files"+e);
        }
       
   }
synchronized void second(ActionEvent event,AnchorPane display){
   
        try{
            File f = new File("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\Server_UI_Controller.java");
                    
            if(f.exists() && !f.isDirectory() && f.renameTo(f)==true) {    
            }    
            else{
                System.out.println("not jc found");
                wait();
            }  
            System.out.println(" found source java");
            Process process = Runtime.getRuntime()
                    .exec("javac E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\Server_UI_Controller.java");        
        System.out.println(" found source compiled");
        }
         
        catch(Exception ex){
          System.out.println("Exception in JavaC \n"+ex);
        }
   }
   
   synchronized void third(ActionEvent event,AnchorPane display){
   
        try{     
            File f = new File("server_ui.fxml");
            
            if(f.exists() && !f.isDirectory() && test==true) {    
            }    
            else{
                System.out.println("not fxml found");
                wait();
            }
                
            System.out.println(" found ui");
            
            Runnable updater = new Runnable() {

                @Override
                public void run() {
                                  
                 try{      
                     String path = "E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\server_ui.fxml";
                     URL  url = Paths.get(path).toUri().toURL();
                     FXMLLoader loader = new FXMLLoader(url);
                     Parent root = loader.load(url);                
                     
                     display.getChildren().setAll(root);
                }
                catch(Exception ex){
                   System.out.println("Exception in fxml load \n"+ex);
                }
        
                }
            };
                Platform.runLater(updater);
        }
         
        catch(Exception ex){
           System.out.println("Exception in run server ui \n"+ex);
        }
   }
}
