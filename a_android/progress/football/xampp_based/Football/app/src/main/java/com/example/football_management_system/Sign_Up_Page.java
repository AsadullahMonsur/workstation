package com.example.football_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import java.sql.Connection;

public class Sign_Up_Page extends AppCompatActivity{

    TextInputEditText f_name;
    TextInputEditText l_name;
    TextInputEditText u_email;
    TextInputEditText u_pass;
    TextInputEditText c_pass;
    Toast mytoast;
    Toast mytoast2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

           f_name = findViewById(R.id.f_name);
           l_name = findViewById(R.id.l_name);
           u_email = findViewById(R.id.u_email);
           u_pass =  findViewById(R.id.u_pass);
           c_pass =  findViewById(R.id.c_pass);

           mytoast2 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
           mytoast = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);

           mytoast.setText("Please fill up input fields !");
           mytoast.show();
    }

    public void submitAction(View view) throws Exception {

        Boolean result = isValidEmail(u_email.getText().toString());


        if(result ==true && f_name.getText().toString().trim().length() > 0 &&
           l_name.getText().toString().trim().length() > 0 &&
           u_email.getText().toString().trim().length() > 0 &&
           u_pass.getText().toString().trim().length() > 0 &&
           c_pass.getText().toString().trim().length() > 0 &&
           (u_pass.getText().toString()).equals(c_pass.getText().toString())  ){

            Sign_Up_Data supd = new Sign_Up_Data(f_name.getText().toString(),
                               l_name.getText().toString(),u_email.getText().toString(),
                               u_pass.getText().toString(), mytoast,mytoast2);

            Sign_Up_DB supdb = new Sign_Up_DB(supd);

            Thread tt = new Thread(supdb);
            tt.start();

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
