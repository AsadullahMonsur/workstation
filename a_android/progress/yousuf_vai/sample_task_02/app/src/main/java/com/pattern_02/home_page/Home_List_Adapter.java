package com.pattern_02.home_page;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern_02.R;
import com.pattern_02.customization.Customization;

public class Home_List_Adapter extends RecyclerView.Adapter<Home_List_Adapter.ViewHolder>{
   private Context context;
   private String []conInfo;
   private int checker = 0;
    public Home_List_Adapter(Context context, String[] conInfo) {
        this.context = context;
        this.conInfo = conInfo;
    }

    @NonNull
    @Override
    public Home_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.b_b_main_view_list_support,
                parent, false);
        return new Home_List_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Home_List_Adapter.ViewHolder holder, int position) {
        final String conInfoData = conInfo[position];

        holder.active_status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checker==0){
                    holder.active_status.setImageResource(R.drawable.on);
                    checker = 1;
                }
                else {
                    holder.active_status.setImageResource(R.drawable.off);
                    checker = 0;
                }
            }
        });
        holder.component.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Customization.class);
                ((Activity)context).startActivityForResult(intent,3);
            }
        });
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView active_status;
        TextView component;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            active_status = itemView.findViewById(R.id.b_b_main_view_list_support_port_01_img);
            component = itemView.findViewById(R.id.b_b_main_view_list_support_port_02_tv);
        }
    }
}
