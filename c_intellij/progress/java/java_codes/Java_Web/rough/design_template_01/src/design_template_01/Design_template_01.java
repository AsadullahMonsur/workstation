/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package design_template_01;

import java.io.File;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Asadullah Monsur
 */
public class Design_template_01 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("windowtab.fxml"));
        
        Scene scene = new Scene(root);
        
//        File file = new File("neon.css");
//        URL url = file.toURI().toURL();
//        scene.getStylesheets().add(url.toExternalForm());

      //  scene.getStylesheets().add(getClass().getResource("neon.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
