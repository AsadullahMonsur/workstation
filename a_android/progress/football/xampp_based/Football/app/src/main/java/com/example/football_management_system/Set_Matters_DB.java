package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class Set_Matters_DB extends AppCompatActivity implements Runnable {

    int flag = 0;
    ArrayList<Money_And_Honey> optionList;
    String user;
    Connection conn;
    Toast mytoast2;

    public Set_Matters_DB() {

    }

    public Set_Matters_DB(int f, String user, ArrayList ol, Toast t) {
        flag = f;
        this.user = user;
        optionList = ol;
        mytoast2 = t;
    }

    @Override
    public void run() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            if(flag==1){

                Money_And_Honey mh = optionList.get(0);

                String objectives = mh.getObjective();
                String manager_id = mh.getOwner_cont2_manager_id();

                Statement stm   = conn.createStatement();
                String query    = "insert into objectives values(null,'"+user
                                  +"','"+manager_id+"','"+objectives+"',0)";

                if(stm.executeUpdate(query)>0){
                     mytoast2.setText("Added Successfully");
                     mytoast2.show();
                }
                else{
                    mytoast2.setText("Failed to Add");
                    mytoast2.show();
                }

            }
            else if(flag==2){

                Money_And_Honey mh = optionList.get(0);

                String manager_id = mh.getOwner_cont3_manager_id();
                String manager_income = mh.getOwner_cont3_manager_income();
                String manager_bonus = mh.getOwner_cont3_manager_bonus();


                Statement stm   = conn.createStatement();
                String query = "update manager_incomes set m_income="+manager_income
                               + ",m_bonus="+manager_bonus+" where manager_id='"
                               +manager_id+"'";


                if(stm.executeUpdate(query)>0){
                    mytoast2.setText("Added Successfully");
                    mytoast2.show();
                }
                else{
                    mytoast2.setText("Failed to Add");
                    mytoast2.show();
                }
            }

            else if(flag==3){

                Money_And_Honey mh = optionList.get(0);

                String player_id = mh.getOwner_cont3_player_id();
                String player_income = mh.getOwner_cont3_player_income();
                String player_bonus = mh.getOwner_cont3_player_bonus();


                Statement stm   = conn.createStatement();
                String query = "update player_incomes set p_income="+player_income
                        + ",p_bonus="+player_bonus+" where player_id='"
                        +player_id+"'";

                if(stm.executeUpdate(query)>0) {
                    mytoast2.setText("Added Successfully");
                    mytoast2.show();
                }
                else{
                    mytoast2.setText("Failed to Add");
                    mytoast2.show();
                }
            }

            else if(flag==4) {

                Money_And_Honey mh = optionList.get(0);

                String point = mh.getManager_cont3_points();
                String player_id = mh.getManager_cont3_player_id();

                Statement stm = conn.createStatement();
                String query = "update gets_morale_point set morale_point='"+point
                                +"' where player_id='"+player_id+"'";

                if(stm.executeUpdate(query) > 0) {
                    mytoast2.setText("Added Successfully");
                    mytoast2.show();
                }
                else{
                    mytoast2.setText("Failed to Add");
                    mytoast2.show();
                }

            }
            optionList.clear();
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
