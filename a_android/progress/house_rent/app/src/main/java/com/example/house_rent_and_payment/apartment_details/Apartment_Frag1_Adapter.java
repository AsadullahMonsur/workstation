package com.example.house_rent_and_payment.apartment_details;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.others.Amount;
import com.example.house_rent_and_payment.others.Contact;
import com.example.house_rent_and_payment.user_credentials.User_Personal_Info;
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

import java.util.List;

public class Apartment_Frag1_Adapter
        extends RecyclerView.Adapter<Apartment_Frag1_Adapter.ViewHolder> {
    private int img_check = 0;
    private Context context;
    private Apartment_Frag1_Content_Info []conInfo;

    private FirebaseAuth fA;
    private FirebaseDatabase dB;
    private DatabaseReference dBR;// main root
    private DatabaseReference dBCR; // child root


    public Apartment_Frag1_Adapter(Context context,
                                   Apartment_Frag1_Content_Info []ci) {
        this.context = context;
        this.conInfo = ci;
        fA = FirebaseAuth.getInstance();
        dB = FirebaseDatabase.getInstance();
        dBR = dB.getReference(); // main root
        dBCR = dBR.child("apartment");
    }

    @NonNull
    @Override
    public Apartment_Frag1_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.g_b_my_apartment_main_frag1_card_view,
                parent, false);

        return new Apartment_Frag1_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Apartment_Frag1_Adapter.ViewHolder holder, int position) {
        Handler handler7_2 = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                final Apartment_Frag1_Content_Info conInfoData = conInfo[position];
                form_reveal(holder,conInfoData);
                rent_id_resolver(holder,conInfoData);
                owner_info_resolver(holder,conInfoData);
                contact_resolver(holder,conInfoData);
                currency_detector(holder,conInfoData);
                switch_resolver(holder,conInfoData);

                holder.img_for_house_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pop_up(holder,v,conInfoData);
                    }
                });

                holder.form_save_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        save_new_rent_form(holder,v,conInfoData);
                    }
                });

                holder.form_publish_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        publish_form(holder,v,conInfoData);
                    }
                });
            }
        };

        Thread updateUi = new Thread() {
            public void run() {
                try {
                    handler7_2.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("failed", "exception");

                }
            }
        };
        updateUi.start();

    }

    @Override
    public int getItemCount() {
        return conInfo.length;
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

    private void pop_up(ViewHolder holder,View v,Apartment_Frag1_Content_Info conInfoData){
        if(holder.rent_id_tv.getText().equals("Tap The Button To Get Code")
                || holder.address_input.getText().toString().equals("Confirm Location Information")
                || holder.amount_input.getText().toString().equals("000")
                || holder.facilities_input.getText().toString().equals("Description of Home")
                || holder.sw_01.isChecked()){

            if(holder.sw_01.isChecked()){
                notification("Turn on active status after image selection");
            }
            else{
                notification("Failed. Collect Rent Id and Fill the Form");
            }
        }
        else{

            if(amount_checker(holder,conInfoData)==false){
                notification("Check amount and currency");
            }
            else if(holder.contact_num1_tv.getText().equals("empty")){
                notification("Sorry, you have not verified contact number." +
                        " Please, go to your info and complete procedure and try again");
            }
            else {
                Handler handler_03 = new Handler(Looper.getMainLooper()) {
                    public void handleMessage(Message msg) {
                        String r_id = holder.rent_id_tv.getText().toString();
                        Intent intent = new Intent(context,Apartment_Frag1_Pop_Up.class);
                        intent.putExtra("rent_id",r_id);
                        ((Activity)context).startActivity(intent);
                        img_check = 1;
                    }
                };
                Thread updateUi = new Thread() {
                    public void run() {
                        try {
                            handler_03.sendEmptyMessage(0);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            Log.d("failed", "exception");
                        }
                    }
                };
                updateUi.start();
            }
        }
    }


    private void publish_form(ViewHolder holder,View v,Apartment_Frag1_Content_Info conInfoData){
        if(holder.rent_id_tv.getText().equals("Tap The Button To Get Code")
                || holder.address_input.getText().toString().equals("Confirm Location Information")
                || holder.amount_input.getText().toString().equals("000")
                || holder.facilities_input.getText().toString().equals("Description of Home")
                || !holder.sw_01.isChecked() || img_check == 0){

            if(!holder.sw_01.isChecked()){
                notification("Turn on active status after image selection and when it will be published.");
            }
            if(img_check==0){
                notification("Please, click image button & insert images");
            }
            else{
                notification("Failed. Collect Rent Id and Fill the Form");
            }
        }
        else{
            if(amount_checker(holder,conInfoData)==false){
                notification("Check amount and currency");
            }
            else if(holder.contact_num1_tv.getText().equals("empty")){
                notification("Sorry, you have not verified contact number." +
                        " Please, go to your info and complete procedure and try again");
            }
            else {
                Contact contact;
                if((holder.contact_num2_input.getText().toString()).equals("")==false){
                    contact = new Contact(holder.contact_num1_tv.getText().toString(),
                            holder.contact_num2_input.getText().toString());
                }
                else{
                    contact = new Contact();
                    contact.setNum1(holder.contact_num1_tv.getText().toString());
                }

                Amount amount = new Amount();
                amount.setPrice(Integer.parseInt(holder.amount_input.getText().toString()));
                amount.setCurrency(holder.amount_currency);

                Handler handler_04 = new Handler(Looper.getMainLooper()) {
                    public void handleMessage(Message msg) {

                        String rent_id = holder.rent_id_tv.getText().toString().trim();

                        dBCR = dBR.child("apartment");

                        dBCR.child(rent_id)
                                .child("owner_id")
                                .setValue(owner_u_id());

                        dBCR.child(rent_id)
                                .child("customer_id")
                                .setValue("empty");

                        dBCR.child(rent_id)
                                .child("owner_name")
                                .setValue(holder.owner_name_tv.getText().toString());


                        dBCR.child(rent_id)
                                .child("contact")
                                .setValue(contact);

                        dBCR.child(rent_id)
                                .child("location")
                                .setValue(holder.address_input.getText().toString());

                        dBCR.child(rent_id)
                                .child("amount")
                                .setValue(amount);

                        dBCR.child(rent_id)
                                .child("description")
                                .setValue(holder.facilities_input.getText().toString());

                        dBCR.child(rent_id)
                                .child("active_status")
                                .setValue(1);

                        dBCR.child(rent_id)
                                .child("is_published")
                                .setValue(1);

                        dBCR.child(rent_id)
                                .child("is_rented")
                                .setValue(0);

                        dBCR.child(rent_id)
                                .child("publish_date")
                                .setValue("21-12-21");

                        dBCR.child(rent_id)
                                .child("rent_date")
                                .setValue("unknown");

                        dBCR.child(rent_id)
                                .child("rent_date")
                                .setValue("unknown");

                        notification("Successfully Saved");
                    }
                };
                Thread updateUi = new Thread() {
                    public void run() {
                        try {
                            handler_04.sendEmptyMessage(0);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                            Log.d("failed", "exception");
                        }
                    }
                };
                updateUi.start();
            }
        }
    }

    private void save_new_rent_form(ViewHolder holder,View v,Apartment_Frag1_Content_Info conInfoData){

        Handler handler_01 = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                if(holder.rent_id_tv.getText().equals("Tap The Button To Get Code")
                        || holder.address_input.getText().toString().equals("Confirm Location Information")
                        || holder.amount_input.getText().toString().equals("000")
                        || holder.facilities_input.getText().toString().equals("Description of Home")
                        || holder.sw_01.isChecked()){

                    if(holder.sw_01.isChecked()){
                        notification("Turn off active status. Active is only activated when it will be published.");

                    }
                    else{
                        notification("Failed. Check Info. Collect Rent Id and Fill Form");
                    }
                }

                else{
                    if(amount_checker(holder,conInfoData)==false){
                        notification("Check amount and currency");
                    }
                    else if(holder.contact_num1_tv.getText().equals("empty")){
                        notification("Sorry, you have not verified contact number." +
                                " Please, go to your info and update and try again");
                    }
                    else {
                        Contact contact;
                        if((holder.contact_num2_input.getText().toString()).equals("")==false){
                            contact = new Contact(holder.contact_num1_tv.getText().toString(),
                                    holder.contact_num2_input.getText().toString());
                        }
                        else{
                            contact = new Contact();
                            contact.setNum1(holder.contact_num1_tv.getText().toString());
                        }
                        Amount amount = new Amount();
                        amount.setPrice(Integer.parseInt(holder.amount_input.getText().toString()));
                        amount.setCurrency(holder.amount_currency);

                        String rent_id = holder.rent_id_tv.getText().toString().trim();

                        dBCR = dBR.child("apartment");

                        dBCR.child(rent_id)
                                .child("owner_id")
                                .setValue(owner_u_id());

                        dBCR.child(rent_id)
                                .child("customer_id")
                                .setValue("empty");

                        dBCR.child(rent_id)
                                .child("owner_name")
                                .setValue(holder.owner_name_tv.getText().toString());


                        dBCR.child(rent_id)
                                .child("contact")
                                .setValue(contact);

                        dBCR.child(rent_id)
                                .child("location")
                                .setValue(holder.address_input.getText().toString());

                        dBCR.child(rent_id)
                                .child("amount")
                                .setValue(amount);

                        dBCR.child(rent_id)
                                .child("description")
                                .setValue(holder.facilities_input.getText().toString());

                        dBCR.child(rent_id)
                                .child("active_status")
                                .setValue(0);

                        dBCR.child(rent_id)
                                .child("is_published")
                                .setValue(0);

                        dBCR.child(rent_id)
                                .child("is_rented")
                                .setValue(0);

                        dBCR.child(rent_id)
                                .child("publish_date")
                                .setValue("21-12-21");

                        dBCR.child(rent_id)
                                .child("rent_date")
                                .setValue("unknown");

                        notification("Successfully Saved");
                    }
                }
            }
        };

        Thread updateUi = new Thread() {
            public void run() {
                try {
                    handler_01.sendEmptyMessage(0);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.d("failed", "exception");
                }
            }
        };
        updateUi.start();
    }

    private void owner_info_resolver(ViewHolder holder, Apartment_Frag1_Content_Info conInfoData) {
        String owner_u_id = owner_u_id();
        dBCR = dBR.child("users");
        Query query = dBCR.child(owner_u_id);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try {
                    User_Personal_Info upi = dataSnapshot.child("user_personal_info").getValue(User_Personal_Info.class);
                    holder.owner_name_tv.setText(upi.getF_name() + upi.getL_name());
                    holder.contact_num1_tv.setText(upi.getContact_no());
                }
                catch (Exception ex){
                    ex.printStackTrace();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void switch_resolver(ViewHolder holder, Apartment_Frag1_Content_Info conInfoData) {

        holder.sw_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.sw_01.isChecked()){
                    holder.right_sw_01.setBackgroundColor(Color.parseColor("#02bf05"));
                    holder.left_sw_01.setBackgroundColor(Color.parseColor("#ffffff"));
                }
                else{
                    holder.right_sw_01.setBackgroundColor(Color.parseColor("#ffffff"));
                    holder.left_sw_01.setBackgroundColor(Color.parseColor("#b71001"));
                }
            }
        });
    }

    private void contact_resolver(ViewHolder holder, Apartment_Frag1_Content_Info conInfoData) {
        holder.new_contact_num_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.contact_num2_input.getVisibility() == v.INVISIBLE){
                    holder.contact_num2_input.setVisibility(v.VISIBLE);
                }
                else{
                    holder.contact_num2_input.setVisibility(v.INVISIBLE);
                }
            }
        });
    }

    private void rent_id_resolver(ViewHolder holder, Apartment_Frag1_Content_Info conInfoData) {
        //using lamda expression instead of new object
        holder.rent_id_btn.setOnClickListener(v -> {
            get_rent_id(holder,conInfoData);
        });
    }

    private void form_reveal(ViewHolder holder, Apartment_Frag1_Content_Info conInfoData) {
        holder.add_new_rent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.content.getVisibility() == v.INVISIBLE){
                   holder.content.setVisibility(v.VISIBLE);
                    notification("Fill All Info");
                }
                else{
                    holder.content.setVisibility(v.INVISIBLE);
                }
            }
        });
    }
    private void currency_detector(ViewHolder holder, Apartment_Frag1_Content_Info conInfoData) {

        ArrayAdapter<CharSequence> spinner_adapter = ArrayAdapter.createFromResource
                              (context, R.array.currency_list,
                               android.R.layout.simple_spinner_item);

        spinner_adapter.setDropDownViewResource
                (android.R.layout
                                                  .simple_spinner_dropdown_item);

        holder.amount_spinner.setAdapter(spinner_adapter);
        holder.amount_spinner
              .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==0) {
                    holder.amount_currency = "Currency($)";
                    holder.amount_input.setError("specify currency");
                }
                if (position == 1) {
                    holder.amount_currency = "BDT";
                    notification("~~ BDT selected ~~");
                } else if (position == 2) {
                    holder.amount_currency = "US";
                    notification("~~ US selected ~~");
                } else if (position == 3) {
                    holder.amount_currency = "GBP";
                    notification("~~ GBP selected ~~");
                }
                else{
                    holder.amount_currency = "Currency($)";
                    holder.amount_input.setError("specify currency");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    private void notification(String s){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    private void get_rent_id(ViewHolder holder, Apartment_Frag1_Content_Info conInfoData){
        Handler handler_00 = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                dBCR = dBR.child("apartment");
                String rent_id = dBCR.push().getKey();
                holder.rent_id_tv.setText(rent_id);
                notification("Rent ID is successfully obtained. But not activated until form submission");
            }
        };

        Thread updateUi = new Thread() {
            public void run() {
                try {
                    handler_00.sendEmptyMessage(0);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Log.d("failed", "exception");
                }
            }
        };
        updateUi.start();
    }

    private boolean amount_checker(ViewHolder holder, Apartment_Frag1_Content_Info conInfoData){
        if(holder.amount_currency.equals("Currency($)")){
            notification("Currency incorrect!!");
            return false;
        }
        try {
            int price = Integer.parseInt(holder.amount_input.getText().toString());
        }
        catch (Exception ex){
            notification("Amount info incorrect  !! Only integer allowed");
            ex.printStackTrace();
            return false;
        }

        return true;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView upper_content_text_titile;
        private RelativeLayout content;
        private FloatingActionButton add_new_rent;
        private MaterialButton rent_id_btn;
        private TextView rent_id_tv;
        private TextView owner_name_tv;
        private FloatingActionButton new_contact_num_btn;
        private TextView contact_num1_tv;
        private EditText contact_num2_input;
        private EditText address_input;
        private EditText amount_input;
        private Spinner amount_spinner;
        private static String amount_currency = "Currency($)";
        private EditText facilities_input;//description
        private SwitchCompat sw_01;
        private TextView left_sw_01;
        private TextView right_sw_01;
        private ImageButton img_for_house_btn;
        private ImageView img_for_house_iv;
        private FloatingActionButton form_save_btn;
        private FloatingActionButton form_publish_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            upper_content_text_titile = itemView.findViewById(R.id.apartment_frag1_upper_txt1);
            content = itemView.findViewById(R.id.apartment_frag1_lower);
            add_new_rent = itemView.findViewById(R.id.apartment_main_frag1_btn0);
            rent_id_btn = itemView.findViewById(R.id.apartment_main_frag1_btn1);
            rent_id_tv = itemView.findViewById(R.id.apartment_main_frag1_txt2);
            owner_name_tv = itemView.findViewById(R.id.apartment_main_frag1_txt4);
            contact_num1_tv = itemView.findViewById(R.id.apartment_main_frag1_txt6);
            new_contact_num_btn = itemView.findViewById(R.id.apartment_main_frag1_btn2);
            contact_num2_input = itemView.findViewById(R.id.apartment_main_frag1_input1);
            address_input = itemView.findViewById(R.id.apartment_main_frag1_input2);
            amount_input = itemView.findViewById(R.id.apartment_main_frag1_input3);
            amount_spinner = itemView.findViewById(R.id.list_currency);
            facilities_input = itemView.findViewById(R.id.apartment_main_frag1_input4);//description is facilities
            sw_01 = itemView.findViewById(R.id.apartment_main_frag1_swt1);
            left_sw_01 = itemView.findViewById(R.id.apartment_main_frag1_txt14);
            right_sw_01 = itemView.findViewById(R.id.apartment_main_frag1_txt15);
            img_for_house_btn = itemView.findViewById(R.id.apartment_main_frag1_img1_btn);
            img_for_house_iv = itemView.findViewById(R.id.apartment_main_frag1_img2);
            form_save_btn = itemView.findViewById(R.id.apartment_main_frag1_btn_form_save);
            form_publish_btn = itemView.findViewById(R.id.apartment_main_frag1_btn_form_publish);
        }
    }

}
