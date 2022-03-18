package com.example.house_rent_and_payment.opening_appearance;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.user_authentication.Sign_In_Page;

public class Opening_Appearance extends AppCompatActivity implements View.OnClickListener {

    CountDownTimer ct ;
    Intent in;

  //  RelativeLayout opening_ap_layout;
    ImageView opening_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_appearance_layout);
    //    opening_ap_layout = findViewById(R.id.opening_ap_layout);

        opening_image = (ImageView) findViewById(R.id.opening_image);
        Animation startRotateAnimation = AnimationUtils.loadAnimation(this, R.anim.opening_animation);
        opening_image.startAnimation (startRotateAnimation);

        final Handler handler = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {

                ct = new CountDownTimer(4000, 900) {

                    @Override
                    public void onTick(long millisUntilFinished) {
//            if(millisUntilFinished>3000){
//                opening_ap_layout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.Green4));
//               }
//            else if(millisUntilFinished>2100){
//                opening_ap_layout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.Blue4));
//            }
//            else if(millisUntilFinished>1200){
//                opening_ap_layout.setBackgroundColor(ContextCompat.getColor(getBaseContext(), R.color.TextColor));
//            }

            }
                    @Override
                    public void onFinish() {
                        in = new Intent(getApplicationContext(), Sign_In_Page.class);
                        startActivity(in);
                        finish();
                    }
                }.start();
            }
        };

            Thread updateUi = new Thread() {
                public void run() {
                    try {
                        handler.sendEmptyMessage(0);

                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("failed", "exception");

                    }
                }
            };
            updateUi.start();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}