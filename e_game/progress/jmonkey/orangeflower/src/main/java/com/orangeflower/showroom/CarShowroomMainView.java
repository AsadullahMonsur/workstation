package com.orangeflower.showroom;

import com.interactivemesh.jfx.importer.Importer;
import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import com.orangeflower.item_list.CarListView;
import javafx.animation.AnimationTimer;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Point3D;
import javafx.scene.*;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.PickResult;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.MeshView;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarShowroomMainView implements Initializable {
    @FXML
    private AnchorPane showroom_main_view_base;

    @FXML
    private AnchorPane showroom_tab_bar;

    @FXML
    private VBox showroom_top_bar_tab_01;

    @FXML
    private Button showroom_top_bar_tab_01_btn;

    @FXML
    private AnchorPane showroom_top_bar_tab_01_fill_left;

    @FXML
    private AnchorPane showroom_top_bar_tab_01_fill_right;

    @FXML
    private HBox showroom_top_bar_tab_01_fill_holder;

    @FXML
    private VBox showroom_top_bar_tab_02;

    @FXML
    private Button showroom_top_bar_tab_02_btn;

    @FXML
    private AnchorPane showroom_top_bar_tab_02_fill_left;

    @FXML
    private AnchorPane showroom_top_bar_tab_02_fill_right;

    @FXML
    private HBox showroom_top_bar_tab_02_fill_holder;

    @FXML
    private VBox showroom_top_bar_tab_03;

    @FXML
    private Button showroom_top_bar_tab_03_btn;

    @FXML
    private AnchorPane showroom_top_bar_tab_03_fill_left;

    @FXML
    private AnchorPane showroom_top_bar_tab_03_fill_right;

    @FXML
    private HBox showroom_top_bar_tab_03_fill_holder;

    @FXML
    private VBox showroom_top_bar_tab_04;

    @FXML
    private Button showroom_top_bar_tab_04_btn;

    @FXML
    private AnchorPane showroom_top_bar_tab_04_fill_left;

    @FXML
    private AnchorPane showroom_top_bar_tab_04_fill_right;

    @FXML
    private HBox showroom_top_bar_tab_04_fill_holder;

    @FXML
    private AnchorPane showroom_side_bar;

    @FXML
    private ImageView showroom_drop_down_menu_img_btn;

    @FXML
    private AnchorPane showroom_drop_down_panel;

    @FXML
    private AnchorPane showroom_car_viewer;

    private ArrayList<AnchorPane> base_containers;
    private GridPane darkroom_centrum_bottom_bar_grid;

    private PerspectiveCamera camera;
    private Group parent;
    private Group bed_model;
    private SubScene model_scene;
    private ArrayList<String> internal_model_paths;
    private ArrayList<String> internal_model_img_list;

    private double mouse_new_x = 0.0;
    private double mouse_new_y = 0.0;
    private double mouse_old_x = 0.0;
    private double mouse_old_y = 0.0;
    private double yaw_angle = 0.0; //rotate around y axis
    private double pitch_angle = 0.0; // //rotate around x axis
    private double distance = 0.0; // car to camera
    private double max = 0.0;

    private AnimationTimer timer1 = new NewTimer(1);
    private AnimationTimer timer2 = new NewTimer(2);
    private AnimationTimer timer3 = new NewTimer(3);
    private AnimationTimer timer4 = new NewTimer(4);

    private double pro = 0;
    private int counter_flag = 0;
    private int tab_flag = 0;
    private int tracker[] = new int[1];
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tab_flag = 0;
        counter_flag = 0;

        pro = (showroom_top_bar_tab_01_btn.getPrefWidth()/100);
        refresh_tab_view(0);
        model_paths_manager();
        model_img_list_manager();
        visualize_model();
        showroom_drop_down_panel.setVisible(false);
    }

    public void push_base_containers(ArrayList<AnchorPane> base_containers) {
        this.base_containers = base_containers;
    }

    public void push_bottom_bar_grid(GridPane darkroom_centrum_bottom_bar_grid) {
        this.darkroom_centrum_bottom_bar_grid = darkroom_centrum_bottom_bar_grid;
    }

    @FXML
    void on_drop_down_hover_entered(MouseEvent event) {
        showroom_drop_down_panel.setVisible(true);
    }

    @FXML
    void on_drop_down_hover_exited(MouseEvent event) {
        showroom_drop_down_panel.setVisible(false);
    }

    @FXML
    void top_bar__tab_01_clicked(MouseEvent event) {
        tab_flag = 1;
        top_bar_tab_clicked_manager(showroom_top_bar_tab_01_btn,
                                    showroom_top_bar_tab_01_fill_holder,1);
    }

    @FXML
    void top_bar__tab_01_entered(MouseEvent event) {
        timer1.start();
        top_bar_tab_hover_manager(showroom_top_bar_tab_01_btn,1);
    }

    @FXML
    void top_bar__tab_01_exited(MouseEvent event) {
        top_bar_tab_hover_manager(showroom_top_bar_tab_01_btn,0);
        String fu1 = "-fx-background-color: linear-gradient(to right,"+
                "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";

        String fu2 = "-fx-background-color: linear-gradient(to left,"+
                "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";

        showroom_top_bar_tab_01_fill_left.setStyle(fu1);
        showroom_top_bar_tab_01_fill_right.setStyle(fu2);

        counter_flag = 0;
        pro = (showroom_top_bar_tab_01_btn.getPrefWidth()/100);
        timer1.stop();
        if(tab_flag!=0){
            top_bar_tab_clicked_manager(showroom_top_bar_tab_01_btn,
                    showroom_top_bar_tab_01_fill_holder,tab_flag);
        }
    }

    @FXML
    void top_bar__tab_02_clicked(MouseEvent event) {
        tab_flag = 2;
        top_bar_tab_clicked_manager(showroom_top_bar_tab_02_btn,
                                    showroom_top_bar_tab_02_fill_holder,2);

        load_car_list_in_bottom_bar_base();
    }

    @FXML
    void top_bar__tab_02_entered(MouseEvent event) {
        timer2.start();
        top_bar_tab_hover_manager(showroom_top_bar_tab_02_btn,1);
    }

    @FXML
    void top_bar__tab_02_exited(MouseEvent event) {
        top_bar_tab_hover_manager(showroom_top_bar_tab_02_btn,0);
        String fu1 = "-fx-background-color: linear-gradient(to right,"+
                "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";

        String fu2 = "-fx-background-color: linear-gradient(to left,"+
                "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";

        showroom_top_bar_tab_02_fill_left.setStyle(fu1);
        showroom_top_bar_tab_02_fill_right.setStyle(fu2);

        counter_flag = 0;
        pro = (showroom_top_bar_tab_01_btn.getPrefWidth()/100);
        timer2.stop();
        if(tab_flag!=0){
            top_bar_tab_clicked_manager(showroom_top_bar_tab_02_btn,
                    showroom_top_bar_tab_02_fill_holder,tab_flag);
        }
    }

    @FXML
    void top_bar__tab_03_clicked(MouseEvent event) {
        tab_flag = 3;
        top_bar_tab_clicked_manager(showroom_top_bar_tab_03_btn,
                                    showroom_top_bar_tab_03_fill_holder,3);
    }

    @FXML
    void top_bar__tab_03_entered(MouseEvent event) {
        timer3.start();
        top_bar_tab_hover_manager(showroom_top_bar_tab_03_btn,1);
    }

    @FXML
    void top_bar__tab_03_exited(MouseEvent event) {
        top_bar_tab_hover_manager(showroom_top_bar_tab_03_btn,0);
        String fu1 = "-fx-background-color: linear-gradient(to right,"+
                "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";

        String fu2 = "-fx-background-color: linear-gradient(to left,"+
                "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";

        showroom_top_bar_tab_03_fill_left.setStyle(fu1);
        showroom_top_bar_tab_03_fill_right.setStyle(fu2);

        counter_flag = 0;
        pro = (showroom_top_bar_tab_01_btn.getPrefWidth()/100);
        timer3.stop();
        if(tab_flag!=0){
            top_bar_tab_clicked_manager(showroom_top_bar_tab_03_btn,
                    showroom_top_bar_tab_03_fill_holder,tab_flag);
        }
    }

    @FXML
    void top_bar__tab_04_clicked(MouseEvent event) {
        tab_flag = 4;
        top_bar_tab_clicked_manager(showroom_top_bar_tab_04_btn,
                                    showroom_top_bar_tab_04_fill_holder,4);
    }

    @FXML
    void top_bar__tab_04_entered(MouseEvent event) {
        top_bar_tab_hover_manager(showroom_top_bar_tab_04_btn,1);
        timer4.start();
    }

    @FXML
    void top_bar__tab_04_exited(MouseEvent event) {
        top_bar_tab_hover_manager(showroom_top_bar_tab_04_btn,0);
        String fu1 = "-fx-background-color: linear-gradient(to right,"+
                "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";

        String fu2 = "-fx-background-color: linear-gradient(to left,"+
                "#000000 "+ (100)+"%,#ffffff "+(100)+"%);";

        showroom_top_bar_tab_04_fill_left.setStyle(fu1);
        showroom_top_bar_tab_04_fill_right.setStyle(fu2);

        counter_flag = 0;
        pro = (showroom_top_bar_tab_01_btn.getPrefWidth()/100);
        timer4.stop();
        if(tab_flag!=0){
            top_bar_tab_clicked_manager(showroom_top_bar_tab_04_btn,
                    showroom_top_bar_tab_04_fill_holder,tab_flag);
        }
    }

    private void top_bar_tab_hover_manager(Button btn,int flag){
        // flag 1 for hover on, flag 0 for hover off

        if(flag==1){
            btn.setStyle("-fx-text-fill:#cae002; " +
                    "-fx-background-color:#000000");
        }
        else if(flag==0){
            btn.setStyle("-fx-text-fill:#b5afaf;" +
                    "-fx-background-color:#000000");
        }
    }


    private void top_bar_tab_clicked_manager(Button tab_btn, HBox pane, int flag) {
        tab_btn.setStyle("-fx-text-fill:#02c92a;" +
                         "-fx-background-color:#000000");
        pane.setVisible(false);
        refresh_tab_view(flag);
    }

    private void refresh_tab_view(int flag) {
        if(flag==0){
            showroom_top_bar_tab_01_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_02_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_03_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_04_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");

            showroom_top_bar_tab_01_fill_holder.setVisible(true);
            showroom_top_bar_tab_02_fill_holder.setVisible(true);
            showroom_top_bar_tab_03_fill_holder.setVisible(true);
            showroom_top_bar_tab_04_fill_holder.setVisible(true);
        }
        else if(flag==1){
            showroom_top_bar_tab_02_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_03_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_04_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");

            showroom_top_bar_tab_02_fill_holder.setVisible(true);
            showroom_top_bar_tab_03_fill_holder.setVisible(true);
            showroom_top_bar_tab_04_fill_holder.setVisible(true);
        }
        else if(flag==2){
            showroom_top_bar_tab_01_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_03_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_04_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");

            showroom_top_bar_tab_01_fill_holder.setVisible(true);
            showroom_top_bar_tab_03_fill_holder.setVisible(true);
            showroom_top_bar_tab_04_fill_holder.setVisible(true);
        }
        else if(flag==3){
            showroom_top_bar_tab_01_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_02_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_04_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");

            showroom_top_bar_tab_01_fill_holder.setVisible(true);
            showroom_top_bar_tab_02_fill_holder.setVisible(true);
            showroom_top_bar_tab_04_fill_holder.setVisible(true);
        }
        else if(flag==4){
            showroom_top_bar_tab_01_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_02_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");
            showroom_top_bar_tab_03_btn.setStyle("-fx-text-fill:#ffffff;" +
                    "-fx-background-color:#000000");

            showroom_top_bar_tab_01_fill_holder.setVisible(true);
            showroom_top_bar_tab_02_fill_holder.setVisible(true);
            showroom_top_bar_tab_03_fill_holder.setVisible(true);
        }
    }

    private void model_img_list_manager() {
        internal_model_img_list = new ArrayList<String>();
        internal_model_img_list.add("/images/car_preview/car_01_180x100.jpg");
        internal_model_img_list.add("/images/car_preview/car_02_180x100.jpg");
        internal_model_img_list.add("/images/car_preview/car_03_180x100.jpg");
        internal_model_img_list.add("/images/car_preview/car_04_180x100.jpg");
        internal_model_img_list.add("/images/car_preview/car_05_180x100.jpg");
        internal_model_img_list.add("/images/car_preview/car_06_180x100.jpg");
    }

    private void model_paths_manager() {
        internal_model_paths = new ArrayList<String>();
        internal_model_paths.add("/Models/bed/car_bed.obj");
        internal_model_paths.add("/Models/cars/type_01/car_03.obj");
        internal_model_paths.add("/Models/cars/zz/cube.obj");
        internal_model_paths.add("/Models/cars/type_01/car_03.obj");
        internal_model_paths.add("/Models/cars/zz/cube.obj");
        internal_model_paths.add("/Models/cars/type_01/car_03.obj");
        internal_model_paths.add("/Models/cars/zz/cube.obj");
    }

    private void load_car_list_in_bottom_bar_base() {
        ArrayList<CarListView> carListViews = new ArrayList<CarListView>();
        tracker[0] = -1;

        if(darkroom_centrum_bottom_bar_grid!=null){
            try {
                for (int i = 0; i < internal_model_img_list.size(); i++) {
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(getClass()
                          .getResource("/layouts/item_list/car_list_view.fxml"));
                    AnchorPane anchorPane = loader.load();

                    CarListView car_list_controller = loader.getController();
                    car_list_controller.set_car_img_viewer_src(
                                       internal_model_img_list.get(i));
                    car_list_controller.set_car_viewers(carListViews,tracker,i);
                    car_list_controller.set_model_viewer_components(
                                         new Group[]{
                                            parent,bed_model
                                         },
                                         internal_model_paths
                    );

                    carListViews.add(car_list_controller);

                    darkroom_centrum_bottom_bar_grid.setPrefWidth(
                                       internal_model_img_list.size()*184);
                    darkroom_centrum_bottom_bar_grid.add(anchorPane,i,1);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            System.out.println("Bottom base grid empty. Can not attach layouts");
        }
    }

    private void visualize_model() {
        camera = new PerspectiveCamera(true);
        camera.setVerticalFieldOfView(false);

        Group single_model = load_internal_models(internal_model_paths.get(1));
        single_model.setScaleX(0.8);
        single_model.setScaleY(0.8);
        single_model.setScaleZ(0.8);
        single_model.setTranslateY(single_model.getTranslateY()+0.2);

        bed_model = load_internal_models(internal_model_paths.get(0));
        bed_model.setScaleX(0.25);
        bed_model.setScaleY(0.25);
        bed_model.setScaleZ(0.25);
        bed_model.setTranslateY(bed_model.getTranslateY()+0.25);

        parent = new Group();
        parent.getChildren().addAll(bed_model,single_model,prepare_light_source());

        model_scene = new SubScene(parent,
                                    showroom_car_viewer.getPrefWidth(),
                                    showroom_car_viewer.getPrefHeight(),
                                    true,
                                    SceneAntialiasing.BALANCED);
        model_scene.setFill(Color.WHITE);


        max = Math.max(single_model.getBoundsInLocal().getWidth(),
        Math.max(single_model.getBoundsInLocal().getHeight(),
                 single_model.getBoundsInLocal().getDepth()));

        camera.setNearClip(0.1);
        camera.setFarClip(1000.0);
        camera.getTransforms().addAll (new Rotate(0,Rotate.X_AXIS),
                                       new Rotate(0, Rotate.Y_AXIS),
                                       new Translate(0, 0, -3*max));

        model_scene.setCamera(camera);

        model_scene.heightProperty().bind(showroom_car_viewer.heightProperty());
        model_scene.widthProperty().bind(showroom_car_viewer.widthProperty());

        showroom_car_viewer.getChildren().add(model_scene);
        model_view_angle_manager(model_scene,single_model);
    }

    private LightBase prepare_light_source() {
        AmbientLight light = new AmbientLight();
        light.setColor(Color.WHITE);
        return light;
    }

    private Group load_internal_models(String path) {
        Group model_root = new Group();

        ObjModelImporter importer = new ObjModelImporter();
        importer.read(getClass().getResource(path));

        for (MeshView view : importer.getImport()) {
            model_root.getChildren().add(view);
        }

        model_root.getTransforms().add(new Rotate(90, Rotate.Y_AXIS));
        return model_root;
    }

    private void model_view_angle_manager(SubScene model_scene, Group single_model) {
        model_scene.setOnMouseClicked(event->{
            mouse_old_x = event.getX();
            mouse_old_y = event.getY();
        });

        model_scene.setOnMouseDragged(event -> {
            mouse_new_x = event.getX();
            mouse_new_y = event.getY();

            double dx = mouse_new_x - mouse_old_x;
            double dy = mouse_new_y - mouse_old_y;

            if(dx>0 && dy==0){
                yaw_angle++;
                camera_fixer(pitch_angle,0,yaw_angle,-1);
            }
            else if(dx<0 && dy==0){
                yaw_angle--;
                camera_fixer(pitch_angle,0,yaw_angle,1);
            }
            else if(dx==0 && dy>0 && pitch_angle>-90){
                pitch_angle--;
                camera_fixer(pitch_angle,1,yaw_angle,0);
            }
            else if(dx==0 && dy<0 && pitch_angle<0){
                pitch_angle++;
                camera_fixer(pitch_angle,-1,yaw_angle,0);
            }

            else if(dx>0 && dy>0 && pitch_angle>-90){
                yaw_angle++;
                pitch_angle--;
                camera_fixer(pitch_angle,1,yaw_angle,-1);
            }
            else if(dx<0 && dy>0  && pitch_angle>-90){
                yaw_angle--;
                pitch_angle--;
                camera_fixer(pitch_angle,1,yaw_angle,1);
            }

            else if(dx<0 && dy<0 && pitch_angle<0){
                yaw_angle--;
                pitch_angle++;
                camera_fixer(pitch_angle,-1,yaw_angle,1);
            }
            else if(dx>0 && dy<0 && pitch_angle<0){
                yaw_angle++;
                pitch_angle++;
                camera_fixer(pitch_angle,-1,yaw_angle,-1);
            }

            mouse_old_x = mouse_new_x;
            mouse_old_y = mouse_new_y;
        });
    }

    private void camera_fixer(double pitch_angle, double pitch_offset,
                                                 double yaw_angle, double yaw_offset ) {
        camera.getTransforms().addAll (
                new Translate(0, 0, 3*max),
                new Rotate(-(pitch_angle+pitch_offset),Rotate.X_AXIS),
                new Rotate(-(yaw_angle+yaw_offset),Rotate.Y_AXIS),
                new Rotate(yaw_angle,Rotate.Y_AXIS),
                new Rotate(pitch_angle,Rotate.X_AXIS),
                new Translate(0, 0, -3*max));
    }

    private class NewTimer extends AnimationTimer {

        int counter = 0;
        long last = 0;
        int selection;

        public NewTimer(int selection){
            this.selection = selection;
        }

        @Override
        public void handle(long now) {
            if((((now-last))/100000)>1){
                doHandle();
                last = now;
            }
        }

        private void doHandle() {
          if(counter_flag==0){
             counter = 0;
          }
          if(counter<showroom_top_bar_tab_01_btn.getPrefWidth()){
             pro = pro+ (showroom_top_bar_tab_01_btn.getPrefWidth()/100);

             String fu1 = "-fx-background-color: linear-gradient(to right,"+
                        "#000000 "+ (100-pro)+"%,#cae002 "+(100-pro)+"%);";

             String fu2 = "-fx-background-color: linear-gradient(to left,"+
                        "#000000 "+ (100-pro)+"%,#cae002 "+(100-pro)+"%);";


             if(selection == 1){
                 showroom_top_bar_tab_01_fill_left.setStyle(fu1);
                 showroom_top_bar_tab_01_fill_right.setStyle(fu2);
             }
             else if(selection == 2){
                 showroom_top_bar_tab_02_fill_left.setStyle(fu1);
                 showroom_top_bar_tab_02_fill_right.setStyle(fu2);
             }
             else if(selection == 3){
                 showroom_top_bar_tab_03_fill_left.setStyle(fu1);
                 showroom_top_bar_tab_03_fill_right.setStyle(fu2);
             }
             else if(selection == 4){
                 showroom_top_bar_tab_04_fill_left.setStyle(fu1);
                 showroom_top_bar_tab_04_fill_right.setStyle(fu2);
             }
             counter++;
            }

        }
    }
}
