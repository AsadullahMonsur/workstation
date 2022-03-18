package com.example.house_rent_and_payment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.house_rent_and_payment.opening_appearance.Opening_Appearance;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent in = new Intent(this, Opening_Appearance.class);
        this.startActivity(in);
        finish();
    }
}
