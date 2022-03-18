package com.arcore_3d_basics.launcher;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.arcore_3d_basics.R;
import com.arcore_3d_basics.options.OperationMenu;

public class Launcher extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.launcher_activity);
        show_notification("preparing operation menu",0);
        shift_activity();
    }

    private void shift_activity(){
        Intent in = new Intent(getApplicationContext(), OperationMenu.class);
        startActivity(in);
        finish();
    }

    private void show_notification(String s,int length){
        Toast.makeText(this,s,length).show();
    }
}
