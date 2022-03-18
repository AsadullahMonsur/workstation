/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package namesofAllah;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author Asadullah Monsur
 */
public class MainLoaderController implements Initializable {

    @FXML
    private Rectangle box_10;
    @FXML
    private Rectangle box_11;
    @FXML
    private Rectangle box_12;
    @FXML
    private Rectangle box_13;
    @FXML
    private Rectangle box_20;
    @FXML
    private Rectangle box_21;
    @FXML
    private Rectangle box_22;
    @FXML
    private Rectangle box_23;
    @FXML
    private Rectangle box_30;
    @FXML
    private Rectangle box_31;
    @FXML
    private Rectangle box_32;
    @FXML
    private Rectangle box_33;
    @FXML
    private Rectangle box_40;
    @FXML
    private Rectangle box_47;
    @FXML
    private Rectangle box_46;
    @FXML
    private Rectangle box_36;
    @FXML
    private Rectangle box_24;
    @FXML
    private Rectangle box_25;
    @FXML
    private Rectangle box_34;
    @FXML
    private Rectangle box_35;
    @FXML
    private Rectangle box_41;
    @FXML
    private Rectangle box_45;
    @FXML
    private Rectangle box_42;
    @FXML
    private Rectangle box_43;
    @FXML
    private Rectangle box_44;
    @FXML
    private Rectangle box_00;
    @FXML
    private Rectangle box_01;
    @FXML
    private Rectangle box_02;
    @FXML
    private Rectangle box_60;
    @FXML
    private Rectangle box_61;
    @FXML
    private Rectangle box_64;
    @FXML
    private Rectangle box_63;
    @FXML
    private Rectangle box_67;
    @FXML
    private Rectangle box_66;
    @FXML
    private Rectangle box_62;
    @FXML
    private Rectangle box_65;
    @FXML
    private Rectangle box_50;
    @FXML
    private Rectangle box_51;
    @FXML
    private Rectangle box_71;
    @FXML
    private Rectangle box_72;
    @FXML
    private Rectangle box_73;
    @FXML
    private Rectangle box_70;
    @FXML
    private Rectangle box_74;
    @FXML
    private Rectangle box_75;
    
  @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        try{
        Rectangle r0[] = new Rectangle[3];
        Rectangle r1[] = new Rectangle[4];
        Rectangle r2[] = new Rectangle[6];
        Rectangle r3[] = new Rectangle[7];
        Rectangle r4[] = new Rectangle[8];
        Rectangle r5[] = new Rectangle[2];
        Rectangle r6[] = new Rectangle[8];
        Rectangle r7[] = new Rectangle[6];  
        
        
        
        r0[0] = box_00;
        r0[1] = box_01;
        r0[2] = box_02;
        
        r1[0] = box_10;
        r1[1] = box_11;
        r1[2] = box_12;
        r1[3] = box_13;
        
        r2[0] = box_20;
        r2[1] = box_21;
        r2[2] = box_22;
        r2[3] = box_23;
        r2[4] = box_24;
        r2[5] = box_25;
        
        r3[0] = box_30;
        r3[1] = box_31;
        r3[2] = box_32;
        r3[3] = box_33;
        r3[4] = box_34;
        r3[5] = box_35;
        r3[6] = box_36;

        r4[0] = box_40;
        r4[1] = box_41;
        r4[2] = box_42;
        r4[3] = box_43;
        r4[4] = box_44;
        r4[5] = box_45;
        r4[6] = box_46;
        r4[7] = box_47; 
        
        r5[0] = box_50;
        r5[1] = box_51;
        
        r6[0] = box_60;
        r6[1] = box_61;
        r6[2] = box_62;
        r6[3] = box_63;
        r6[4] = box_64;
        r6[5] = box_65;
        r6[6] = box_66;
        r6[7] = box_67;
        
        r7[0] = box_70;
        r7[1] = box_71;
        r7[2] = box_72;
        r7[3] = box_73;
        r7[4] = box_74;
        r7[5] = box_75;
        
      
        
        Color c = Color.web("#07032e");// blue
        Color b = Color.web("#000000");// white
        
        Color a = Color.web("#86036e");//pink
        Color d = Color.web("#858a05");//yellow
        Color e = Color.web("#02667c");//sky-blue
        Color f = Color.web("#8a0606");// red
        Color g = Color.web("#a63b05");// orange
        Color h = Color.web("#075702");
        
        Process p00 =  new Process(r0[0],a,b);
        Thread  t00 = new Thread(p00);
        
        Process p01 =  new Process(r0[1],a,b);
        Thread  t01 = new Thread(p01);
        
        Process p02 =  new Process(r0[2],a,b);
        Thread  t02 = new Thread(p02);
        
        Process p10 =  new Process(r1[0],c,b);
        Thread  t10 = new Thread(p10);
        

        Process p11 =  new Process(r1[1],c,b);
        Thread  t11 = new Thread(p11);
        
        Process p12 =  new Process(r1[2],c,b);
        Thread  t12 = new Thread(p12);
        
        Process p13 =  new Process(r1[3],c,b);
        Thread  t13 = new Thread(p13);
        
        Process p20 =  new Process(r2[0],d,b);
        Thread  t20 = new Thread(p20);
        

        Process p21 =  new Process(r2[1],d,b);
        Thread  t21 = new Thread(p21);
        
        Process p22 =  new Process(r2[2],d,b);
        Thread  t22 = new Thread(p22);
        
        Process p23 =  new Process(r2[3],d,b);
        Thread  t23 = new Thread(p23);
        
        Process p24 =  new Process(r2[4],d,b);
        Thread  t24 = new Thread(p24);
        

        Process p25 =  new Process(r2[5],d,b);
        Thread  t25 = new Thread(p25);
        
        Process p30 =  new Process(r3[0],e,b);
        Thread  t30 = new Thread(p30);
        
        Process p31 =  new Process(r3[1],e,b);
        Thread  t31 = new Thread(p31);
        
        Process p32 =  new Process(r3[2],e,b);
        Thread  t32 = new Thread(p32);
        

        Process p33 =  new Process(r3[3],e,b);
        Thread  t33 = new Thread(p33);
        
        Process p34 =  new Process(r3[4],e,b);
        Thread  t34 = new Thread(p34);
        
        Process p35 =  new Process(r3[5],e,b);
        Thread  t35 = new Thread(p35);
        
        Process p36 =  new Process(r3[6],e,b);
        Thread  t36 = new Thread(p36);
        
        Process p40 =  new Process(r4[0],f,b);
        Thread  t40 = new Thread(p40);
        
        Process p41 =  new Process(r4[1],f,b);
        Thread  t41 = new Thread(p41);
        
        Process p42 =  new Process(r4[2],f,b);
        Thread  t42 = new Thread(p42);
        
        Process p43 =  new Process(r4[3],f,b);
        Thread  t43 = new Thread(p43);
        

        Process p44 =  new Process(r4[4],f,b);
        Thread  t44 = new Thread(p44);
        
        Process p45 =  new Process(r4[5],f,b);
        Thread  t45 = new Thread(p45);
        
        Process p46 =  new Process(r4[6],f,b);
        Thread  t46 = new Thread(p46);
        
        Process p47 =  new Process(r4[7],f,b);
        Thread  t47 = new Thread(p47);
        
        
        Process p50 =  new Process(r5[0],c,b);
        Thread  t50 = new Thread(p50);
        
        Process p51 =  new Process(r5[1],c,b);
        Thread  t51 = new Thread(p51);
        
        Process p60 =  new Process(r6[0],h,b);
        Thread  t60 = new Thread(p60);
        

        Process p61 =  new Process(r6[1],h,b);
        Thread  t61 = new Thread(p61);
        
        Process p62 =  new Process(r6[2],h,b);
        Thread  t62 = new Thread(p62);
        
        Process p63 =  new Process(r6[3],h,b);
        Thread  t63 = new Thread(p63);
        
        Process p64 =  new Process(r6[4],h,b);
        Thread  t64 = new Thread(p64);
        

        Process p65 =  new Process(r6[5],h,b);
        Thread  t65 = new Thread(p65);
        
        Process p66 =  new Process(r6[6],h,b);
        Thread  t66 = new Thread(p66);
        
        Process p67 =  new Process(r6[7],h,b);
        Thread  t67 = new Thread(p67);
        
        Process p70 =  new Process(r7[0],g,b);
        Thread  t70 = new Thread(p70);
        

        Process p71 =  new Process(r7[1],g,b);
        Thread  t71 = new Thread(p71);
        
        Process p72 =  new Process(r7[2],g,b);
        Thread  t72 = new Thread(p72);
        
        Process p73 =  new Process(r7[3],g,b);
        Thread  t73 = new Thread(p73);
        
        Process p74 =  new Process(r7[4],g,b);
        Thread  t74 = new Thread(p74);
        

        Process p75 =  new Process(r7[5],g,b);
        Thread  t75 = new Thread(p75);
        
        
        t00.start();
        t00.join(400);
        t01.start();
        t01.join(200);
        
        t02.start();
        t02.join(400);
        t10.start();
        t10.join(200);
        
        t11.start();
        t11.join(400);
        t12.start();
        t12.join(200);
        
        t13.start();
        t13.join(400);
        t20.start();
        t20.join(200);
        
        
        t21.start();
        t21.join(400);
        t22.start();
        t22.join(200);
        
        t23.start();
        t23.join(400);
        t24.start();
        t24.join(200);
        
        t25.start();
        t25.join(400);
        t30.start();
        t30.join(200);
        
        t31.start();
        t31.join(400);
        t32.start();
        t32.join(200);
        
        t33.start();
        t33.join(400);
        t34.start();
        t34.join(200);
        
        t35.start();
        t35.join(400);
        t36.start();
        t36.join(200);
        
        
        t40.start();
        t40.join(400);
        t41.start();
        t41.join(200);
        
        t42.start();
        t42.join(400);
        t43.start();
        t43.join(200);
        
        t44.start();
        t44.join(400);
        t45.start();
        t45.join(200);
        
        t46.start();
        t46.join(400);
        t47.start();
        t47.join(200);
        
        
        t50.start();
        t50.join(400);
        t51.start();
        t51.join(200);
        
        t60.start();
        t60.join(400);
        
        t61.start();
        t61.join(200);
        t62.start();
        t62.join(400);
        
        t63.start();
        t63.join(200);
        t64.start();
        t64.join(400);
        
        t65.start();
        t65.join(200);
        t66.start();
        t66.join(400);
        
        t67.start();
        t67.join(200);
        t70.start();
        t70.join(400);
        
        t71.start();
        t71.join(200);
        t72.start();
        t72.join(400);
        
        
        t73.start();
        t73.join(200);
        t74.start();
        t74.join(400);
        
        t75.start();
        t75.join(200);
        
    }
    catch(Exception ex){
         ex.printStackTrace();    
     }
    
    }   
    
}
