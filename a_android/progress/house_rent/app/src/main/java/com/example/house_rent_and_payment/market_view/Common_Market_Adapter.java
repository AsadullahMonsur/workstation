package com.example.house_rent_and_payment.market_view;

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

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.apartment_details.Apartment_Frag2_Adapter;
import com.example.house_rent_and_payment.apartment_details.Apartment_Frag2_Content_Info;
import com.example.house_rent_and_payment.others.Image_File;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

import java.util.List;


public class Common_Market_Adapter extends
        RecyclerView.Adapter<Common_Market_Adapter.ViewHolder> {

    private Context context;
    private Common_Market_Content_Info []conInfo;

    public Common_Market_Adapter(Context context,Common_Market_Content_Info[]ci) {
        this.context = context;
        this.conInfo = ci;
    }

    @NonNull
    @Override
    public Common_Market_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.j_a_common_market_view,
                parent, false);
        return new Common_Market_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Common_Market_Adapter.ViewHolder holder, int position) {
        Handler handler7_2 = new Handler(Looper.getMainLooper()) {
            public void handleMessage(Message msg) {
                final Common_Market_Content_Info conInfoData = conInfo[position];
                Picasso.get()
                        .load(conInfoData.getProfile_img().getImg_url())
                        .error(R.drawable.cancel_foreground)
                        .into(holder.profile_img);

                holder.owner_name_tv.setText(conInfoData.getOwner_name());
                String rate_unit = "** "+conInfoData.getUser_rating()+" **";
                holder.owner_ratings_tv.setText(rate_unit);
                holder.location_tv.setText(conInfoData.getLocation());
                String amount_unit = conInfoData.getAmount().getPrice()+
                                           "  "+conInfoData.getAmount().getCurrency();
                holder.amount_tv.setText(amount_unit);
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

    public static class ViewHolder extends RecyclerView.ViewHolder{
        ImageView profile_img;
        TextView owner_name_tv;
        TextView owner_ratings_tv;
        TextView amount_tv;
        TextView location_tv;
        Button detail_view_btn;
        Button go_to_map_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            profile_img = itemView.findViewById(R.id.j_a_common_market_view_profile_img);
            owner_name_tv = itemView.findViewById(R.id.j_a_common_market_view_title_txt);
            owner_ratings_tv = itemView.findViewById(R.id.j_a_common_market_view_support_txt);
            location_tv = itemView.findViewById(R.id.j_a_common_market_view_location_txt2);
            amount_tv = itemView.findViewById(R.id.j_a_common_market_view_amount_txt2);
            detail_view_btn = itemView.findViewById(R.id.j_a_common_market_view_full_view_btn);
            go_to_map_btn = itemView.findViewById(R.id.j_a_common_market_view_google_map_btn);
        }
    }
}
