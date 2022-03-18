package com.pattern_02.home_page;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern_02.R;
import com.pattern_02.customization.Customization;
import com.pattern_02.organize.Organize_Component;
import com.pattern_02.settings.Settings;
import com.pattern_02.tests.Test_01;

public class Home extends AppCompatActivity {
    private ImageView settings_btn;
    private TextView organise_btn;
    private RecyclerView recyclerView;
    private Home_List_Adapter adapter;
    private String []data = {"No elements Added","No elements Added",
                             "No elements Added","No elements Added",
                             "No elements Added","No elements Added"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_a_main_view);
        settings_btn = findViewById(R.id.b_a_main_view_port_02_img);
        organise_btn = findViewById(R.id.b_a_main_view_port_03_tv);
        recyclerView = findViewById(R.id.b_a_main_view_port_04_recycle);

        adapter = new Home_List_Adapter(this,data);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();

        settings_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_to_settings();
            }
        });

        organise_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                move_to_organize_component();
            }
        });
    }

    private void move_to_organize_component() {
        Intent intent = new Intent(this, Organize_Component.class);
        ((Activity)this).startActivityForResult(intent,2);
    }

    private void move_to_settings() {
        Intent intent = new Intent(this, Settings.class);
        ((Activity)this).startActivityForResult(intent,1);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                notification("Change Code 1");
                Test_01 t = new Test_01(this);
            }
        }
        if (requestCode == 2) {
            if(resultCode == RESULT_OK) {
                notification("Change Code 2");
            }
        }
        if (requestCode == 3) {
            if(resultCode == RESULT_OK) {
                notification("Change Code 3");

            }
        }
    }
    private void notification(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
