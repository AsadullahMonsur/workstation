package com.example.football_management_system.manager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_management_system.R;
import com.example.football_management_system.accessories.View_Team_Statistics;

public class Manager_View_Matches_Adapter
       extends RecyclerView.Adapter<Manager_View_Matches_Adapter.ViewHolder> {
    private Context context;
    private View_Team_Statistics []conInfo;
    private int flag = 0;

    public Manager_View_Matches_Adapter(Context context, View_Team_Statistics []conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Preparing View");
    }

    @NonNull
    @Override
    public Manager_View_Matches_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.manager_content5_card,
                parent, false);
        return new Manager_View_Matches_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Manager_View_Matches_Adapter.ViewHolder holder, int position) {
        final View_Team_Statistics vts = conInfo[position];
        holder.match_id.setText(vts.getMatch_id());
        holder.list_of_scorer.setText(vts.getList_of_scorer());
        holder.goal_times.setText(vts.getGoal_time());
        holder.list_of_injured.setText(vts.getList_of_got_injured());
        holder.injured_time.setText(vts.getInjured_time());
        holder.list_of_got_card.setText(vts.getList_of_got_card());
        holder.got_card_time.setText(vts.getCard_time());
        holder.card_type.setText(vts.getCard_type());
    }


    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView match_id;
        TextView list_of_scorer;
        TextView goal_times;
        TextView list_of_injured;
        TextView injured_time;
        TextView list_of_got_card;
        TextView got_card_time;
        TextView card_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            match_id = itemView.findViewById(R.id.manager_cont5_port_02_card_tv1);
            list_of_scorer = itemView.findViewById(R.id.manager_cont5_port_05_card_tv1);
            goal_times = itemView.findViewById(R.id.manager_cont5_port_07_card_tv1);
            list_of_injured = itemView.findViewById(R.id.manager_cont5_port_10_card_tv1);
            injured_time = itemView.findViewById(R.id.manager_cont5_port_12_card_tv1);
            list_of_got_card = itemView.findViewById(R.id.manager_cont5_port_15_card_tv1);
            got_card_time = itemView.findViewById(R.id.manager_cont5_port_17_card_tv1);
            card_type = itemView.findViewById(R.id.manager_cont5_port_19_card_tv1);
        }
    }
}
