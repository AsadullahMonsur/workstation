/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shasroyi_app;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author Asadullah Monsur
 */
public class Shasroyi_OpeningController implements Initializable {
    
   
    @FXML
    private AnchorPane opening_pane;
    
    @FXML
    private ScrollPane opening_scrollpane;
    
    private void handleButtonAction(ActionEvent event) {
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try{
        
         opening_scrollpane.setFitToWidth(false); 
         opening_scrollpane.setFitToHeight(false); 
            
         Parent root = FXMLLoader.load(getClass().getResource("Sign_In_Page.fxml"));
         opening_pane.setPrefHeight(3000);
         opening_pane.setPrefHeight(1000);
         
         opening_pane.getChildren().setAll(root);
        }
        catch(Exception e){
        e.printStackTrace();
        }
      
    }    
    
}
