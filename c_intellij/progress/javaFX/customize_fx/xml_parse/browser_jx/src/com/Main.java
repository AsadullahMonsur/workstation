package com;

import com.page.Page;
import com.parser.XML_Parser;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Button b = new Button("Submit");
        b.setOnAction(e->
                frontpage(primaryStage)
        );
        GridPane root1 = new GridPane();
        root1.addRow(0,b);

        Scene scene = new Scene(root1,800,500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Browser");
        primaryStage.show();
    }

    private void frontpage(Stage primaryStage) {
        try {
            XML_Parser parser = new XML_Parser();
            Page page = parser.xml_to_page();
            GridPane root2 = new GridPane();
            root2.addRow(0, page.getLabels().get(0));
            root2.addRow(1, page.getTextFields().get(0));
            root2.addRow(2, page.getButtons().get(0));
            Scene scene = new Scene(root2, 800, 200);
            primaryStage.setScene(scene);
            primaryStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error From XML to parse");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
