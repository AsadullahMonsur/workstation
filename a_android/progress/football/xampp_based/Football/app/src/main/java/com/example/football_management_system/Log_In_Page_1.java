package com.example.football_management_system;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class Log_In_Page_1 extends AppCompatActivity {

    TextInputEditText um;
    TextInputEditText pass;

    TextInputLayout usermail;
    TextInputLayout password;

    Toast mytoast;
    Toast mytoast2;
    Toast mytoast3;
    static int myChecker[];
    static String profile[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout);
        um = findViewById(R.id.um);
        pass = findViewById(R.id.pass);
        usermail = findViewById(R.id.UserMail);
        password = findViewById(R.id.UserPassword);

        mytoast3 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast.setText("Please fill up input fields !");
        mytoast.show();

        myChecker = new int[1];
        profile = new String[2];

        myChecker[0] = 0;

    }

    public void passwordForgot(View view) {
    }

    public void LogInButton(View view) {

        Boolean result = isValidEmail(um.getText().toString());

        if(result!=false && pass.getText().toString().trim().length()>0){
            usermail.setBoxStrokeColor(Color.GREEN);
            password.setBoxStrokeColor(Color.GREEN);

            Log_In_Page_1_Data lipd = new Log_In_Page_1_Data(um.getText().toString(),
                                         pass.getText().toString(),mytoast,mytoast2,myChecker,profile);

            Log_In_Page_1_DB lipdb  = new Log_In_Page_1_DB(lipd);

            Thread tt = new Thread(lipdb);
            tt.start();

            Thread match = new Thread(){
                public void run() {
                    try {
                        Thread.sleep(4000);
                        if(myChecker[0]==1){
                            Intent in = new Intent(getBaseContext(),Viewer_Home.class);
                            in.putExtra("user_mail",profile[0]);
                            startActivity(in);
                            myChecker[0]=0;
                        }
                        else{
                            Thread.sleep(4000);
                            if(myChecker[0]==1){
                                Intent in = new Intent(getBaseContext(),Viewer_Home.class);
                                in.putExtra("user_mail",profile[0]);
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
           if(result==false) {
               usermail.setBoxStrokeColor(Color.RED);
               mytoast.setText("Invalid email !");
               mytoast.show();
           }
           else{
               usermail.setBoxStrokeColor(Color.GREEN);
           }

           if(pass.getText().toString().trim().length()<=0){
               password.setBoxStrokeColor(Color.RED);
               mytoast.setText("Please enter password !");
               mytoast.show();
           }
           else{
               password.setBoxStrokeColor(Color.GREEN);
           }
          }
    }

    public static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}

