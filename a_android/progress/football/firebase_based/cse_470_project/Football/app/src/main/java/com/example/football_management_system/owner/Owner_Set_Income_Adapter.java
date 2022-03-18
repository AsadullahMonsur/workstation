package com.example.football_management_system.owner;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_management_system.R;
import com.example.football_management_system.accessories.Income;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Owner_Set_Income_Adapter
       extends RecyclerView.Adapter<Owner_Set_Income_Adapter.ViewHolder>{
    private Context context;
    private String []conInfo;
    private int flag = 0;

    public Owner_Set_Income_Adapter(Context context, String[] conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Preparing View");
        flag = 0;
    }

    @NonNull
    @Override
    public Owner_Set_Income_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.owner_content3_card,
                parent, false);
        return new Owner_Set_Income_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Owner_Set_Income_Adapter.ViewHolder holder, int position) {
        final String conInfoData = conInfo[position];
        holder.manager_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.manager_id_tv.getText().toString().equals("") ||
                   holder.manager_income_tv.getText().toString().equals("") ||
                   holder.manager_bonus_tv.getText().toString().equals("")){

                    notification(context,"Invalid Info");
                }
                else {
                    execute_manager_section(context,holder);
                }
            }
        });

        holder.player_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (holder.player_id_tv.getText().toString().equals("") ||
                   holder.player_income_tv.getText().toString().equals("") ||
                   holder.player_bonus_tv.getText().toString().equals("")){
                   notification(context,"Invalid Info");
               }
               else {
                   execute_player_section(context,holder);
               }
            }
        });
    }

    private void execute_player_section(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("official_users")
           .orderByChild("id")
           .equalTo(holder.player_id_tv.getText().toString())
           .addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
              if (dataSnapshot.exists()) {
                 DatabaseReference dBR = FirebaseDatabase.getInstance().getReference();
                 String key = dBR.push().getKey();
                 Income income = new Income(holder.player_id_tv.getText().toString(),
                 holder.player_income_tv.getText().toString(),
                 holder.player_bonus_tv.getText().toString(),key);

                 dBR.child("owner_section")
                    .child("income")
                    .child(key)
                    .setValue(income)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                       notification(context,"Successful");
                       holder.player_id_tv.getText().clear();
                       holder.player_income_tv.getText().clear();
                       holder.player_bonus_tv.getText().clear();
                    }
                 }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                       notification(context,"Uploading failed");
                    }
                 });
              }
              else {
                 notification(context,"Player ID does not exists");
              }
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {
              notification(context,"Database Error");
           }
        });

        holder.player_id_tv.invalidate();
        holder.player_income_tv.invalidate();
        holder.player_bonus_tv.invalidate();
    }

    private void execute_manager_section(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("official_users")
                .orderByChild("id")
                .equalTo(holder.manager_id_tv.getText().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            DatabaseReference dBR = FirebaseDatabase.getInstance().getReference();
                            String key = dBR.push().getKey();
                            Income income = new Income(holder.manager_id_tv.getText().toString(),
                                    holder.manager_income_tv.getText().toString(),
                                    holder.manager_bonus_tv.getText().toString(),key);

                            dBR.child("owner_section")
                                    .child("income")
                                    .child(key)
                                    .setValue(income)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            notification(context,"Successful");
                                            holder.manager_id_tv.getText().clear();
                                            holder.manager_income_tv.getText().clear();
                                            holder.manager_bonus_tv.getText().clear();

                                        }
                                    }).addOnCanceledListener(new OnCanceledListener() {
                                @Override
                                public void onCanceled() {
                                    notification(context,"Uploading failed");
                                }
                            });
                        }
                        else {
                            notification(context,"Manager ID does not exists");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        notification(context,"Database Error");
                    }
                });


        holder.manager_id_tv.invalidate();
        holder.manager_income_tv.invalidate();
        holder.manager_bonus_tv.invalidate();
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText manager_id_tv;
        EditText manager_income_tv;
        EditText manager_bonus_tv;
        Button manager_btn;

        EditText player_id_tv;
        EditText player_income_tv;
        EditText player_bonus_tv;
        Button player_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            manager_id_tv = itemView.findViewById(R.id.owner_cont3_manager_id);
            manager_income_tv = itemView.findViewById(R.id.owner_cont3_manager_income);
            manager_bonus_tv = itemView.findViewById(R.id.owner_cont3_manager_bonus);
            manager_btn = itemView.findViewById(R.id.owner_cont3_manager_done);

            player_id_tv = itemView.findViewById(R.id.owner_cont3_player_id);
            player_income_tv = itemView.findViewById(R.id.owner_cont3_player_income);
            player_bonus_tv = itemView.findViewById(R.id.owner_cont3_player_bonus);
            player_btn = itemView.findViewById(R.id.owner_cont3_player_done);
        }
    }

}
