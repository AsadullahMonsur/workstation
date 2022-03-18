package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Searchresult extends AppCompatActivity {

    Toast mytoast3;
    Toast mytoast2;
    Toast mytoast;

    int mychecker[];
    EditText searchText;

    TextView lostTv;
    TextView foundTv;

    ArrayList<Search_Data> lost_List;
    ArrayList<Search_Data> found_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult);

        searchText = findViewById(R.id.editText7se);

        mytoast3 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);

        mytoast.setText("Please fill up input fields !");
        mytoast.show();
        mychecker = new int[2];
        mychecker[0] = 0;
        mychecker[1] = 0;

        lostTv  = findViewById(R.id.lost_tv);
        foundTv = findViewById(R.id.found_tv);

        lost_List  = new ArrayList<Search_Data>();
        found_List = new ArrayList<Search_Data>();

    }

    public void confirm_home6(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }

    public void confirm_lostfndpage(View view) {
        Intent in = new Intent(this, Postlogin.class);
        startActivity(in);
        finish();
    }

    public void searching(View view) {

        if(searchText.getText().toString().trim().length() > 0) {

            Search_Data sd = new Search_Data(searchText.getText().toString(),
                                       mytoast,mytoast2,mychecker);

            Search_DB sdb = new Search_DB(sd,lost_List,found_List);

            Thread tt = new Thread(sdb);
            tt.start();

            Thread match = new Thread(){
                public void run() {
                    try {
                        Thread.sleep(4000);
                        if(mychecker[0]==1 || mychecker[1]==1){

                            Thread updateUi = new Thread(){
                                public void run() {
                                    try{
                                        Thread.sleep(4500);
                                        handler.sendEmptyMessage(0);
                                    }
                                    catch (Exception e){
                                        e.printStackTrace();
                                        Log.d("failed","exception");
                                    }
                                }
                            };
                            updateUi.start();
                            mychecker[0] = 0;
                            mychecker[1] = 0;
                        }
                        else{
                            Thread.sleep(4000);
                            if(mychecker[0]==1 || mychecker[1]==1){
                                Thread updateUi = new Thread(){
                                    public void run() {
                                        try{
                                            Thread.sleep(4500);
                                            handler.sendEmptyMessage(0);
                                        }
                                        catch (Exception e){
                                            e.printStackTrace();
                                            Log.d("failed","exception");
                                        }
                                    }
                                };
                                updateUi.start();
                                mychecker[0] = 0;
                                mychecker[1] = 0;
                            }
                            else{
                                mychecker[0] = 0;
                                mychecker[1] = 0;
                                mytoast3.setText("Network issues !!");
                                mytoast3.show();
                            }
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            match.start();


        }
        else{
            mytoast.setText("Check your info again!");
            mytoast.show();
        }

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {

         if(lost_List.isEmpty()==false) {
             String product_dt = "empty";
             String loc = "empty";
             String ime = "empty";

             Search_Data pi = lost_List.get(0);

             product_dt = pi.getProduct();
             loc = pi.getLoction();
             ime = pi.getImei();

             lostTv.setText("Product    : " + product_dt
                     + "\nLocation   : " + loc
                     + "\nIMEI       : " + ime);

         }
         else {
             lostTv.setText("No such item");

         }

         if(found_List.isEmpty()==false) {
             String product_dt = "empty";

             String loc = "empty";
             String ime = "empty";

             Search_Data pi = found_List.get(0);

             product_dt = pi.getProduct();
             loc = pi.getLoction();
             ime = pi.getImei();

             foundTv.setText("Product    : " + product_dt
                     + "\nLocation   : " + loc
                     + "\nIMEI       : " + ime);
         }
         else {
             foundTv.setText("No such item");
         }
        }
    };
}
