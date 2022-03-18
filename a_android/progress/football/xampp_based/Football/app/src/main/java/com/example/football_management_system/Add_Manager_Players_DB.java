package com.example.football_management_system;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Add_Manager_Players_DB extends AppCompatActivity implements Runnable {

    int flag =0;
    ArrayList<String> list;
    Toast mytoast2;
    Connection conn;

    public Add_Manager_Players_DB() {

    }

    public Add_Manager_Players_DB(int flag, ArrayList<String> list, Toast t) {
        this.flag = flag;
        this.list = list;
        mytoast2 = t;
    }

    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.199:3306/football_management_database", "pen", "pen");


            if(flag==1){
                String  uid = list.get(0);
                Statement stm   = conn.createStatement();
                String query    = "select * from manager where manager_id='"+uid+"'";
                ResultSet rs    = stm.executeQuery(query);

            if(rs.next()==false){

                String query1 = "insert into manager values('"+
                        list.get(0)  + "','"+
                        list.get(1)  + "','"+
                        list.get(2) + "','"+
                        list.get(3) + "','"+
                        list.get(4)  + "')";

                Statement stm1 = conn.createStatement();

                if(stm1.executeUpdate(query1)>0){

                    mytoast2.setText("Added Succefully");
                    mytoast2.show();
                }

                else{
                    mytoast2.setText("Data insertion failed !");
                    mytoast2.show();
                }
            }

            else {
                mytoast2.setText("Manager Id already exists!");
                mytoast2.show();
            }
            }

            if(flag==2){
                String  uid     = list.get(0);
                Statement stm   = conn.createStatement();
                String query    = "select * from players where player_id='"+uid+"'";
                ResultSet rs    = stm.executeQuery(query);

                if(rs.next()==false){

                    String query1 = "insert into players values('"+
                            list.get(0)  + "','"+
                            list.get(1)  + "','"+
                            list.get(2) + "','"+
                            list.get(3) + "','"+
                            list.get(4) + "','"+
                            list.get(5)  + "')";

                    Statement stm1 = conn.createStatement();

                    if(stm1.executeUpdate(query1)>0){

                        mytoast2.setText("Added Succefully");
                        mytoast2.show();
                    }

                    else{
                        mytoast2.setText("Data insertion failed !");
                        mytoast2.show();
                    }
                }

                else {
                    mytoast2.setText("Player Id already exists!");
                    mytoast2.show();
                }
            }
            list.clear();
            conn.close();
        }
        catch (Exception e){
            e.printStackTrace();
            Log.d("Failed ","Exception occured !!"+"\n Failed in Added_Manager_Players");
        }
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
