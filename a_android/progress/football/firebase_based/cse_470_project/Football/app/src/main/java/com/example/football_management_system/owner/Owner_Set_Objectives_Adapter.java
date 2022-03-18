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
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Owner_Set_Objectives_Adapter
        extends RecyclerView.Adapter<Owner_Set_Objectives_Adapter.ViewHolder>{

    private Context context;
    private String []conInfo;
    private int flag = 0;

    public Owner_Set_Objectives_Adapter(Context context, String[] conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Preparing View");
        flag = 0;
    }

    @NonNull
    @Override
    public Owner_Set_Objectives_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.owner_content2_card,
                parent, false);
        return new Owner_Set_Objectives_Adapter.ViewHolder(itemView);
    }

 @Override
 public void onBindViewHolder(@NonNull Owner_Set_Objectives_Adapter.ViewHolder holder, int position) {
   final String conInfoData = conInfo[position];
   holder.submit_btn.setOnClickListener(new View.OnClickListener() {
       @Override
       public void onClick(View v) {
           if(holder.objective_tv.getText().toString().equals("")){
             notification(context,"Invalid Objective");
           }
           else {
             execute_task(context,holder);
           }
       }
   });
 }
    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText objective_tv;
        EditText manager_id_tv;
        Button submit_btn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            objective_tv = itemView.findViewById(R.id.owner_cont2_objective);
            manager_id_tv = itemView.findViewById(R.id.owner_cont2_manager_id);
            submit_btn = itemView.findViewById(R.id.owner_cont2_btn);
        }
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    private void execute_task(Context context,ViewHolder holder){
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
               Objectives ots = new Objectives(holder.manager_id_tv.getText().toString(),
                                     holder.objective_tv.getText().toString(),key);
               dBR.child("owner_section").child("objectives")
                  .child(key)
                  .setValue(ots)
                  .addOnCompleteListener(new OnCompleteListener<Void>() {
                  @Override
                      public void onComplete(@NonNull Task<Void> task) {
                         notification(context,"Successful");
                         holder.objective_tv.getText().clear();
                         holder.objective_tv.invalidate();
                         holder.manager_id_tv.getText().clear();
                         holder.manager_id_tv.invalidate();
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

        holder.objective_tv.invalidate();
        holder.manager_id_tv.invalidate();
    }
}
