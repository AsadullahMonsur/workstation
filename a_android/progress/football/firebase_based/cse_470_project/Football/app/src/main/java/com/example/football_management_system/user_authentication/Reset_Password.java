package com.example.football_management_system.user_authentication;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Reset_Password extends AppCompatActivity implements Runnable {

    Connection conn = null;
    private String u_id;
    private String u_pass;
    private Toast mytoast2;
    private int flag= 0;

    public Reset_Password() {
    }

    public Reset_Password(int flag,String u_id, String u_pass, Toast mytoast2) {
       this.flag = flag;
        this.u_id = u_id;
        this.u_pass = u_pass;
        this.mytoast2 = mytoast2;
    }

    @Override
    public void run() {
        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");

            if(flag==1){
            Statement stm   = conn.createStatement();
            String query  = "update owner set owner_password='"+u_pass+"' where owner_id='"+u_id+"'";

            if(stm.executeUpdate(query)>0){

                mytoast2.setText("Reset successful");
                mytoast2.show();
            }

            else {
                mytoast2.setText("Failed to reset");
                mytoast2.show();
            }
            }

            if(flag==2){
                Statement stm   = conn.createStatement();
                String query  = "update manager set manager_password='"+u_pass+"' where manager_id='"+u_id+"'";

                if(stm.executeUpdate(query)>0){

                    mytoast2.setText("Reset successful");
                    mytoast2.show();
                }

                else {
                    mytoast2.setText("Failed to reset");
                    mytoast2.show();
                }
            }
            if(flag==3){
                Statement stm   = conn.createStatement();
                String query  = "update players set player_password='"+u_pass+"' where player_id='"+u_id+"'";

                if(stm.executeUpdate(query)>0){

                    mytoast2.setText("Reset successful");
                    mytoast2.show();
                }

                else {
                    mytoast2.setText("Failed to reset");
                    mytoast2.show();
                }
            }
            if(flag==4){

                Statement stm   = conn.createStatement();
                String query  = "update general_users set viewer_password='"+u_pass+"' where viewer_email='"+u_id+"'";

                if(stm.executeUpdate(query)>0){

                    mytoast2.setText("Reset successful");
                    mytoast2.show();
                }

                else {
                    mytoast2.setText("Failed to reset");
                    mytoast2.show();
                }
            }
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("Failed","Exception occured !!"+"\n Failed in Reset");
        }


    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
