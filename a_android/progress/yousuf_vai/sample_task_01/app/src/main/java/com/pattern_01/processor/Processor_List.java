package com.pattern_01.processor;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern_01.R;
import com.pattern_01.display.Display_List;

public class Processor_List  extends AppCompatActivity {
    private Processor_List_Adapter adapter;
    private RecyclerView recyclerView;
    private Button add_btn;
    private String []process = {"No elements Added","No elements Added"};

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_a_processor_list);
        add_btn = findViewById(R.id.c_a_processor_list_port_01_btn);
        recyclerView = findViewById(R.id.c_a_processor_list_recycle);

        adapter = new Processor_List_Adapter(this,process);
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
              notification("Whooo ho ho");
                add_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        go_to_next_activity();
                    }
                });
            }
        }
    }

    private void go_to_next_activity() {
        Intent intent = new Intent(this, Display_List.class);
        startActivity(intent);
        finish();
    }

    private void notification(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
