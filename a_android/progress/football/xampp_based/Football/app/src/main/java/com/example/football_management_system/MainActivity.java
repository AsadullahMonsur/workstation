package com.example.football_management_system;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickIt(View view) {
        String bt =  ((Button) view).getText().toString();
        if(bt.equals("Owner")){

            Intent intent = new Intent(this,Log_In_Page_2.class);
            intent.putExtra("logger","owner");
            startActivity(intent);
        }
        else if(bt.equals("Manager")){
            Intent intent = new Intent(this,Log_In_Page_2.class);
            intent.putExtra("logger","manager");
            startActivity(intent);
        }
        else if(bt.equals("Player")){
            Intent intent = new Intent(this,Log_In_Page_2.class);
            intent.putExtra("logger","player");
            startActivity(intent);
        }
        else if(bt.equals("General User")){
            Intent intent = new Intent(this, Log_In_Page_1.class);
            startActivity(intent);
        }
        else if(bt.equals("Sign Up")){
            Intent intent = new Intent(this,Sign_Up_Page.class);
            startActivity(intent);
        }
        else{
        }
    }

}

