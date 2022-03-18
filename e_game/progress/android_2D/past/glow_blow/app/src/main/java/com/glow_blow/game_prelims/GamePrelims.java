package com.glow_blow.game_prelims;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.glow_blow.R;
import com.glow_blow.game_menu.GameMenu;

public class GamePrelims extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_prelims);

        show_notification("Game Prelims Loaded");
        switch_activity();
    }

    private void switch_activity(){
        Intent in = new Intent(this, GameMenu.class);
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
