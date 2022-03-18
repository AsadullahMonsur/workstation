package com.example.football_management_system.accessories;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_management_system.R;

public class Profile_Update_Adapter extends
        RecyclerView.Adapter<Profile_Update_Adapter.ViewHolder>{

    private Context context;
    private User_Info []conInfo;
    private int flag = 0;
    public Profile_Update_Adapter(Context context, User_Info[] conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Fetching Data");
        flag = 0;
    }

    @NonNull
    @Override
    public Profile_Update_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.profile_content1_card,
                parent, false);
        return new Profile_Update_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Profile_Update_Adapter.ViewHolder holder, int position) {
        final User_Info conInfoData = conInfo[position];
        holder.id.setText(conInfoData.getId());
        holder.name.setText(conInfoData.getName());
        holder.country.setText(conInfoData.getCountry());
        holder.reset_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    boolean result = validity(holder.input,context);
                    if(result){
                        flag = 1;
                        notification(context,"Success");
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return  conInfo.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView id;
        TextView name;
        TextView country;
        EditText input;
        Button reset_pass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.profile_cont1_id);
            name = itemView.findViewById(R.id.profile_cont1_name);
            country = itemView.findViewById(R.id.profile_cont1_country);
            input = itemView.findViewById(R.id.profile_cont1_password);
            reset_pass = itemView.findViewById(R.id.profile_cont1_confirm_change);
        }
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    private boolean validity(EditText editText,Context context){
        if(editText.getText().toString().trim().length() > 5){
            return true;
        }
        notification(context,"Not a Valid Password");
        return false;
    }

}
