package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Income_DB extends AppCompatActivity implements Runnable {
    int flag = 0;
    ArrayList<Income> myincome;
    String user;
    Connection conn;

    public Income_DB() {

    }

    public Income_DB(int flag, String user, ArrayList<Income> income) {
        this.flag = flag;
        this.myincome = income;
        this.user = user;
    }

    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            if(flag==1){

            Statement stm   = conn.createStatement();
            String query    = "select * from manager_incomes where manager_id='"+user+"'";
            ResultSet rs    = stm.executeQuery(query);

            while(rs.next()==true){
                String income  = rs.getString("m_income");
                String bonus   = rs.getString("m_bonus");

                Income pi = new Income(income,bonus);
                myincome.add(pi);
            }
        }

         if(flag==2){

            Statement stm   = conn.createStatement();
            String query    = "select * from player_incomes where player_id='"+user+"'";
            ResultSet rs    = stm.executeQuery(query);


            while(rs.next()==true){

                String income  = rs.getString("p_income");
                String bonus   = rs.getString("p_bonus");

                Income pi = new Income(income,bonus);
                myincome.add(pi);
            }
        }
     }
        catch (Exception e){
            e.printStackTrace();
            Log.d("failed","failed");
        }
    }


    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
