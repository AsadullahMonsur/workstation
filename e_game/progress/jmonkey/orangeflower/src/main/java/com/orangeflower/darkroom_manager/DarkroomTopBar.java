package com.orangeflower.darkroom_manager;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.net.URL;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DarkroomTopBar implements Initializable {

    @FXML
    private AnchorPane darkroom_top_bar_container;

    @FXML
    private ImageView darkroom_top_bar_menu_img_btn;

    private ArrayList<AnchorPane> base_containers;

    private int side_nav_flag = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            configure_icon();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in Darkroom Top Bar initialization");
        }
    }

    @FXML
    void side_bar_visualizer(MouseEvent event) {
        try{
            TranslateTransition t1 = new TranslateTransition(
                                        Duration.seconds(0.5),
                                        base_containers.get(1));

            if(side_nav_flag==0){
                t1.setToX(-200);

                t1.setOnFinished(event1 -> {
                    fluid_behaviour(0.0);
                });

                side_nav_flag = 1;
            }
            else{
                base_containers.get(0).getChildren().add(base_containers.get(1));
                t1.setToX(0);

                t1.setOnFinished(event1 -> {
                    fluid_behaviour(200.0);
                });

                side_nav_flag = 0;
            }

            t1.play();
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error in menu click, side bar visualizer");
        }
    }

    private void fluid_behaviour(double x){

        AnchorPane.setTopAnchor(base_containers.get(2), 0.0);
        AnchorPane.setLeftAnchor(base_containers.get(2),x );
        AnchorPane.setRightAnchor(base_containers.get(2), 0.0);
        AnchorPane.setBottomAnchor(base_containers.get(2), 0.0);

        base_containers.get(0).getChildren().clear();

        if(side_nav_flag==0){
            base_containers.get(0).getChildren().addAll(
                    base_containers.get(1),
                    base_containers.get(2));
        }
        else {
            base_containers.get(0).getChildren().addAll(base_containers.get(2));
        }

    }

    public void push_base_containers(ArrayList<AnchorPane> base_containers) {
        this.base_containers = base_containers;
    }

    @FXML
    void menu_hover_on_enter(MouseEvent event) {
        Image menu_icon_02 = new Image("/images/menu_bar/menu_icon_07_45x45.png");
        darkroom_top_bar_menu_img_btn.setImage(menu_icon_02);
    }

    @FXML
    void menu_hover_on_exit(MouseEvent event) {
        Image menu_icon_01 = new Image("/images/menu_bar/menu_icon_06_45x45.png");
        darkroom_top_bar_menu_img_btn.setImage(menu_icon_01);
    }

    private void configure_icon(){
            Image menu_icon_01 = new Image("/images/menu_bar/menu_icon_06_45x45.png");
            darkroom_top_bar_menu_img_btn.setImage(menu_icon_01);
    }

    // alternative transition method

    private void alternative_transition(){
        Timeline timeline = new Timeline();
        KeyValue kv;
        if (side_nav_flag==0) {
            kv = new KeyValue(base_containers.get(1).translateXProperty(),
                         -200, Interpolator.EASE_BOTH);
            side_nav_flag = 1;
        }
        else {
            kv = new KeyValue(base_containers.get(1).translateXProperty(),
                          0, Interpolator.LINEAR);
            side_nav_flag = 0;
            }
            KeyFrame kf = new KeyFrame(Duration.seconds(0.5),kv);
            timeline.getKeyFrames().add(kf);
            timeline.play();
    }
}
