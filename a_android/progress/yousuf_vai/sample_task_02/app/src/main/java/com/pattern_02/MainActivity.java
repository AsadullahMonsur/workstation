package com.pattern_02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.pattern_02.home_page.Home;
import com.pattern_02.opener.Opener_Animation;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = new Intent(this, Opener_Animation.class);
        this.startActivity(intent);
        finish();
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}