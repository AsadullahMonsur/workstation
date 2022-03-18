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
import com.example.football_management_system.accessories.Income;

public class Manager_View_Income_Adaptor
       extends RecyclerView.Adapter<Manager_View_Income_Adaptor.ViewHolder> {

    private Context context;
    private Income []conInfo;
    private int flag = 0;

    public Manager_View_Income_Adaptor(Context context, Income []conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Preparing View");
    }
    @NonNull
    @Override
    public Manager_View_Income_Adaptor.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.manager_content2_card,
                parent, false);
        return new Manager_View_Income_Adaptor.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Manager_View_Income_Adaptor.ViewHolder holder, int position) {
        final Income conInfoData = conInfo[position];
        String s1 = conInfoData.getIncome()+"  USD";
        String s2 = conInfoData.getBonus()+"  USD";
        holder.income.setText(s1);
        holder.bonus.setText(s2);
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView income;
        TextView bonus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            income = itemView.findViewById(R.id.manager_cont2_income);
            bonus  = itemView.findViewById(R.id.manager_cont2_bonus);
        }
    }
}
