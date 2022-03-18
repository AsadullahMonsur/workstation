package com.hospital.home;

import com.hospital.Main;
import com.hospital.insertion.PersonController;
import com.hospital.viewer.ViewerController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomePageController implements Initializable {

    @FXML
    private Button add_doc;

    @FXML
    private Button add_patient;

    @FXML
    private Button close;

    @FXML
    private Button patient_reocord_doc;

    @FXML
    private Button patient_reocrds;

    @FXML
    private Button set_appointment;

    @FXML
    private Button view_appointments;

    @FXML
    private Button view_doc_list;

    @FXML
    private Button view_patient_list;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("Home Page Loaded");

        view_doc_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String data  = Main.h.getDoctors().toString();
                load_viewer(event,"Doctor List",data);
            }
        });

        view_appointments.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String data = "No Appointments";
                load_viewer(event,"Appointments",data);
            }
        });

        patient_reocord_doc.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                load_insertion(event,"Insert Doctors ID and Submit",3);
            }
        });

        patient_reocrds.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                load_insertion(event,"Insert Patient ID and Submit",4);
            }
        });

        view_patient_list.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String data = Main.h.getPatients().toString();
                load_viewer(event,"Patient List",data);
            }
        });

        set_appointment.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                load_appointment(event);
            }
        });

        add_doc.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                load_insertion(event,"Adding Doctor",1);
            }
        });

        add_patient.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
               load_insertion(event,"Adding Patient",2);
            }
        });

        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                exit_to_sign_in_page(event);
            }
        });
    }

    private void load_appointment(MouseEvent event) {
        try {
            URL path =  getClass().getResource("/appointment/appointment.fxml");
            Parent layout = FXMLLoader.load(path);

            Scene scene = new Scene(layout, 1024, 620);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Fill Up Form and Submit");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Sign In Page: "+e.getMessage());
        }
    }

    private void load_insertion(MouseEvent event, String title, int type) {
        try {
            URL path =  getClass().getResource("/insertion/person_insertion.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(path);
            Parent layout = loader.load();

            PersonController controller = loader.getController();
            controller.setType(type);

            Scene scene = new Scene(layout, 1024, 620);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Sign In Page: "+e.getMessage());
        }
    }

    private void load_viewer(MouseEvent event, String title,String data) {
        try {
            URL path =  getClass().getResource("/viewer/view_generalize.fxml");
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(path);

            Parent layout = loader.load();

            ViewerController controller = loader.getController();
            controller.setData(data);

            Scene scene = new Scene(layout, 1024, 620);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Sign In Page: "+e.getMessage());
        }
    }


    private void exit_to_sign_in_page(MouseEvent event) {
        try {
            URL path =  getClass().getResource("/sign_in/sign_in.fxml");
            Parent layout = FXMLLoader.load(path);

            Scene scene = new Scene(layout, 1024, 620);

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setTitle("Hospital Management");
            stage.setScene(scene);
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading Sign In Page: "+e.getMessage());
        }
    }
}
