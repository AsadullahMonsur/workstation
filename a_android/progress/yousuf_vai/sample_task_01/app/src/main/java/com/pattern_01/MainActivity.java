package com.pattern_01;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.pattern_01.processor.Processor_Selection;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.main_activity_btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_to_next_activity();
            }
        });
    }

    public void move_to_next_activity(){
        Intent in = new Intent(this, Processor_Selection.class);
        this.startActivity(in);
    }
}