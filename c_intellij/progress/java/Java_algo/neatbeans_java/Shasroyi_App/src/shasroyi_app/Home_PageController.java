/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shasroyi_app;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asadullah Monsur
 */
public class Home_PageController implements Initializable {

   
    @FXML
    private AnchorPane customer_Middle_Content;

  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void move_to_personal_info(ActionEvent event) throws IOException {
        
        Parent root = FXMLLoader.load(getClass().getResource("Personal_Info.fxml"));
        customer_Middle_Content.getChildren().setAll(root);
    }
    
}
