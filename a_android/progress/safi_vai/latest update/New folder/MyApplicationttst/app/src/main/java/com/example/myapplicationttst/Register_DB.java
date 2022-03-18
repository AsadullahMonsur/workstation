package com.example.myapplicationttst;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Register_DB extends AppCompatActivity implements Runnable {

    Connection conn;
    static String name;
    static String email;
    static String pass;
    static String location;
    static String contact;
    Toast mytoast;
    Toast mytoast2;
    int mychecker[];

    public Register_DB() {
    }

    public Register_DB(Register_Data rd) {

        this.mychecker = rd.mychecker;
        this.name = rd.name;
        this.email = rd.email;
        this.pass = rd.pass ;
        this.location = rd.location;
        this.contact =rd.contact;
        this.mytoast = rd.mytoast;
        this.mytoast2 = rd.mytoast2;

        mytoast.setText("Please wait until register ");
        mytoast.show();

    }

    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.152:3306/lost_and_found", "pen", "pen");

            Statement stm   = conn.createStatement();

            String query    = "insert into register values(null,'"+name+"','"+email+"','"+pass+"','"+location+"','"+contact+"')";

            if(stm.executeUpdate(query)>0){
                mychecker[0]= 1;
                mytoast2.setText("Register Successful");
                mytoast2.show();
            }

            else {
                mytoast2.setText("Register failed");
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
