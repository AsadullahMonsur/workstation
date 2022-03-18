package com.page;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.paint.Color;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Page {
    private ArrayList<Button> buttons = new ArrayList<Button>();
    private ArrayList<Label> labels = new ArrayList<Label>();
    private ArrayList<TextField> textFields = new ArrayList<TextField>();

    private enum Type{
        SET,
        GET,
        SEND,
        RECEIVE
    }

    private enum Depth{
        SIMPLE,
        COMPLEX,
        NESTED
    }

    private enum Pattern{
        SEQUENTIAL,
        PARALLEL,
        BOTH_CASE_A,
        BOTH_CASE_B
    }

    private Queue<Integer> dependencyList = new LinkedList<Integer>();

    public Page() {
    }

    public ArrayList<Button> getButtons() {
        return buttons;
    }

    public void setButtons(ArrayList<Button> buttons) {
        this.buttons = buttons;
    }

    public ArrayList<Label> getLabels() {
        return labels;
    }

    public void setLabels(ArrayList<Label> labels) {
        this.labels = labels;
    }

    public ArrayList<TextField> getTextFields() {
        return textFields;
    }

    public void setTextFields(ArrayList<TextField> textFields) {
        this.textFields = textFields;
    }

    public void method(String a,String id){
        try{
            int  command_no = Integer.parseInt(a);
            int  obj_id = Integer.parseInt(id);

            buttons.get(obj_id).setOnMouseClicked(new EventHandler<MouseEvent>() {
               @Override
                 public void handle(MouseEvent event) {
                    if(command_no==1){
                            labels.get(obj_id).setPrefWidth(250);
                            labels.get(obj_id).setText("Alhamdulillah");
                    }
                    else if(command_no==2) {
                            buttons.get(obj_id).setStyle("-fx-background-color: #00ff00");
                    }
                 }
              });
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){

    }
}
