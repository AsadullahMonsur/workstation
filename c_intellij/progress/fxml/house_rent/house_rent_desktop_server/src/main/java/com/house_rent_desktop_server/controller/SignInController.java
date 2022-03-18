package com.house_rent_desktop_server.controller;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserRecord;
import com.google.firebase.database.*;
import com.example.house_rent_and_payment.user_credentials.User_Profile;
import com.house_rent_desktop_server.messages.Result;
import com.jme3.network.Filters;
import com.jme3.network.Server;

public class SignInController {
    private FirebaseAuth fA;
    // result :   1 for successful, 0 un-successful

    private Server server;

    public SignInController(String[] sign_in_data, Server server) {
       try {
           this.server = server;
           fA = FirebaseAuth.getInstance();
           UserRecord record = fA.getUserByEmail(sign_in_data[0]);
           String uid = record.getUid();

           System.out.println("Processing in SignInController");

           FirebaseDatabase db = FirebaseDatabase.getInstance();
           DatabaseReference dBR = db.getReference();
           dBR.child("users")
              .child(uid).addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.exists()){
                   try {
                     User_Profile profile = snapshot.getValue(User_Profile.class);
                     if(profile.getUser_info().getU_pass().equals(sign_in_data[1])){
                         broadcast_sign_in_result(sign_in_data[2], 1);
                         System.out.println("Successful LogIn by:" +"\n user-> "+
                                             sign_in_data[0]+"\n Client ID: "+
                                             sign_in_data[2]);
                     }
                     else {
                         broadcast_sign_in_result(sign_in_data[2], 0);
                         System.out.println("Failed to LogIn by:" +"\n user-> "+
                                             sign_in_data[0]+"\n Client ID: "+
                                             sign_in_data[2]);
                     }
                   }
                   catch (Exception e){
                       broadcast_sign_in_result(sign_in_data[2], 0);
                       System.out.println("Error Fetching data sign in controller: \n"
                                          +e.getMessage());
                   }
                }
                else {
                    broadcast_sign_in_result(sign_in_data[2], 0);
                    System.out.println("No user Info");
                }
               }

               @Override
               public void onCancelled(DatabaseError error) {
                   broadcast_sign_in_result(sign_in_data[2], 0);
                   System.out.println("Database error in sign in controller .\n"+error);
               }
           });

       }
       catch (Exception e){
           broadcast_sign_in_result(sign_in_data[2], 0);
           System.out.println("Error Sign In : \n"+e.getMessage());
       }
    }

    public void broadcast_sign_in_result(String id,int value){
        server.broadcast(new Result(value));
        //server.broadcast(Filters.equalTo(0),new Result(value));
        System.out.println("sign in result is broadcast for ->"+
                           "\n client :"+id+"\n result: "+value);
    }
}
