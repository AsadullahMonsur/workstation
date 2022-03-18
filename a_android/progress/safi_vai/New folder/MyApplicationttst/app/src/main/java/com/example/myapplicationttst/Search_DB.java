package com.example.myapplicationttst;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Search_DB extends AppCompatActivity implements Runnable {
    Connection conn = null;

    static  String imei;
    Toast mytoast;
    Toast mytoast2;
    int mychecker[];

    public Search_DB() {
    }

    public Search_DB(Search_Data lpd) {

        this.imei = lpd.imei;
        this.mytoast = lpd.mytoast;
        this.mytoast2 = lpd.mytoast2;
        this.mychecker = lpd.mychecker;

        mytoast.setText("Searching");
        mytoast.show();
    }




    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.31.56:3306/lost_and_found", "pen", "pen");

            Statement stm   = conn.createStatement();

            String query    = "select * from  lost_page where imei='"+imei+"'";


            ResultSet rs    = stm.executeQuery(query);

            if(rs.next()==true){
                mychecker[0]= 1;
                mytoast2.setText("Log In Successful");
                mytoast2.show();

            }

            else {
                mytoast2.setText("Invalid info");
                mytoast2.show();
            }

            Statement stm1   = conn.createStatement();
            String query1    = "select * from  lost_page where imei='"+imei+"'";


            ResultSet rs1    = stm1.executeQuery(query1);

            if(rs1.next()==true){
                mychecker[0]= 1;
                mytoast2.setText("Log In Successful");
                mytoast2.show();

            }

            else {
                mytoast2.setText("Invalid info");
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
