package com.pattern_02.opener;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern_02.home_page.Home;

public class Opener_Animation extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        move_to_home();
    }

    private void move_to_home() {
        Intent intent = new Intent(this, Home.class);
        this.startActivity(intent);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
