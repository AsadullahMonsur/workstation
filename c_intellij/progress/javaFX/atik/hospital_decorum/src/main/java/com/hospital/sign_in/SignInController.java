package com.hospital.sign_in;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignInController implements Initializable {
    @FXML
    private TextField email_input;

    @FXML
    private PasswordField password_input;

    @FXML
    private RadioButton radio_option_01;

    @FXML
    private RadioButton radio_option_02;

    @FXML
    private RadioButton radio_option_03;

    @FXML
    private Button sign_in_btn;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        sign_in_btn.setOnMouseClicked(new EventHandler<MouseEvent>() {
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
