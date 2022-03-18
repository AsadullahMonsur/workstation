package com.example.house_rent_and_payment.market_view;

import com.example.house_rent_and_payment.others.Amount;
import com.example.house_rent_and_payment.others.Contact;
import com.example.house_rent_and_payment.others.Image_File;

import java.util.List;

public class Common_Market_Content_Info {
    private int user_mode = 0; // 0 for viewer only, 1 for owner, 2 for subscriber
    private Image_File profile_img;
    private String rent_id = "empty";
    private String owner_name = "default";
    private String location = "empty";
    private Amount amount;
    private Contact contact_number;
    private String description = "empty";
    private int isActive = 0;
    private int is_published = 0;
    private int is_rented = 0;
    private String publish_date = "default";
    private String rent_date = "default";
    private double user_rating = -1.0;
    private List<Image_File> img_list;

    public Common_Market_Content_Info() {
    }

    public Common_Market_Content_Info(int user_mode, Image_File profile_img, String rent_id, String owner_name, String location, Amount amount, Contact contact_number, String description, int isActive, int is_published, int is_rented, String publish_date, String rent_date, double user_rating, List<Image_File> img_list) {
        this.user_mode = user_mode;
        this.profile_img = profile_img;
        this.rent_id = rent_id;
        this.owner_name = owner_name;
        this.location = location;
        this.amount = amount;
        this.contact_number = contact_number;
        this.description = description;
        this.isActive = isActive;
        this.is_published = is_published;
        this.is_rented = is_rented;
        this.publish_date = publish_date;
        this.rent_date = rent_date;
        this.user_rating = user_rating;
        this.img_list = img_list;
    }

    public int getUser_mode() {
        return user_mode;
    }

    public void setUser_mode(int user_mode) {
        this.user_mode = user_mode;
    }

    public Image_File getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(Image_File profile_img) {
        this.profile_img = profile_img;
    }

    public String getRent_id() {
        return rent_id;
    }

    public void setRent_id(String rent_id) {
        this.rent_id = rent_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }

    public Contact getContact_number() {
        return contact_number;
    }

    public void setContact_number(Contact contact_number) {
        this.contact_number = contact_number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getIsActive() {
        return isActive;
    }

    public void setIsActive(int isActive) {
        this.isActive = isActive;
    }

    public int getIs_published() {
        return is_published;
    }

    public void setIs_published(int is_published) {
        this.is_published = is_published;
    }

    public int getIs_rented() {
        return is_rented;
    }

    public void setIs_rented(int is_rented) {
        this.is_rented = is_rented;
    }

    public String getPublish_date() {
        return publish_date;
    }

    public void setPublish_date(String publish_date) {
        this.publish_date = publish_date;
    }

    public String getRent_date() {
        return rent_date;
    }

    public void setRent_date(String rent_date) {
        this.rent_date = rent_date;
    }

    public double getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(double user_rating) {
        this.user_rating = user_rating;
    }

    public List<Image_File> getImg_list() {
        return img_list;
    }

    public void setImg_list(List<Image_File> img_list) {
        this.img_list = img_list;
    }

    @Override
    public String toString() {
        return "Common_Market_Content_Info{" +
                "user_mode=" + user_mode +
                ", profile_img=" + profile_img +
                ", rent_id='" + rent_id + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", location='" + location + '\'' +
                ", amount=" + amount +
                ", contact_number=" + contact_number +
                ", description='" + description + '\'' +
                ", isActive=" + isActive +
                ", is_published=" + is_published +
                ", is_rented=" + is_rented +
                ", publish_date='" + publish_date + '\'' +
                ", rent_date='" + rent_date + '\'' +
                ", user_rating=" + user_rating +
                ", img_list=" + img_list +
                '}';
    }
}
