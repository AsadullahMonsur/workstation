package com.example.football_management_system;

import android.widget.Toast;

public class Sign_Up_Data {
    static String fname;
    static String lname;
    static String email;
    static String password;
    static Toast mytoast;
    static Toast mytoast2;

    public Sign_Up_Data() {
    }
    public Sign_Up_Data(String fn, String ln, String em, String pass, Toast t, Toast t2) {
         fname    = fn;
         lname    = ln;
         email    = em;
         password = pass;
         mytoast  = t;
         mytoast2 = t2;
    }

}
