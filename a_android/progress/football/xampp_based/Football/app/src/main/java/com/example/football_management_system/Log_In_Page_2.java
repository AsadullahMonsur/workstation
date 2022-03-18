package com.example.football_management_system;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Log_In_Page_2 extends AppCompatActivity {

    TextInputEditText uID;
    TextInputEditText pass;

    TextInputLayout userId;
    TextInputLayout password;

    Toast mytoast;
    Toast mytoast2;
    Toast mytoast3;
    int identifier = 0;

    static int myChecker[];
    static String profile[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout_2);

        myChecker = new int[1];
        myChecker[0] = 0;
        Intent in = getIntent();
        Bundle bu = in.getExtras();
        String logger = bu.getString("logger");

        uID      = findViewById(R.id.uID);
        pass     = findViewById(R.id.Upass);
        userId   = findViewById(R.id.UserId);
        password = findViewById(R.id.UserPass);

        profile = new String[2];

        if(logger.equals("owner")){
             identifier = 1;
        }
        else if(logger.equals("manager")){
            identifier = 2;
        }
        else{
            identifier = 3;
        }

        mytoast3 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast.setText("Please fill up input fields !");
        mytoast.show();
    }

    public void passwordForgot(View view) {
    }

    public void LogInButtone(View view) {

        if(uID.getText().toString().trim().length()>0 && pass.getText().toString().trim().length()>0){
            userId.setBoxStrokeColor(Color.GREEN);
            password.setBoxStrokeColor(Color.GREEN);

            Log_In_Page_2_Data lipd = new Log_In_Page_2_Data(uID.getText().toString(),
                    pass.getText().toString(),mytoast, mytoast2,myChecker,identifier,profile);

            Log_In_Page_2_DB lipdb  = new Log_In_Page_2_DB(lipd);

            Thread tt = new Thread(lipdb);
            tt.start();

            Thread match = new Thread(){
                public void run() {
                    try {
                        Thread.sleep(4000);
                        if(myChecker[0]==1){

                            Intent in;
                            if(identifier==1){
                                 in = new Intent(getBaseContext(),Owner_Home.class);
                                 in.putExtra("us_id",profile[0]);
                            }
                            else if(identifier==2){
                                 in = new Intent(getBaseContext(),Manager_Home.class);
                                 in.putExtra("us_id",profile[0]);
                            }
                            else{
                                 in = new Intent(getBaseContext(),Player_Home.class);
                                 in.putExtra("us_id",profile[0]);
                            }

                            startActivity(in);
                            myChecker[0]=0;
                        }
                        else{
                            Thread.sleep(4000);
                            if(myChecker[0]==1){

                                Intent in;
                                if(identifier==1){
                                    in = new Intent(getBaseContext(),Owner_Home.class);
                                    in.putExtra("us_id",profile[0]);
                                }
                                else if(identifier==2){
                                    in = new Intent(getBaseContext(),Manager_Home.class);
                                    in.putExtra("us_id",profile[0]);
                                }
                                else{
                                    in = new Intent(getBaseContext(),Player_Home.class);
                                    in.putExtra("us_id",profile[0]);
                                }

                                startActivity(in);
                                myChecker[0]=0;
                            }
                            else{
                                myChecker[0]=0;
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
//            if(result==false) {
//                userId.setBoxStrokeColor(Color.RED);
//            }
//            else{
//                userId.setBoxStrokeColor(Color.GREEN);
//            }
//
//            if(pass.getText().toString().trim().length()<=0){
//                password.setBoxStrokeColor(Color.RED);
//            }
//            else{
//                password.setBoxStrokeColor(Color.GREEN);
//            }
            mytoast.setText("Invalid info !");
            mytoast.show();
        }
    }

}
