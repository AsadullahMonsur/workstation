package com.example.house_rent_and_payment.user_authentication;

import com.example.house_rent_and_payment.opening_appearance.Main;
import com.example.house_rent_and_payment.utility.Notification;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;

import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

import static com.example.house_rent_and_payment.user_authentication.AuthenticationUtility.*;
import static com.example.house_rent_and_payment.utility.MediaUtility.getImage;

public class UserAuthentication implements Initializable {
    @FXML
    private VBox authentication_container;

    @FXML
    private Pane authentication_root_content;

    @FXML
    private Text authentication_sign_in_txt;

    @FXML
    private AnchorPane authentication_slider;

    @FXML
    private ToolBar authentication_toolbar;

    @FXML
    private ImageView authentication_toolbar_exit;

    @FXML
    private ImageView authentication_toolbar_minimize;

    @FXML
    private Label bottom_note;

    @FXML
    private Circle circular_image_container;

    @FXML
    private Label mail_limit_label_01;

    @FXML
    private Label mail_limit_label_02;

    @FXML
    private ImageView mail_validity_signal_01;

    @FXML
    private ImageView mail_validity_signal_02;

    @FXML
    private Label password_hint_01;

    @FXML
    private Label password_hint_02;

    @FXML
    private Label password_limit_label_01;

    @FXML
    private Label password_limit_label_02;

    @FXML
    private Label password_limit_label_03;

    @FXML
    private ImageView password_validity_signal_01;

    @FXML
    private ImageView password_validity_signal_02;

    @FXML
    private ImageView password_validity_signal_03;

    @FXML
    private ImageView password_visibility_img_01;

    @FXML
    private ImageView password_visibility_img_02;

    @FXML
    private ImageView password_visibility_img_03;

    @FXML
    private ProgressIndicator progress_bar_01;

    @FXML
    private ProgressIndicator progress_bar_02;

    @FXML
    private Label reset_password;

    @FXML
    private Button sign_in_btn;

    @FXML
    private AnchorPane sign_in_container;

    @FXML
    private Button sign_up_btn;

    @FXML
    private AnchorPane sign_up_container;

    @FXML
    private TextField user_first_name;

    @FXML
    private TextField user_last_name;

    @FXML
    private TextField user_mail_input_01;

    @FXML
    private TextField user_mail_input_02;

    @FXML
    private TextField user_password_input_01;

    @FXML
    private TextField user_password_input_01_dummy;

    @FXML
    private TextField user_password_input_02;

    @FXML
    private TextField user_password_input_02_dummy;

    @FXML
    private TextField user_password_input_03;

    @FXML
    private TextField user_password_input_03_dummy;

    @FXML
    private ImageView user_validity_signal_01;

    @FXML
    private ImageView user_validity_signal_02;

    private int slider_flag = 0;
    private double x,y;
    private int []opacity_flags;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
      opacity_flags = new int[3];
      try{
          load_ui_configuration();

          window_resolver();
          slider_operator();

          authentication_toolbar_exit.setOnMouseClicked(mouseEvent ->
                                                        close_application(mouseEvent));
          authentication_toolbar_minimize.setOnMouseClicked(mouseEvent ->
                                                        minimize_application(mouseEvent));

          load_accessories();
      }
      catch (Exception e){
          Logger.getLogger(UserAuthentication.class.getName())
                                             .log(Level.SEVERE, null, e);
          e.printStackTrace();
          System.out.println("Error in initialization()-> UserAuthentication: "
                             +e.getMessage());
      }
    }

    private void window_resolver() throws Exception{
        authentication_root_content.setOnMouseMoved(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            authentication_container.setLayoutX(stage.getScene().getX() + stage.getScene().getWidth() / 2 - authentication_container.getWidth() / 2);
            authentication_container.setLayoutY(stage.getScene().getY() + stage.getScene().getHeight() / 2 - authentication_container.getHeight() / 2);
            authentication_root_content.setPrefHeight(stage.getScene().getHeight());
            authentication_root_content.setPrefWidth(stage.getScene().getWidth());
            authentication_container.setPrefWidth(stage.getScene().getWidth());
            authentication_container.setPrefHeight(stage.getScene().getHeight());

            configure_switcher_icon();
        });
    }

    private void slider_operator() throws Exception{
        circular_image_container.setOnMouseClicked(event -> {
            switch_slider(event);
        });
    }

    private void switch_slider(MouseEvent event) {
        configure_signals();

        RotateTransition rotate = new RotateTransition();
        rotate.setAxis(Rotate.Z_AXIS);
        rotate.setByAngle(180);
        rotate.setCycleCount(1);
        rotate.setDuration(Duration.millis(250));
        rotate.setAutoReverse(false);
        rotate.setNode(circular_image_container);
        rotate.play();

        TranslateTransition translate = new TranslateTransition();
        translate.setNode(authentication_slider);
        translate.setDuration(Duration.millis(250));

        TranslateTransition translate_toolbar = new TranslateTransition();
        translate_toolbar.setNode(authentication_toolbar);
        translate_toolbar.setDuration(Duration.millis(250));

        if(slider_flag==0) {
            double calculate = authentication_root_content.getLayoutX()
                    +authentication_container.getPrefWidth()
                    -authentication_slider.getPrefWidth();
            translate.setToX(calculate);

            translate_toolbar.setToX(authentication_slider.getPrefWidth()
                    -authentication_toolbar.getPrefWidth());
            slider_flag = 1;
        }
        else if (slider_flag==1){
            translate.setToX(authentication_root_content.getLayoutX());
            translate_toolbar.setToX(authentication_root_content.getLayoutX());
            slider_flag = 0;
        }

        ParallelTransition pT = new ParallelTransition (translate,rotate,translate_toolbar);
        pT.play();
    }

    private void minimize_application(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setIconified(true);
    }
    private void close_application(MouseEvent event){
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        System.exit(1);
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


    private void load_ui_configuration() throws Exception{
        configure_icons();
        configure_signals();
        configure_stylesheet();
    }

    private void load_accessories() throws Exception{
      configure_email_input(user_mail_input_01,mail_limit_label_01,mail_validity_signal_01);
      configure_email_input(user_mail_input_02,mail_limit_label_02,mail_validity_signal_02);

      configure_password_input(user_password_input_01,
                               user_password_input_01_dummy,
                               password_limit_label_01,
                               password_validity_signal_01,
                               0);

      configure_password_input(user_password_input_02,
                               user_password_input_02_dummy,
                               password_limit_label_02,
                               password_validity_signal_02,
                               1);

      configure_password_input(user_password_input_03,
                               user_password_input_03_dummy,
                               password_limit_label_03,
                               password_validity_signal_03,
                               1);

      authenticate_user_data();
    }

    private void configure_signals(){
        mail_validity_signal_01.setVisible(false);
        mail_validity_signal_02.setVisible(false);
        password_validity_signal_01.setVisible(false);
        password_validity_signal_02.setVisible(false);
        password_validity_signal_03.setVisible(false);
        user_validity_signal_01.setVisible(false);
        user_validity_signal_02.setVisible(false);

        progress_bar_01.setVisible(false);
        progress_bar_02.setVisible(false);
    }

    private void configure_stylesheet() {
        authentication_root_content.getStylesheets()
                                   .add(getClass()
                                   .getResource("/stylesheets/" +
                                   "user_authentication/user_authentication.css")
                                   .toExternalForm());

        user_first_name.getStyleClass().add("user_input");
        user_last_name.getStyleClass().add("user_input");

        user_mail_input_01.getStyleClass().add("user_input");
        user_mail_input_02.getStyleClass().add("user_input");

        user_password_input_01.getStyleClass().add("user_password");
        user_password_input_02.getStyleClass().add("user_password");
        user_password_input_03.getStyleClass().add("user_password");

        sign_in_btn.getStyleClass().add("button");
        sign_up_btn.getStyleClass().add("button");

        password_hint_01.getStyleClass().add("user_password_hint");
        password_hint_02.getStyleClass().add("user_password_hint");

        reset_password.getStyleClass().add("reset_password_hint");

        URL name_path_before = getClass().getResource("/stylesheets/" +
                                           "user_authentication/" +
                                           "person_w_over_b_30x30.png");
        URL name_path_after = getClass().getResource("/stylesheets/" +
                                           "user_authentication/" +
                                           "person_b_over_w_30x30.png");

        user_first_name.setStyle("-fx-background-image:url('"+name_path_before+"')");
        user_last_name.setStyle("-fx-background-image:url('"+name_path_before+"')");

        on_hover_text_input(user_first_name,name_path_before,name_path_after);
        on_hover_text_input(user_last_name,name_path_before,name_path_after);

        on_hover_password(user_password_input_01,
                          user_password_input_01_dummy);
        on_hover_password(user_password_input_02,
                          user_password_input_02_dummy);
        on_hover_password(user_password_input_03,
                          user_password_input_03_dummy);
    }

    private void configure_icons() throws Exception{
        String password_visibilty_01_before_01 = "/stylesheets/user_authentication/" +
                                                  "eye_not_b_over_w_512x512.png";
        String password_visibilty_01_after_01 = "/stylesheets/user_authentication/" +
                                                  "eye_not_w_over_b_512x512.png";

        password_visibility_img_01.setImage(getImage(password_visibilty_01_before_01));
        on_hover_image(password_visibility_img_01,
                       getImage(password_visibilty_01_before_01),
                       getImage(password_visibilty_01_after_01));

        password_visibility_img_02.setImage(getImage(password_visibilty_01_before_01));
        on_hover_image(password_visibility_img_02,
                       getImage(password_visibilty_01_before_01),
                       getImage(password_visibilty_01_after_01));

        password_visibility_img_03.setImage(getImage(password_visibilty_01_before_01));
        on_hover_image(password_visibility_img_03,
                       getImage(password_visibilty_01_before_01),
                       getImage(password_visibilty_01_after_01));

        String exit_before = "/images/others/close_b_over_w_512x512.png";
        String exit_after = "/images/others/close_w_over_b_512x512.png";
        authentication_toolbar_exit.setImage(getImage(exit_before));
        on_hover_image(authentication_toolbar_exit,
                       getImage(exit_before),
                       getImage(exit_after));

        String minimizer_before = "/images/others/minus_b_over_w_512x512.png";
        String minimizer_after = "/images/others/minus_w_over_b_512x512.png";
        authentication_toolbar_minimize.setImage(getImage(minimizer_before));
        on_hover_image(authentication_toolbar_minimize,
                       getImage(minimizer_before),
                       getImage(minimizer_after));

        on_open_password_view(password_visibility_img_01,
                              user_password_input_01,0);
        on_open_password_view(password_visibility_img_02,
                              user_password_input_02,1);
        on_open_password_view(password_visibility_img_03,
                              user_password_input_03,2);
    }

    private void configure_switcher_icon() {
      try{
        String circle_path = "/images/user_authentication/day_night_01.png";
        circular_image_container.setFill(new ImagePattern(getImage(circle_path)));
        circular_image_container.setCenterX(authentication_root_content.getWidth() / 2);
        circular_image_container.setCenterY(authentication_root_content.getHeight() / 2);
        circular_image_container.setRadius(75);
      }
      catch (Exception e){
         Logger.getLogger(UserAuthentication.class.getName())
                                            .log(Level.SEVERE, null, e);
         e.printStackTrace();
         System.out.println("Error in configure_switcher_icon() -> UserAuthentication :"+
                             e.getMessage());
      }
    }

    private void configure_password_input(TextField view,
                          TextField dummy_view,Label counter,
                          ImageView output,int flag ){
      // 0 for sign_in, 1 for sign_up page
      view.setOnKeyTyped(keyEvent -> {
         String password = view.getText();
         counter.setText(password.length()+"/10");
         dummy_view.setText(hidden_string(password.length()));

         if(password_validity_check(password)){
           String path = "/stylesheets/user_authentication/" +
                         "green_circle_30x30.png";
           output.setImage(getImage(path));
         }
         else{
           String path = "/stylesheets/user_authentication/" +
                           "red_circle_30x30.png";
           output.setImage(getImage(path));
         }
         if(flag==1) {
           configure_password_input_matcher();
         }
         output.setVisible(true);
      });
    }

    private boolean configure_password_input_matcher(){
        String second = user_password_input_02.getText();
        String third = user_password_input_03.getText();
        if (!(second.equals(third))  || (second.equals("") && third.equals(""))) {
            String path = "/stylesheets/user_authentication/" +
                          "red_circle_30x30.png";
            password_validity_signal_03.setImage(getImage(path));

            return false;
        }
        else{
            String path = "/stylesheets/user_authentication/" +
                          "green_circle_30x30.png";
            password_validity_signal_03.setImage(getImage(path));
        }
        password_validity_signal_03.setVisible(true);
        return true;
    }

    private void configure_email_input(TextField view,Label counter,
                                       ImageView output) {
      view.setOnKeyTyped(keyEvent ->{
         String mail = view.getText().toString();
         counter.setText(mail.length()+"/50");
          if(mail_validity_check(mail)){
              String path = "/stylesheets/user_authentication/" +
                            "red_circle_30x30.png";
              output.setImage(getImage(path));
          }
          else{
              String path = "/stylesheets/user_authentication/" +
                            "green_circle_30x30.png";
              output.setImage(getImage(path));
          }
          output.setVisible(true);
      });
    }

    private void authenticate_user_data() {
      sign_in_btn.setOnMouseClicked(mouseEvent -> {
        user_validity_signal_01.setVisible(false);
        progress_bar_01.setVisible(true);

        if(verification()){
          String path = "/stylesheets/user_authentication/" +
                        "green_circle_30x30.png";
          user_validity_signal_01.setImage(getImage(path));

            VBox notification = Notification.getBaseNotification(
                    "Alhamdulillah, Sign In Successful",
                    Notification.Gravity.CENTER,
                    1000);
            authentication_slider.getChildren()
                    .add(notification);

          PauseTransition shifter_delay = new PauseTransition(
                                              Duration.seconds(1.5));
          shifter_delay.setOnFinished(event -> move_to_homepage(mouseEvent,notification));
          shifter_delay.play();
        }
        else{
          String path = "/stylesheets/user_authentication/" +
                        "red_circle_30x30.png";
          user_validity_signal_01.setImage(getImage(path));
          progress_bar_01.setVisible(false);
        }

        user_validity_signal_01.setVisible(true);
      });

      sign_up_btn.setOnMouseClicked(mouseEvent -> {
        user_validity_signal_02.setVisible(false);
        progress_bar_02.setVisible(true);

        if(confirmation()){
          String path = "/stylesheets/user_authentication/" +
                        "green_circle_30x30.png";
          user_validity_signal_02.setImage(getImage(path));
          user_validity_signal_02.setVisible(true);
          PauseTransition shifter_delay = new PauseTransition(
                                              Duration.seconds(4.5));
          shifter_delay.setOnFinished(event -> {
              progress_bar_02.setVisible(false);
              switch_slider(mouseEvent);
          });
          shifter_delay.play();
        }
        else{
          String path = "/stylesheets/user_authentication/" +
                        "red_circle_30x30.png";
          user_validity_signal_02.setImage(getImage(path));
          user_validity_signal_02.setVisible(true);
          progress_bar_02.setVisible(false);
        }
      });
    }

    private boolean verification() {
        if((!mail_validity_check(user_mail_input_01.getText())) &&
           password_validity_check(user_password_input_01.getText())){

           Main.manager.handle_sign_in();
           return true;
        }
        return false;
    }

    private boolean confirmation() {
      boolean result = (!mail_validity_check(user_mail_input_02.getText())) &&
                        password_validity_check(user_password_input_02.getText()) &&
                        password_validity_check(user_password_input_03.getText()) &&
                        configure_password_input_matcher() &&
                        general_input_pattern_check(user_first_name.getText()) &&
                        general_input_pattern_check(user_last_name.getText());

      return result;
    }

    private void move_to_homepage(MouseEvent event, VBox notification) {
        progress_bar_01.setVisible(false);
        notification.setVisible(false);
    }

    private void on_hover_image(ImageView view,Image before, Image after){
       view.setOnMouseEntered(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
             view.setImage(after);
           }
       });
       view.setOnMouseExited(new EventHandler<MouseEvent>() {
           @Override
           public void handle(MouseEvent event) {
              view.setImage(before);
           }
       });
    }

    private void on_open_password_view(ImageView view,
                                       TextField to_be_hidden,
                                       int index){
      String password_visibilty_01_before_02 = "/stylesheets/" +
                                               "user_authentication/" +
                                               "eye_b_over_w_512x512.png";
      String password_visibilty_01_after_02 = "/stylesheets/" +
                                              "user_authentication/" +
                                              "eye_w_over_b_512x512.png";
      String password_visibilty_01_before_01 = "/stylesheets/user_authentication/" +
                                               "eye_not_b_over_w_512x512.png";
      String password_visibilty_01_after_01 = "/stylesheets/user_authentication/" +
                                              "eye_not_w_over_b_512x512.png";

        view.setOnMouseClicked(event -> {
        if(opacity_flags[index]==0){
          to_be_hidden.setOpacity(1);
          opacity_flags[index] = 1;
          view.setImage(getImage(password_visibilty_01_before_02));
          on_hover_image(view,
                    getImage(password_visibilty_01_before_02),
                    getImage(password_visibilty_01_after_02));
        }
        else if (opacity_flags[index]==1){
          to_be_hidden.setOpacity(0);
          opacity_flags[index] = 0;
          view.setImage(getImage(password_visibilty_01_before_01));
          on_hover_image(view,
                    getImage(password_visibilty_01_before_01),
                    getImage(password_visibilty_01_after_01));
        }
      });
    }

    private void on_hover_text_input(TextField view, URL before, URL after){

       view.setOnMouseEntered(event -> {
           view.setStyle("-fx-background-image:url('"+ after +"')");
       });

       view.setOnMouseExited(event -> {
           view.setStyle("-fx-background-image:url('"+ before +"')");
       });
    }

    private void on_hover_password(TextField view, TextField dummy_view){
        String before = "    -fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 15;\n" +
                "    -fx-background-color: #ffffff;\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;\n" +
                "\t-fx-transition: width 2s, height 2s, transform 2s;\n" +
                "\t-fx-prompt-text-fill: #000000;\n" +
                "    -fx-text-fill: #000000;\n" +
                "    -fx-background-repeat: no-repeat;\n" +
                "    -fx-background-position: right center;\n" +
                "\t-fx-font-size: 15;\n" +
                "    -fx-text-box-border: red ;\n" +
                "    -fx-focus-color: red ;";


        String after = "  -fx-background-color:#000000;\n" +
                       "  -fx-prompt-text-fill: #ffffff;\n" +
                       "  -fx-text-fill: #ffffff;\n" +
                       "  -fx-background-repeat: no-repeat;\n" +
                       "  -fx-background-position: right center;";

        view.setOnMouseEntered(event -> {
            dummy_view.setStyle(after);
        });

        view.setOnMouseExited(event -> {
            dummy_view.setStyle(before);
        });
    }
}
