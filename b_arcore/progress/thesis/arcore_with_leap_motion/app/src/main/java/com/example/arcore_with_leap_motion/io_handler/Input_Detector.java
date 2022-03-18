package com.example.arcore_with_leap_motion.io_handler;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.arcore_with_leap_motion.R;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Input_Detector extends AppCompatActivity {
    private Button left;
    private Button right;

    private FirebaseDatabase dB;
    private DatabaseReference dBR;// main root
    private DatabaseReference dBCR; // child root

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_input_detector_layout);

        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference();
        dBCR = dBR.child("input");

        left = findViewById(R.id.left_btn);
        right = findViewById(R.id.right_btn);

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choice choice = new Choice(1);
                dBCR.setValue(choice).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        notification("Value updated");
                    }
                });
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Choice choice = new Choice(2);
                dBCR.setValue(choice).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        notification("Value updated");
                    }
                });
            }
        });
    }
    private void notification(String s) {
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
