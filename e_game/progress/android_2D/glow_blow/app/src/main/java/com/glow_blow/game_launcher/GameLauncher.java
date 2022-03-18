package com.glow_blow.game_launcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.glow_blow.R;
import com.glow_blow.game_prelims.GamePrelims;

public class GameLauncher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_launcher);
        show_notification("Loading Prelims");
        switch_activity();
    }

    private void switch_activity(){
       Intent in = new Intent(this, GamePrelims.class);
       startActivity(in);
       finish();
    }

    private void show_notification(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    private void show_notification_long(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}