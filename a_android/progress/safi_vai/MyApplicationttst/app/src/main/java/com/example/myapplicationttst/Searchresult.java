package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Searchresult extends AppCompatActivity {
    Button home;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult);

        home=findViewById(R.id.button5home);





    }

    public void confirm_home6(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }

    public void confirm_lostfndpage(View view) {
        Intent in = new Intent(this, Postlogin.class);
        startActivity(in);
        finish();
    }
}
