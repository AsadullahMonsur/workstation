package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class View_Match_DB extends AppCompatActivity implements Runnable {

    ArrayList<String> match_list;
    ArrayList<String> goal_list;
    ArrayList<String> card_list;
    ArrayList<String> injury_list;
    ArrayList<String> viewer_mat_list;



    Connection conn;
    int flag =0;


    public View_Match_DB(int flag,ArrayList<String> list1,ArrayList<String> list2,
                           ArrayList<String> list3,ArrayList<String> list4) {

        this.flag = flag;
        this.match_list = list1;
        goal_list = list2;
        card_list = list3;
        injury_list = list4;
    }

    public View_Match_DB(int flag,ArrayList<String> viewer_mat_list) {
        this.viewer_mat_list = viewer_mat_list;
        this.flag = flag;
    }

    public View_Match_DB() {
    }

    @Override
    public void run() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            if(flag==1) {
                Statement stm = conn.createStatement();
                String query = "select * from matches";
                ResultSet rs = stm.executeQuery(query);

                while (rs.next() == true) {

                    String mid = rs.getString("match_id");
                    String venue = rs.getString("match_venue");
                    String result = rs.getString("match_result");
                    String total = " Match Id   :  " + mid
                            + "\n Venue        :  " + venue
                            + "\n Match Result   :  " + result;
                    match_list.add(total);
                }


                Statement stm1 = conn.createStatement();
                String query1 = "select * from scores_goal";
                ResultSet rs1 = stm1.executeQuery(query1);

                while (rs1.next() == true) {

                    String match_id = rs1.getString("match_id");
                    String player_id = rs1.getString("player_id");
                    String goal_num = rs1.getString("goal_no");
                    String goal_time = rs1.getString("goal_time");

                    String total = " Match Id   :  " + match_id
                            + "\n Player Id     :  " + player_id
                            + "\n Goal No      :  " + goal_num
                            + "\n Goal Time    :  " + goal_time;

                    goal_list.add(total);
                }


                Statement stm2 = conn.createStatement();
                String query2 = "select * from gets_card";
                ResultSet rs2 = stm2.executeQuery(query2);

                while (rs2.next() == true) {

                    String a = rs2.getString("match_id");
                    String b = rs2.getString("player_id");
                    String c = rs2.getString("card_type");
                    String d = rs2.getString("card_time");

                    String total = " Match Id   :  " + a
                            + "\n Player     :  " + b
                            + "\n Card type   :  " + c
                            + "\n Card Time   :  " + d;

                    card_list.add(total);
                }


                Statement stm3 = conn.createStatement();
                String query3 = "select * from gets_injured";
                ResultSet rs3 = stm3.executeQuery(query3);

                while (rs3.next() == true) {

                    String a = rs3.getString("match_id");
                    String b = rs3.getString("player_id");
                    String c = rs3.getString("injury_time");

                    String total = " Match Id   :  " + a
                            + "\n Player Id :  " + b
                            + "\n Injury Time   :  " + c;
                    injury_list.add(total);
                }
            }
            if(flag==2){

                Statement stm = conn.createStatement();
                String query = "select * from matches";
                ResultSet rs = stm.executeQuery(query);

                while (rs.next() ==
                        true) {
                    String mid = rs.getString("match_id");
                    String venue = rs.getString("match_venue");
                    String result = rs.getString("match_result");
                    String total = " Match Id   :  " + mid
                            + "\n Venue        :  " + venue
                            + "\n Match Result   :  " + result;
                    viewer_mat_list.add(total);
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
