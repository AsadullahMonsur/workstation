package com.example.football_management_system.general_user;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_management_system.R;
import com.example.football_management_system.accessories.User_Info;

public class Viewer_Team_Info  extends RecyclerView.Adapter<Viewer_Team_Info.ViewHolder> {
    private Context context;
    private User_Info []conInfo;
    private int flag = 0;

    public Viewer_Team_Info(Context context, User_Info []conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Fetching Data");
        flag = 0;
    }


    @NonNull
    @Override
    public Viewer_Team_Info.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.viewer_content2_card,
                parent, false);
        return new Viewer_Team_Info.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewer_Team_Info.ViewHolder holder, int position) {
        User_Info user_info = conInfo[position];
        String s1 = "Player ID:   "+user_info.getId();
        String s2  = "Name:   "+user_info.getName();
        String s3  = "Country:   "+user_info.getCountry();
        String s4 = "Position:   "+user_info.getPosition();
        holder.id.setText(s1);
        holder.name.setText(s2);
        holder.country.setText(s3);
        holder.position.setText(s4);
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView id;
        TextView name;
        TextView country;
        TextView position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.viewer_cont2_port_01_card_tv1);
            name = itemView.findViewById(R.id.viewer_cont2_port_02_card_tv1);
            country = itemView.findViewById(R.id.viewer_cont2_port_03_card_tv1);
            position = itemView.findViewById(R.id.viewer_cont2_port_04_card_tv1);
        }
    }
}
