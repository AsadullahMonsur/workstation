package com.example.football_management_system;

import android.widget.Toast;

public class Log_In_Page_1_Data {
  static String usermail;
  static String password;
  static Toast mytoast;
  static Toast mytoast2;
  static int mychecker[];
  static String profile[];

    public Log_In_Page_1_Data() {
    }

    public Log_In_Page_1_Data(String um, String pass, Toast t, Toast t2, int checker[],String prof[]) {
        usermail  = um;
        password  = pass;
        mytoast   = t;
        mytoast2  = t2;
        mychecker = checker;
        profile = prof;
    }


}
