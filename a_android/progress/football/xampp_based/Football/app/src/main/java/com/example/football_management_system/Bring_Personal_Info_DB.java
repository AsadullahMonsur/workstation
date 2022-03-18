package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Bring_Personal_Info_DB extends AppCompatActivity implements Runnable {

    int flag = 0;
    ArrayList<Person_Info> person_list;
    ArrayList<String> mygoals;
    String user;
    Connection conn;

    public Bring_Personal_Info_DB() {

    }

    public Bring_Personal_Info_DB(int f, String user, ArrayList<Person_Info> pl) {
       flag = f;
       this.user = user;
       person_list = pl;
    }
    public Bring_Personal_Info_DB(int f, String user, ArrayList<String> goals,int extra) {
        flag = f;
        this.user = user;
        mygoals = goals;
    }

    @Override
    public void run() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            if(flag==1){
            Statement stm   = conn.createStatement();
            String query    = "select * from owner where owner_id='"+user+"'";
            ResultSet rs    = stm.executeQuery(query);

            if(rs.next()==true){
                String id       = rs.getString("owner_id");
                String name     = rs.getString("owner_name");
                String country  = rs.getString("owner_country");

                Person_Info pi = new Person_Info(id,name,country);
                person_list.add(pi);
            }

            }
            else if(flag==2){

                Statement stm   = conn.createStatement();
                String query    = "select * from manager where manager_id='"+user+"'";
                ResultSet rs    = stm.executeQuery(query);

                if(rs.next()==true){
                    String id       = rs.getString("manager_id");
                    String name     = rs.getString("manager_name");
                    String country  = rs.getString("manager_country");

                    Person_Info pi = new Person_Info(id,name,country);
                    person_list.add(pi);
                }
            }

            else if(flag==3){

                Statement stm   = conn.createStatement();
                String query    = "select * from players where player_id='"+user+"'";
                ResultSet rs    = stm.executeQuery(query);


                if(rs.next()==true){
                    String id       = rs.getString("player_id");
                    String name     = rs.getString("player_name");
                    String country  = rs.getString("player_country");

                    Person_Info pi = new Person_Info(id,name,country);
                    person_list.add(pi);
                }
            }
            else if(flag==4){

                Statement stm   = conn.createStatement();
                String query    = "select * from general_users where viewer_email='"+user+"'";
                ResultSet rs    = stm.executeQuery(query);

                if(rs.next()==true){
                    int  id       = rs.getInt("viewer_id");
                    String fname  = rs.getString("viewer_first_name");
                    String lname  = rs.getString("viewer_last_name");
                    String em     = rs.getString("viewer_email");
                    Person_Info pi = new Person_Info(0,fname,lname,em);
                    person_list.add(pi);
                }
            }
            else if(flag==5){

                Statement stm   = conn.createStatement();
                String query    = "select COUNT(*) from scores_goal where player_id='"+user+"'";
                ResultSet rs    = stm.executeQuery(query);

                if(rs.next()==true){

                    int Goal = rs.getInt("COUNT(*)");
                    String goals = ""+Goal;
                    mygoals.add(goals);
                }
                else{
                    String goals = "00";
                    mygoals.add(goals);
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
