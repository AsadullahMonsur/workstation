package com.example.football_management_system.manager;

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
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Manager_Set_Points_Adapter
       extends RecyclerView.Adapter<Manager_Set_Points_Adapter.ViewHolder> {

    private Context context;
    private Points []conInfo;
    private int flag = 0;

    public Manager_Set_Points_Adapter(Context context, Points []conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Preparing View");
    }

    @NonNull
    @Override
    public Manager_Set_Points_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.manager_content3_card,
                parent, false);
        return new Manager_Set_Points_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Manager_Set_Points_Adapter.ViewHolder holder, int position) {
        final Points conInfoData = conInfo[position];
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                if(holder.points.getText().toString().equals("") ||
                        holder.id.getText().toString().equals("")){
                    notification(context,"Invalid Info");
                }
                else {
                      execute_command(context,holder);
                }
            }
        });
    }

    private void execute_command(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("official_users")
           .orderByChild("id")
           .equalTo(holder.id.getText().toString())
           .addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()) {
                   DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                   ref.child("manager_section")
                      .child("set_points")
                      .orderByChild("id")
                      .equalTo(holder.id.getText().toString())
                      .addValueEventListener(new ValueEventListener() {
                         @Override
                         public void onDataChange(DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                    if(flag==0){
                                       flag = 1;
                                       nested_command_1(context,holder,dataSnapshot);
                                    }
                            }
                            else {
                                if (flag==0){
                                   flag = 1;
                                   nested_command_2(context,holder);
                                }
                            }
                         }
                         @Override
                         public void onCancelled(DatabaseError databaseError) {
                            notification(context,"Database Error");
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

        holder.id.invalidate();
        holder.points.invalidate();
    }

    private void nested_command_2(Context context, ViewHolder holder) {
            DatabaseReference dBR = FirebaseDatabase.getInstance().getReference();
            DatabaseReference dBR2 = dBR.child("manager_section")
                                        .child("set_points");

            String token = dBR2.push().getKey();
            Points points1 = new Points(holder.points.getText().toString(),
                                        holder.id.getText().toString(),token);
            dBR.child("manager_section")
                    .child("set_points")
                    .child(token)
                    .setValue(points1)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(flag==1){
                            notification(context, "Successful");
                            holder.id.getText().clear();
                            holder.points.getText().clear();
                            flag = 2;
                            }
                        }
                    })
                    .addOnCanceledListener(new OnCanceledListener() {
                     @Override
                     public void onCanceled() {
                         notification(context, "Uploading failed");
                     }
                     });
    }

    private void nested_command_1(Context context, ViewHolder holder, DataSnapshot dataSnapshot) {
        for(DataSnapshot ds : dataSnapshot.getChildren()) {
            Points points = ds.getValue(Points.class);
            int x = Integer.parseInt(points.getPoints());
            int y = Integer.parseInt(holder.points.getText().toString());
            int z = x+y;
            String s = ""+z;
            DatabaseReference dBR = FirebaseDatabase.getInstance().getReference();

            dBR.child("manager_section")
                    .child("set_points")
                    .child(points.getPatch())
                    .child("points")
                    .setValue(s)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(flag==1){
                            notification(context, "Successful");
                            holder.id.getText().clear();
                            holder.points.getText().clear();
                            flag = 2;
                            }
                        }
                    }).addOnCanceledListener(new OnCanceledListener() {
                @Override
                public void onCanceled() {
                    notification(context, "Uploading failed");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        EditText points;
        EditText id;
        Button submit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            points = itemView.findViewById(R.id.manager_cont3_player_points);
            id  = itemView.findViewById(R.id.manager_cont3_player_id);
            submit = itemView.findViewById(R.id.manager_cont3_btn);
        }
    }
}
