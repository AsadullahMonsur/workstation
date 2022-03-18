package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Current_Match_DB extends AppCompatActivity implements Runnable {
    private String goal_mat_id;
    private String goal_player_id;
    private String goal_number;
    private String goal_time;

    private String card_mat_id;
    private String card_player_id;
    private String card_type;
    private String card_time;

    private String injury_mat_id;
    private String injury_player_id;
    private String injury_time;

    private String set_match_id;
    private String set_match_venu;
    private String uid;

    int flag =0;
    Toast mytoast2;
    Toast mytoast3;
    Toast mytoast4;
    Toast mytoast5;
    Connection conn;

    public Current_Match_DB() {

    }

    public Current_Match_DB(String uid,String goal_mat_id, String goal_player_id, String goal_number, String goal_time, int flag, Toast t) {
        this.goal_mat_id = goal_mat_id;
        this.goal_player_id = goal_player_id;
        this.goal_number = goal_number;
        this.goal_time = goal_time;
        this.flag = flag;
        this.mytoast2 = t;
        this.uid = uid;
    }

    public Current_Match_DB(String uid,String card_mat_id, String card_player_id, String card_type, String card_time,int flag,Toast t,int flag2) {
        this.card_mat_id = card_mat_id;
        this.card_player_id = card_player_id;
        this.card_type = card_type;
        this.card_time = card_time;
        this.flag = flag;
        this.mytoast3 = t;
        this.uid = uid;
    }

    public Current_Match_DB(String uid,String injury_mat_id, String injury_player_id, String injury_time, int flag, Toast t) {
        this.injury_mat_id = injury_mat_id;
        this.injury_player_id = injury_player_id;
        this.injury_time = injury_time;
        this.flag = flag;
        this.mytoast4 = t;
        this.uid = uid;
    }

    public Current_Match_DB( String uid,String set_match_id, String set_match_venu,int flag,Toast t) {
        this.set_match_id = set_match_id;
        this.set_match_venu = set_match_venu;
        this.uid = uid;
        this.flag = flag;
        this.mytoast5 = t;
    }

    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

           if(flag==1){
            Statement stm   = conn.createStatement();
            String query    = "select * from matches where match_id='"+goal_mat_id+"'";
            ResultSet rs    = stm.executeQuery(query);

            if(rs.next()==true){

                String query1 = "insert into scores_goal values('"+
                        goal_mat_id + "','"+
                        goal_player_id  + "','"+
                        goal_number + "','"+
                        goal_time  + "')";

                Statement stm1 = conn.createStatement();

                if(stm1.executeUpdate(query1)>0){

                    mytoast2.setText("Added successfully");
                    mytoast2.show();
                }

                else{
                    mytoast2.setText("Data insertion failed !");
                    mytoast2.show();
                }
            }

            else {
                mytoast2.setText("The match does not exists!");
                mytoast2.show();
            }
           }

            if(flag==2){
                Statement stm   = conn.createStatement();
                String query    = "select * from matches where match_id='"+card_mat_id+"'";
                ResultSet rs    = stm.executeQuery(query);

                if(rs.next()==true){

                    String query1 = "insert into gets_card values('"+
                            card_mat_id  + "','"+
                            card_player_id  + "','"+
                            card_type + "','"+
                            card_time + "')";

                    Statement stm1 = conn.createStatement();

                    if(stm1.executeUpdate(query1)>0){
                        mytoast3.setText("Added successfully");
                        mytoast3.show();
                    }

                    else{
                        mytoast3.setText("Data insertion failed !");
                        mytoast3.show();
                    }
                }

                else {
                    mytoast3.setText("The match does not exists!");
                    mytoast3.show();
                }
            }

            if(flag==3){
                Statement stm   = conn.createStatement();
                String query    = "select * from matches where match_id='"+injury_mat_id+"'";
                ResultSet rs    = stm.executeQuery(query);

                if(rs.next()==true){

                    String query1 = "insert into gets_injured values('"+
                            injury_mat_id  + "','"+
                            injury_player_id  + "','"+
                            injury_time + "')";

                    Statement stm1 = conn.createStatement();

                    if(stm1.executeUpdate(query1)>0){

                        mytoast4.setText("Added successfully");
                        mytoast4.show();
                    }

                    else{
                        mytoast4.setText("Data insertion failed !");
                        mytoast4.show();
                    }
                }

                else {
                    mytoast4.setText("The match does not exists!");
                    mytoast4.show();
                }
            }

            if(flag==4){
                Statement stm   = conn.createStatement();
                String query    = "select * from matches where match_id='"+set_match_id+"'";
                ResultSet rs    = stm.executeQuery(query);

                if(rs.next()==false){

                    String query1 = "insert into matches values('"
                                    + set_match_id  + "','"
                                    + uid + "','"
                                    + set_match_venu+ "')";

                    Statement stm1 = conn.createStatement();

                    if(stm1.executeUpdate(query1)>0){

                        mytoast5.setText("Added successfully");
                        mytoast5.show();
                    }

                    else{
                        mytoast5.setText("Data insertion failed !");
                        mytoast5.show();
                    }
                }

                else {
                    mytoast2.setText("The match id already exists!"
                            +"\n Please enter another one");
                    mytoast2.show();
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
