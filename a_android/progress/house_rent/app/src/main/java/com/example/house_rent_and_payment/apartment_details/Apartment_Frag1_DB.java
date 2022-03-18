package com.example.house_rent_and_payment.apartment_details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.others.Amount;
import com.example.house_rent_and_payment.others.Contact;
import com.example.house_rent_and_payment.others.Image_File;
import com.example.house_rent_and_payment.user_credentials.User_Personal_Info;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
import com.google.firebase.storage.UploadTask;

public class Apartment_Frag1_DB{

    private Context context;
    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR;// main root
    private DatabaseReference dBCR; // child root

    private FragmentActivity activity;
    private Apartment_Frag1_Adapter adapter;
    private RecyclerView recyclerView;
    private Apartment_Frag1_Content_Info []apartment_frag1_content_infos;

    public Apartment_Frag1_DB(Context context,FragmentActivity activity, View root_apart_frag1) {
        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference(); // main root
        dBCR = dBR.child("apartment");

        this.context = context;
        this.activity = activity;
        notification_X("Fetching Data",context);
        recyclerView = (RecyclerView)
                root_apart_frag1.findViewById(R.id.g_a_my_apartment_main_frag1_recycle);

        apartment_frag1_content_infos = new Apartment_Frag1_Content_Info[1];
        adapter = new Apartment_Frag1_Adapter(activity,apartment_frag1_content_infos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(activity));
        recyclerView.setAdapter(adapter);
        recyclerView.setNestedScrollingEnabled(false);
        adapter.notifyDataSetChanged();
        notification("Updated");
    }


    private String owner_u_id(){
        String s = "default";
        FirebaseUser user = fA.getCurrentUser();

        if (user != null) {
            s = user.getUid();
        }
        else {
            notification("failed to fetch data ");

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

