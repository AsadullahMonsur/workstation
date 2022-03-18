package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Bring_Managers_Players_DB extends AppCompatActivity implements Runnable {

    int flag=0;
    Connection conn;
    ArrayList<String> mylist;

    public Bring_Managers_Players_DB(int flag, ArrayList<String> mylist) {
        this.flag = flag;
        this.mylist = mylist;
    }

    public Bring_Managers_Players_DB() {
    }


    @Override
    public void run() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            if(flag==1){
                Statement stm   = conn.createStatement();
                String query    = "select * from manager";
                ResultSet rs    = stm.executeQuery(query);

                while(rs.next()==true){
                    String id       = rs.getString("manager_id");
                    String name     = rs.getString("manager_name");
                    String country  = rs.getString("manager_country");
                    String age     = rs.getString("manager_age");
                    String pass  = rs.getString("manager_password");

                    String total = " ID                :  "+id
                                   +"\n Name         :  "+name
                                   +"\n Country      :  "+country
                                   +"\n Age             :  "+age
                                   +"\n Password  :  "+pass;

                    mylist.add(total);
                }
            }

            if(flag==2){
                Statement stm   = conn.createStatement();
                String query    = "select * from players";
                ResultSet rs    = stm.executeQuery(query);

                while(rs.next()==true){

                    String id       = rs.getString("player_id");
                    String name     = rs.getString("player_name");
                    String country  = rs.getString("player_country");
                    String age      = rs.getString("player_age");
                    String position =  rs.getString("player_position");
                    String pass     = rs.getString("player_password");

                    String total = " ID                :  "+id
                            +"\n Name         :  "+name
                            +"\n Country      :  "+country
                            +"\n Age             :  "+age
                            +"\n Position     :  "+position
                            +"\n Password  :  "+pass;

                    mylist.add(total);
                }
            }
            if(flag==3){
                Statement stm   = conn.createStatement();
                String query    = "select * from players";
                ResultSet rs    = stm.executeQuery(query);

                while(rs.next()==true){

                    String name     = rs.getString("player_name");
                    String country  = rs.getString("player_country");
                    String age      = rs.getString("player_age");
                    String position =  rs.getString("player_position");

                    String total = " Name         :  "+name
                            +"\n Country      :  "+country
                            +"\n Age             :  "+age
                            +"\n Position     :  "+position;

                    mylist.add(total);
                }
            }

            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("Failed","Exception occured !!");
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
