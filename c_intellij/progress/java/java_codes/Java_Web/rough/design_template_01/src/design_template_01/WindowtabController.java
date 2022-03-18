/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design_template_01;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Rectangle;

/**
 * FXML Controller class
 *
 * @author Asadullah Monsur
 */
public class WindowtabController implements Initializable {

    @FXML
    private Button b1;
    @FXML
    private AnchorPane r1;
    @FXML
    private Button b2;
    @FXML
    private AnchorPane r2;

    private AnimationTimer timer = new NewTimer(1);
    private AnimationTimer timer2 = new NewTimer(2);
    
    double pro; 
    @FXML
    private AnchorPane r3;
    @FXML
    private AnchorPane r4;
            int c = 0;
    @FXML
    private void b1_leave(MouseEvent event) {
 String fu1 = "-fx-background-color: linear-gradient(to right,"+
                       "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";
                        
               String fu2 = "-fx-background-color: linear-gradient(to left,"+
                       "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";
                                       
               r1.setStyle(fu1);
               r2.setStyle(fu2);  
        c = 0;
        pro = (b1.getPrefWidth()/100); 
        timer.stop();
  //      b1.setStyle("-fx-background-color:black");
    //            b1.setStyle("-fx-text-fill:white");
    }

    @FXML
    private void b1_entry(MouseEvent event) {
        timer.start();
    //    b1.setStyle("-fx-background-color:white");
  //      b1.setStyle("-fx-text-fill:black");
  String path1 = "select_02.mp3";
  // String path1 = "lightning_msg.mp3";

        try {
          //  MediaView viewer = new MediaView();
            System.out.println(getClass().getResource(path1).toURI().toString());
            Media media = new Media(getClass().getResource(path1).toURI().toString());
            final MediaPlayer mediaPlayer = new MediaPlayer(media);
            mediaPlayer.setVolume(1.0);
          //  viewer.setMediaPlayer(mediaPlayer);
            mediaPlayer.play();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @FXML
    private void b2_leave(MouseEvent event) {
             String fu1 = "-fx-background-color: linear-gradient(to right,"+
                       "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";
                        
               String fu2 = "-fx-background-color: linear-gradient(to left,"+
                       "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";
                                       
               r3.setStyle(fu1);
               r4.setStyle(fu2);  
        c = 0;
        pro = (b1.getPrefWidth()/100); 
        timer2.stop();
      //  b2.setStyle("-fx-background-color:black");
          //      b2.setStyle("-fx-text-fill:white");
    }

    @FXML
    private void b2_entry(MouseEvent event) {
                timer2.start();
    //            b2.setStyle("-fx-background-color:white");
                 //               b2.setStyle("-fx-text-fill:black");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
       pro = (b1.getPrefWidth()/100); 
    }
    
     private class NewTimer extends AnimationTimer {

        int counter = 0; 
        long last = 0;
        
        int selection;
        
        public NewTimer(int selection){
          this.selection = selection;
        }
        
        @Override
        public void handle(long now) {
            if((((now-last))/100000)>1){
               doHandle();
               last = now;
            }
        }

        private void doHandle() {
               if(c==0){
                   counter = 0;
               }
               if(counter<b1.getPrefWidth()){
                   
                pro = pro+ (b1.getPrefWidth()/100);
                        
               String fu1 = "-fx-background-color: linear-gradient(to right,"+
                       "#000000 "+ (100-pro)+"%,#ffffff "+(100-pro)+"%);";
                        
               String fu2 = "-fx-background-color: linear-gradient(to left,"+
                       "#000000 "+ (100-pro)+"%,#ffffff "+(100-pro)+"%);";
                             
               
               if(selection == 1){
               r1.setStyle(fu1);
               r2.setStyle(fu2); 
               }
               else if(selection == 2){
               r3.setStyle(fu1);
               r4.setStyle(fu2); 
               }
 
               
                   counter++;
                }
                             
        }
     }

    }
