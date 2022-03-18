package com.example.myapplicationttst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {


  //  EditText et;
    Button b1;
    Button b2;
    Button b3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // et  = findViewById(R.id.editText2search);
        b1 =findViewById(R.id.login);
        b2 =findViewById(R.id.register);
        b3 =findViewById(R.id.buttonsearch);

    }

    public void confirm_login(View view) {
        Intent in = new Intent(this, Login.class);
        startActivity(in);
        finish();
    }

    public void confirm_register(View view) {
        Intent in = new Intent(this, Register.class);
        startActivity(in);
        finish();
    }

    public void confirm_searchbutton(View view) {
        Intent in = new Intent(this, Searchresult.class);
        startActivity(in);
        finish();
    }
}
