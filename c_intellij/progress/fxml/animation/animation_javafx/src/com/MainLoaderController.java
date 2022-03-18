/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.FillTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 *
 * @author Asadullah Monsur
 */
public class MainLoaderController implements Initializable {
    
    @FXML
    private Rectangle box_00;
    @FXML
    private Rectangle box_01;
    @FXML
    private Rectangle box_02;
    @FXML
    private Rectangle box_03;
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
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            
            Rectangle [][]box = new Rectangle[4][4];
            box[0][0] = box_00;
            box[0][1] = box_01;
            box[0][2] = box_02;
            box[0][3] = box_03;
            
            box[1][0] = box_10;
            box[1][1] = box_11;
            box[1][2] = box_12;
            box[1][3] = box_13;
            
            box[2][0] = box_20;
            box[2][1] = box_21;
            box[2][2] = box_22;
            box[2][3] = box_23;
                     
            
            int checker[][] = new int[4][4];
            
            Color []c = new Color[7];
            c[0] = Color.web("#020e2f");
            c[1] = Color.web("#ffffff");
            
            Thread thread0 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f0 = new FillTransition();
                        f0.setDuration(Duration.millis(2000));
                        f0.setFromValue(c[0]);  
                        f0.setToValue(c[1]);  
                        f0.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f0.setAutoReverse(true);
                        f0.setShape(box[0][0]);
                        f0.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        
        
            Thread thread1 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f1 = new FillTransition();
                        f1.setDuration(Duration.millis(2000));
                        f1.setFromValue(c[0]);  
                        f1.setToValue(c[1]);  
                        f1.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f1.setAutoReverse(true);
                        f1.setShape(box[0][1]);
                        f1.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
            
            
            Thread thread2 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[0][2]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
            
             Thread thread3 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[0][3]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
             
              Thread thread4 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[1][3]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
               Thread thread5 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[1][2]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
               
                Thread thread6 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[1][1]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
                
                
                Thread thread7 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[1][0]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
                
                   Thread thread8 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[2][0]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
                   
                      Thread thread9 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[2][1]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
                      
                      
                         Thread thread10 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[2][2]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
                         
                            Thread thread11 = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {
                        
                        FillTransition f2 = new FillTransition();
                        f2.setDuration(Duration.millis(2000));
                        f2.setFromValue(c[0]);  
                        f2.setToValue(c[1]);  
                        f2.setCycleCount(FillTransition.INDEFINITE); //FillTransition.INDEFINITE 
                        f2.setAutoReverse(true);
                        f2.setShape(box[2][3]);
                        f2.play();
                        
                        
                    }
                };

                while (true) {
                    try {
                        Thread.sleep(4000);
                    } catch(Exception ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
       // thread.setDaemon(true);
        thread0.start();
        thread0.join(400);
        thread1.start();
        thread1.join(200);
        thread2.start();
        thread2.join(400);
        thread3.start();
        thread3.join(200);
        thread4.start();
        thread4.join(400);
        thread5.start();
        thread5.join(200);
        thread6.start();
        thread6.join(400);
        thread7.start();
        thread7.join(200);
        thread8.start();
        thread8.join(400);
        thread9.start();
        thread9.join(200);
        thread10.start();
        thread10.join(400);
        thread11.start();
        thread11.join(200);
       
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
    }    
    
}
