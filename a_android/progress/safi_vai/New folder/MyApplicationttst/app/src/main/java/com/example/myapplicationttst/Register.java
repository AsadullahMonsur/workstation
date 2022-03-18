package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import static com.example.myapplicationttst.Log_In_Data.mytoast;

public class Register extends AppCompatActivity {

    EditText name;
    EditText email;
    EditText pass;
    EditText location;
    EditText contact;

    Toast mytoast3;
    Toast mytoast2;
    Toast mytoast;

    int mychecker[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        name = findViewById(R.id.editText2);
        email = findViewById(R.id.editText3);
        pass = findViewById(R.id.editText4);
        location = findViewById(R.id.editText5);
        contact = findViewById(R.id.editText6);

        mytoast3 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);

        mytoast.setText("Please fill up input fields !");
        mytoast.show();
        mychecker = new int[1];
        mychecker[0] = 0;


    }


    public void confirm_home4(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();

    }

    public void gotologin(View view) {


        Boolean result = isValidEmail(email.getText().toString());


        if(result ==true && name.getText().toString().trim().length() > 0 &&
                pass.getText().toString().trim().length() > 0 &&
                location.getText().toString().trim().length() > 0 &&
                contact.getText().toString().trim().length() > 0)
        {

            Register_Data rtd = new Register_Data( name.getText().toString(),
                                email.getText().toString(),
                                pass.getText().toString(),
                                location.getText().toString(),
                                contact.getText().toString(),
                                mytoast,mytoast2,mychecker);

            Register_DB rdb = new Register_DB(rtd);

            Thread tt = new Thread(rdb);
            tt.start();

            Thread match = new Thread(){
                public void run() {
                    try {
                        Thread.sleep(4000);
                        if(mychecker[0]==1){
                            Intent in = new Intent(getBaseContext(),Login.class);
                            startActivity(in);
                            mychecker[0]=0;
                        }
                        else{
                            Thread.sleep(4000);
                            if(mychecker[0]==1){
                                Intent in = new Intent(getBaseContext(),Login.class);
                                startActivity(in);
                                mychecker[0]=0;
                            }
                            else{
                                mychecker[0]=0;
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
