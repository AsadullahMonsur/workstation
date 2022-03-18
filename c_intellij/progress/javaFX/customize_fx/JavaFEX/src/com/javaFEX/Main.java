package com.javaFEX;

import com.javaFEX.converter.xml.XML_Formatter;
import com.javaFEX.ui.components.Pages;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        String directory = "E:\\WorkStation\\javafx_custom\\JavaFEX_Output";
        XML_Formatter formatter = new XML_Formatter(new Pages(),directory);
        Pane display = new Pane();
        Scene scene = new Scene(display,800, 500);
        primaryStage.setTitle("JavaFEX (Java for Formatting XML)");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
