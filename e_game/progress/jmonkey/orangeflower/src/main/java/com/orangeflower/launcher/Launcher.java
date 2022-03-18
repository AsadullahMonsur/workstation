package com.orangeflower.launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.logging.Level;
import java.util.logging.Logger;
//
//
public class Launcher extends Application {
    public static void main(String []args){
        Rectangle2D screenBounds = Screen.getPrimary().getBounds();
        System.out.println(screenBounds);
       // System.exit(0);
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
      try {
        Pane root = FXMLLoader.load(getClass().getResource("/layouts/darkroom_manager/darkroom_centrum.fxml"));

        Scene scene = new Scene(root, 1080, 720);
        root.prefHeightProperty().bind(scene.heightProperty());
        root.prefWidthProperty().bind(scene.widthProperty());

        primaryStage.setTitle("Sparklings");
        //primaryStage.initStyle(StageStyle.TRANSPARENT);
        primaryStage.setScene(scene);
        primaryStage.show();
      }
      catch (Exception e){
        Logger.getLogger(Launcher.class.getName()).log(Level.SEVERE, null, e);
        e.printStackTrace();
        System.out.println("Error in Launcher");
      }
    }
}
