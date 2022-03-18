package com.example.football_management_system.owner;

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

public class Owner_Bring_Manager_Adapter
        extends RecyclerView.Adapter<Owner_Bring_Manager_Adapter.ViewHolder> {

    private Context context;
    private User_Info []conInfo;
    private int flag = 0;

    public Owner_Bring_Manager_Adapter(Context context, User_Info []conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Fetching List");
    }

    @NonNull
    @Override
    public Owner_Bring_Manager_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.owner_content6_card,
                parent, false);
        return new Owner_Bring_Manager_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Owner_Bring_Manager_Adapter.ViewHolder holder, int position) {
        User_Info contInfoData = conInfo[position];
        holder.id.setText(contInfoData.getId());
        holder.name.setText(contInfoData.getName());
        holder.country.setText(contInfoData.getCountry());
        holder.age.setText(contInfoData.getAge());
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
        TextView age;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.owner_content6_port_00_02_tv);
            name = itemView.findViewById(R.id.owner_content6_port_02_tv);
            country = itemView.findViewById(R.id.owner_content6_port_04_tv);
            age = itemView.findViewById(R.id.owner_content6_port_06_tv);
        }
    }
}
