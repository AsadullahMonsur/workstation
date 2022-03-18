package com.example.football_management_system.user_authentication;

public class Log_In_Page_2_Data {
    private String id;
    private String password;

    public Log_In_Page_2_Data() {
    }

    public Log_In_Page_2_Data(String uid, String pass) {
        this.id              = uid;
        this.password        = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
