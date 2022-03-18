package com.example.house_rent_and_payment.utility;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class NotificationLayout extends VBox{
    private AnchorPane header;
    private AnchorPane body;
    private AnchorPane footer;
    private Label display;

    public NotificationLayout() {
       header = new AnchorPane();
       body = new AnchorPane();
       footer = new AnchorPane();
       display = new Label();

       configure_header();
       configure_body();
       configure_footer();
    }

    private void configure_header() {
        header.setPrefHeight(30);
        header.prefWidthProperty().bind(this.prefWidthProperty());
        header.setMinHeight(AnchorPane.USE_PREF_SIZE);
        header.setMinWidth(AnchorPane.USE_PREF_SIZE);
        header.setMaxHeight(AnchorPane.USE_PREF_SIZE);
        header.setMaxWidth(AnchorPane.USE_PREF_SIZE);

        HBox sections = new HBox();
        sections.prefHeightProperty().bind(header.prefHeightProperty());
        sections.prefWidthProperty().bind(header.prefWidthProperty());
        sections.setAlignment(Pos.CENTER);
        sections.setSpacing(20.0d);
        sections.setStyle("-fx-background-color:black");

        ImageView status = new ImageView();
        status.setFitHeight(30);
        status.setFitWidth(30);

        Label title = new Label("Notification");
        title.setStyle("-fx-text-fill:white");
        title.setTextAlignment(TextAlignment.JUSTIFY);
        title.prefWidthProperty().bind(sections.prefWidthProperty());

        ImageView close = new ImageView();
        close.setFitHeight(30);
        close.setFitWidth(30);

        String status_path = "/stylesheets/user_authentication/" +
                             "green_circle_30x30.png";
        String close_path = "/images/others/close_b_over_w_512x512.png";

        status.setImage(MediaUtility.getImage(status_path));
        close.setImage(MediaUtility.getImage(close_path));
        sections.getChildren().addAll(status,title,close);

        header.getChildren().add(sections);
        getChildren().addAll(header);
    }

    private void configure_body() {
        body.setPrefHeight(50);
        body.prefWidthProperty().bind(this.prefWidthProperty());
        body.setMinHeight(AnchorPane.USE_PREF_SIZE);
        body.setMinWidth(AnchorPane.USE_PREF_SIZE);
        body.setMaxHeight(AnchorPane.USE_PREF_SIZE);
        body.setMaxWidth(AnchorPane.USE_PREF_SIZE);

        display.prefHeightProperty().bind(body.prefHeightProperty());
        display.prefWidthProperty().bind(body.prefWidthProperty());

        body.setStyle("-fx-background-color:white");
        display.setStyle("-fx-text-fill:black");
        display.setTextAlignment(TextAlignment.LEFT);

        body.getChildren().add(display);
        getChildren().add(body);
    }

    private void configure_footer() {
    }

    public AnchorPane getHeader() {
        return header;
    }

    public void setHeader(AnchorPane header) {
        this.header = header;
    }

    public AnchorPane getBody() {
        return body;
    }

    public void setBody(AnchorPane body) {
        this.body = body;
    }

    public AnchorPane getFooter() {
        return footer;
    }

    public void setFooter(AnchorPane footer) {
        this.footer = footer;
    }

    public Label getDisplay() {
        return display;
    }

    public void setDisplay(Label display) {
        this.display = display;
    }
}
