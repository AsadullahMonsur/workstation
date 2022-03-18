package com.example.myapplicationttst;

import android.widget.Toast;

public class Search_Data {

    static  String imei;
    Toast mytoast;
    Toast mytoast2;
    int mychecker[];

     String product;
     String loction;

    public Search_Data() {
    }

    public Search_Data(String im, Toast mytoast, Toast mytoast2, int[] mychecker) {

        imei = im;
        this.mytoast = mytoast;
        this.mytoast2 = mytoast2;
        this.mychecker = mychecker;
    }

    public Search_Data(String a,String b, String c) {

        product = a;
        loction = b;
        imei = c;
    }

    public static String getImei() {
        return imei;
    }

    public static void setImei(String imei) {
        Search_Data.imei = imei;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getLoction() {
        return loction;
    }

    public void setLoction(String loction) {
        this.loction = loction;
    }
}
