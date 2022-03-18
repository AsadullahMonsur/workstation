package com.pattern_02.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.pattern_02.R;

public class Settings extends AppCompatActivity {
    private ImageView setting_btn;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_a_settings);
        setting_btn = findViewById(R.id.d_a_settings_card_01_img);
        setting_btn.setOnClickListener(new View.OnClickListener() {
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
