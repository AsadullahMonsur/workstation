package com.example.house_rent_and_payment.user_credentials;

public class User_Profile {
    private User_Info user_info;
    private User_Personal_Info user_personal_info;

    public User_Profile(User_Info user_info, User_Personal_Info user_personal_info) {
        this.user_info = user_info;
        this.user_personal_info = user_personal_info;
    }

    public User_Info getUser_info() {
        return user_info;
    }

    public void setUser_info(User_Info user_info) {
        this.user_info = user_info;
    }

    public User_Personal_Info getUser_personal_info() {
        return user_personal_info;
    }

    public void setUser_personal_info(User_Personal_Info user_personal_info) {
        this.user_personal_info = user_personal_info;
    }

    @Override
    public String toString() {
        return "User_Profile{" +
                "user_info=" + user_info +
                ", user_personal_info=" + user_personal_info +
                '}';
    }
}
