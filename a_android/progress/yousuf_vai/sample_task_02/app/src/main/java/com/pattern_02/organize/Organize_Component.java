package com.pattern_02.organize;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern_02.R;

public class Organize_Component extends AppCompatActivity {
    private Button add_component_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_a_id_manager);
        add_component_btn = findViewById(R.id.c_a_id_manager_port_back_btn);
        add_component_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_result();
            }
        });
    }
    private void send_result() {
        Intent intent = new Intent();
        intent.putExtra("editTextValue", "value_here");
        setResult(RESULT_OK, intent);
        finish();
    }
}
