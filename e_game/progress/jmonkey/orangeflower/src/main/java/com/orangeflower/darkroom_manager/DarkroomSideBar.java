package com.orangeflower.darkroom_manager;

import com.orangeflower.showroom.CarShowroomMainView;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DarkroomSideBar implements Initializable {

    @FXML
    private AnchorPane darkroom_side_bar_container;

    @FXML
    private VBox darkroom_side_bar_holder;

    @FXML
    private AnchorPane darkroom_side_bar_title_extender;

    @FXML
    private AnchorPane darkroom_side_bar_title_container;

    @FXML
    private ImageView darkroom_side_bar_title_close_btn;

    @FXML
    private AnchorPane darkroom_side_bar_extender;

    @FXML
    private AnchorPane darkroom_side_bar_instructions_btn_container;

    @FXML
    private Button darkroom_side_bar_instruction_btn;

    @FXML
    private AnchorPane darkroom_side_bar_game_container;

    @FXML
    private Button darkroom_side_bar_game_btn;

    @FXML
    private AnchorPane darkroom_side_bar_championship_container;

    @FXML
    private Button darkroom_side_bar_championship_btn;

    @FXML
    private AnchorPane darkroom_side_bar_showroom_container;

    @FXML
    private Button darkroom_side_bar_showroom_btn;

    @FXML
    private AnchorPane darkroom_side_bar_friends_container;

    @FXML
    private Button darkroom_side_bar_friends_btn;

    @FXML
    private AnchorPane darkroom_side_bar_settings_container;

    @FXML
    private Button darkroom_side_bar_settings_btn;

    @FXML
    private AnchorPane darkroom_side_bar_notifications_container;

    @FXML
    private Button darkroom_side_bar_notifications_btn;

    @FXML
    private AnchorPane darkroom_side_bar_exit_container;

    @FXML
    private Button darkroom_side_bar_exit_btn;

    private AudioClip clip;
    private ArrayList<AnchorPane> base_containers;
    private GridPane darkroom_centrum_bottom_bar_grid;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            configure_icon();
            String path = "/Sounds/lightening/select_02.mp3";
            clip = new AudioClip(getClass().getResource(path).toURI().toString());
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Could not find media file");
        }
    }

    public void push_base_containers(ArrayList<AnchorPane> base_containers) {
        this.base_containers = base_containers;
    }

    public void push_bottom_bar_grid(GridPane darkroom_centrum_bottom_bar_grid) {
        this.darkroom_centrum_bottom_bar_grid = darkroom_centrum_bottom_bar_grid;
    }

    @FXML
    void on_entering_bar_title_close_btn(MouseEvent event) {
        Image menu_icon_01 = new Image("/images/window/exit_icon_01.png");
        darkroom_side_bar_title_close_btn.setImage(menu_icon_01);
    }

    @FXML
    void on_entering_to_instructions(MouseEvent event){
        hover_sound_thread_manager();
        hover_manager(darkroom_side_bar_instructions_btn_container,
                      darkroom_side_bar_instruction_btn,1);
    }

    @FXML
    void on_entering_game(MouseEvent event) {
        hover_sound_thread_manager();
        hover_manager(darkroom_side_bar_game_container,
                darkroom_side_bar_game_btn,1);
    }

    @FXML
    void on_entering_championship(MouseEvent event) {
        hover_sound_thread_manager();
        hover_manager(darkroom_side_bar_championship_container,
                darkroom_side_bar_championship_btn,1);
    }

    @FXML
    void on_entering_showroom(MouseEvent event) {
        hover_sound_thread_manager();
        hover_manager(darkroom_side_bar_showroom_container,
                darkroom_side_bar_showroom_btn,1);
    }

    @FXML
    void on_entering_friends(MouseEvent event) {
        hover_sound_thread_manager();
        hover_manager(darkroom_side_bar_friends_container,
                darkroom_side_bar_friends_btn,1);
    }

    @FXML
    void on_entering_settings(MouseEvent event) {
        hover_sound_thread_manager();
        hover_manager(darkroom_side_bar_settings_container,
                darkroom_side_bar_settings_btn,1);
    }

    @FXML
    void on_entering_notifications(MouseEvent event) {
        hover_sound_thread_manager();
        hover_manager(darkroom_side_bar_notifications_container,
                darkroom_side_bar_notifications_btn,1);
    }


    @FXML
    void on_entering_exit(MouseEvent event) {
        hover_sound_thread_manager();
        hover_manager(darkroom_side_bar_exit_container,
                darkroom_side_bar_exit_btn,3);
    }

    @FXML
    void on_leaving_bar_title_close_btn(MouseEvent event) {
        Image menu_icon_01 = new Image("/images/window/exit_icon_02.png");
        darkroom_side_bar_title_close_btn.setImage(menu_icon_01);
    }

    @FXML
    void on_leave_instructions(MouseEvent event) {
        hover_manager(darkroom_side_bar_instructions_btn_container,
                darkroom_side_bar_instruction_btn,0);
    }

    @FXML
    void on_leave_game(MouseEvent event) {
        hover_manager(darkroom_side_bar_game_container,
                darkroom_side_bar_game_btn,0);
    }

    @FXML
    void on_leave_championship(MouseEvent event) {
        hover_manager(darkroom_side_bar_championship_container,
                darkroom_side_bar_championship_btn,0);
    }

    @FXML
    void on_leave_showroom(MouseEvent event) {
        hover_manager(darkroom_side_bar_showroom_container,
                darkroom_side_bar_showroom_btn,0);
    }

    @FXML
    void on_leave_friends(MouseEvent event) {
        hover_manager(darkroom_side_bar_friends_container,
                darkroom_side_bar_friends_btn,0);
    }


    @FXML
    void on_leave_settings(MouseEvent event) {
        hover_manager(darkroom_side_bar_settings_container,
                darkroom_side_bar_settings_btn,0);
    }

    @FXML
    void on_leave_notifications(MouseEvent event) {
        hover_manager(darkroom_side_bar_notifications_container,
                darkroom_side_bar_notifications_btn,0);
    }

    @FXML
    void on_leave_exit(MouseEvent event) {
        hover_manager(darkroom_side_bar_exit_container,
                darkroom_side_bar_exit_btn,0);
    }

    @FXML
    void showroom_opener(MouseEvent event) {
        try{
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(
                    "/layouts/showroom/car_showroom_main_view.fxml"));
            AnchorPane main_view = loader.load();

            AnchorPane.setTopAnchor(main_view, 0.0);
            AnchorPane.setLeftAnchor(main_view, 0.0);
            AnchorPane.setRightAnchor(main_view, 0.0);
            AnchorPane.setBottomAnchor(main_view, 0.0);

            CarShowroomMainView showroom_controller = loader.getController();
            showroom_controller.push_base_containers(base_containers);
            showroom_controller.push_bottom_bar_grid(darkroom_centrum_bottom_bar_grid);

            base_containers.get(3).getChildren().addAll(main_view);
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("Error loading showroom");
        }
    }

    private void configure_icon(){
        Image menu_icon_01 = new Image("/images/window/exit_icon_02.png");
        darkroom_side_bar_title_close_btn.setImage(menu_icon_01);
    }

    private void hover_manager(AnchorPane pane, Button btn,int flag){
        // flag 1 for hover on, flag 0 for hover off

        if(flag==1){
            pane.setStyle("-fx-background-color:#2192fb;");
            btn.setStyle("-fx-text-fill:#2192fb; " +
                    "-fx-border-radius:10px; " +
                    "-fx-border-color:#000000; " +
                    "-fx-background-radius:10px; " +
                    "-fx-background-color:#000000");
        }
        else if(flag==0){
            pane.setStyle("-fx-background-color:#000000;");
            btn.setStyle("-fx-text-fill:#b5afaf;" +
                    "-fx-border-radius:10px; " +
                    "-fx-border-color:#000000; " +
                    "-fx-background-radius:10px; " +
                    "-fx-background-color:#000000");
        }
        else if(flag==3){
            pane.setStyle("-fx-background-color:#a10827;");
            btn.setStyle("-fx-text-fill:#a10827; " +
                    "-fx-border-radius:10px; " +
                    "-fx-border-color:#000000; " +
                    "-fx-background-radius:10px; " +
                    "-fx-background-color:#000000");
        }
    }
    private void hover_sound_thread_manager(){
        Task<Void> task = new Task<Void>() {
            @Override
            protected Void call() throws Exception {
                Platform.runLater(new Runnable() {
                    public void run() {
                        //System.out.println("menu button hovered");
                        clip.play(50);
                    }
                });
                return null;
            }
        };

        new Thread(task).start();
    }

}
