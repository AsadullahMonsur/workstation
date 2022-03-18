package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Log_In_Page_2_DB extends AppCompatActivity implements Runnable {

    Connection conn = null;
    private String u_id;
    private String u_pass;
    private Toast mytoast;
    private Toast mytoast2;
    static int mychecker[];
    static int identifier;
    static String profile[];

    public Log_In_Page_2_DB() {
    }

    public Log_In_Page_2_DB(Log_In_Page_2_Data lipd) {
        profile         = lipd.profile;
        mychecker       = lipd.checker;
        this.identifier = lipd.identifier;
        u_id            = lipd.uID;
        u_pass          = lipd.upass;
        mytoast2        = lipd.mytoast2;
        mytoast         = lipd.mytoast;
        this.mytoast.setText("Please wait until log in is approved");
        mytoast.show();
    }

    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            Statement stm   = conn.createStatement();
            String query;

            if(identifier==1){
                query  = "select * from owner where owner_id='"+u_id+"' and owner_password='"+u_pass+"'";
            }
            else if(identifier==2){
                query  = "select * from manager where manager_id='"+u_id+"' and manager_password='"+u_pass+"'";
            }
            else{
                query  = "select * from players where player_id='"+u_id+"' and player_password='"+u_pass+"'";
            }

            ResultSet rs    = stm.executeQuery(query);

            if(rs.next()==true){
                mychecker[0] = 1;
                profile[0]   = u_id;
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
}
