package com.pattern_01.display;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.pattern_01.R;
import com.pattern_01.items.Item_List;
import com.pattern_01.processor.Processor_List_Adapter;

public class Display_List_Adapter extends RecyclerView.Adapter<Display_List_Adapter.ViewHolder>{
    private Context context;
    private String []conInfo;

    public Display_List_Adapter(Context context, String[] conInfo) {
        this.context = context;
        this.conInfo = conInfo;
    }

    @NonNull
    @Override
    public Display_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.e_b_display_list_support,
                parent, false);
        return new Display_List_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Display_List_Adapter.ViewHolder holder, int position) {
        final String conInfoData = conInfo[position];
        holder.component_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Item_List.class);
                ((Activity)context).startActivityForResult(intent,1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView component_icon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            component_icon = itemView.findViewById(R.id.e_display_list_support_port_02_sub_01_img);
        }
    }
}
