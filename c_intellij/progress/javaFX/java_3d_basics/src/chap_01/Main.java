package chap_01;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int height = 600;
    private static final int width = 1280;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Sphere sphere_01 = new Sphere(50);
        Group group_01 = new Group();
        group_01.getChildren().add(sphere_01);

        Camera camera = new PerspectiveCamera();
        Scene scene_01 = new Scene(group_01,width,height);
        scene_01.setFill(Color.SILVER);
        scene_01.setCamera(camera);

        sphere_01.translateXProperty().setValue(width/2);
        sphere_01.translateYProperty().setValue(height/2);


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,event -> {
            switch (event.getCode()){
                case UP:
                    sphere_01.translateZProperty().setValue(sphere_01.getTranslateZ() + 500);
                    break;

                case DOWN:
                    sphere_01.translateZProperty().setValue(sphere_01.getTranslateZ() - 500);
                    break;
            }
        });

        primaryStage.setTitle("JavaFX 3D");
        primaryStage.setScene(scene_01);
        primaryStage.show();

    }


    public static void main(String[] args) {

        launch(args);
    }
}
