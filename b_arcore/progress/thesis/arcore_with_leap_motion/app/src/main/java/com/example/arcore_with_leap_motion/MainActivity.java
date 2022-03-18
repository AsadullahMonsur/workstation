package com.example.arcore_with_leap_motion;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.arcore_with_leap_motion.authentication.Sign_In;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        move_to_sign_in();
    }

    private void move_to_sign_in(){
        try{
            Intent intent = new Intent(this, Sign_In.class);
            startActivity(intent);
        }
        catch (Exception ex){
            Toast.makeText(this,
                 "Error in Loading Sign_In",Toast.LENGTH_LONG).show();
        }
    }
}
