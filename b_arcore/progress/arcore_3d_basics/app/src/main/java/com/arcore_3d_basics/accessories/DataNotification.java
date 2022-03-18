package com.arcore_3d_basics.accessories;


public class DataNotification  {
    private int number;
    private String text;

    public DataNotification(int number) {
        this.number = number;
    }

    public DataNotification(String text) {
        this.text = text;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
