package com.pattern_01.items;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern_01.R;
import com.pattern_01.processor.Processor_List_Adapter;

public class Item_List extends AppCompatActivity {
    private Item_List_Adapter adapter;
    private RecyclerView recyclerView;
    private ImageView content_icon;
    private String []content = {"No elements Added","No elements Added"};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.g_a_item_list);
        content_icon = findViewById(R.id.g_a_item_list_port_01_img);
        recyclerView = findViewById(R.id.g_a_item_list_port_02_recycle);

        adapter = new Item_List_Adapter(this,content);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if(resultCode == RESULT_OK) {
                notification("lots of potato");
                content_icon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        move_to_next_activity();
                    }
                });
            }
        }
    }
    private void move_to_next_activity() {
        Intent intent = new Intent();
        intent.putExtra("editTextValue", "value_here");
        setResult(RESULT_OK, intent);
        finish();
    }
    private void notification(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
