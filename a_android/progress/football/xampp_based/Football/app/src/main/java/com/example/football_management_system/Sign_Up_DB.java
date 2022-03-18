package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;


public class Sign_Up_DB extends AppCompatActivity implements Runnable{


    private String f_name;
    private String l_name;
    private String u_email;
    private String u_pass;
    private Toast mytoast;
    private Toast mytoast2;
    Connection conn;

    public Sign_Up_DB() {
    }

    public Sign_Up_DB(Sign_Up_Data supd){
          f_name    = supd.fname;
          l_name    = supd.lname;
          u_email   = supd.email;
          u_pass    = supd.password;
      this.mytoast  = supd.mytoast;
      this.mytoast2 = supd.mytoast2;
      this.mytoast.setText("Please wait until your profile is approved");
      mytoast.show();
    }

    @Override
    public void run() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            Statement stm   = conn.createStatement();
            String query    = "select * from general_users where viewer_email='"+u_email+"'";
            ResultSet rs    = stm.executeQuery(query);

            if(rs.next()==false){

                String query1 = "insert into general_users values(null,'"+
                                f_name  + "','"+
                                l_name  + "','"+
                                u_email + "','"+
                                u_pass  + "')";

                Statement stm1 = conn.createStatement();

                if(stm1.executeUpdate(query1)>0){

                    mytoast2.setText("Account successfully formed");
                    mytoast2.show();
                   }

                 else{
                    mytoast2.setText("Data insertion failed !");
                    mytoast2.show();
                 }
              }

              else {
                mytoast2.setText("Email already exists!");
                mytoast2.show();
              }
            conn.close();
           }
           catch (Exception e){
               e.printStackTrace();
               Log.d("Failed in Sign_Up_DB","Exception occured !!"+"\n Failed in Sign_Up_DB");
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
