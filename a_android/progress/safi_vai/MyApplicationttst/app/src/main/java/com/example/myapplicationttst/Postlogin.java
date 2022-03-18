package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Postlogin  extends AppCompatActivity {
    Button lostpage;
    Button foundpage;
    Button home;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.postlogin);
        lostpage=findViewById(R.id.lostp11);;
        foundpage=findViewById(R.id.found123);;
        home=findViewById(R.id.Home123);;
        logout=findViewById(R.id.logootp);;


    }

    public void confirm_home10(View view) {
        Intent in = new Intent(this, Searchresult.class);
        startActivity(in);
        finish();
    }


    public void confirm_lostpage(View view) {
        Intent in = new Intent(this, Lostpage.class);
        startActivity(in);
        finish();
    }

    public void foundppage_cofimrm(View view) {
        Intent in = new Intent(this, Foundpage.class);
        startActivity(in);
        finish();
    }
}