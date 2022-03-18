package com.example.house_rent_and_payment.user_credentials;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.others.Country;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class User_Personal_Info_Adapter extends ArrayAdapter<User_Personal_Info> {

    private Context context;
    private FirebaseAuth fA;

    private String mVerificationId;
    private PhoneAuthProvider.ForceResendingToken mResendToken;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    private String country_code = "000";

    public User_Personal_Info_Adapter(@NonNull Context context,
                         ArrayList<User_Personal_Info> user_personal_infos) {
        super(context,0,user_personal_infos);
        this.context = context;
        fA = FirebaseAuth.getInstance();
    }

    @Override
    public View getView(int position, View holder, ViewGroup parent) {

        User_Personal_Info user_personal_info = getItem(position);

        if (holder == null) {
            holder = LayoutInflater.from(getContext())
                                  .inflate(R.layout.c_b_user_profile_frag2_card_view,
                                  parent, false);
        }

        final View holders = holder;

        MaterialCardView main_view = holders.findViewById(R.id.profile_frag2_card7);
        MaterialCardView pop_up_view = holders.findViewById(R.id.profile_frag2_card8);

        update_ui(holders,user_personal_info);
        contact_confirmation(holders,main_view,pop_up_view);

        return holder;

    }

    private void update_ui(View holders, User_Personal_Info user_personal_info) {
        TextView first_name_tv = (TextView) holders.findViewById(R.id.profile_main_frag2_txt2);
        TextView last_name_tv = (TextView) holders.findViewById(R.id.profile_main_frag2_txt4);
        TextView email_tv = (TextView) holders.findViewById(R.id.profile_main_frag2_txt6);
        TextView national_id_tv = (TextView) holders.findViewById(R.id.profile_main_frag2_txt8);
        TextView number_tv = (TextView) holders.findViewById(R.id.profile_main_frag2_txt10);

        first_name_tv.setText(user_personal_info.getF_name());
        last_name_tv.setText(user_personal_info.getL_name());
        email_tv.setText(user_personal_info.getU_email());
        national_id_tv.setText(user_personal_info.getNational_id());
        number_tv.setText(user_personal_info.getContact_no());
    }

    private void contact_confirmation(View holders, MaterialCardView main_view, MaterialCardView pop_up_view) {
        String amount_currency = "Currency($)";
        Country country = new Country();

        Spinner country_spinner = holders.findViewById(R.id.profile_frag2_card7_lower_spinner);
        country_spinner.setAdapter(new ArrayAdapter<String>(context,
                android.R.layout.simple_spinner_dropdown_item,country.getCountry_name()));
        country_spinner
                .setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if(position==0) {
                            if (position == 1) {
                                int s[] = country.getCountry_mobile_code_prefix();
                                notification(""+s[position]);
                                String names[]  = country.getCountry_name();
                                country_code = ""+s[position];
                            }
                            else if (position == 2) {
                                int s[] = country.getCountry_mobile_code_prefix();
                                notification(""+s[position]);
                                country_code = ""+s[position];
                            }
                            else if (position == 3) {
                                int s[] = country.getCountry_mobile_code_prefix();
                                notification(""+s[position]);
                                country_code = ""+s[position];
                            }
                        }
                        else{
                            int s[] = country.getCountry_mobile_code_prefix();
                            notification(""+s[position]);
                            country_code = ""+s[position];
                        }
                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {
                    }
                });

        Button verify_btn = holders.findViewById(R.id.profile_frag2_card7_lower_submit_btn);
        verify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                main_view.setVisibility(View.GONE);
                pop_up_view.setVisibility(View.VISIBLE);
                send_otp(holders);
                input_verify(holders,main_view,pop_up_view);
            }
        });

    }

    private void send_otp(View holder) {
        EditText input = holder.findViewById(R.id.profile_frag2_card7_lower_number_input);

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
                signInWithPhoneAuthCredential(credential);
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e("otp_error", e.getMessage());
                notification("OTP Failed");
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                mVerificationId = s;
                mResendToken = forceResendingToken;
            }
        };

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(fA)
                        .setPhoneNumber("+"+country_code+input.getText().toString())       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity((Activity) context)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void verifyPhoneNumberWithCode(String verificationId, String code) {
        // [START verify_with_code]
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        // [END verify_with_code]
    }
    private void resendVerificationCode(String phoneNumber,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(fA)
                        .setPhoneNumber(phoneNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity((Activity) context)                 // Activity (for callback binding)
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .setForceResendingToken(token)     // ForceResendingToken from callbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        fA.signInWithCredential(credential)
          .addOnCompleteListener((Activity) context,
              new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                 if (task.isSuccessful()) {
                    notification("Done OTP");
                 }
                 else {
                    notification("Failed OTP");
                 }
              }
        });
    }

    private void input_verify(View holders, MaterialCardView main_view, MaterialCardView pop_up_view) {
    Button cancel = holders.findViewById(R.id.c_b_user_profile_frag2_pop_up_view_sheet_03_close_btn_01);
    Button submit = holders.findViewById(R.id.c_b_user_profile_frag2_pop_up_view_sheet_03_submit_btn_01);

    cancel.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            pop_up_view.setVisibility(View.GONE);
            main_view.setVisibility(View.VISIBLE);
        }
    });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pop_up_view.setVisibility(View.GONE);
                main_view.setVisibility(View.VISIBLE);
            }
        });
    }

    private void notification(String s){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }
    
}
