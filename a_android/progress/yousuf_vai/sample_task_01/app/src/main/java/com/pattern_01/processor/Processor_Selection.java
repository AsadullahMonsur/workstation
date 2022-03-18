package com.pattern_01.processor;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern_01.R;

public class Processor_Selection extends AppCompatActivity {
    private Button organise_btn;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_processor_selection);
        organise_btn = findViewById(R.id.b_processor_selection_card_04_btn);
        organise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_to_next_activity();
            }
        });
    }

    private void move_to_next_activity() {
        Intent in = new Intent(this,Processor_List.class);
        this.startActivity(in);
    }
}
