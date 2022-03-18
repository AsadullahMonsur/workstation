package com.example.house_rent_and_payment.others;

import java.util.Arrays;

public class Country {
    private String []country_name;
    private int []country_mobile_code_prefix;

    public Country() {
        country_name = new String[]{"Select Your Country Code","Afghanistan","Bangladesh","India","Pakistan","Bhutan"};
        country_mobile_code_prefix = new int[]{0,1,880,3,4,5};
    }

    public String[] getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String[] country_name) {
        this.country_name = country_name;
    }

    public int[] getCountry_mobile_code_prefix() {
        return country_mobile_code_prefix;
    }

    public void setCountry_mobile_code_prefix(int[] country_mobile_code_prefix) {
        this.country_mobile_code_prefix = country_mobile_code_prefix;
    }

    @Override
    public String toString() {
        return "Country{" +
                "country_name=" + Arrays.toString(country_name) +
                ", country_mobile_code_prefix=" + Arrays.toString(country_mobile_code_prefix) +
                '}';
    }
}
