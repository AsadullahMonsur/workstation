package com.example.myapplicationttst;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Lost_Page_DB extends AppCompatActivity implements Runnable {

    Connection conn = null;
    static String prod;
    static  String loc;
    static  String imei;
    Toast mytoast;
    Toast mytoast2;
    int mychecker[];

    public Lost_Page_DB() {
    }

    public Lost_Page_DB(Lost_Page_Data lpd) {

        this.prod = lpd.prod;
        this.loc = lpd.loc;
        this.imei = lpd.imei;
        this.mytoast = lpd.mytoast;
        this.mytoast2 = lpd.mytoast2;
        this.mychecker = lpd.mychecker;

        mytoast.setText("Waiting");
        mytoast.show();
    }

    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.31.56:3306/lost_and_found", "pen", "pen");

            Statement stm   = conn.createStatement();

            String query    = "insert into lost_page values(null,'"+prod+"','"+loc+"','"+imei+"')";

            if(stm.executeUpdate(query)>0){
                mychecker[0]= 1;
                mytoast2.setText("Successful Added");
                mytoast2.show();
            }

            else {
                mytoast2.setText("Info insertion failed");
                mytoast2.show();
            }
            conn.close();
        }

        catch (Exception e){
            e.printStackTrace();
            Log.d("Failed","Exception occured");
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
