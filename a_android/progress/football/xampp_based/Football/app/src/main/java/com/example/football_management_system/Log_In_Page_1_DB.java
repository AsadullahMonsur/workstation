package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Log_In_Page_1_DB extends AppCompatActivity implements Runnable{

    Connection conn = null;
    private String u_email;
    private String u_pass;
    private Toast mytoast;
    private Toast mytoast2;
    static int checker[];
    static String profile[];

    public Log_In_Page_1_DB() {
    }

    public Log_In_Page_1_DB(Log_In_Page_1_Data lipd) {
        checker      = lipd.mychecker;
        profile      = lipd.profile;
        u_email      = lipd.usermail;
        u_pass       = lipd.password;
        mytoast      = lipd.mytoast;
        mytoast2     = lipd.mytoast2;

        this.mytoast.setText("Please wait until log in is approved");
        mytoast.show();
    }


    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            Statement stm   = conn.createStatement();
            String query    = "select * from general_users where viewer_email='"+u_email+"' and viewer_password='"+u_pass+"'";
            ResultSet rs    = stm.executeQuery(query);

            if(rs.next()==true){
                 checker[0] = 1;
                 profile[0] = u_email;
                 //profile[1] = "";
                 mytoast2.setText("Log in successful");
                 mytoast2.show();
            }

            else {
                mytoast2.setText("Wrong log in info");
                mytoast2.show();
            }
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("Failed","Exception occured !!"+"\n Failed in Sign_In_page_1_DB");
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
