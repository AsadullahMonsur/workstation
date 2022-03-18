package com.hospital.insertion;

import com.hospital.Main;
import com.hospital.others.Doctor;
import com.hospital.others.Patient;
import com.hospital.viewer.ViewerController;
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

public class PersonController implements Initializable {

    @FXML
    private TextField age;

    @FXML
    private Button back_btn;

    @FXML
    private TextField gender;

    @FXML
    private TextField id;

    @FXML
    private TextField name;

    @FXML
    private TextField speciality;

    @FXML
    private Button submit_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        back_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                load_home_page(event);
            }
        });
    }

    private void data_insertion(MouseEvent event, int type) {
      try {

          if (type == 1) {
              int value = Integer.parseInt(age.getText());
              Main.h.addDoctor(name.getText(),
                      id.getText(),
                      age.getText(),
                      value,
                      speciality.getText());
          }
          else if (type == 2) {
              int value = Integer.parseInt(age.getText());
              Main.h.addPatient(name.getText(),
                      id.getText(),
                      age.getText(),
                      value);
          }
          else if (type == 3){
             String data = "id not found";
             Doctor dt = Main.h.findDoctor(id.getText()) ;
             if(dt!=null){
                data = dt.getPatientRecords().toString();
             }
             load_viewer(event,"Patient records of Doctor",data);
          }
          else if(type==4){
              String data = "id not found";
              Patient pt = Main.h.findPatient(id.getText());
              if(pt!=null){
                  data = pt.getPatientRecords().toString();
              }
             load_viewer(event,"Patient's Record",data);
          }
      }
      catch (Exception e){
          e.printStackTrace();
          System.out.println("Error in Data insertion :"+e.getMessage());
      }
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

    public void setType(int type) {
        submit_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                data_insertion(event,type);
            }
        });
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

}
