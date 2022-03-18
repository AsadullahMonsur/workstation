package com.example.house_rent_and_payment.apartment_details;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.others.Amount;
import com.example.house_rent_and_payment.others.Contact;
import com.example.house_rent_and_payment.others.Image_File;
import com.example.house_rent_and_payment.user_credentials.User_Personal_Info;
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

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Apartment_Frag2_DB {
    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR; // main root

    private FirebaseStorage dS;
    private StorageReference dSR;
    private StorageReference dSCR;

    private Context context;
    private FragmentActivity activity;

    private List<Apartment_Frag2_Content_Info> ci_list_frag2;
    private Apartment_Frag2_Adapter adapter;
    private RecyclerView recyclerView;
    private Apartment_Frag2_Content_Info []apartment_frag2_content_infos;

    //private File file = new File("/assets/chat.png");
    private Image_File image_file_01 = new Image_File("chat.png", "/assets/chat.png");

    public Apartment_Frag2_DB(Context context,
           FragmentActivity activity,View root_apart_frag2) {

        notification_X("Fetching Data",context);
        this.context = context;
        this.activity = activity;

        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference(); // main root
        dS = FirebaseStorage.getInstance();

        ci_list_frag2 = new ArrayList<Apartment_Frag2_Content_Info>();

        recyclerView = (RecyclerView)
                root_apart_frag2.findViewById(R.id.g_a_my_apartment_main_frag2_recycle);
        Image_File image_file = new Image_File();
        List<Image_File> image_file_list = new ArrayList<Image_File>();
        image_file_list.add(image_file);

        Amount amount = new Amount("loading",0);
        Contact contact = new Contact("loading","loading");

        Apartment_Frag2_Content_Info ci_frag2 = new Apartment_Frag2_Content_Info(
                1, image_file, "loading info",
                "loading info", amount,
                 contact, "loading info",0,0,0,"default",
                "default",-1.0,
                 image_file_list);
        read();
       }

    public Image_File getImage_file_01() {
        return image_file_01;
    }

    public void setImage_file_01(Image_File image_file_01) {
        this.image_file_01 = image_file_01;
    }

    private void read(){
      DatabaseReference dBCR1 = dBR.child("user").child(owner_u_id())
                                   .child("personal_info");
      dBCR1.addValueEventListener(new ValueEventListener() {
          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if(dataSnapshot.exists()){
              User_Personal_Info user_personal_info = dataSnapshot.getValue(
                                                       User_Personal_Info.class);
              try{
                setImage_file_01(user_personal_info.getProfile_photo());
              }
              catch (Exception e){
                  notification("Profile photo fetching error");
              }
            }
            }

          @Override
          public void onCancelled(@NonNull DatabaseError databaseError) {
              notification("Error in fetching data");
          }
      });

      DatabaseReference dBCR2 = dBR.child("apartment");
      Query query2 = dBCR2.orderByChild("owner_id").equalTo(owner_u_id());

      query2.addValueEventListener(new ValueEventListener() {

         @Override
         public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          if(!ci_list_frag2.isEmpty()) {
              ci_list_frag2.clear();
          }
          if(dataSnapshot.exists()){
               for(DataSnapshot ds: dataSnapshot.getChildren()){
                 try{
                    Apartment_Frag1_Content_Info ci_frag1 = ds.getValue(
                                 Apartment_Frag1_Content_Info.class);
                    Apartment_Frag2_Content_Info ci_frag2 = new Apartment_Frag2_Content_Info(
                         1,ci_frag1.getProfile_img(),
                         ci_frag1.getRent_id(),ci_frag1.getLocation(),
                         ci_frag1.getAmount(), ci_frag1.getContact(),
                         ci_frag1.getDescription(),ci_frag1.getActive_status(),
                         ci_frag1.getIs_published(),ci_frag1.getIs_rented(),
                         ci_frag1.getPublish_date(),ci_frag1.getRent_date(),
                         ci_frag1.getUser_rating(),
                         ci_frag1.getImg_list_info());

                    ci_list_frag2.add(ci_frag2);
                 }
                 catch (Exception e){
                   notification("Error in fetching data");
                 }
               }

              apartment_frag2_content_infos = list_to_array(ci_list_frag2);
              adapter = new Apartment_Frag2_Adapter(activity,apartment_frag2_content_infos);
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

    private Apartment_Frag2_Content_Info[] list_to_array(List<Apartment_Frag2_Content_Info> list){
        int length = list.size();
        Apartment_Frag2_Content_Info []apartment_frag2_content_infos = new
                Apartment_Frag2_Content_Info[length];
        int i = 0;
        while(i< length){
            apartment_frag2_content_infos[i] = list.get(i);
            i++;
        }
     return apartment_frag2_content_infos;
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
    private void notification_X(String s,Context context){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
}

