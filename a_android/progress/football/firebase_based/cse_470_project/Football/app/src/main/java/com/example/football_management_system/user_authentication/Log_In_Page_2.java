package com.example.football_management_system.user_authentication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.football_management_system.R;
import com.example.football_management_system.accessories.User_Info;
import com.example.football_management_system.manager.Manager_Home;
import com.example.football_management_system.owner.Owner_Home;
import com.example.football_management_system.player.Player_Home;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Log_In_Page_2 extends AppCompatActivity {

    private TextInputEditText uID;
    private TextInputEditText pass;
    private TextInputLayout userId;
    private TextInputLayout password;
    private String identifier = "empty";
    private Button log_in_btn;

    private int checker = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout_2);

        Intent in = getIntent();
        Bundle bu = in.getExtras();
        identifier= bu.getString("logger");

        uID      = findViewById(R.id.uID);
        pass     = findViewById(R.id.Upass);
        userId   = findViewById(R.id.UserId);
        password = findViewById(R.id.UserPass);
        log_in_btn = findViewById(R.id.sign_in_button_2);

        notification("Please fill up input fields !");
        try {

          log_in_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checker = 0;
                log_in();
            }
         });
        }
         catch (Exception exception){
            notification("Error");
            exception.printStackTrace();
         }
    }

    public void passwordForgot(View view) {
    }

    public void log_in() {

        if(uID.getText().toString().trim().length()>0 && pass.getText().toString().trim().length()>5){
            userId.setBoxStrokeColor(Color.GREEN);
            password.setBoxStrokeColor(Color.GREEN);

            check_log_in_info(uID.getText().toString()
                    ,pass.getText().toString());

        }
        else{
            if (uID.getText().toString().trim().length() <= 0) {
              userId.setBoxStrokeColor(Color.RED);
              notification("Invalid ID!");
            }
            else {
              userId.setBoxStrokeColor(Color.GREEN);
            }

            if (pass.getText().toString().trim().length() <= 5) {
              password.setBoxStrokeColor(Color.RED);
              notification("Invalid Password");
            }
            else {
              password.setBoxStrokeColor(Color.GREEN);
            }
        }
    }

    private void check_log_in_info(String s1, String s2) {
      DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
      ref.child("official_users")
         .orderByChild("id")
         .equalTo(s1)
         .addValueEventListener(new ValueEventListener() {

         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
           if (dataSnapshot.exists()) {
             for (DataSnapshot dataSnapshots : dataSnapshot.getChildren()) {

               if(identifier.equals("owner")){
                   Log_In_Page_2_Data lipd = dataSnapshots.getValue(Log_In_Page_2_Data.class);
                   if ((lipd.getPassword().equals(s2) && checker == 0)) {
                       move_to_homepage(identifier);
                       notification("Go for, yeah");
                       checker = 2;
                   }
                   else {
                       notification("Password is Incorrect");
                   }
               }
               else {
                   User_Info lipd = dataSnapshots.getValue(User_Info.class);
                   if ((lipd.getPassword().equals(s2) && checker == 0)) {
                       move_to_homepage(identifier);
                       notification("Go for, yeah");
                       checker = 2;
                   }
                   else {
                       notification("Password is Incorrect");
                   }
               }
             }
           }
           else {
              checker = 1;
              notification("Could not identify this user !!");
           }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
            notification("Database Error");
         }
      });
    }

    private void move_to_homepage(String identifier){
        Intent in;
        if(identifier.equals("owner")){
            in = new Intent(this, Owner_Home.class);
            in.putExtra("identifier",uID.getText().toString());
            startActivity(in);
            finish();
        }
        else if(identifier.equals("manager")){
            in = new Intent(this, Manager_Home.class);
            in.putExtra("identifier",uID.getText().toString());
            startActivity(in);
            finish();
        }
        else if(identifier.equals("player")){
            in = new Intent(this, Player_Home.class);
            in.putExtra("identifier",uID.getText().toString());
            startActivity(in);
            finish();
        }
        else {
            notification("No identifier matched");
        }
    }
    private void notification(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
