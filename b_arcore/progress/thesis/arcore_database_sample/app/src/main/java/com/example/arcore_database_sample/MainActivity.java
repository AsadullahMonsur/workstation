package com.example.arcore_database_sample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.ar.core.AugmentedImage;
import com.google.ar.core.AugmentedImageDatabase;
import com.google.ar.core.Config;
import com.google.ar.core.Frame;
import com.google.ar.core.Session;
import com.google.ar.sceneform.ArSceneView;
import com.google.ar.sceneform.FrameTime;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;

public class MainActivity extends AppCompatActivity {

    private  Custom_AR_Fragment crf;
    private TextView tv;
    private Button btn;
    private AugmentedImageDatabase aid;

    private  Toast notify_00;
    private  Toast notify_01;
    private  Toast notify_02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        crf = (Custom_AR_Fragment) getSupportFragmentManager()
                .findFragmentById(R.id.arFragment);

        tv = findViewById(R.id.textView);

        notify_00 = Toast.makeText(this,null,Toast.LENGTH_LONG);
     //   notify_01 = Toast.makeText(this,null,Toast.LENGTH_LONG);
      //  notify_02 = Toast.makeText(this,null,Toast.LENGTH_LONG);

        crf.getArSceneView().getScene().addOnUpdateListener(this::onUpdate);

        btn = findViewById(R.id.btn);
        btn.setOnClickListener(v -> {

            if(ActivityCompat.checkSelfPermission
                    (this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){

               ActivityCompat.requestPermissions(this,
                  new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            return;
            }
            //serialize();
        });

    }

    private void serialize() {

        Handler handler = new Handler(Looper.getMainLooper()){
            public void handleMessage(Message inputMessage) {

        File file = new File(getExternalFilesDir(null)+"/db.imgdb");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.drawable.aqsa_mosque_01);

            aid.addImage("aqsa_mosque_01",bitmap);
            aid.serialize(fileOutputStream);
            fileOutputStream.close();

            notify_00.setText("Database serialized");
            notify_00.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
            }
        };

        Thread update = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handler.sendEmptyMessage(0);
                }
                catch (Exception e){
                 e.printStackTrace();
                }
            }
        });
        update.start();
    }

    private void onUpdate(FrameTime frameTime) {

        Handler handler = new Handler(Looper.getMainLooper()){
            public void handleMessage(Message inputMessage) {

                Frame frame = crf.getArSceneView().getArFrame();
                Collection<AugmentedImage> images = frame.
                        getUpdatedTrackables(AugmentedImage.class);

                for(AugmentedImage image : images){
                    if(image.getTrackingMethod()==AugmentedImage.TrackingMethod.FULL_TRACKING){

                        if(image.getName().equals("mecca_01.jpg")){
                            tv.setText("makkah");
                        }
                        else if(image.getName().equals("aqsa_mosque_01.jpg")){
                            tv.setText("aqsa");
                        }
            }
                }
            }
        };

        Thread update = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handler.sendEmptyMessage(0);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        update.start();
    }

    public void load_db(Session session, Config config){

        Handler handler = new Handler(Looper.getMainLooper()){
            public void handleMessage(Message inputMessage) {
                InputStream dbStream = getResources().openRawResource(R.raw.imagedb_01);
                try {
                    aid = AugmentedImageDatabase.deserialize(session,dbStream);

//                    File  file = new File(getExternalFilesDir(null)+"/db.imgdb");
//                    FileInputStream inputStream = new FileInputStream(file);
//                    aid = AugmentedImageDatabase.deserialize(session,inputStream);
                    config.setAugmentedImageDatabase(aid);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };

        Thread update = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    handler.sendEmptyMessage(0);
                }
                catch (Exception e){
                    e.printStackTrace();
                }

            }
        });
        update.start();
    }
}
