package com.example.myapplicationttst;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Log_In_DB  extends AppCompatActivity implements Runnable {

    String umail;
    String pass;
    Toast mytoast;
    Toast mytoast2;
    Connection conn =null;
    int mychecker[];

    public  Log_In_DB(){

    }

    public  Log_In_DB(Log_In_Data lid){

        mychecker = lid.mychecker;
        umail = lid.umail;
        pass  = lid.pass;

        mytoast  = lid.mytoast;
        mytoast2 = lid.mytoast2;

        this.mytoast.setText("Please wait until log in is approved");
        mytoast.show();
    }



    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.152:3306/lost_and_found", "pen", "pen");

            Statement stm   = conn.createStatement();

            String query    = "select * from  register where email='"+umail+"' and password='"+pass+"'";

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
            conn.close();
        }

        catch (Exception e){
            e.printStackTrace();
            Log.d("Failed","Exception occured");
        }

    }

}
