package com.pattern_01.processor;

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

import com.pattern_01.R;
import com.pattern_01.components.Component_Selection;

public class Processor_List_Adapter extends RecyclerView.Adapter<Processor_List_Adapter.ViewHolder>{

    private Context context;
    private String []conInfo;

    public Processor_List_Adapter(Context context, String []ci) {
        this.context = context;
        this.conInfo = ci;
    }

    @NonNull
    @Override
    public Processor_List_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.c_b_processor_list_support,
                parent, false);
        return new Processor_List_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Processor_List_Adapter.ViewHolder holder, int position) {
        final String conInfoData = conInfo[position];
        holder.subtitle.setText(conInfoData);
        holder.process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    Intent intent = new Intent(context, Component_Selection.class);
                    ((Activity) context).startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView process;
        TextView subtitle;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            process = itemView.findViewById(R.id.c_b_processor_list_support_card_01_port_01_img);
            subtitle = itemView.findViewById(R.id.c_b_processor_list_support_card_01_port_02_tv);
        }
    }
}
