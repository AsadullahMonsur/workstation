/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design_template_01;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.AnimationTimer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.BlurType;
import javafx.scene.effect.ColorInput;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.InnerShadow;
import javafx.scene.effect.Shadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.util.Duration;

/**
 *
 * @author Asadullah Monsur
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Button bot;
    @FXML
    private AnchorPane container;
    
     DropShadow dropShadow;
     InnerShadow innerShadow;
     AnimationTimer timer = new MyTimer();
     AnimationTimer timer2 = new NewTimer();
     private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
    
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      Shadow shadow = new Shadow(); 
      shadow.setBlurType(BlurType.GAUSSIAN); 
      shadow.setColor(Color.RED); 
      shadow.setHeight(100); 
      shadow.setWidth(100); 
      shadow.setRadius(100); 
      
      
//        innerShadow = new InnerShadow();
//        innerShadow.setWidth(2);
//        innerShadow.setHeight(2);
//        innerShadow.setRadius(5);
//        innerShadow.setOffsetX(0);
//        innerShadow.setOffsetY(0);
//        innerShadow.setChoke(0.05);
//        innerShadow.setColor(Color.web("#17eb17"));
//        
//        dropShadow = new DropShadow();
//        dropShadow.setWidth(2);
//        dropShadow.setHeight(2);
//        dropShadow.setRadius(5);
//        dropShadow.setOffsetX(0);
//        dropShadow.setOffsetY(0);
//        dropShadow.setSpread(0.05);
//        dropShadow.setColor(Color.web("#17eb17"));
//        dropShadow.setInput(innerShadow);
//        bot.setEffect(dropShadow);
//      
//      bot.getStylesheets().add("button");

//      container.setEffect(shadow); 
    }    

    @FXML
    private void entering(MouseEvent event) {
//        Stop[] stops = new Stop[] { new Stop(1, Color.web("#ff008f")), new Stop(1, Color.web("#0af822"))};
//        LinearGradient lg = new LinearGradient(0, 0, 1, 0, true, CycleMethod.NO_CYCLE, stops);
//       
//        ColorInput input = new ColorInput();
//        input.setPaint(lg);
        

        timer2.start();
        
//        dropShadow.setWidth(20);
//        dropShadow.setHeight(20);
//        dropShadow.setRadius(50);
//        dropShadow.setOffsetX(0);
//        dropShadow.setOffsetY(0);
//        
//        innerShadow.setWidth(10);
//        innerShadow.setHeight(10);
//        innerShadow.setRadius(50);
//        innerShadow.setOffsetX(0);
//        innerShadow.setOffsetY(0);
    }

    @FXML
    private void leaving(MouseEvent event) {
        timer2.stop();
        
//        dropShadow.setColor(Color.web("#09b51a")); 
//        innerShadow.setColor(Color.web("#09b51a"));  
//        bot.setStyle("-fx-text-fill: #09b51a");
        
//        dropShadow.setWidth(0.2);
//        dropShadow.setHeight(0.2);
//        dropShadow.setRadius(0.5);
//        dropShadow.setOffsetX(0);
//        dropShadow.setOffsetY(0);
//        
//        innerShadow.setWidth(2);
//        innerShadow.setHeight(2);
//        innerShadow.setRadius(5);
//        innerShadow.setOffsetX(0);
//        innerShadow.setOffsetY(0);
        System.out.println("Animation stopped");
    }
    
        private class MyTimer extends AnimationTimer {

        int counter = 1;
        long last = 0;
        String color_name[] = new String[]{
            "#ffffff",
            "#6a0699","#0e036b","#1382eb",
            "#09b51a","#d0eb07","#ff860d","#db0000"
        };
        
        Color color[] = new Color[]{
                Color.web(color_name[0]),
                Color.web(color_name[1]),
                Color.web(color_name[2]),
                Color.web(color_name[3]),
                Color.web(color_name[4]),
                Color.web(color_name[5]),
                Color.web(color_name[6]),
                Color.web(color_name[7]),
                };
        
        @Override
        public void handle(long now) {
            if((((now-last))/100000)>2000){
               doHandle();
               last = now;
            }
        }

        private void doHandle() {


               dropShadow.setColor(color[counter]); 
               innerShadow.setColor(color[counter]);
          
               bot.setStyle("-fx-text-fill: "+color_name[counter]);
               
            counter++;
            if (counter>7) {
                counter = 1;
            }
        }
     }
        
        private class NewTimer extends AnimationTimer {

        int counter = 1;
        long last = 0;
        String color_name[] = new String[]{
            "#ffffff",
            "#000099","#e806bd"
            
        };
        
        Color color[] = new Color[]{
                Color.web(color_name[0]),
                Color.web(color_name[1]),
                Color.web(color_name[2]),
                };
        
        @Override
        public void handle(long now) {
            if((((now-last))/100000)>1000){
               doHandle();
               last = now;
            }
        }

        private void doHandle() {
               
               if(counter==1){
                   
                  String full = "-fx-border-color: linear-gradient(to right,"+ color_name[1] + "," + color_name[2] + ");"; 
               bot.setStyle(full);
               }
               else if(counter==2){
               String full = "-fx-border-color: linear-gradient(to top,"+ color_name[1] + "," + color_name[2] + ");"; 
               bot.setStyle(full);
               }
               else if(counter==3){
                  String full = "-fx-border-color: linear-gradient(to left,"+ color_name[1] + "," + color_name[2] + ");"; 
               bot.setStyle(full);
               }
               else if(counter==4){
                  String full = "-fx-border-color: linear-gradient(to bottom,"+ color_name[1] + "," + color_name[2] + ");"; 
               bot.setStyle(full);
               }
               
               
            counter++;
            if (counter>4) {
                counter = 1;
            }
        }
     }
    
}
