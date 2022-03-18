package com.example.house_rent_and_payment.others;

public class Contact {

    private String num1 = "default";
    private String num2 = "default";

    public Contact() {
    }

    public Contact(String num1, String num2) {
        this.num1 = num1;
        this.num2 = num2;
    }

    public String getNum1() {
        return num1;
    }

    public void setNum1(String num1) {
        this.num1 = num1;
    }

    public String getNum2() {
        return num2;
    }

    public void setNum2(String num2) {
        this.num2 = num2;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "num1='" + num1 + '\'' +
                ", num2='" + num2 + '\'' +
                '}';
    }
}
