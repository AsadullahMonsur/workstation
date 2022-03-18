package com.javaEX.ui.components;

import com.javaEX.ui.components.button.Button;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Button b = new Button("Ghum astese");
        b.setPrefHeight(40);
        b.setPrefWidth(200);
        Pane root = new Pane();
        root.getChildren().add(b);
        Scene scene=new Scene(root,700,450);
        File file = new File("E:\\WorkStation\\javafx_custom\\css\\button\\button.css");
        scene.getStylesheets().add(file.toURI().toString());
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaEX API");
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
