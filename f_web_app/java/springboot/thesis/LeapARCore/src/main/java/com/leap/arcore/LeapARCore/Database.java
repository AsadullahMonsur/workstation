package com.leap.arcore.LeapARCore;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Database {
   public void initialization(){
     try {
        FileInputStream serviceAccount =
                  new FileInputStream("./arcore-with-leap-motion-firebase-adminsdk-v4xxl-aa95986b59.json");

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://arcore-with-leap-motion.firebaseio.com")
                .build();
        FirebaseApp.initializeApp(options);

        update();
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
//        dBCR.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Iterable<DataSnapshot> children = dataSnapshot.getChildren();
//
//                for(DataSnapshot child: children){
//                    try {
//                        Object choice = child.getValue(Object.class);
//                        System.out.println("Successfully fetched data"+child);
//
//                    }
//                    catch (Exception e){
//                        System.out.println("Error Fetching Data");
//                        e.printStackTrace();
//                    }
//            }
//            }
//
//            @Override
//            public void onCancelled(DatabaseError error) {
//            }
//        });

    }
}
