package com.example.house_rent_and_payment.apartment_details;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.preference.PreferenceManager;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.others.Amount;
import com.example.house_rent_and_payment.others.Contact;
import com.example.house_rent_and_payment.others.Image_File;
import com.example.house_rent_and_payment.user_home_main.Placeholder_Fragment;
import com.example.house_rent_and_payment.user_home_main.User_Home_Page_Main;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class Apartment_Frag1_Pop_Up extends AppCompatActivity {

    private static final int RESULT_LOAD_IMAGE = 1;
    private RecyclerView upload_view;
    private List<String> file_name_list;
    private List<String> file_done_list;
    private String rent_id;
    private int global_attack = 0;
    private Image_File img_file_info;
    private List<Image_File> img_list_info = new ArrayList<Image_File>();

    private Apartment_Frag1_Pop_Up_Adapter upload_adapter;
    private FloatingActionButton close;
    private Context context;
    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR;// main root
    private DatabaseReference dBCR; // child root
    private StorageReference storage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try{
        setContentView(R.layout.g_a_my_apartment_pop_up_fragment1);
        close = findViewById(R.id.g_a_my_apartment_pop_up_form_close);

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 finish();
            }
        });

        Intent in = getIntent();
        rent_id = in.getExtras().getString("rent_id");

        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference();
        dBCR = dBR.child("apartment");

        storage = FirebaseStorage.getInstance().getReference();

        upload_view = (RecyclerView) findViewById(R.id.g_a_my_apartment_pop_up_frag1_recycle);

        file_name_list = new ArrayList<>();
        file_done_list = new ArrayList<>();

        upload_adapter = new Apartment_Frag1_Pop_Up_Adapter(file_name_list, file_done_list);

        upload_view.setLayoutManager(new LinearLayoutManager(this));
        upload_view.setHasFixedSize(true);
        upload_view.setAdapter(upload_adapter);

        Intent intent = new Intent();
        intent.setType("image/*");
        intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"select pictures"),RESULT_LOAD_IMAGE);
        Toast.makeText(this, "Pick Images", Toast.LENGTH_SHORT).show();
    }
    catch(Exception e){
        e.printStackTrace();
    }
    }
    @Override
     public void onActivityResult(int requestCode,int resultCode, @Nullable Intent data) {
       super.onActivityResult(requestCode,resultCode,data);
       Toast.makeText(getApplicationContext(), "Wait until upload is complete", Toast.LENGTH_SHORT).show();
       if(requestCode==RESULT_LOAD_IMAGE && resultCode == RESULT_OK){

         if(data.getClipData() != null){

           int totalItemsSelected = data.getClipData().getItemCount();

           for(int i = 0; i < totalItemsSelected; i++){

             Uri fileUri = data.getClipData().getItemAt(i).getUri();

             String fileName = getFileName(fileUri);

             file_name_list.add(fileName);
             file_done_list.add("uploading");
             upload_adapter.notifyDataSetChanged();

             StorageReference fileToUpload = storage.child("images").child(rent_id).child(fileName);

             final int finalI = i;
             fileToUpload.putFile(fileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               fileToUpload.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                  @Override
                  public void onSuccess(Uri uri) {
                    img_file_info = new Image_File(fileName,String.valueOf(uri));
                    img_list_info.add(img_file_info);
                     global_attack++;

                     if(global_attack==totalItemsSelected){
                     Contact contact = new Contact();
                     contact.setNum1("00000000");
                     Amount amount = new Amount();
                     amount.setPrice(Integer.parseInt("000"));
                     amount.setCurrency("none");
                     Image_File profile_img = new Image_File("empty","empty");

                     Apartment_Frag1_Content_Info apartment_frag1_content_info;
                     apartment_frag1_content_info = new Apartment_Frag1_Content_Info(
                           rent_id, "none","none","none",
                           contact,"empty",amount,"empty",0,
                           0,0,"empty","empty",-1.0,
                           img_list_info,profile_img);

                     dBCR.child(rent_id).setValue(apartment_frag1_content_info);
                     }
                  }
               });
               file_done_list.remove(finalI);
               file_done_list.add(finalI, "done");
               upload_adapter.notifyDataSetChanged();
             }
           });
         }
       }

       else if (data.getData() != null){
            Toast.makeText(getApplicationContext(), "Selected Single File", Toast.LENGTH_SHORT).show();
       }
      }
     }

     private String getFileName(Uri uri) {
         String result = null;
         if (uri.getScheme().equals("content")) {
         Cursor cursor = getApplicationContext().getContentResolver()
                                        .query(uri, null, null,
                                        null, null);
         try {
            if (cursor != null && cursor.moveToFirst()) {
            result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
            }
         }
         finally {
            cursor.close();
         }
       }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
               result = result.substring(cut + 1);
            }
        }
        return result;
    }

}
