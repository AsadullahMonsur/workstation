package com.example.myapplicationttst;

import android.widget.Toast;

public class Lost_Page_Data {
    static String prod;
    static  String loc;
    static  String imei;
    Toast mytoast;
    Toast mytoast2;
    int mychecker[];

    public Lost_Page_Data() {
    }

    public Lost_Page_Data(String p, String l, String im, Toast mytoast, Toast mytoast2, int[] mychecker) {

        prod = p;
        loc = l;
        imei = im;

        this.mytoast = mytoast;
        this.mytoast2 = mytoast2;
        this.mychecker = mychecker;
    }
}
