package com.example.house_rent_and_payment.rent_details;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.rent_details.Rent_Frag2_Content_Info;
import com.example.house_rent_and_payment.others.Amount;
import com.example.house_rent_and_payment.others.Contact;
import com.example.house_rent_and_payment.others.Image_File;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;

public class Rent_Frag2_Adapter
        extends RecyclerView.Adapter<com.example.house_rent_and_payment.rent_details.Rent_Frag2_Adapter.ViewHolder> {

    private Context context;
    private Rent_Frag2_Content_Info []conInfo;
    private int counter;
    private int []rate_box = new int[5];

    public Rent_Frag2_Adapter(Context context,
                                   Rent_Frag2_Content_Info []ci) {
        this.context = context;
        this.conInfo = ci;
    }

    @NonNull
    @Override
    public com.example.house_rent_and_payment.rent_details.Rent_Frag2_Adapter.ViewHolder onCreateViewHolder(
            @NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.j_b_full_deal_card_view,
                parent, false);
        return new com.example.house_rent_and_payment.rent_details.Rent_Frag2_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(
            @NonNull com.example.house_rent_and_payment.rent_details.Rent_Frag2_Adapter.ViewHolder holder, int position) {

        Handler handler7_2 = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                final Rent_Frag2_Content_Info conInfoData = conInfo[position];
                //holder.profile_img.setImageBitmap();
                String rent_id_info = "Rent Id:   "+conInfoData.getRent_id();
                holder.rent_id_tv.setText(rent_id_info);
                holder.location_tv.setText(conInfoData.getLocation());
                holder.amount_tv
                        .setText(conversion_amount(conInfoData.getAmount()));
                holder.contact_tv
                        .setText(conversion_contact(conInfoData.getContact_number()));
                holder.description_tv.setText(conInfoData.getDescription());
                List<Image_File> image_fileList = conInfoData.getImg_list();
                image_switcher(holder,image_fileList);
                get_ratings(holder,conInfoData);
                rater(holder,conInfoData);
                edit_rent(holder,conInfoData);
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

    private void edit_rent(ViewHolder holder,
                           Rent_Frag2_Content_Info content_info_data) {
        if(content_info_data.getUser_mode()!=1){
            holder.edit.setBackgroundColor(
                    ContextCompat.getColor(context, R.color.Red));
            holder.edit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notification("You are not authorized to this facility");
                }
            });
        }
    }

    private void rater(com.example.house_rent_and_payment.rent_details.Rent_Frag2_Adapter.ViewHolder holder,
                       Rent_Frag2_Content_Info content_info_data) {
    if(content_info_data.getUser_mode()==3){

        holder.rate_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate_box[0]==0){
                    rate_box[0] = 1;
                    holder.rate_01.setImageDrawable(((Activity)context)
                          .getDrawable(R.drawable.rate_star_full));
                }
                else if(rate_box[0]==1){
                    rate_box[0] = 0;
                    holder.rate_01.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_empty));                }
            }
        });
        holder.rate_02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate_box[1]==0){
                    rate_box[1] = 1;
                    holder.rate_02.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_full));
                }
                else if(rate_box[1]==1){
                    rate_box[1] = 0;
                    holder.rate_02.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_empty));                }
            }
        });
        holder.rate_03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate_box[2]==0){
                    rate_box[2] = 1;
                    holder.rate_03.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_full));
                }
                else if(rate_box[2]==1){
                    rate_box[2] = 0;
                    holder.rate_03.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_empty));                }
            }
        });
        holder.rate_04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate_box[3]==0){
                    rate_box[3] = 1;
                    holder.rate_04.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_full));
                }
                else if(rate_box[3]==1){
                    rate_box[3] = 0;
                    holder.rate_04.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_empty));                }
            }
        });
        holder.rate_05.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(rate_box[4]==0){
                    rate_box[4] = 1;
                    holder.rate_05.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_full));
                }
                else if(rate_box[4]==1){
                    rate_box[4] = 0;
                    holder.rate_05.setImageDrawable(((Activity)context)
                            .getDrawable(R.drawable.rate_star_empty));                }
            }
        });

        holder.rate_06.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int result_picker = 0;
                int sum = 0;
                while(result_picker<5){
                    sum = rate_box[result_picker]+ sum;
                    result_picker++;
                }
                notification("Total Rate :  "+sum);
                FirebaseDatabase dB = FirebaseDatabase.getInstance();
                DatabaseReference dBR = dB.getReference(); // main root
                DatabaseReference dBCR = dBR.child("apartment");
                dBCR.child(content_info_data.getRent_id())
                        .child("user_rating")
                        .setValue(sum);
            }
        });
    }
  }

    private void get_ratings(com.example.house_rent_and_payment.rent_details.Rent_Frag2_Adapter.ViewHolder holder,
                             Rent_Frag2_Content_Info content_info_data) {
        if(content_info_data.getUser_rating()!=-1.0){
            String rate_plate = "**"+ content_info_data.getUser_rating()+"**";
            holder.owner_ratings.setText(rate_plate);
        }
    }

    private void image_switcher(com.example.house_rent_and_payment.rent_details.Rent_Frag2_Adapter.ViewHolder holder, List<Image_File> image_fileList) {
        int element_number = image_fileList.size();
        counter = 0;
        Picasso.get()
                .load(image_fileList.get(counter).getImg_url())
                .error(R.drawable.cancel_foreground)
                .into(holder.view_photos);

        holder.next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter<(element_number-1)){
                    counter++;
                    Picasso.get()
                            .load(image_fileList.get(counter).getImg_url())
                            .error(R.drawable.cancel_foreground)
                            .into(holder.view_photos);
                }
                else{
                    counter = 0;
                    Picasso.get()
                            .load(image_fileList.get(counter).getImg_url())
                            .error(R.drawable.cancel_foreground)
                            .into(holder.view_photos);
                }

            }
        });

        holder.previous_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter>=1){
                    counter--;
                    Picasso.get()
                            .load(image_fileList.get(counter).getImg_url())
                            .error(R.drawable.cancel_foreground)
                            .into(holder.view_photos);
                }
                else{
                    counter = element_number-1;
                    Picasso.get()
                            .load(image_fileList.get(counter).getImg_url())
                            .error(R.drawable.cancel_foreground)
                            .into(holder.view_photos);
                }
            }
        });
    }

    private String conversion_amount(Amount amount){
        return amount.getPrice()+"  "+amount.getCurrency();
    }

    private String conversion_contact(Contact contact){
        return contact.getNum1()+" ,\n"+contact.getNum2();
    }

    private void notification(String s){
        Toast.makeText(context, s, Toast.LENGTH_SHORT).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView profile_img;
        TextView rent_id_tv;
        TextView location_tv;
        TextView amount_tv;
        TextView contact_tv;
        TextView description_tv;
        ImageView view_photos;
        FloatingActionButton next_btn;
        FloatingActionButton previous_btn;
        Button edit;
        Button cancel;
        Button active;
        TextView publish_date;
        TextView rent_access_publisher;
        TextView rent_date;
        TextView rent_access_renter;
        FloatingActionButton rate_01;
        FloatingActionButton rate_02;
        FloatingActionButton rate_03;
        FloatingActionButton rate_04;
        FloatingActionButton rate_05;
        Button rate_06;
        TextView owner_ratings;

        public ViewHolder(View itemView) {
            super(itemView);
//            profile_img = itemView.findViewById(R.id.j_b_full_deal_card_view_2_profile_img);
//            rent_id_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_2_rent_id);
//            location_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_2_location2);
//            amount_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_2_amount_2);
//            contact_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_2_contact_2);
//            description_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_2_description_tv);
//            view_photos = itemView.findViewById(R.id.j_b_full_deal_card_view_2_photos);
//            next_btn = itemView.findViewById(R.id.j_b_full_deal_card_view_2_photo_btn_next);
//            previous_btn = itemView.findViewById(R.id.j_b_full_deal_card_view_2_photo_btn_previous);
//            edit = itemView.findViewById(R.id.j_b_full_deal_card_view_2_bottom_btn1);
//            cancel = itemView.findViewById(R.id.j_b_full_deal_card_view_2_bottom_btn2);
//            active = itemView.findViewById(R.id.j_b_full_deal_card_view_2_bottom_btn3);
//            publish_date = itemView.findViewById(R.id.j_b_full_deal_card_view_2_publish_date_tv);
//            rent_access_publisher = itemView.findViewById(R.id.j_b_full_deal_card_view_2_access_publisher_tv);
//            rent_date = itemView.findViewById(R.id.j_b_full_deal_card_view_2_rent_date_tv);
//            rent_access_renter = itemView.findViewById(R.id.j_b_full_deal_card_view_2_access_renter_tv);
//            rate_01 = itemView.findViewById(R.id.j_b_full_deal_card_view_2_rate_star_btn_1);
//            rate_02 = itemView.findViewById(R.id.j_b_full_deal_card_view_2_rate_star_btn_2);
//            rate_03 = itemView.findViewById(R.id.j_b_full_deal_card_view_2_rate_star_btn_3);
//            rate_04 = itemView.findViewById(R.id.j_b_full_deal_card_view_2_rate_star_btn_4);
//            rate_05 = itemView.findViewById(R.id.j_b_full_deal_card_view_2_rate_star_btn_5);
//            rate_06 = itemView.findViewById(R.id.j_b_full_deal_card_view_2_rate_star_btn_6);


            profile_img = itemView.findViewById(R.id.j_b_full_deal_card_view_profile_img);
            rent_id_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_rent_id);
            location_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_location2);
            amount_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_amount_2);
            contact_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_contact_2);
            description_tv = itemView.findViewById(R.id.j_b_full_deal_card_view_description_tv);
            view_photos = itemView.findViewById(R.id.j_b_full_deal_card_view_photos);
            next_btn = itemView.findViewById(R.id.j_b_full_deal_card_view_photo_btn_next);
            previous_btn = itemView.findViewById(R.id.j_b_full_deal_card_view_photo_btn_previous);
            edit = itemView.findViewById(R.id.j_b_full_deal_card_view_bottom_btn1);
            cancel = itemView.findViewById(R.id.j_b_full_deal_card_view_bottom_btn2);
            active = itemView.findViewById(R.id.j_b_full_deal_card_view_bottom_btn3);
            publish_date = itemView.findViewById(R.id.j_b_full_deal_card_view_publish_date_tv);
            rent_access_publisher = itemView.findViewById(R.id.j_b_full_deal_card_view_access_publisher_tv);
            rent_date = itemView.findViewById(R.id.j_b_full_deal_card_view_rent_date_tv);
            rent_access_renter = itemView.findViewById(R.id.j_b_full_deal_card_view_access_renter_tv);
            rate_01 = itemView.findViewById(R.id.j_b_full_deal_card_view_rate_star_btn_1);
            rate_02 = itemView.findViewById(R.id.j_b_full_deal_card_view_rate_star_btn_2);
            rate_03 = itemView.findViewById(R.id.j_b_full_deal_card_view_rate_star_btn_3);
            rate_04 = itemView.findViewById(R.id.j_b_full_deal_card_view_rate_star_btn_4);
            rate_05 = itemView.findViewById(R.id.j_b_full_deal_card_view_rate_star_btn_5);
            rate_06 = itemView.findViewById(R.id.j_b_full_deal_card_view_rate_star_btn_6);
            owner_ratings = itemView.findViewById(R.id.j_b_full_deal_card_view_owner_ratings_tv);

        }
    }
}
