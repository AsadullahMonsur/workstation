package com.example.house_rent_and_payment.user_credentials;

import com.example.house_rent_and_payment.others.Image_File;

public class User_Personal_Info {
    private String f_name;
    private String l_name;
    private String u_email;
    private String contact_no;
    private String national_id;
    private Image_File profile_photo;

    public User_Personal_Info() {
    }

    public User_Personal_Info(String f_name, String l_name, String u_email, String contact_no, String national_id, Image_File profile_photo) {
        this.f_name = f_name;
        this.l_name = l_name;
        this.u_email = u_email;
        this.contact_no = contact_no;
        this.national_id = national_id;
        this.profile_photo = profile_photo;
    }

    public String getF_name() {
        return f_name;
    }

    public void setF_name(String f_name) {
        this.f_name = f_name;
    }

    public String getL_name() {
        return l_name;
    }

    public void setL_name(String l_name) {
        this.l_name = l_name;
    }

    public String getU_email() {
        return u_email;
    }

    public void setU_email(String u_email) {
        this.u_email = u_email;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getNational_id() {
        return national_id;
    }

    public void setNational_id(String national_id) {
        this.national_id = national_id;
    }

    public Image_File getProfile_photo() {
        return profile_photo;
    }

    public void setProfile_photo(Image_File profile_photo) {
        this.profile_photo = profile_photo;
    }

    @Override
    public String toString() {
        return "User_Personal_Info{" +
                "f_name='" + f_name + '\'' +
                ", l_name='" + l_name + '\'' +
                ", u_email='" + u_email + '\'' +
                ", contact_no='" + contact_no + '\'' +
                ", national_id='" + national_id + '\'' +
                ", profile_photo=" + profile_photo +
                '}';
    }
}
