package com.hospital.appointment;

import com.hospital.Main;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppointmentController implements Initializable {

    @FXML
    private Button back_btn;

    @FXML
    private TextField doc_id;

    @FXML
    private TextField patient_id;

    @FXML
    private TextField prescription;

    @FXML
    private Button submit_btn;

    @FXML
    private TextField symptoms;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        submit_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Main.h.meetDoctor(doc_id.getText(),
                                  patient_id.getText(),
                                  symptoms.getText(),
                                  prescription.getText());
            }
        });

        back_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                load_home_page(event);
            }
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
}
