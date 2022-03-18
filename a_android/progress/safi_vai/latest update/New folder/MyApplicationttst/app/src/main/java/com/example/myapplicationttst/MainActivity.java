package com.example.myapplicationttst;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
