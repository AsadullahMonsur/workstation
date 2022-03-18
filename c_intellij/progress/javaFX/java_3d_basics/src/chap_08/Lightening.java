package chap_08;

import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Lightening extends Application {

    private static final float height = 600;
    private static final float width = 1280;

    private double anchor_x,anchor_y;
    private double anchor_angle_x = 0;
    private double anchor_angle_y = 0;

    private final DoubleProperty angle_x = new SimpleDoubleProperty(0);
    private final DoubleProperty angle_y = new SimpleDoubleProperty(0);


    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Box box_01 = prepare_box();
        Smart_Group group_01 = new Smart_Group();
        group_01.getChildren().add(box_01);
        group_01.getChildren().addAll(prepare_light_source());

        Camera camera = new PerspectiveCamera();
        Scene scene_01 = new Scene(group_01,width,height);
        scene_01.setFill(Color.SILVER);
        scene_01.setCamera(camera);

        group_01.translateXProperty().setValue(width/2);
        group_01.translateYProperty().setValue(height/2);
        group_01.translateZProperty().setValue(-500);


        initialize_mouse_controll(group_01,scene_01,primaryStage);

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

    private Node[] prepare_light_source() {
//        AmbientLight light = new AmbientLight();
//        light.setColor(Color.AQUA);

        PointLight light = new PointLight();
        light.setColor(Color.AQUA);
        light.getTransforms().add(new Translate(0,-50,100));

        Sphere sphere_02 = new Sphere(20);
        sphere_02.getTransforms().setAll(light.getTransforms());
        return new Node[]{light,sphere_02};
    }

    private Box prepare_box(){
        PhongMaterial phongMaterial= new PhongMaterial();
        // phongMaterial.setDiffuseColor(Color.DARKBLUE);

        phongMaterial.setDiffuseMap(new Image(getClass().getResourceAsStream("/resources/texture_03.jpg")));
        // phongMaterial.setSpecularColor(Color.BLACK);
        // phongMaterial.setSpecularColor(Color.WHITE);
        phongMaterial.setSpecularColor(Color.DARKBLUE);
        // phongMaterial.setSpecularMap(new Image(getClass().getResourceAsStream("/resources/texture_02.jpg")));

        Box box_01 = new Box(100,20,50);
        box_01.setMaterial(phongMaterial);
        return box_01;

    }
    private void initialize_mouse_controll(Smart_Group group_01, Scene scene_01,Stage stage) {
        Rotate x_rotation = new Rotate(0,Rotate.X_AXIS);
        Rotate y_rotation = new Rotate(0,Rotate.Y_AXIS);

        group_01.getTransforms().addAll(x_rotation,y_rotation);

        x_rotation.angleProperty().bind(angle_x);
        y_rotation.angleProperty().bind(angle_y);

        scene_01.setOnMouseClicked(event -> {
            anchor_x = event.getSceneX();
            anchor_y = event.getSceneY();

            anchor_angle_x = angle_x.get();
            anchor_angle_y = angle_y.get();
        });

        scene_01.setOnMouseDragged(event -> {
            angle_x.set(anchor_angle_x - (anchor_y - event.getSceneY()));
            angle_y.set(anchor_angle_y - (anchor_x - event.getSceneX()));
        });

        stage.addEventHandler(ScrollEvent.SCROLL,event -> {
            double movement = event.getDeltaY();
            group_01.translateZProperty().setValue(group_01.getTranslateZ() + movement);

        });
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
