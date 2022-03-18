package com.example.myapplicationttst;

import android.widget.Toast;

public class Register_Data {
   static String name;
   static String email;
   static String pass;
   static String location;
   static String contact;
    Toast mytoast;
    Toast mytoast2;
    int mychecker[];

    public Register_Data() {
    }

    public Register_Data(String n, String e, String p, String l, String con, Toast t, Toast t2, int c[]) {
     name = n;
     email = e;
     pass = p;
     location = l;
     contact = con;
     mytoast = t;
     mytoast2 = t2;
     mychecker = c;

    }
}
