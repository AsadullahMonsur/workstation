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
import com.example.football_management_system.accessories.Card_Info;
import com.example.football_management_system.accessories.Goal;
import com.example.football_management_system.accessories.Goal_Info;
import com.example.football_management_system.accessories.Injury_Info;
import com.example.football_management_system.accessories.Match_Info;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Manager_Set_Current_Match_Adapter
       extends RecyclerView.Adapter<Manager_Set_Current_Match_Adapter.ViewHolder> {

    private Context context;
    private String []conInfo;
    private int match_flag = 0;
    private int goal_flag = 0;
    private int goal_flag2 = 0;
    private int card_flag = 0;
    private int injury_flag = 0;

    public Manager_Set_Current_Match_Adapter(Context context, String []conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Preparing View");
    }

    @NonNull
    @Override
    public Manager_Set_Current_Match_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.manager_content4_card,
                parent, false);
        return new Manager_Set_Current_Match_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Manager_Set_Current_Match_Adapter.ViewHolder holder, int position) {
        String conInfoData = conInfo[position];
        holder.match_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                match_flag = 0;
                if(holder.match_id.getText().toString().equals("") ||
                   holder.match_venue.getText().toString().equals("")){
                    notification(context,"Invalid Info");
                }
                else{
                  execute_match_command(context,holder);
                }
            }
        });

        holder.goal_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goal_flag = 0;
                goal_flag2 = 0;
                if(holder.goal_match_id.getText().toString().equals("") ||
                   holder.goal_player_id.getText().toString().equals("")||
                   holder.goal_number.getText().toString().equals("") ||
                   holder.goal_time.getText().toString().equals("")){
                    notification(context,"Invalid Info");
                }
                else{
                    execute_goal_command(context,holder);
                }
            }
        });
        holder.card_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                card_flag = 0;
                if(holder.card_match_id.getText().equals("") ||
                   holder.card_player_id.getText().equals("") ||
                   holder.card_type.getText().equals("") ||
                   holder.card_time.getText().equals("")){
                    notification(context,"Invalid Info");
                }
                else{
                    execute_card_command(context,holder);
                }
            }
        });
        holder.injury_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                injury_flag = 0;
                if(holder.injury_match_id.getText().toString().equals("") ||
                   holder.injury_player_id.getText().toString().equals("") ||
                   holder.injury_time.getText().toString().equals("")){
                    notification(context,"Invalid Info");
                }
                else {
                    execute_injury_command(context,holder);
                }
            }
        });
    }

    private void execute_injury_command(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("manager_section")
                .child("matches")
                .orderByChild("match_id")
                .equalTo(holder.injury_match_id.getText().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (injury_flag == 0) {
                                injury_flag = 1;
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                ref.child("official_users")
                                        .orderByChild("id")
                                        .equalTo(holder.injury_player_id.getText().toString())
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    if (injury_flag==1){
                                                        injury_flag = 2;
                                                        execute_injury_command_nested_01(context,holder);
                                                    }
                                                }
                                                else{
                                                    if (injury_flag==1){
                                                        injury_flag = 2;
                                                        notification(context,"Player ID does not exits");
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                notification(context,"Database Error");
                                            }
                                        });
                            }
                        }
                        else {
                            notification(context, "Match does not exits. Create Match first");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        notification(context,"Database Error");
                    }
                });

        holder.injury_match_id.invalidate();
        holder.injury_player_id.invalidate();
        holder.injury_time.invalidate();
    }

    private void execute_injury_command_nested_01(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dBR = ref.child("manager_section")
                .child("injury_info");

        String key = dBR.push().getKey();
        Injury_Info injury_info = new Injury_Info(
                holder.injury_match_id.getText().toString(),
                holder.injury_player_id.getText().toString(),
                holder.injury_time.getText().toString(), key);

        dBR.child(key)
                .setValue(injury_info)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (injury_flag == 2) {
                            notification(context, "Successful Step 1");
                            injury_flag = 3;
                            holder.injury_match_id.getText().clear();
                            holder.injury_player_id.getText().clear();
                            holder.injury_time.getText().clear();
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

    private void execute_card_command(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("manager_section")
                .child("matches")
                .orderByChild("match_id")
                .equalTo(holder.card_match_id.getText().toString())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            if (card_flag == 0) {
                                card_flag = 1;
                                DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                                ref.child("official_users")
                                        .orderByChild("id")
                                        .equalTo(holder.card_player_id.getText().toString())
                                        .addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                                if(snapshot.exists()){
                                                    if (card_flag==1){
                                                        card_flag = 2;
                                                        execute_card_command_nested_01(context,holder);
                                                    }
                                                }
                                                else{
                                                    if (card_flag==1){
                                                        card_flag = 2;
                                                        notification(context,"Player ID does not exits");
                                                    }
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError error) {
                                                notification(context,"Database Error");
                                            }
                                        });
                            }
                        }
                        else {
                            notification(context, "Match does not exits. Create Match first");
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        notification(context,"Database Error");
                    }
                });

        holder.card_match_id.invalidate();
        holder.card_player_id.invalidate();
        holder.card_type.invalidate();
        holder.card_time.invalidate();

    }

    private void execute_card_command_nested_01(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dBR = ref.child("manager_section")
                .child("card_info");

        String key = dBR.push().getKey();
        Card_Info card_info = new Card_Info(
                holder.card_match_id.getText().toString(),
                holder.card_player_id.getText().toString(),
                holder.card_type.getText().toString(),
                holder.card_time.getText().toString(), key);

        dBR.child(key)
                .setValue(card_info)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (card_flag == 2) {
                            notification(context, "Successful Step 1");
                            card_flag = 3;
                            holder.card_match_id.getText().clear();
                            holder.card_player_id.getText().clear();
                            holder.card_type.getText().clear();
                            holder.card_time.getText().clear();
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

    private void execute_goal_command(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("manager_section")
           .child("matches")
           .orderByChild("match_id")
           .equalTo(holder.goal_match_id.getText().toString())
           .addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
             if (dataSnapshot.exists()) {
                if (goal_flag == 0) {
                    goal_flag = 1;
                    DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                    ref.child("official_users")
                       .orderByChild("id")
                       .equalTo(holder.goal_player_id.getText().toString())
                       .addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    if (goal_flag==1){
                                        goal_flag = 2;
                                        execute_goal_command_nested_01(context,holder);
                                    }
                                }
                                else{
                                    if (goal_flag==1){
                                        goal_flag = 2;
                                        notification(context,"Player ID does not exits");
                                    }
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                notification(context,"Database Error");
                            }
                        });
                }
             }
             else {
                 notification(context, "Match does not exits. Create Match first");
             }
           }
           @Override
           public void onCancelled(DatabaseError databaseError) {
               notification(context,"Database Error");
           }
         });

        holder.goal_match_id.invalidate();
        holder.goal_player_id.invalidate();
        holder.goal_number.invalidate();
        holder.goal_time.invalidate();
    }

    private void execute_goal_command_nested_01(Context context, ViewHolder holder) {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        DatabaseReference dBR = ref.child("manager_section")
                                   .child("goal_info");

        String key = dBR.push().getKey();
        Goal_Info goal_info = new Goal_Info(
                holder.goal_match_id.getText().toString(),
                holder.goal_player_id.getText().toString(),
                holder.goal_number.getText().toString(),
                holder.goal_time.getText().toString(), key);

        dBR.child(key)
                .setValue(goal_info)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (match_flag == 2) {
                            notification(context, "Successful Step 1");
                            match_flag = 3;
                        }
                    }
                })
                .addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        notification(context, "Uploading failed");
                    }
                });

        DatabaseReference dBR2 = ref.child("player_section")
                                    .child("goal");
        dBR2.orderByChild("player_id")
            .equalTo(holder.goal_player_id.getText().toString())
            .addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.exists()){
                        if(goal_flag2==0){
                            goal_flag2 = 1;
                        for(DataSnapshot ds : snapshot.getChildren()){
                            Goal goal_01 = ds.getValue(Goal.class);
                            int result = goal_01.getGoal()+ 1;

                            DatabaseReference refs = FirebaseDatabase.getInstance().getReference();
                            DatabaseReference dBR3 = refs.child("player_section")
                                    .child("goal");

                            dBR3.child(goal_01.getPatch())
                                .child("goal")
                                 .setValue(result)
                                 .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(goal_flag2==1){
                                                notification(context, "Successful Step 2");
                                                holder.goal_match_id.getText().clear();
                                                holder.goal_player_id.getText().clear();
                                                holder.goal_number.getText().clear();
                                                holder.goal_time.getText().clear();
                                                goal_flag2 = 2;
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
                    }
                    else{
                        DatabaseReference refs = FirebaseDatabase.getInstance().getReference();
                        DatabaseReference dBR3 = refs.child("player_section")
                                                     .child("goal");
                        String key = dBR3.push().getKey();

                        Goal goal = new Goal(
                                holder.goal_player_id.getText().toString(),
                                1,key);

                        dBR3.child(key)
                            .setValue(goal)
                            .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(goal_flag2==0){
                                    notification(context, "Successful Step 2");
                                    holder.goal_match_id.getText().clear();
                                    holder.goal_player_id.getText().clear();
                                    holder.goal_number.getText().clear();
                                    holder.goal_time.getText().clear();
                                    goal_flag2 = 1;
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
                public void onCancelled(@NonNull DatabaseError error) {
                    notification(context,"Database Error Occurred");
                }
            });
    }

    private void execute_match_command(Context context, ViewHolder holder) {
      DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
      ref.child("manager_section")
         .child("matches")
         .orderByChild("match_id")
         .equalTo(holder.match_id.getText().toString())
         .addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()) {
                   if (match_flag == 0) {
                       match_flag = 1;
                       notification(context, "Match Id Already exits");
                   }
               } else {
                   if (match_flag == 0) {
                       match_flag = 1;
                       DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
                       DatabaseReference dBR = ref.child("manager_section")
                                                  .child("matches");

                       String key = dBR.push().getKey();
                       Match_Info match_info = new Match_Info(
                               holder.match_id.getText().toString(),
                               holder.match_venue.getText().toString(),
                               key);

                       dBR.child(key)
                          .setValue(match_info)
                          .addOnCompleteListener(new OnCompleteListener<Void>() {
                          @Override
                          public void onComplete(@NonNull Task<Void> task) {
                             if (match_flag == 1) {
                                notification(context, "Successful");
                                holder.match_id.getText().clear();
                                holder.match_venue.getText().clear();
                                match_flag = 2;
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
               }
           }

           @Override
           public void onCancelled(DatabaseError databaseError) {
               notification(context,"Database Error");
           }
         });

        holder.match_id.invalidate();
        holder.match_venue.invalidate();
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }
    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        EditText goal_match_id;
        EditText goal_player_id;
        EditText goal_number;
        EditText goal_time;
        Button goal_info_btn;

        EditText card_match_id;
        EditText card_player_id;
        EditText card_type;
        EditText card_time;
        Button card_info_btn;

        EditText injury_match_id;
        EditText injury_player_id;
        EditText injury_time;
        Button injury_info_btn;

        EditText match_id;
        EditText match_venue;
        Button  match_info_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            goal_match_id = itemView.findViewById(R.id.manager_cont4_input1);
            goal_player_id = itemView.findViewById(R.id.manager_cont4_input2);
            goal_number = itemView.findViewById(R.id.manager_cont4_input3);
            goal_time = itemView.findViewById(R.id.manager_cont4_input4);
            goal_info_btn = itemView.findViewById(R.id.manager_cont4_btn1);

            card_match_id = itemView.findViewById(R.id.manager_cont4_input5);
            card_player_id = itemView.findViewById(R.id.manager_cont4_input6);
            card_type = itemView.findViewById(R.id.manager_cont4_input7);
            card_time = itemView.findViewById(R.id.manager_cont4_input8);
            card_info_btn = itemView.findViewById(R.id.manager_cont4_btn2);

            injury_match_id = itemView.findViewById(R.id.manager_cont4_input9);
            injury_player_id = itemView.findViewById(R.id.manager_cont4_input10);
            injury_time = itemView.findViewById(R.id.manager_cont4_input11);
            injury_info_btn = itemView.findViewById(R.id.manager_cont4_btn3);

            match_id = itemView.findViewById(R.id.manager_cont4_input12);
            match_venue = itemView.findViewById(R.id.manager_cont4_input13);
            match_info_btn = itemView.findViewById(R.id.manager_cont4_btn4);
        }
    }
}
