package main_section;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import runtime_load.Load_Scheduler;

public class Main extends Application {

    public static Stage primaryStage = new Stage();
    public static FlowPane root = new FlowPane();
    public static Scene scene;
    public static String url_txt;

    private static String arg[];

    @Override
    public void start(Stage primaryStage) throws Exception{
       // this.primaryStage = primaryStage;
        root.setVgap(10);
        root.setHgap(10);
        root.setPrefWrapLength(1080);

        TextField search_tf = new TextField();
        search_tf.setAlignment(Pos.CENTER);
        search_tf.setPromptText("Enter Your Search Info"); //to set the hint text
        search_tf.requestFocus();
        search_tf.setPrefHeight(30);
        search_tf.setPrefWidth(600);

        Button b = new Button("Search");
        b.setPrefHeight(30);
        b.setPrefWidth(200);
        b.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                url_txt = search_tf.getText().toString();
                System.out.println(url_txt);
                Load_Scheduler ls = new Load_Scheduler();
                ls.processor();
            }
        });

        root.getChildren().addAll(search_tf,b);

        scene = new Scene(root, 1080, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Turbulent");
        primaryStage.show();
    }

    public static void main(String[] args) {
        arg = args;
        launch(args);
    }
}
