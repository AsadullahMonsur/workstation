package com.example.football_management_system.user_authentication;

public class Sign_Up_Data {
    private String fname;
    private String lname;
    private String email;
    private String password;


    public Sign_Up_Data() {
    }
    public Sign_Up_Data(String fn, String ln, String em, String pass) {
         fname    = fn;
         lname    = ln;
         email    = em;
         password = pass;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
