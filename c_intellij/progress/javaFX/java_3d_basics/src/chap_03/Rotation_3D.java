package chap_03;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class Rotation_3D extends Application {

    private static final int height = 600;
    private static final int width = 1280;

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Box box_01 = new Box(100,20,50);
        Smart_Group group_01 = new Smart_Group();
        group_01.getChildren().add(box_01);

        Camera camera = new PerspectiveCamera();
        Scene scene_01 = new Scene(group_01,width,height);
        scene_01.setFill(Color.SILVER);
        scene_01.setCamera(camera);

        group_01.translateXProperty().setValue(width/2);
        group_01.translateYProperty().setValue(height/2);
        group_01.translateZProperty().setValue(-500);


        primaryStage.addEventHandler(KeyEvent.KEY_PRESSED,event -> {
            switch (event.getCode()){
               case UP:
                    group_01.translateZProperty().setValue(group_01.getTranslateZ() + 100);
                    break;

                case DOWN:
                    group_01.translateZProperty().setValue(group_01.getTranslateZ() - 100);
                    break;

                case Q:
                    group_01.rotate_x(15);
                    break;

                case E:
                    group_01.rotate_x(-15);
                    break;

                case A:
                   group_01.rotate_y(15);
                    break;

                case D:
                    group_01.rotate_y(-15);
                    break;

                case Z:
                    group_01.rotate_z(15);
                    break;

                case C:
                    group_01.rotate_z(-15);
                    break;
            }
        });

        primaryStage.setTitle("JavaFX 3D");
        primaryStage.setScene(scene_01);
        primaryStage.show();

    }

class Smart_Group extends Group{

        Rotate r;
        Transform t = new Rotate();

        void rotate_x(int ang){
            r = new Rotate(ang,Rotate.X_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotate_y(int ang){
            r = new Rotate(ang,Rotate.Y_AXIS);
            t = t.createConcatenation(t);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);
        }

        void rotate_z(int ang){
            r = new Rotate(ang,Rotate.Z_AXIS);
            t = t.createConcatenation(r);
            this.getTransforms().clear();
            this.getTransforms().addAll(t);}
        }

    public static void main(String[] args) {
        launch(args);
    }
}
