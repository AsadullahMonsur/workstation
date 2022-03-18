package com.hospital.viewer;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.awt.desktop.SystemEventListener;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewerController implements Initializable {

    @FXML
    private Button back_btn;

    @FXML
    private TextArea display;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("additional window loaded");

        back_btn.setOnMouseClicked(event -> {
            load_home_page(event);
        });
    }

    private void load_home_page(MouseEvent event) {
        try {
            URL path =  getClass().getResource("/home/homepage.fxml");
            Parent layout = FXMLLoader.load(path);

            Scene scene = new Scene(layout, 1024, 620);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Home Page");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Sign In Page: "+e.getMessage());
        }
    }

    public void setData(String data) {
        if(data==null){
            display.setText("empty");
        }

        display.setText(data);
    }
}
