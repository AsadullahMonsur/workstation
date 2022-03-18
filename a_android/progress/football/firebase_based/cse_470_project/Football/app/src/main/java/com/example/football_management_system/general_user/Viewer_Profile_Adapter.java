package com.example.football_management_system.general_user;

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
import com.example.football_management_system.user_authentication.Sign_Up_Data;

public class Viewer_Profile_Adapter
       extends RecyclerView.Adapter<Viewer_Profile_Adapter.ViewHolder> {

    private Context context;
    private Sign_Up_Data []conInfo;
    private int flag = 0;
    public Viewer_Profile_Adapter(Context context, Sign_Up_Data []conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Fetching Data");
        flag = 0;
    }

    @NonNull
    @Override
    public Viewer_Profile_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.viewer_content1_card,
                parent, false);
        return new Viewer_Profile_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewer_Profile_Adapter.ViewHolder holder, int position) {
        final Sign_Up_Data conInfoData = conInfo[position];
        holder.f_name.setText(conInfoData.getFname());
        holder.l_name.setText(conInfoData.getLname());
        holder.email.setText(conInfoData.getEmail());
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
        return conInfo.length;
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView f_name;
        TextView l_name;
        TextView email;
        EditText input;
        Button reset_pass;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            f_name = itemView.findViewById(R.id.viewer_cont1_id);
            l_name = itemView.findViewById(R.id.viewer_cont1_name);
            email = itemView.findViewById(R.id.viewer_cont1_email);
            input = itemView.findViewById(R.id.viewer_cont1_password);
            reset_pass = itemView.findViewById(R.id.viewer_confirm_change);
        }
    }
    private boolean validity(EditText editText,Context context){
        if(editText.getText().toString().trim().length() > 5){
            return true;
        }
        notification(context,"Not a Valid Password");
        return false;
    }
}
