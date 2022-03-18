package com.pattern_01.components;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern_01.R;
import com.pattern_01.processor.Processor_List;

public class Component_Selection extends AppCompatActivity {

    private Button organise_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_component_selection);
        organise_btn = findViewById(R.id.d_component_selection_card_04_btn);
        organise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_to_next_activity();
            }
        });

    }

    private void move_to_next_activity() {
        Intent intent = new Intent();
        intent.putExtra("editTextValue", "value_here");
        setResult(RESULT_OK, intent);
        finish();
    }
}
