package chap_02;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;

public class Camera_Move extends Application {

    private static final int height = 600;
    private static final int width = 1280;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Sphere sphere_01 = new Sphere(50);
        Group group_01 = new Group();
        group_01.getChildren().add(sphere_01);

        Camera camera = new PerspectiveCamera(true);
        Scene scene_01 = new Scene(group_01,width,height);
        scene_01.setFill(Color.SILVER);
        scene_01.setCamera(camera);

        camera.translateXProperty().setValue(0);
        camera.translateYProperty().setValue(0);
        camera.translateZProperty().setValue(-500);

        camera.setNearClip(1);
        camera.setFarClip(1000);


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,event -> {
            switch (event.getCode()){
                case UP:
                    camera.translateZProperty().setValue(camera.getTranslateZ() + 100);
                    break;

                case DOWN:
                    camera.translateZProperty().setValue(camera.getTranslateZ() - 100);
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
