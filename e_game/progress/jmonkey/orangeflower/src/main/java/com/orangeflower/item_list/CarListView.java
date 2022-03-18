package com.orangeflower.item_list;

import com.interactivemesh.jfx.importer.obj.ObjModelImporter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.MeshView;
import javafx.scene.transform.Rotate;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CarListView implements Initializable {

    @FXML
    private AnchorPane car_list_view_container;

    @FXML
    private ImageView car_list_view_img_viewer;

    private int tracker[];
    private int select_flag = 0;
    private ArrayList<CarListView> carListViews;
    private int id;

    private Group model_viewer_components[];
    private ArrayList<String> internal_model_paths;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tracker = new int[]{-1};
        select_flag = 0;
    }

    public void set_car_img_viewer_src(String path){
        car_list_view_img_viewer.setImage(new Image(path));
    }

    public void set_car_viewers(ArrayList<CarListView> carListViews,
                                int tracker[], int id) {
        this.carListViews = carListViews;
        this.tracker = tracker;
        this.id = id;
    }

    @FXML
    void car_list_view_img_clicked(MouseEvent event) {
        if(event.getClickCount() == 2){
            select_flag = 1;
            int last_print = tracker[0];
            tracker[0] = id;
            remove_past_foot_print(last_print);
            car_list_view_container.setStyle("-fx-background-color:green;");
            model_viewer_components[0].getChildren().clear();

            Group single_model = load_internal_models(internal_model_paths.get(tracker[0]));
            single_model.setScaleX(0.2);
            single_model.setScaleY(0.2);
            single_model.setScaleZ(0.2);
            single_model.setTranslateY(single_model.getTranslateY()+0.2);

            model_viewer_components[0].getChildren().addAll(
                    model_viewer_components[1],
                    single_model);
        }
    }

    @FXML
    void car_list_view_img_hover_entered(MouseEvent event) {
        car_list_view_container.setStyle("-fx-background-color:red;");
    }

    @FXML
    void car_list_view_img_hover_exited(MouseEvent event) {
        car_list_view_container.setStyle("-fx-background-color:black;");
        if(select_flag==1){
            car_list_view_container.setStyle("-fx-background-color:green;");
        }
    }

    public void reset_select_flag(int flag){
        if(flag==0){
            select_flag = 0;
            car_list_view_container.setStyle("-fx-background-color:black;");
        }
    }

    private void remove_past_foot_print(int last_print) {
        if(last_print!=-1){
          carListViews.get(last_print).reset_select_flag(0);
        }
    }

    public void set_model_viewer_components(Group[] components,
                                            ArrayList<String> internal_model_paths) {
        this.model_viewer_components = components;
        this.internal_model_paths = internal_model_paths;
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
}
