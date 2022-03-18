/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design_template_01;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * FXML Controller class
 *
 * @author Asadullah Monsur
 */
public class CardexController implements Initializable {

    @FXML
    private AnchorPane top;
    @FXML
    private AnchorPane down;
    @FXML
    private AnchorPane container;
    
    AnimationTimer timer2 = new NewTimer();
    DropShadow drop1;
    DropShadow drop2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        drop1 = new DropShadow();
        drop1.setHeight(64.0);
        drop1.setWidth(64.0);
        drop1.setRadius(32.0);
        drop1.setOffsetX(2.0);
        drop1.setOffsetY(-2.0);
        drop1.setColor(Color.web("#ff0000"));
        drop1.setSpread(0.2);
        
        drop2 = new DropShadow();
        drop2.setHeight(64.0);
        drop2.setWidth(64.0);
        drop2.setRadius(32.0);
        drop2.setOffsetX(-2.0);
        drop2.setOffsetY(2.0);
        drop2.setColor(Color.web("#09ab00"));
        drop1.setSpread(0.2);
    }    


    @FXML
    private void on_mouse_entered(MouseEvent event) {
        top.setEffect(drop1);
        down.setEffect(drop2);
                   
        // timer2.start();
    }
    


    @FXML
    private void on_mouse_exited(MouseEvent event) {
        
                           top.setEffect(drop2);
                   down.setEffect(drop1);
              //   timer2.stop();
                 System.out.println("Animation stopped");
    }


     private class NewTimer extends AnimationTimer {

        int counter = 1;
        long last = 0;
        
        
        @Override
        public void handle(long now) {
            if((((now-last))/100000)>4000){
               doHandle();
               last = now;
            }
        }

        private void doHandle() {
               
               if(counter==1){
                   top.setEffect(drop2);
                   down.setEffect(drop1);
                }
               else if(counter==2){
                   top.setEffect(drop1);
                   down.setEffect(drop2);
               }
                              
            counter++;
            if (counter>2) {
                counter = 1;
            }
        }
     }
    
}
