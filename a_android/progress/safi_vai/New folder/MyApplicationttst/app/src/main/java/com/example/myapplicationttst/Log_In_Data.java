package com.example.myapplicationttst;

import android.widget.Toast;

public class Log_In_Data {

    static String umail;
    static String pass;
    static Toast mytoast;
    static Toast mytoast2;
    static int mychecker[];

    public Log_In_Data(){

    }


    public Log_In_Data(String u, String p, Toast t,Toast t2, int c[]){

        umail = u;
        pass = p;
        mytoast = t;
        mytoast2 = t2;
        mychecker = c;
    }
}
