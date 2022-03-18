package com.example.football_management_system;

import android.widget.Toast;

public class Log_In_Page_2_Data {
    static String uID;
    static String upass;
    static Toast mytoast;
    static Toast mytoast2;
    static int checker[];
    static int identifier;
    static String profile[];

    public Log_In_Page_2_Data() {
    }

    public Log_In_Page_2_Data(String uid, String pass, Toast mytoast,Toast t2,int f[],int idn,String prof[]) {
        uID          = uid;
        upass        = pass;
        this.mytoast = mytoast;
        mytoast2     = t2;
        checker      = f;
        identifier   = idn;
        profile      = prof;
    }

}
