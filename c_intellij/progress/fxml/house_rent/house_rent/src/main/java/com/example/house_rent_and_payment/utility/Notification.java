package com.example.house_rent_and_payment.utility;


import javafx.animation.PauseTransition;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class Notification {
    public static enum Gravity{
       TOP_LEFT,
       TOP_RIGHT,
       BOTTOM_LEFT,
       BOTTOM_RIGHT,
       CENTER
    }

    public static VBox getBaseNotification(String message,
                       Notification.Gravity position, int duration){
      VBox layout = get_layout(message);
      return layout;
    }

    private static VBox get_layout(String message) {
       NotificationLayout layout = new NotificationLayout();
       layout.getDisplay().setText(message);
       layout.setPrefHeight(80);
       layout.setPrefWidth(300);
       layout.setLayoutY(260);
       String design = "-fx-padding: 8 15 15 15;\n" +
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
               "    -fx-text-box-border: red;\n" +
               "    -fx-focus-color: red;";
       layout.setStyle(design);
       return layout;
    }
}
