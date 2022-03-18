/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_web_browser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Asadullah Monsur
 */
public class HomepageController implements Initializable {
    
   
    @FXML
    private TextField url_text;
    @FXML
    private Button search_btn;
    @FXML
    private AnchorPane display;
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void search_on_web(ActionEvent event) {
        
        
        System.out.println("location :  @@@>"+
                this.getClass().getProtectionDomain().getCodeSource().getLocation());
        
       

//        Thread first = new Thread(new Runnable(){
//            @Override
//            public void run() {
//                
//               try{            
//                 File file = new File("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\server_ui.fxml");
//                 file.createNewFile();
//        
//                 File file2 = new File("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\Server_UI_Controller.java");
//                 file2.createNewFile();
//        
//        
//                 URL url = new URL("http://localhost/java/test_ui.html");
//                 BufferedReader  br = new BufferedReader(
//                                             new InputStreamReader(url.openStream()));
//
//                 FileWriter fw = new FileWriter("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\server_ui.fxml", false);
//                 FileWriter fw2 = new FileWriter("E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\Server_UI_Controller.java", false);
//        
//        
//                 String s = br.readLine();
//                 s = br.readLine();
//        
//                 int count = Integer.parseInt(s);
//        
//                 for(int i=1;i<count;i++) {
//                    s = br.readLine();
//                    fw2.write("\n"+s); 
//                    fw2.flush();
//                }
//                fw2.close();
//        
//                s = br.readLine();
//                count = Integer.parseInt(s);
//       
//                for(int i=1;i<count;i++) { 
//     
//                   s = br.readLine();
//                   fw.write(s+"\n"); 
//                   fw.flush();
//                }
//                fw.close();
//                br.close();
//
//        }
//        catch(Exception e){
//            System.out.println("Exception in coping files");
//        }
//       }
//     });
//   
//        
//      Thread second = new Thread(new Runnable(){
//           
//        @Override
//        public void run() {
//                            
//            try{
//              Process process = Runtime.getRuntime()
//	               .exec("javac E:\\WorkStation\\java_codes\\Java_Web\\rough\\Java_Web_Browser\\src\\java_web_browser\\Server_UI_Controller.java");        
//            }
//            catch(Exception ex){
//               System.out.println("Exception in JavaC \n"+ex);
//            }
//        }
//    });    
//        
//        
//      Thread third = new Thread(new Runnable(){
//           
//        @Override
//        public void run() {
//                
//            Runnable updater = new Runnable() {
//
//                @Override
//                public void run() {
//                                  
//                 try{                 
//                  Parent root = FXMLLoader.load(getClass().getResource("server_ui.fxml")); 
//                  display.getChildren().setAll(root);
//                }
//                catch(Exception ex){
//                   System.out.println("Exception in run server ui \n"+ex);
//                }
//        
//                }
//            };
//
//                Platform.runLater(updater);
//          }
//      });
      
      try{
//         first.start();
//         first.join();
//         second.start();
//         second.join();
//         third.start();
//                  
         
//          Dynamic_Load d = new Dynamic_Load();
                   
//          new Thread(){  
//              public void run(){
//                d.third(event,display);
//              }  
//          }.start();
          
//          new Thread(){  
//              public void run(){
//                d.second(event,display);
//              }  
//          }.start();
//           
//          new Thread(){  
//              public void run(){
//                d.first("");
//              }  
//          }.start();          

runProcess("javac E:\\WorkStation\\java_codes\\Java_Web\\rough\\Server_UI_Controller.java");        
     System.out.println("Sudfkkdnfkd");
      }
      catch(Exception ex){
           System.out.println("Error 2"+ex);
      }
       
    }
        
     private  void runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        printLines(command + " stdout:", pro.getInputStream());
        printLines(command + " stderr:", pro.getErrorStream());
        pro.waitFor();
        System.out.println(command + " exitValue() " + pro.exitValue());
    }
     
     private  void printLines(String name, InputStream ins) throws Exception {

        String line = null;

        BufferedReader in = new BufferedReader(new InputStreamReader(ins));
        while ((line = in.readLine()) != null) {
            System.out.println(name + " " + line);
        }
    }
}
