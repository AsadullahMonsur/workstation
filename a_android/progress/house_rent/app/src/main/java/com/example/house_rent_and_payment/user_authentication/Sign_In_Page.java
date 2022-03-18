package com.example.house_rent_and_payment.user_authentication;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.user_home_main.User_Home_Page_Main;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Sign_In_Page extends AppCompatActivity{

   private static final String TAG = "GoogleActivity";
   private static final int RC_SIGN_IN = 5459;

   private GoogleSignInClient gSIC;
   private FirebaseAuth fA;

   private Toast notify_00;
   private Toast notify_01;
   private Toast notify_02;
   private Toast notify_03;

   private TextInputEditText user_email;
   private TextInputEditText user_password;
   private TextView forgot_password;

   @Override
   protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_sign_in_layout);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(
                GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        gSIC = GoogleSignIn.getClient(this, gso);

        fA = FirebaseAuth.getInstance();

        notify_00 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        notify_01 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        notify_02 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        notify_03 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);

       notify_00.setText("Please, fill up the form !");
       notify_00.show();

       user_email = findViewById(R.id.a_user_mail_sign_in_input_edit_txt);
       user_password = findViewById(R.id.a_user_sign_in_password_input_edit_txt);
       forgot_password = findViewById(R.id.forgotPass);

   }

    public void Password_Forgot(View view) {

    }

    public void Log_In_Button(View view) {
//        Intent in = new Intent(getBaseContext(), User_Home_Page_Main.class);
//        startActivity(in);

        final Handler handler_01 = new Handler(Looper.getMainLooper()) {
        public void handleMessage(Message msg) {

            Boolean result = isValidEmail(user_email.getText().toString());

        if(result==false || user_password.getText().toString().equals("") || user_password.getText().toString().length()<6){
            notify_01.setText("Log in failed. Check info & retry !!");
            notify_01.show();

            if(result==false){
                user_email.setError("Enter a valid email ~~~");
                user_email.requestFocus();
            }
            else{
                user_password.setError("Enter Correct Password ~~~");
                user_password.requestFocus();
            }
        }
        else{
            fA.signInWithEmailAndPassword(user_email.getText().toString().trim(),user_password.getText().toString().trim())
              .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                  @Override
                  public void onComplete(@NonNull Task<AuthResult> task) {
                      if(task.isSuccessful()){
                          notify_03.setText("~~ Successfully Logged In ~~");
                          notify_03.show();
                          Intent in = new Intent(getBaseContext(), User_Home_Page_Main.class);
                          startActivity(in);
                      }
                      else {
                          notify_03.setText("Log in failed. Check info & retry !!");
                          notify_03.show();
                      }
                  }
            });
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
        user_email.invalidate();
        user_password.invalidate();
    }

    public void Shift_To_Sign_Up(View view) {
        Intent in = new Intent(getBaseContext(), Sign_Up_Page.class);
        startActivity(in);
    }

    private final boolean isValidEmail(CharSequence target) {
        if (target == null) {
            return false;
        }
        else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }
}
