package com.example.football_management_system.player;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_management_system.R;

public class Player_View_Goal_Adapter
       extends RecyclerView.Adapter<Player_View_Goal_Adapter.ViewHolder> {
    private Context context;
    private int[]conInfo;
    private int flag = 0;

    public Player_View_Goal_Adapter(Context context, int []conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Fetching Data");
    }
    @NonNull
    @Override
    public Player_View_Goal_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.player_content3_card,
                parent, false);
        return new Player_View_Goal_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Player_View_Goal_Adapter.ViewHolder holder, int position) {
        int conInfoData = conInfo[position];
        String s = conInfoData+"";
        holder.goal.setText(s);
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView goal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goal = itemView.findViewById(R.id.player_cont3_goals);
        }
    }
}
