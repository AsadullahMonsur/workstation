package com.example.mysqlspring.model;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Address {
    @Id
    @GeneratedValue
    private  int id;
    private String streetAddress;
    private String city;
    private String country;
    private String postalCodes;

    @Override
    public String toString() {
        return "Address{" +
                "streetAddress='" + streetAddress + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", postalCodes='" + postalCodes + '\'' +
                '}';
    }

    public Address() {
    }

    public Address(String streetAddress, String city, String country, String postalCodes) {
        this.streetAddress = streetAddress;
        this.city = city;
        this.country = country;
        this.postalCodes = postalCodes;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPostalCodes() {
        return postalCodes;
    }

    public void setPostalCodes(String postalCodes) {
        this.postalCodes = postalCodes;
    }
}
