package com.pattern_01.display;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern_01.R;
import com.pattern_01.items.Item_List_Adapter;

public class Display_List  extends AppCompatActivity {
    private Display_List_Adapter adapter;
    private RecyclerView recyclerView;
    private String[] content = {"No elements Added", "No elements Added"};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.e_a_display_list);
        recyclerView = findViewById(R.id.e_display_list_recycle);

        adapter = new Display_List_Adapter(this, content);
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
                notification("this business man, this is business");
            }
        }
    }
    private void notification(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}