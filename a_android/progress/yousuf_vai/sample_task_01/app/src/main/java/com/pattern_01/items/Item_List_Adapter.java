package com.pattern_01.items;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern_01.R;
import com.pattern_01.display.Display_List_Adapter;

public class Item_List_Adapter extends RecyclerView.Adapter<Item_List_Adapter.ViewHolder>{
    private Context context;
    private String []conInfo;

    public Item_List_Adapter(Context context, String[] conInfo) {
        this.context = context;
        this.conInfo = conInfo;
    }

    @NonNull
    @Override
    public Item_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.g_b_item_list_support,
                parent, false);
        return new Item_List_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Item_List_Adapter.ViewHolder holder, int position) {
        final String conInfoData = conInfo[position];
        holder.tv_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Item_Details.class);
                ((Activity)context).startActivityForResult(intent,1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_item;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_item = itemView.findViewById(R.id.g_b_item_list_port_01_tv);
        }
    }
}
