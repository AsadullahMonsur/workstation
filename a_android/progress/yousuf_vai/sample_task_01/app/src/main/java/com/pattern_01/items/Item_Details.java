package com.pattern_01.items;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern_01.R;

public class Item_Details extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.h_item_details);
        RelativeLayout layout = findViewById(R.id.h_item_details_port_01);
        layout.setOnClickListener(new View.OnClickListener() {
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
