package com.hospital;

import com.hospital.others.Hospital;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
        launch(args);
    }
    public static Hospital h = new Hospital("Central Hospital");

    @Override
    public void start(Stage primaryStage) throws Exception {
      try {
        URL path =  getClass().getResource("/sign_in/sign_in.fxml");
        Parent layout = FXMLLoader.load(path);

        Scene scene = new Scene(layout, 1024, 620);

        primaryStage.setTitle("Hospital Management");
        primaryStage.setScene(scene);
        primaryStage.show();
        }

      catch (IOException e) {
         e.printStackTrace();
         System.out.println("Error loading Sign In Page: "+e.getMessage());
      }
    }

}
