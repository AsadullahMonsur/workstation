package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Login extends AppCompatActivity {
    EditText name;
    EditText pass;
    Button login;
    Button home;

 @Override
protected void onCreate(Bundle savedInstanceState) {
     super.onCreate(savedInstanceState);
     setContentView(R.layout.login);

     name=findViewById(R.id.editText);
     pass=findViewById(R.id.editText2pas);
     login=findViewById(R.id.buttonlogin);
     home=findViewById(R.id.buttonhome);

 }

    public void confirm_home(View view) {
        Intent in = new Intent(this, MainActivity.class);
       // Intent in = new Intent(this, Postlogin.class);
        startActivity(in);
        finish();
    }

    public void confirm_postloggin(View view) {
        //Intent in = new Intent(this, MainActivity.class);
        Intent in = new Intent(this, Postlogin.class);
        startActivity(in);
        finish();
    }
}
