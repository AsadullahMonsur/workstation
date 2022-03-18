package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Foundpage extends AppCompatActivity {
    EditText prod;
    EditText loc;
    EditText imei;
    Button enter;
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foundpage);

        prod=findViewById(R.id.editTextPD);;
        loc=findViewById(R.id.editText2Loc);;
        imei=findViewById(R.id.editText3imei);;
        enter=findViewById(R.id.buttonenter);;
        home=findViewById(R.id.button2home);;




    }

    public void confirm_home3(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }
}
