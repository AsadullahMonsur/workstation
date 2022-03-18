package com.example.football_management_system.user_authentication;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.football_management_system.R;
import com.example.football_management_system.general_user.Viewer_Home;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Log_In_Page_1 extends AppCompatActivity {

    private TextInputEditText um;
    private TextInputEditText pass;

    private TextInputLayout usermail;
    private TextInputLayout password;
    private Button log_in_btn;
    private int checker = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_layout);

        um = findViewById(R.id.um);
        pass = findViewById(R.id.pass);
        usermail = findViewById(R.id.UserMail);
        password = findViewById(R.id.UserPassword);
        log_in_btn = findViewById(R.id.sign_in_button);
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
        catch (Exception ex){
            notification("Error");
            ex.printStackTrace();
        }
    }

    public void passwordForgot(View view) {
    }

    public void log_in() {
        boolean result1 = isValidEmail(um.getText().toString());

        if(result1 && pass.getText().toString().trim().length()>5){
          usermail.setBoxStrokeColor(Color.GREEN);
          password.setBoxStrokeColor(Color.GREEN);

          check_log_in_info(um.getText().toString()
                    ,pass.getText().toString());
        }
        else{
           if(!result1) {
               usermail.setBoxStrokeColor(Color.RED);
               notification("Invalid email !");
           }
           else{
               usermail.setBoxStrokeColor(Color.GREEN);
           }

           if(pass.getText().toString().trim().length()<6){
               password.setBoxStrokeColor(Color.RED);
               notification("Please enter password !");
           }
           else{
               password.setBoxStrokeColor(Color.GREEN);
           }
          }

        um.invalidate();
        pass.invalidate();
        usermail.invalidate();
        password.invalidate();
    }

    private void check_log_in_info(String s1, String s2) {
      DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
      ref.child("general_users")
         .orderByChild("email")
         .equalTo(s1)
         .addValueEventListener(new ValueEventListener() {

         @Override
         public void onDataChange(DataSnapshot dataSnapshot) {
             if (dataSnapshot.exists()) {
                 for (DataSnapshot dataSnapshots : dataSnapshot.getChildren()) {
                     Sign_Up_Data sign_up_data = dataSnapshots.getValue(Sign_Up_Data.class);
                     if ((sign_up_data.getPassword().equals(s2) && checker == 0)) {
                         move_to_viewer_hompage(um.getText().toString());
                     }
                     else {
                         notification("Password is Incorrect");
                     }
                 }
             } else {
                 checker = 1;
                 notification("Could not identify this Email !!");
             }
         }

         @Override
         public void onCancelled(DatabaseError databaseError) {
                 notification("Database Error");
         }
      });
    }

    private static boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private void notification(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private void move_to_viewer_hompage(String s){
        Intent in = new Intent(this, Viewer_Home.class);
        in.putExtra("user_mail", s);
        startActivity(in);
        finish();
    }

}

