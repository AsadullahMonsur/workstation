package com.orangeflower.darkroom_manager;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DarkroomCentrum implements Initializable {
    @FXML
    private AnchorPane root;

    @FXML
    private AnchorPane side_bar_base;

    @FXML
    private AnchorPane darkroom_centrum_three_base;

    @FXML
    private AnchorPane top_bar_base;

    @FXML
    private AnchorPane main_view_base;

    @FXML
    private AnchorPane bottom_bar_base;

    @FXML
    private ScrollPane darkroom_centrum_bottom_bar_scroll_pane;

    @FXML
    private GridPane darkroom_centrum_bottom_bar_grid;

    private double x,y;
    private ArrayList<AnchorPane> base_containers;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        base_containers = new ArrayList<AnchorPane>();
        base_containers.add(root);
        base_containers.add(side_bar_base);
        base_containers.add(darkroom_centrum_three_base);
        base_containers.add(main_view_base);
        base_containers.add(bottom_bar_base);

        load_accessories(location,resources);
    }

    private void load_accessories(URL location, ResourceBundle resources) {
        load_top_bar();
        load_side_bar();
    }

    private void load_side_bar() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(
                    "/layouts/darkroom_manager/darkroom_side_bar.fxml"));
            AnchorPane side_bar = loader.load();

            DarkroomSideBar side_bar_controller = loader.getController();
            side_bar_controller.push_base_containers(base_containers);
            side_bar_controller.push_bottom_bar_grid(darkroom_centrum_bottom_bar_grid);

            side_bar_base.getChildren().addAll(side_bar);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in loading side bar");
        }
    }

    private void load_top_bar() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(
                    "/layouts/darkroom_manager/darkroom_top_bar.fxml"));
            AnchorPane top_bar = loader.load();

            AnchorPane.setTopAnchor(top_bar, 0.0);
            AnchorPane.setLeftAnchor(top_bar, 0.0);
            AnchorPane.setRightAnchor(top_bar, 0.0);

            DarkroomTopBar top_bar_controller = loader.getController();
            top_bar_controller.push_base_containers(base_containers);

            top_bar_base.getChildren().addAll(top_bar);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error in loading top bar");
        }
    }


    @FXML
    void drag_screen_to_place(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setX(event.getScreenX()-x);
        stage.setY(event.getScreenY()-y);
    }

    @FXML
    void press_to_pick_screen(MouseEvent event) {
        x = event.getX();
        y = event.getY();
    }
}
