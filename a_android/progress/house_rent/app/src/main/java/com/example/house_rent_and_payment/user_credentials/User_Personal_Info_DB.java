package com.example.house_rent_and_payment.user_credentials;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.example.house_rent_and_payment.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class User_Personal_Info_DB {
    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR; // main root

    private FirebaseStorage dS;
    private StorageReference dSR;
    private StorageReference dSCR;

    private Context context;
    private FragmentActivity activity;
    private ListView listView;
    private  User_Personal_Info_Adapter adapter;
    private ArrayList<User_Personal_Info> user_personal_infos;

    public User_Personal_Info_DB(Context context, FragmentActivity activity, View root_profile_frag2) {
        notification_X("Fetching Data",context);
        this.context = context;
        this.activity = activity;

        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference(); // main root
        dS = FirebaseStorage.getInstance();

        user_personal_infos = new ArrayList<User_Personal_Info>();
        listView = (ListView) root_profile_frag2.findViewById(
                R.id.c_a_user_profile_frag2_list_view);
        read();
    }

    private void read(){
        DatabaseReference dBCR2 = dBR.child("users").child(owner_u_id());
        dBCR2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {
                    try {
                        User_Personal_Info ci_frag1 = dataSnapshot.child("user_personal_info")
                                .getValue(User_Personal_Info.class);
                        user_personal_infos.clear();
                        user_personal_infos.add(ci_frag1);
                    }
                    catch (Exception e) {
                        notification("Error in fetching data for recycle");
                    }

                    adapter = new User_Personal_Info_Adapter(activity, user_personal_infos);
                    listView.setAdapter(adapter);
                    listView.setNestedScrollingEnabled(false);
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
