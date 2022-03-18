package com.example.house_rent_and_payment.user_authentication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.others.Image_File;
import com.example.house_rent_and_payment.user_credentials.User_Info;
import com.example.house_rent_and_payment.user_credentials.User_Personal_Info;
import com.example.house_rent_and_payment.user_credentials.User_Profile;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.File;

public class Sign_Up_Page extends AppCompatActivity{

    private TextInputEditText f_name;
    private TextInputEditText l_name;
    private TextInputEditText u_email;
    private TextInputEditText u_pass;
    private TextInputEditText c_pass;
    private Button sign_up_btn;

    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR;// main root
    private DatabaseReference dBCR; // child root

    private Toast sign_up_confirm;
    private Toast notify;
    private Toast notify_01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sign_up_layout);

        f_name = findViewById(R.id.a_first_name_sign_up_input_edit_txt);
        l_name = findViewById(R.id.a_last_name_sign_up_input_edit_txt);
        u_email = findViewById(R.id.a_user_mail_sign_up_input_edit_txt);
        u_pass = findViewById(R.id.a_user_sign_up_password_input_edit_txt);
        c_pass = findViewById(R.id.a_user_sign_up_confirm_password_input_edit_txt);
        sign_up_btn = findViewById(R.id.sign_up_button);

        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference(); // main root
        dBCR = dBR.child("users");

        sign_up_confirm = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        notify = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        notify_01 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);

        notify.setText("Please, fill up the form !");
        notify.show();
    }

    public void Shift_To_Sign_In(View view) {
        Intent in = new Intent(getBaseContext(), Sign_In_Page.class);
        startActivity(in);
        finish();
    }

    public void Sign_Up_Button(View view) {

      final Handler handler_01 = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {
          Boolean result = isValidEmail(u_email.getText().toString());

          if(result ==true && f_name.getText().toString().trim().length() > 0 &&
              l_name.getText().toString().trim().length() > 0 &&
              u_email.getText().toString().trim().length() > 0 &&
              u_pass.getText().toString().trim().length() > 5 &&
              c_pass.getText().toString().trim().length() > 5 &&
              (u_pass.getText().toString()).equals(c_pass.getText().toString())){

            fA.fetchSignInMethodsForEmail(u_email.getText().toString().trim())
              .addOnCompleteListener(new OnCompleteListener<SignInMethodQueryResult>() {

              @Override
              public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {
                boolean check = task.getResult().getSignInMethods().isEmpty();
                  if (check) {
                    try{
                       fA.createUserWithEmailAndPassword(u_email.getText().toString()
                          .trim(),u_pass.getText().toString().trim())
                       .addOnCompleteListener(Sign_Up_Page.this,
                       new OnCompleteListener<AuthResult>() {

                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()) {
                         String id = fA.getCurrentUser().getUid();

                         Image_File image_file = new Image_File("unknown","unknown");
                         User_Personal_Info userA = new User_Personal_Info(
                                 f_name.getText().toString(),
                                 l_name.getText().toString(),
                                 u_email.getText().toString(),
                                "empty","empty",
                                 image_file);

                         User_Info userB = new User_Info(id, name_generator(12),
                                 u_pass.getText().toString(),
                                 0,0);

                         User_Profile user_profile = new User_Profile(userB,userA);
                         dBCR.child(id).setValue(user_profile);

                         sign_up_confirm.setText("Successful !!");
                         sign_up_confirm.show();

                         Intent in = new Intent(getBaseContext(), Sign_In_Page.class);
                         startActivity(in);
                         finish();
                         }
                         else {
                          sign_up_confirm.setText("Failed. Recheck info & try again !!");
                          sign_up_confirm.show();
                         }
                       }
                       });
                    }
                    catch (Exception ex){
                        sign_up_confirm.setText("Failed !!");
                        sign_up_confirm.show();
                        ex.printStackTrace();
                     }
                  }
                  else {
                     sign_up_confirm.setText("Email Already Exists !!");
                     sign_up_confirm.show();
                     u_email.getText().clear();
                     u_email.setError("Previous email has been taken !!");
                     u_email.requestFocus();
                  }
              }
           });
          }
          else if(result==false){
              u_email.setError("Enter a valid email ~~~");
              u_email.requestFocus();
              notify_01.setText("Invalid Email !!");
              notify_01.show();
          }
          else if(u_pass.getText().toString().length()<6){
              u_pass.setError("Enter a valid password ~~~");
              u_pass.requestFocus();
              notify_01.setText("Minimum Length of Password is 6 !!");
              notify_01.show();
            }
          else {
              notify_01.setText("Failed. Recheck your info again !!");
              notify_01.show();
          }
        }
      };

      Thread updateUi = new Thread() {
            public void run() {
           try {
             //  Thread.sleep(100);
               handler_01.sendEmptyMessage(0);
           }
           catch (Exception e) {
               e.printStackTrace();
               Log.d("failed", "exception");
           }
         }
      };

        updateUi.start();
        f_name.invalidate();
        l_name.invalidate();
        u_email.invalidate();
        u_pass.invalidate();
        c_pass.invalidate();

    }

    private final boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        }
        else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    private final String name_generator(int n){

        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                     + "0123456789"
                                     + "abcdefghijklmnopqrstuvxyz";

        StringBuilder sb = new StringBuilder(n);

        for (int i = 0; i < n; i++) {
            int index = (int)(AlphaNumericString.length() * Math.random());
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
