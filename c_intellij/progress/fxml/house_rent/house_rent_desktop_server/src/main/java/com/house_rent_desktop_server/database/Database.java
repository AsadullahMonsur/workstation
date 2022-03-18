package com.house_rent_desktop_server.database;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;

public class Database{
    public void initialization(){
        try {
            FileInputStream serviceAccount =
                    new FileInputStream("./houserent-bf36c-firebase-adminsdk-czehs-c8fd1ef4f6.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://houserent-bf36c.firebaseio.com/")
                    .build();
            FirebaseApp.initializeApp(options);

        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void update(){

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dBR = db.getReference();
        DatabaseReference dBCR = dBR.child("input");

        dBCR.child("choice").setValue(2, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError error, DatabaseReference ref) {
                System.out.println("Successfully updated");
            }
        });
    }
}
