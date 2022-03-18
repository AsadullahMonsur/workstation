package com.game_3d;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.stage.Stage;

public class Main extends Application {

    private static final float window_height = 600;
    private static final float window_width = 1200;

    private double anchor_x,anchor_y;
    private double anchor_angle_x = 0;
    private double anchor_angle_y = 0;

    private final DoubleProperty angle_x = new SimpleDoubleProperty(0);
    private final DoubleProperty angle_y = new SimpleDoubleProperty(0);

    private final Box earth = new Box(100,20,50);

    @Override
    public void start(Stage primaryStage) throws Exception{
        //    Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        Camera camera = new PerspectiveCamera(true);
        camera.setNearClip(1);
        camera.setFarClip(1000);
        camera.translateZProperty().setValue(-650);
        camera.setRotate(360);

        Smart_Group group = new Smart_Group();
           group.getChildren().add(load_3d_model());
        //   group.getChildren().add();

        Scene scene = new Scene(group,window_width,
                window_height,true);
        scene.setFill(Color.WHITE);
        scene.setCamera(camera);

        primaryStage.setTitle("Game 3D");
        primaryStage.setScene(scene);
        primaryStage.show();

        mouse_controller(group,scene,primaryStage);
        keyboard_controller(primaryStage,group);
        //prepare_animation();
    }

    private Node prepare_obj() {
        //earth.setRotationAxis(Rotate.Y_AXIS);
        return earth;
    }

    private Group load_3d_model(){
        Group model = new Group();
        ObjModelImporter importer = new ObjModelImporter();
        //getClass().getResource("model.obj");
        importer.read(getClass().getResource("/model.obj"));

        for(MeshView meshView : importer.getImport()) {
            model.getChildren().add(meshView);
        }
        model.getTransforms().add(new Rotate(90,Rotate.Y_AXIS));
        model.setTranslateX(600);
        model.setTranslateY(300);
        model.setScaleX(900);
        model.setScaleY(900);
        model.setScaleZ(900);
        return model;
    }

    private void prepare_animation() {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long now) {

                earth.rotateProperty().set(earth.getRotate() - 1);
            }
        };

        timer.start();

    }

    private void mouse_controller(Smart_Group group,
                                  Scene scene,Stage stage) {

        Rotate x_rotation = new Rotate(0,Rotate.X_AXIS);
        Rotate y_rotation = new Rotate(0,Rotate.Y_AXIS);

        group.getTransforms().addAll(x_rotation,y_rotation);

        x_rotation.angleProperty().bind(angle_x);
        y_rotation.angleProperty().bind(angle_y);

        scene.setOnMouseClicked(event -> {
            anchor_x = event.getSceneX();
            anchor_y = event.getSceneY();

            anchor_angle_x = angle_x.get();
            anchor_angle_y = angle_y.get();
        });

        scene.setOnMouseDragged(event -> {
            angle_x.set(anchor_angle_x - (anchor_y - event.getSceneY()));
            angle_y.set(anchor_angle_y - (anchor_x - event.getSceneX()));
        });

        stage.addEventHandler(ScrollEvent.SCROLL, event -> {
            double movement = event.getDeltaY();
            group.translateZProperty()
                    .setValue(group.getTranslateZ() + movement);
        });
    }

    private void keyboard_controller(Stage stage,Group group){
        stage.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            switch (event.getCode()){
                case UP:
                    group.translateYProperty()
                            .setValue(group.getTranslateY() + 100);
                    break;

                case DOWN:
                    group.translateYProperty()
                            .setValue(group.getTranslateY() - 100);
                    break;
            }
        });
    }

    private class Smart_Group extends Group {

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