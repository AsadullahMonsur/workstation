package com.example.myapplicationttst;

import android.widget.Toast;

public class Found_Page_Data {
    static String prod;
    static  String loc;
    static  String imei;
    Toast mytoast;
    Toast mytoast2;
    int mychecker[];

    public Found_Page_Data() {
    }

    public Found_Page_Data(String p, String l, String im, Toast mytoast, Toast mytoast2, int[] mychecker) {

        prod = p;
        loc = l;
        imei = im;

        this.mytoast = mytoast;
        this.mytoast2 = mytoast2;
        this.mychecker = mychecker;
    }

}
