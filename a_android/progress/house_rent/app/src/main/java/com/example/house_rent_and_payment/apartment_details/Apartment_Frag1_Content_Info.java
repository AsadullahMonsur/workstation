package com.example.house_rent_and_payment.apartment_details;

import com.example.house_rent_and_payment.others.Amount;
import com.example.house_rent_and_payment.others.Contact;
import com.example.house_rent_and_payment.others.Image_File;

import java.util.List;

public class Apartment_Frag1_Content_Info {
    private String rent_id = "default";
    private String owner_id = "default";
    private String customer_id = "default";
    private String owner_name = "default";
    private Contact contact;
    private String location = "default";
    private Amount amount;
    private String description = "default";
    private int active_status = 0; // 0 means not active rent, 1 means active rent
    private int is_published = 0;
    private int is_rented = 0;
    private String publish_date = "default";
    private String rent_date = "default";
    private double user_rating = -1.0;
    private List<Image_File> img_list_info;
    private Image_File profile_img;

    public Apartment_Frag1_Content_Info() {
    }

    public Apartment_Frag1_Content_Info(String rent_id, String owner_id, String customer_id, String owner_name, Contact contact, String location, Amount amount, String description, int active_status, int is_published, int is_rented, String publish_date, String rent_date, double user_rating, List<Image_File> img_list_info, Image_File profile_img) {
        this.rent_id = rent_id;
        this.owner_id = owner_id;
        this.customer_id = customer_id;
        this.owner_name = owner_name;
        this.contact = contact;
        this.location = location;
        this.amount = amount;
        this.description = description;
        this.active_status = active_status;
        this.is_published = is_published;
        this.is_rented = is_rented;
        this.publish_date = publish_date;
        this.rent_date = rent_date;
        this.user_rating = user_rating;
        this.img_list_info = img_list_info;
        this.profile_img = profile_img;
    }

    public String getRent_id() {
        return rent_id;
    }

    public void setRent_id(String rent_id) {
        this.rent_id = rent_id;
    }

    public String getOwner_id() {
        return owner_id;
    }

    public void setOwner_id(String owner_id) {
        this.owner_id = owner_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getOwner_name() {
        return owner_name;
    }

    public void setOwner_name(String owner_name) {
        this.owner_name = owner_name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getActive_status() {
        return active_status;
    }

    public void setActive_status(int active_status) {
        this.active_status = active_status;
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

    public List<Image_File> getImg_list_info() {
        return img_list_info;
    }

    public void setImg_list_info(List<Image_File> img_list_info) {
        this.img_list_info = img_list_info;
    }

    public Image_File getProfile_img() {
        return profile_img;
    }

    public void setProfile_img(Image_File profile_img) {
        this.profile_img = profile_img;
    }

    @Override
    public String toString() {
        return "Apartment_Frag1_Content_Info{" +
                "rent_id='" + rent_id + '\'' +
                ", owner_id='" + owner_id + '\'' +
                ", customer_id='" + customer_id + '\'' +
                ", owner_name='" + owner_name + '\'' +
                ", contact=" + contact +
                ", location='" + location + '\'' +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", active_status=" + active_status +
                ", is_published=" + is_published +
                ", is_rented=" + is_rented +
                ", publish_date='" + publish_date + '\'' +
                ", rent_date='" + rent_date + '\'' +
                ", user_rating=" + user_rating +
                ", img_list_info=" + img_list_info +
                ", profile_img=" + profile_img +
                '}';
    }
}