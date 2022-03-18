package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {


    EditText name;
    EditText email;
    EditText pass;
    EditText loc;
    EditText contact;
    Button register;
    Button home;
    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.register);

    name= findViewById(R.id.editText2);
    email=findViewById(R.id.editText3);
    pass=findViewById(R.id.editText4);
    loc=findViewById(R.id.editText5);
    contact=findViewById(R.id.editText6);
    register=findViewById(R.id.buttonregister);
    home=findViewById(R.id.button2);


}


    public void confirm_home4(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();

    }

    public void gotologin(View view) {
        Intent in = new Intent(this, Login.class);
        startActivity(in);
        finish();
    }
}
