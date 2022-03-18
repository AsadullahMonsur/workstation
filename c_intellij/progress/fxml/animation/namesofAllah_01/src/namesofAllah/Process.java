/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namesofAllah;



import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

public class Process implements Runnable{  

    Rectangle r;
   
    Color a;
    Color b;
    
    Process(Rectangle r,Color from, Color to){
        this.a = from;
        this.b = to;
        this.r = r;
    }
    
    @Override
    public void run(){ 
        
        Runnable updater = new Runnable() {

            @Override
            public void run() {
                try{
          
                    
                    FillTransition f = new FillTransition();
         
          
                    f.setAutoReverse(true);  

                    f.setCycleCount(FillTransition.INDEFINITE);  

                    f.setDuration(Duration.millis(2000));  

                    f.setFromValue(b);  

                    f.setToValue(a);  

                    f.setShape(r);
                    
                    f.play();

             
                } 
                
                catch(Exception e){
                    System.out.println("Exception");
                }
            }              
        };
         
         while (true) {
            try {
                Thread.sleep(4000);
            }
            catch (InterruptedException ex) {
            
            }

            Platform.runLater(updater);
        }
        
    } 
        
}
