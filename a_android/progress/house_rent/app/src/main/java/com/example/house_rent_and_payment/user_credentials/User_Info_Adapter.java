package com.example.house_rent_and_payment.user_credentials;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import static android.content.ContentValues.TAG;

public class User_Info_Adapter
        extends RecyclerView.Adapter<User_Info_Adapter.ViewHolder> {

        private Context context;
        private User_Info[]conInfo;

        public User_Info_Adapter(Context context, User_Info []ci) {
            this.context = context;
            this.conInfo = ci;
        }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.c_b_user_profile_frag1_card_view,
                parent, false);
        return new com.example.house_rent_and_payment.user_credentials.User_Info_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Handler handler7_2 = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                final User_Info conInfoData = conInfo[position];
                reset_ui(holder);

                holder.username_tv.setText(conInfoData.getU_name());
                holder.password_tv.setText("******");
                holder.mobile_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changer(1,conInfoData);
                    }
                });

                holder.email_code.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        changer(2, conInfoData);
                    }
                });
                security_management(holder,conInfoData);


                holder.password_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        update(2,holder,conInfoData);
                    }
                });

                holder.username_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        update(1,holder,conInfoData);
                    }
                });
            }
        };

        Thread updateUi = new Thread() {
            public void run() {
                try {
                    handler7_2.sendEmptyMessage(0);
                }
                catch (Exception e) {
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

    private void update(int i, ViewHolder holder, User_Info conInfoData) {

        holder.pop_up_dialogue.setVisibility(View.VISIBLE);
        holder.main_view.setVisibility(View.GONE);

        holder.dialogue_cancel_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.pop_up_dialogue.setVisibility(View.GONE);
                holder.main_view.setVisibility(View.VISIBLE);
                holder.pop_up_content_05.setVisibility(View.GONE);
                holder.pop_up_content_06.setVisibility(View.GONE);
                holder.pop_up_content_07.setVisibility(View.GONE);
                holder.request_confirmation.setImageDrawable(context.getDrawable(R.drawable.loading));

                reset_ui(holder);
            }
        });

        holder.dialogue_submit_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String s1 = holder.dialogue_input_01.getText().toString();
                String s2 = conInfoData.getU_pass();
                if(s1.equals(s2)){
                holder.request_confirmation.setImageDrawable(context.getDrawable(R.drawable.checked));
                holder.pop_up_content_05.setVisibility(View.VISIBLE);
                holder.pop_up_content_06.setVisibility(View.VISIBLE);
                holder.pop_up_content_07.setVisibility(View.VISIBLE);

                reset_ui(holder);
                }
                else {
                    holder.request_confirmation.setImageDrawable(context.getDrawable(R.drawable.cancel_red_foreground));
                    notification("Wrong Password");
                    reset_ui(holder);
                }
            }
        });
        holder.dialogue_cancel_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.pop_up_dialogue.setVisibility(View.GONE);
                holder.main_view.setVisibility(View.VISIBLE);
                holder.pop_up_content_05.setVisibility(View.GONE);
                holder.pop_up_content_06.setVisibility(View.GONE);
                holder.pop_up_content_07.setVisibility(View.GONE);
                holder.request_confirmation.setImageDrawable(context.getDrawable(R.drawable.loading));

                reset_ui(holder);
            }
        });
        holder.dialogue_submit_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase dB = FirebaseDatabase.getInstance();
                DatabaseReference dBR = dB.getReference(); // main root
                DatabaseReference dBCR = dBR.child("users");
                String s1 = holder.dialogue_input_02.getText().toString();

                if (i ==1) {
                    if(s1.toString().length() >=6) {
                        dBCR.child(owner_u_id())
                                .child("user_info")
                                .child("u_name")
                                .setValue(s1);
                        notification("Successful");
                        holder.pop_up_dialogue.setVisibility(View.GONE);
                        holder.main_view.setVisibility(View.VISIBLE);
                        holder.pop_up_content_05.setVisibility(View.GONE);
                        holder.pop_up_content_06.setVisibility(View.GONE);
                        holder.pop_up_content_07.setVisibility(View.GONE);
                        holder.request_confirmation.setImageDrawable(context.getDrawable(R.drawable.loading));

                    }
                    else {
                        notification("Username should be at least 6 digit long");
                    }
                }
                else if(i==2){
                    if(s1.toString().trim().length() > 5) {
                        FirebaseAuth fA = FirebaseAuth.getInstance();
                        FirebaseUser user = fA.getCurrentUser();
                        user.updatePassword(s1).addOnCompleteListener(
                                                 new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if (task.isSuccessful()) {
                                    dBCR.child(owner_u_id())
                                            .child("user_info")
                                            .child("u_pass")
                                            .setValue(s1);
                                    notification("Successful");
                                    holder.pop_up_dialogue.setVisibility(View.GONE);
                                    holder.main_view.setVisibility(View.VISIBLE);
                                    holder.pop_up_content_05.setVisibility(View.GONE);
                                    holder.pop_up_content_06.setVisibility(View.GONE);
                                    holder.pop_up_content_07.setVisibility(View.GONE);
                                    holder.request_confirmation.setImageDrawable(context.getDrawable(R.drawable.loading));

                                    Log.d(TAG, "Password updated");
                                } else {
                                    Log.d(TAG, "Error password not updated");
                                }
                            }
                        });
                    }
                    else {
                        notification("Password should be at least 6 digit long");
                    }
                }
                else {
                    notification("Failed ! Password Criteria Not Matched");
                }
                reset_ui(holder);
            }
        });
    }

    private void reset_ui(ViewHolder holder) {
        Handler handler7_2 = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                holder.dialogue_input_01.getText().clear();
                holder.dialogue_input_02.getText().clear();
            }
        };
        Thread updateUi = new Thread() {
                public void run() {
        try {
             handler7_2.sendEmptyMessage(0);
        }
        catch (Exception e) {
             e.printStackTrace();
            Log.d("failed", "exception");
        }
        }
            };
        updateUi.start();
    }

    private void changer(int i, User_Info conInfoData) {
        FirebaseDatabase dB = FirebaseDatabase.getInstance();
        DatabaseReference dBR = dB.getReference(); // main root
        DatabaseReference dBCR = dBR.child("users");
        if(i == 1){
            if(conInfoData.getSecurity_method()==0){
                dBCR.child(owner_u_id())
                    .child("user_info")
                    .child("security_method")
                    .setValue(1);
            }
            else if(conInfoData.getSecurity_method()==1){
                dBCR.child(owner_u_id())
                        .child("user_info")
                        .child("security_method")
                        .setValue(0);
            }
            else if (conInfoData.getSecurity_method()==2){
                dBCR.child(owner_u_id())
                        .child("user_info")
                        .child("security_method")
                        .setValue(3);
            }
            else {
                dBCR.child(owner_u_id())
                        .child("user_info")
                        .child("security_method")
                        .setValue(2);
            }
        }
        else if(i==2){
            if(conInfoData.getSecurity_method()==0){
                dBCR.child(owner_u_id())
                        .child("user_info")
                        .child("security_method")
                        .setValue(2);
            }
            else if(conInfoData.getSecurity_method()==1){
                dBCR.child(owner_u_id())
                        .child("user_info")
                        .child("security_method")
                        .setValue(3);
            }
            else if (conInfoData.getSecurity_method()==2){
                dBCR.child(owner_u_id())
                        .child("user_info")
                        .child("security_method")
                        .setValue(0);
            }
            else {
                dBCR.child(owner_u_id())
                        .child("user_info")
                        .child("security_method")
                        .setValue(1);
            }
        }
    }
    private void security_management(ViewHolder holder, User_Info conInfoData) {
    if(conInfoData.getSecurity_method()==1){
        holder.mobile_code.setChecked(true);
        holder.email_code.setChecked(false);
    }
    else if (conInfoData.getSecurity_method()==2){
        holder.mobile_code.setChecked(false);
        holder.email_code.setChecked(true);
    }
    else if (conInfoData.getSecurity_method()==3){
        holder.mobile_code.setChecked(true);
        holder.email_code.setChecked(true);
    }
    else {
        holder.mobile_code.setChecked(false);
        holder.email_code.setChecked(false);
    }
    }

    private String owner_u_id(){
        FirebaseAuth fA = FirebaseAuth.getInstance();
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

        public static class ViewHolder extends RecyclerView.ViewHolder{

            TextView username_tv;
            TextView password_tv;
            FloatingActionButton username_btn;
            FloatingActionButton password_btn;
            CheckBox mobile_code;
            CheckBox email_code;

            RelativeLayout main_view;
            RelativeLayout pop_up_dialogue;
            RelativeLayout pop_up_content_05;
            RelativeLayout pop_up_content_06;
            RelativeLayout pop_up_content_07;

            Button dialogue_submit_01;
            Button dialogue_submit_02;
            Button dialogue_cancel_01;
            Button dialogue_cancel_02;

            EditText dialogue_input_01;
            EditText dialogue_input_02;

            ImageView request_confirmation;

            public ViewHolder(View itemView) {
                super(itemView);

                username_tv = itemView.findViewById(R.id.c_b_user_profile_main_frag1_txt2);
                password_tv = itemView.findViewById(R.id.c_b_user_profile_main_frag1_txt4);
                username_btn = itemView.findViewById(R.id.c_b_user_profile_main_frag1_float_btn1);
                password_btn = itemView.findViewById(R.id.c_b_user_profile_main_frag1_float_btn2);
                mobile_code = itemView.findViewById(R.id.c_b_user_profile_main_frag1_checkBox1);
                email_code = itemView.findViewById(R.id.c_b_user_profile_main_frag1_checkBox2);

                main_view = itemView.findViewById(R.id.c_b_user_profile_frag1_main_view);
                pop_up_dialogue = itemView.findViewById(R.id.c_b_user_profile_frag1_pop_up_view);
                pop_up_content_05 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_sheet_05);
                pop_up_content_06 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_sheet_06);
                pop_up_content_07 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_sheet_07);

                dialogue_cancel_01 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_close_btn_01);
                dialogue_submit_01 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_submit_btn_01);
                dialogue_cancel_02 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_close_btn_02);
                dialogue_submit_02 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_submit_btn_02);

                dialogue_input_01 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_sheet_02_input_01);
                dialogue_input_02 = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_sheet_06_input);
                request_confirmation = itemView.findViewById(R.id.c_b_user_profile_frag1_dialog_sheet_04_img);
            }
    }
 }
