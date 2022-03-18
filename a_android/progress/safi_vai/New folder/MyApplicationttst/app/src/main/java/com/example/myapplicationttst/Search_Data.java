package com.example.myapplicationttst;

import android.widget.Toast;

public class Search_Data {

    static  String imei;
    Toast mytoast;
    Toast mytoast2;
    int mychecker[];

    public Search_Data() {
    }

    public Search_Data(String im, Toast mytoast, Toast mytoast2, int[] mychecker) {

        imei = im;
        this.mytoast = mytoast;
        this.mytoast2 = mytoast2;
        this.mychecker = mychecker;
    }

}
