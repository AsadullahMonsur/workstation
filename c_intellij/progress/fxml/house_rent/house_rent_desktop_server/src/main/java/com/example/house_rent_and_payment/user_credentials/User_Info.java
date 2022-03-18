package com.example.house_rent_and_payment.user_credentials;

public class User_Info {
    private String u_id;
    private String u_name;
    private String u_pass;
    private int active_status; //0 offline, 1 online
    private int security_method; //0 for none, 1 for sms, 2 for email, 3 both

    public User_Info() {
    }

    public User_Info(String u_id, String u_name, String u_pass, int active_status, int security_method) {
        this.u_id = u_id;
        this.u_name = u_name;
        this.u_pass = u_pass;
        this.active_status = active_status;
        this.security_method = security_method;
    }

    public String getU_id() {
        return u_id;
    }

    public void setU_id(String u_id) {
        this.u_id = u_id;
    }

    public String getU_name() {
        return u_name;
    }

    public void setU_name(String u_name) {
        this.u_name = u_name;
    }

    public String getU_pass() {
        return u_pass;
    }

    public void setU_pass(String u_pass) {
        this.u_pass = u_pass;
    }

    public int getActive_status() {
        return active_status;
    }

    public void setActive_status(int active_status) {
        this.active_status = active_status;
    }

    public int getSecurity_method() {
        return security_method;
    }

    public void setSecurity_method(int security_method) {
        this.security_method = security_method;
    }

    @Override
    public String toString() {
        return "User_Info{" +
                "u_id='" + u_id + '\'' +
                ", u_name='" + u_name + '\'' +
                ", u_pass='" + u_pass + '\'' +
                ", active_status=" + active_status +
                ", security_method=" + security_method +
                '}';
    }
}
