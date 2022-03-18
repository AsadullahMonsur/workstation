package com.example.house_rent_and_payment.market_view;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.apartment_details.Apartment_Frag1_Content_Info;
import com.example.house_rent_and_payment.others.Image_File;
import com.example.house_rent_and_payment.rent_details.Rent_Frag2_Adapter;
import com.example.house_rent_and_payment.rent_details.Rent_Frag2_Content_Info;
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

public class Common_Market_DB {
    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR; // main root

    private FirebaseStorage dS;
    private StorageReference dSR;
    private StorageReference dSCR;

    private Context context;
    private FragmentActivity activity;

    private List<Common_Market_Content_Info> ci_list_frag2;
    private Common_Market_Adapter adapter;
    private RecyclerView recyclerView;
    private Common_Market_Content_Info[] common_market_content_infos;

    public Common_Market_DB(Context context, FragmentActivity activity, View root_market_frag) {
        notification_X("Fetching Data", context);
        this.context = context;
        this.activity = activity;

        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference(); // main root
        dS = FirebaseStorage.getInstance();

        ci_list_frag2 = new ArrayList<Common_Market_Content_Info>();
        recyclerView = (RecyclerView) root_market_frag.findViewById(R.id.i_a_available_rent_main_recycle);
        read();
    }

    private void read() {
        DatabaseReference dBCR2 = dBR.child("apartment");
        Query query2 = dBCR2.orderByChild("is_published").equalTo(1);

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
                            Common_Market_Content_Info ci_frag2 = new Common_Market_Content_Info(
                                    0, ci_frag1.getProfile_img(), ci_frag1.getRent_id(),
                                    ci_frag1.getOwner_name(),
                                    ci_frag1.getLocation(), ci_frag1.getAmount(),
                                    ci_frag1.getContact(), ci_frag1.getDescription(),
                                    ci_frag1.getActive_status(),
                                    ci_frag1.getIs_published(), ci_frag1.getIs_rented(),
                                    ci_frag1.getPublish_date(), ci_frag1.getRent_date(),
                                    ci_frag1.getUser_rating(),
                                    ci_frag1.getImg_list_info());

                            if (ci_frag1.getIs_rented() == 0) {
                                ci_list_frag2.add(ci_frag2);
                            }
                        } catch (Exception e) {
                            notification("Error in fetching data");
                        }
                    }

                    common_market_content_infos = list_to_array(ci_list_frag2);
                    adapter = new Common_Market_Adapter(activity, common_market_content_infos);
                    recyclerView.setHasFixedSize(true);
                   // recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                    recyclerView.setLayoutManager(
                           new GridLayoutManager(activity,2));
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

    private Common_Market_Content_Info[] list_to_array(
            List<Common_Market_Content_Info> list) {
        int length = list.size();
        Common_Market_Content_Info[] common_market_content_infos = new
                Common_Market_Content_Info[length];
        int i = 0;
        while (i < length) {
            common_market_content_infos[i] = list.get(i);
            i++;
        }
        return common_market_content_infos;
    }

    private String owner_u_id() {
        String s = "default";
        FirebaseUser user = fA.getCurrentUser();

        if (user != null) {
            s = user.getUid();
        } else {
            notification("error in fetching data");
        }

        return s;
    }

    private void notification(String s) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void notification_X(String s, Context context) {
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}