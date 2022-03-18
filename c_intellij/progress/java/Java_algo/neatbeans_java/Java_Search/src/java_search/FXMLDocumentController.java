/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_search;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.animation.*;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.util.Duration;

/**
 *
 * @author Asadullah Monsur
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private Rectangle box_00;
    @FXML
    private Rectangle box_01;
    @FXML
    private Rectangle box_02;
    @FXML
    private Rectangle box_03;
    @FXML
    private Rectangle box_04;
    @FXML
    private Rectangle box_05;
    @FXML
    private Rectangle box_06;
    @FXML
    private Rectangle box_07;
    @FXML
    private Rectangle box_10;
    @FXML
    private Rectangle box_11;
    @FXML
    private Rectangle box_12;
    @FXML
    private Rectangle box_13;
    @FXML
    private Rectangle box_14;
    @FXML
    private Rectangle box_15;
    @FXML
    private Rectangle box_16;
    @FXML
    private Rectangle box_17;
    @FXML
    private Rectangle box_20;
    @FXML
    private Rectangle box_21;
    @FXML
    private Rectangle box_22;
    @FXML
    private Rectangle box_23;
    @FXML
    private Rectangle box_24;
    @FXML
    private Rectangle box_25;
    @FXML
    private Rectangle box_26;
    @FXML
    private Rectangle box_27;
    @FXML
    private Rectangle box_30;
    @FXML
    private Rectangle box_31;
    @FXML
    private Rectangle box_32;
    @FXML
    private Rectangle box_33;
    @FXML
    private Rectangle box_34;
    @FXML
    private Rectangle box_35;
    @FXML
    private Rectangle box_36;
    @FXML
    private Rectangle box_37;
    @FXML
    private Rectangle box_40;
    @FXML
    private Rectangle box_41;
    @FXML
    private Rectangle box_42;
    @FXML
    private Rectangle box_43;
    @FXML
    private Rectangle box_44;
    @FXML
    private Rectangle box_45;
    @FXML
    private Rectangle box_46;
    @FXML
    private Rectangle box_47;
    @FXML
    private Rectangle box_50;
    @FXML
    private Rectangle box_51;
    @FXML
    private Rectangle box_52;
    @FXML
    private Rectangle box_53;
    @FXML
    private Rectangle box_54;
    @FXML
    private Rectangle box_55;
    @FXML
    private Rectangle box_56;
    @FXML
    private Rectangle box_57;
    @FXML
    private Rectangle box_60;
    @FXML
    private Rectangle box_61;
    @FXML
    private Rectangle box_62;
    @FXML
    private Rectangle box_63;
    @FXML
    private Rectangle box_64;
    @FXML
    private Rectangle box_65;
    @FXML
    private Rectangle box_66;
    @FXML
    private Rectangle box_67;
    @FXML
    private Rectangle box_70;
    @FXML
    private Rectangle box_71;
    @FXML
    private Rectangle box_72;
    @FXML
    private Rectangle box_73;
    @FXML
    private Rectangle box_74;
    @FXML
    private Rectangle box_75;
    @FXML
    private Rectangle box_76;
    @FXML
    private Rectangle box_77;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
//     Rectangle rect = new Rectangle (100, 40, 100, 100);
//     rect.setArcHeight(50);
//     rect.setArcWidth(50);
//     rect.setFill(Color.VIOLET);
// 
//     final Duration SEC_2 = Duration.millis(2000);
//     final Duration SEC_3 = Duration.millis(3000);
// 
//     PauseTransition pt = new PauseTransition(Duration.millis(1000));
//     FadeTransition ft = new FadeTransition(SEC_3);
//     ft.setFromValue(1.0f);
//     ft.setToValue(0.3f);
//     ft.setCycleCount(2);
//     ft.setAutoReverse(true);
     
//     TranslateTransition tt = new TranslateTransition(SEC_2);
//     tt.setCycleCount(2);
//     tt.setAutoReverse(true);
//     RotateTransition rt = new RotateTransition(SEC_3);
//     rt.setCycleCount(Timeline.INDEFINITE);
//     rt.setAutoReverse(true);
//     
//     ScaleTransition st = new ScaleTransition(SEC_2);
//     st.setCycleCount(2);
//     st.setAutoReverse(true);
// 
//     SequentialTransition seqT = new SequentialTransition (rect, pt);
//     seqT.play();
    
    //SequentialTransition s = 
      //         new SequentialTransition(t1, t2, t3)
//s.play();
       try{         
      
         Walk_On_Four();
    
       }
       catch(Exception ex){
           ex.printStackTrace();
           System.out.print("Failed"+ex);
       }
    }

public void Walk_On_Four(){
          try{
       
        Rectangle [][]anchors = new Rectangle[8][8];
        
        anchors[0][0] = box_00;
        anchors[0][1] = box_01;
        anchors[0][2] = box_02;
        anchors[0][3] = box_03;
        anchors[0][4] = box_04;
        anchors[0][5] = box_05;
        anchors[0][6] = box_06;
        anchors[0][7] = box_07;
        
        anchors[1][0] = box_10;
        anchors[1][1] = box_11;
        anchors[1][2] = box_12;
        anchors[1][3] = box_13;
        anchors[1][4] = box_14;
        anchors[1][5] = box_15;
        anchors[1][6] = box_16;
        anchors[1][7] = box_17;
        
        anchors[2][0] = box_20;
        anchors[2][1] = box_21;
        anchors[2][2] = box_22;
        anchors[2][3] = box_23;
        anchors[2][4] = box_24;
        anchors[2][5] = box_25;
        anchors[2][6] = box_26;
        anchors[2][7] = box_27;
        
        anchors[3][0] = box_30;
        anchors[3][1] = box_31;
        anchors[3][2] = box_32;
        anchors[3][3] = box_33;
        anchors[3][4] = box_34;
        anchors[3][5] = box_35;
        anchors[3][6] = box_36;
        anchors[3][7] = box_37;
        
        anchors[4][0] = box_40;
        anchors[4][1] = box_41;
        anchors[4][2] = box_42;
        anchors[4][3] = box_43;
        anchors[4][4] = box_44;
        anchors[4][5] = box_45;
        anchors[4][6] = box_46;
        anchors[4][7] = box_47;
        
        anchors[5][0] = box_50;
        anchors[5][1] = box_51;
        anchors[5][2] = box_52;
        anchors[5][3] = box_53;
        anchors[5][4] = box_54;
        anchors[5][5] = box_55;
        anchors[5][6] = box_56;
        anchors[5][7] = box_57;
        
        anchors[6][0] = box_60;
        anchors[6][1] = box_61;
        anchors[6][2] = box_62;
        anchors[6][3] = box_63;
        anchors[6][4] = box_64;
        anchors[6][5] = box_65;
        anchors[6][6] = box_66;
        anchors[6][7] = box_67;
        
        anchors[7][0] = box_70;
        anchors[7][1] = box_71;
        anchors[7][2] = box_72;
        anchors[7][3] = box_73;
        anchors[7][4] = box_74;
        anchors[7][5] = box_75;
        anchors[7][6] = box_76;
        anchors[7][7] = box_77;
       
    
        int array[][] = new int[8][8];
        
        int leftTop_x = (int)(array.length/2)-1;
        int leftTop_y = (int)(array.length/2)-1;

        int rightTop_x = (int)((array.length/2));
        int rightTop_y = (int)((array.length/2)-1);
        
        int leftBottom_x = (int)((array.length/2)-1);
        int leftBottom_y = (int)((array.length/2));
        
        int rightBottom_x = (int)((array.length/2));
        int rightBottom_y = (int)((array.length/2));
        
        System.out.print(leftTop_x+" "+leftTop_y+" "+rightTop_x+""+rightTop_y+""+leftBottom_x+""+leftBottom_y+""+rightBottom_x+""+rightBottom_y);
        
       
      //  box_34.setStyle("-fx-background-color: #048727;");
        Process p1 = new Process(box_43,rightTop_x,rightTop_y,array,anchors,1,Color.WHITE);
        Thread  t1 = new Thread(p1);
        
        //box_33.setStyle("-fx-background-color: #048727;");
        Process p2 = new Process(box_33,leftTop_x,leftTop_y,array,anchors,2,Color.WHITE);
        Thread  t2 = new Thread(p2);
              
        Process p3 = new Process(box_34,leftBottom_x,leftBottom_y,array,anchors,3,Color.WHITE);
       Thread  t3 = new Thread(p3);
       
        //box_44.setStyle("-fx-background-color: #048727;");
        Process p4 = new Process(box_44,rightBottom_x,rightBottom_y,array,anchors,4,Color.WHITE);
        Thread  t4 = new Thread(p4);
        
        t1.start();
       // t1.setDaemon(true);
      t1.join(400);
        
        t2.start();
        //t2.setDaemon(true);
        
        t2.join(200);
        t3.start();
        //t3.setDaemon(true);
       t3.join(400);
        t4.start();
        t4.join(200);
        //t4.setDaemon(true);
        
          }
          catch(Exception ex){
              ex.printStackTrace();
          }
     
     }
       
        

    private void start_it(MouseEvent event)throws Exception {
    
        
    
    }
    
    
}
