package com.example.football_management_system.user_authentication;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.football_management_system.R;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Sign_Up_Page extends AppCompatActivity {

    private TextInputEditText f_name;
    private TextInputEditText l_name;
    private TextInputEditText u_email;
    private TextInputEditText u_pass;
    private TextInputEditText c_pass;
    private Button submit_btn;

    private int checker = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up_page);

           f_name = findViewById(R.id.f_name);
           l_name = findViewById(R.id.l_name);
           u_email = findViewById(R.id.u_email);
           u_pass =  findViewById(R.id.u_pass);
           c_pass =  findViewById(R.id.c_pass);
           submit_btn = findViewById(R.id.Submit);

           notification("Please fill up the form");
           submit_btn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   checker = 0;
                   submit_action();
               }
           });
    }

    private void submit_action(){

        boolean result1 = is_valid_email(u_email.getText().toString());
        boolean result3 = form_validity();

        if (result1 && result3){
            is_email_exists();
        }
        else{
            notification("Check your info again!");
        }
        f_name.invalidate();
        l_name.invalidate();
        u_email.invalidate();
        u_pass.invalidate();
        c_pass.invalidate();
    }

    private void is_email_exists() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("general_users")
           .orderByChild("email")
           .equalTo(u_email.getText().toString())
           .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
              if (dataSnapshot.exists()) {
                 for(DataSnapshot dataSnapshots: dataSnapshot.getChildren()){
                  Sign_Up_Data sign_up_data = dataSnapshots.getValue(Sign_Up_Data.class);
                  if ((sign_up_data.getEmail().equals(u_email.getText().toString()) && checker==0)) {
                      notification("This Email Already Taken. Insert New.");
                  }
                 }
              }
              else {
                  checker = 1;
                  Sign_Up_Data supd = new Sign_Up_Data(f_name.getText().toString(),
                          l_name.getText().toString(), u_email.getText().toString(),
                          u_pass.getText().toString());

                  DatabaseReference dBR = FirebaseDatabase.getInstance().getReference();
                  dBR.child("general_users")
                          .child(dBR.push().getKey())
                          .setValue(supd)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                              @Override
                              public void onComplete(@NonNull Task<Void> task) {
                                  notification("Successfully saved");
                              }
                          }).addOnCanceledListener(new OnCanceledListener() {
                      @Override
                      public void onCanceled() {
                          notification("Uploading failed");
                      }
                  });
              }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
              notification("Database Error");
            }
        });

    }

    private void notification(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

    private boolean form_validity(){
        if(f_name.getText().toString().trim().length() > 0 &&
                l_name.getText().toString().trim().length() > 0 &&
                u_email.getText().toString().trim().length() > 0 &&
                u_pass.getText().toString().trim().length() > 5 &&
                c_pass.getText().toString().trim().length() > 5 &&
                (u_pass.getText().toString()).equals(c_pass.getText().toString())  ){
            return true;
        }
        return false;
    }

    private static boolean is_valid_email(CharSequence target) {
        if (target == null) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
