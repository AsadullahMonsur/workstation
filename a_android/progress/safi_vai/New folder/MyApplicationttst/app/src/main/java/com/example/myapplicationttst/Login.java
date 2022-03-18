package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText u_email;
    EditText pass;

    Toast mytoast;
    Toast mytoast2;
    Toast mytoast3;

    static int myChecker[];

 @Override
protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.login);

     u_email=findViewById(R.id.editText);
     pass=findViewById(R.id.editText2pas);

     mytoast3 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
     mytoast2 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
     mytoast = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);

     mytoast.setText("Please fill up input fields !");
     mytoast.show();
     myChecker = new int[1];
     myChecker[0] = 0;

 }

    public void confirm_home(View view) {
        Intent in = new Intent(this, MainActivity.class);
       // Intent in = new Intent(this, Postlogin.class);
        startActivity(in);
        finish();
    }




    public void confirm_postloggin(View view) {



        Boolean result = isValidEmail(u_email.getText().toString());


        if(result ==true &&
                pass.getText().toString().trim().length() > 0)
        {

            Log_In_Data lid = new Log_In_Data( u_email.getText().toString(),pass.getText().toString(),mytoast,mytoast2,myChecker);

            Log_In_DB ldb = new Log_In_DB(lid);

            Thread tt = new Thread(ldb);
            tt.start();

            Thread match = new Thread(){
                public void run() {
                    try {
                        Thread.sleep(4000);
                        if(myChecker[0]==1){
                            Intent in = new Intent(getBaseContext(),Postlogin.class);
                            startActivity(in);
                            myChecker[0]=0;
                        }
                        else{
                            Thread.sleep(4000);
                            if(myChecker[0]==1){
                                Intent in = new Intent(getBaseContext(),Postlogin.class);
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
            mytoast.setText("Check your info again!");
            mytoast.show();
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
