package com.example.house_rent_and_payment.rent_details;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.apartment_details.Apartment_Frag1_Content_Info;
import com.example.house_rent_and_payment.others.Image_File;
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

public class Rent_Frag2_DB {
    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR; // main root

    private FirebaseStorage dS;
    private StorageReference dSR;
    private StorageReference dSCR;

    private Context context;
    private FragmentActivity activity;

    private List<Rent_Frag2_Content_Info> ci_list_frag2;
    private Rent_Frag2_Adapter adapter;
    private RecyclerView recyclerView;
    private Rent_Frag2_Content_Info []rent_frag2_content_infos;

    public Rent_Frag2_DB(Context context, FragmentActivity activity, View root_rent_frag2) {
        notification_X("Fetching Data",context);
        this.context = context;
        this.activity = activity;

        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference(); // main root
        dS = FirebaseStorage.getInstance();

        ci_list_frag2 = new ArrayList<Rent_Frag2_Content_Info>();
        recyclerView = (RecyclerView) root_rent_frag2.findViewById(R.id.h_a_my_rent_main_frag2_recycle);
        read();
    }

    private void read(){
        DatabaseReference dBCR2 = dBR.child("apartment");
        Query query2 = dBCR2.orderByChild("customer_id").equalTo("empty");

        query2.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!ci_list_frag2.isEmpty()) {
                    ci_list_frag2.clear();
                }
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        try {
                            Apartment_Frag1_Content_Info ci_frag1 = ds.getValue(
                                    Apartment_Frag1_Content_Info.class);
                            Rent_Frag2_Content_Info ci_frag2 = new Rent_Frag2_Content_Info(
                                    3, ci_frag1.getProfile_img(), ci_frag1.getRent_id(), ci_frag1.getLocation(),
                                    ci_frag1.getAmount(), ci_frag1.getContact(),
                                    ci_frag1.getDescription(), ci_frag1.getActive_status(),
                                    ci_frag1.getIs_published(), ci_frag1.getIs_rented(),
                                    ci_frag1.getPublish_date(), ci_frag1.getRent_date(),
                                    ci_frag1.getUser_rating(),
                                    ci_frag1.getImg_list_info());

                            ci_list_frag2.add(ci_frag2);
                        } catch (Exception e) {
                            notification("Error in fetching data");
                        }
                    }

                    rent_frag2_content_infos = list_to_array(ci_list_frag2);
                    adapter = new Rent_Frag2_Adapter(activity, rent_frag2_content_infos);
                    recyclerView.setHasFixedSize(true);
                    recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    recyclerView.setAdapter(adapter);
                    recyclerView.setNestedScrollingEnabled(false);
                    adapter.notifyDataSetChanged();
                    notification("Updated");
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                notification("Error in fetching data");
            }
        });
    }
    private Rent_Frag2_Content_Info[] list_to_array(
                            List<Rent_Frag2_Content_Info> list){
        int length = list.size();
        Rent_Frag2_Content_Info [] rent_frag2_content_infos = new
                Rent_Frag2_Content_Info[length];
        int i = 0;
        while(i< length){
            rent_frag2_content_infos[i] = list.get(i);
            i++;
        }
        return rent_frag2_content_infos;
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
