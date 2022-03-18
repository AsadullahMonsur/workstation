package com.example.myapplicationttst;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Search_DB extends AppCompatActivity implements Runnable {
    Connection conn = null;

    static  String imei;

     Toast mytoast;
    Toast mytoast2;
    int mychecker[];

    ArrayList<Search_Data> lost_list;
    ArrayList<Search_Data> found_list;


    public Search_DB() {
    }

    public Search_DB(Search_Data lpd,ArrayList<Search_Data> ll,ArrayList<Search_Data> fl) {

        this.imei = lpd.imei;
        this.mytoast = lpd.mytoast;
        this.mytoast2 = lpd.mytoast2;
        this.mychecker = lpd.mychecker;
        this.found_list = fl;
        this.lost_list = ll;

        mytoast.setText("Searching");
        mytoast.show();
    }




    @Override
    public void run() {

        try{
            Class.forName("com.mysql.jdbc.Driver");

            conn = DriverManager.getConnection("jdbc:mysql://192.168.0.152:3306/lost_and_found", "pen", "pen");

            Statement stm   = conn.createStatement();

            String query    = "select * from  lost_page where imei='"+imei+"'";


            ResultSet rs    = stm.executeQuery(query);

            if(rs.next()==true){

                String product = rs.getString("product_details");
                String location = rs.getString("location");
                Search_Data sd = new Search_Data(product,location, imei);
                lost_list.add(sd);

                mychecker[0]= 1;

            }

            Statement stm1   = conn.createStatement();
            String query1    = "select * from found_page where imei='"+imei+"'";


            ResultSet rs1    = stm1.executeQuery(query1);

            if(rs1.next()==true){

                String product1 = rs1.getString("product_details");
                String location1 = rs1.getString("location");
                Search_Data sd1 = new Search_Data(product1,location1, imei);

                found_list.add(sd1);
                mychecker[1]= 1;
            }

            conn.close();
        }

        catch (Exception e){
            e.printStackTrace();
            Log.d("Failed","Exception occured");
        }



    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
