package main_view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Rectangle rec = new Rectangle(20,20);
        Group root = new Group(rec);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
        String path1 = "/resources/chair_01.obj";
        String path2 = "/resources/model.obj";
        Object_Loader loader = new Object_Loader(
                getClass().getResource(path2).getFile(),primaryStage);
    }


    public static void main(String[] args) {
        launch(args);
    }
}
