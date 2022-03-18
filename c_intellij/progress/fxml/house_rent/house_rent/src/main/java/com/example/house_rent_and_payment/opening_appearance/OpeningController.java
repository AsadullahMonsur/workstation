package com.example.house_rent_and_payment.opening_appearance;

import com.example.house_rent_and_payment.user_authentication.UserAuthentication;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OpeningController implements Initializable {
    @FXML
    private Pane opening_root_pane;
    @FXML
    private AnchorPane logo_container;
    @FXML
    private ImageView opening_logo_text;
    @FXML
    private ImageView opening_logo_icon;

    private int flag = 0;
    private PauseTransition shifter_delay;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      try{
        shifter_delay = new PauseTransition(Duration.seconds(4));

        Platform.runLater(() -> {
        final Animation animation = new Transition() {{
                setCycleDuration(Duration.millis(2000));
                setInterpolator(Interpolator.EASE_OUT);
            }

            @Override
            protected void interpolate(double frac) {
                Color vColor = new Color(0.0235, 0.0157, 0.1176, 1 - frac);
                opening_root_pane.setBackground(new Background(new BackgroundFill(vColor, CornerRadii.EMPTY, Insets.EMPTY)));
            }
        };
        animation.play();
        opening_root_pane.setOnMouseMoved(event -> {
            try {
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                logo_container.setLayoutX(stage.getScene().getX() + stage.getScene().getWidth() / 2 - logo_container.getWidth() / 2);
                logo_container.setLayoutY(stage.getScene().getY() + stage.getScene().getHeight() / 2 - logo_container.getHeight() / 2);
                configure_text(stage);
                configure_icon(stage);

                if(flag==0){
                FadeTransition fade = new FadeTransition();
                fade.setDuration(Duration.millis(1500));
                fade.setFromValue(0);
                fade.setToValue(10);
                fade.setCycleCount(Animation.INDEFINITE);
                fade.setAutoReverse(true);
                fade.setNode(opening_logo_text);

                RotateTransition rotate = new RotateTransition();
                rotate.setAxis(Rotate.Z_AXIS);
                rotate.setByAngle(360);
                rotate.setCycleCount(Animation.INDEFINITE);
                rotate.setDuration(Duration.millis(1500));
                rotate.setAutoReverse(true);
                rotate.setNode(opening_logo_icon);

                ParallelTransition pT = new ParallelTransition ( fade,rotate);
                pT.play();
                flag = 1;

                activity_shifter(event);
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
        });
        });
      }
      catch (Exception e){
          Logger.getLogger(OpeningController.class.getName()).log(Level.SEVERE, null, e);
          e.printStackTrace();
          System.out.println("Error in Opening Animation");
      }
    }

    private void configure_text(Stage stage) throws FileNotFoundException {
       // FileInputStream inputstream = new FileInputStream("/images/opening_appearance/company_logo_photo.png");
        Image image = new Image("/images/opening_appearance/company_logo_photo.png");
        opening_logo_text.setImage(image);
    }
    private void configure_icon(Stage stage) throws FileNotFoundException {
      //  FileInputStream inputstream = new FileInputStream("/images/opening_appearance/opening_logo_final.png");
        Image image = new Image("/images/opening_appearance/opening_logo_final.png");
        opening_logo_icon.setImage(image);
    }


    private void activity_shifter(MouseEvent events) {
        shifter_delay.setOnFinished( event -> move_to_user_authentication(events) );
        shifter_delay.play();
    }

    private void move_to_user_authentication(MouseEvent event){
       try {
           Parent root = FXMLLoader.load(getClass().getResource("/layouts/user_authentication/authentication.fxml"));
           Scene scene = new Scene(root);
           scene.getStylesheets().add("/stylesheets/user_authentication/user_authentication.css");
           Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
           stage.setScene(scene);
           stage.show();
       }
       catch (Exception e){
           Logger.getLogger(UserAuthentication.class.getName()).log(Level.SEVERE, null, e);
           e.printStackTrace();
           System.out.println("Error in OpeningController");
       }
    }
}
