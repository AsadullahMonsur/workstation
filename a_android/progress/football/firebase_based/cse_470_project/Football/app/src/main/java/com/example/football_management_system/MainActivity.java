package com.example.football_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.football_management_system.user_authentication.Log_In_Page_1;
import com.example.football_management_system.user_authentication.Log_In_Page_2;
import com.example.football_management_system.user_authentication.Sign_Up_Page;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void clickIt(View view) {
        try {
            String bt = ((Button) view).getText().toString();
            switch (bt) {
                case "Owner": {

                    Intent intent = new Intent(this, Log_In_Page_2.class);
                    intent.putExtra("logger", "owner");
                    startActivity(intent);
                    break;
                }
                case "Manager": {
                    Intent intent = new Intent(this, Log_In_Page_2.class);
                    intent.putExtra("logger", "manager");
                    startActivity(intent);
                    break;
                }
                case "Player": {
                    Intent intent = new Intent(this, Log_In_Page_2.class);
                    intent.putExtra("logger", "player");
                    startActivity(intent);
                    break;
                }
                case "General User": {
                    Intent intent = new Intent(this, Log_In_Page_1.class);
                    startActivity(intent);
                    break;
                }
                case "Sign Up": {
                    Intent intent = new Intent(this, Sign_Up_Page.class);
                    startActivity(intent);
                    break;
                }
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }
}

