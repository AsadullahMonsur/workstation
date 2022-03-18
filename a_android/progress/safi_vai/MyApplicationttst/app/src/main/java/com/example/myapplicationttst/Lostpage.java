package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Lostpage extends AppCompatActivity {
    EditText prod;
    EditText loc;
    EditText imei;
    Button enter;
    Button home;
    @Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
        setContentView(R.layout.lostpage);
        prod=findViewById(R.id.editTextPDa);;
        loc=findViewById(R.id.editText2Loca);;
        imei=findViewById(R.id.editText3imeia);;
        enter=findViewById(R.id.buttonentera);;
        home=findViewById(R.id.button2homea);;

}

    public void confirm_home2(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }
}
