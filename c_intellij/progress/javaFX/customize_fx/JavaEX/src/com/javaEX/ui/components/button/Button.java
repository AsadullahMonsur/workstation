package com.javaEX.ui.components.button;

import javafx.scene.Node;
import javafx.scene.control.ContentDisplay;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class Button extends javafx.scene.control.Button {
    public Button() {

    }

    public Button(String text) {
        super(text);
        File file = new File("F:\\icon\\heart.png");
        Image image = new Image(file.toURI().toString());
        //    Image cameraIcon = new Image(getClass().getResourceAsStream("camera-128.png"));
        ImageView cameraIconView = new ImageView(image);
        cameraIconView.setFitHeight(30);
        cameraIconView.setFitWidth(30);
        this.setGraphic(cameraIconView);
        this.setContentDisplay(ContentDisplay.RIGHT);

    }

    public Button(String text, Node graphic) {
        super(text, graphic);
    }


}
