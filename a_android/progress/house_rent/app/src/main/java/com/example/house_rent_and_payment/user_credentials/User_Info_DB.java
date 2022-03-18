package com.example.house_rent_and_payment.user_credentials;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class User_Info_DB {
    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR; // main root

    private FirebaseStorage dS;
    private StorageReference dSR;
    private StorageReference dSCR;

    private Context context;
    private FragmentActivity activity;
    private RecyclerView recyclerView;
    private  User_Info_Adapter adapter;
    private User_Info []user_infos;

    public User_Info_DB(Context context, FragmentActivity activity, View root_profile_frag1) {
        notification_X("Fetching Data",context);
        this.context = context;
        this.activity = activity;

        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference(); // main root
        dS = FirebaseStorage.getInstance();

        user_infos = new User_Info[1];
        recyclerView = (RecyclerView) root_profile_frag1.findViewById(
                             R.id.c_a_user_profile_frag1_recycle);
        read();
    }

    private void read(){
        DatabaseReference dBCR2 = dBR.child("users").child(owner_u_id());
        dBCR2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    try {
                       User_Info ci_frag1 = dataSnapshot.child("user_info")
                                                    .getValue(User_Info.class);
                       user_infos[0] = ci_frag1;
                    }
                    catch (Exception e) {
                        notification("Error in fetching data for recycle");
                    }

                    adapter = new User_Info_Adapter(activity, user_infos);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setNestedScrollingEnabled(false);
                    adapter.notifyDataSetChanged();
                    notification("Updated");                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                notification("Error in fetching data");
            }
        });
    }
    private String owner_u_id(){
        String s = "default";
        FirebaseUser user = fA.getCurrentUser();

        if (user != null) {
            s = user.getUid();
        }
        else {
            notification("error in fetching data");
        }

        return s;
    }
    private void notification(String s){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
    private void notification_X(String s, Context context){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

}
