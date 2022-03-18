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
import com.example.football_management_system.accessories.User_Info;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Owner_Add_Manager_Adapter
        extends RecyclerView.Adapter<Owner_Add_Manager_Adapter.ViewHolder> {
    private Context context;
    private String []conInfo;
    private int flag = 0;

    public Owner_Add_Manager_Adapter(Context context, String[] conInfo) {
        this.context = context;
        this.conInfo = conInfo;
        notification(context,"Preparing View");
    }

    @NonNull
    @Override
    public Owner_Add_Manager_Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.owner_content4_card,
                parent, false);
        return new Owner_Add_Manager_Adapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull Owner_Add_Manager_Adapter.ViewHolder holder, int position) {
        final String conInfoData = conInfo[position];
        holder.submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = 0;
                execute_command(context,holder);
            }
        });
    }

    private void execute_command(Context context, ViewHolder holder) {
        if(holder.id_input.getText().toString().equals("") ||
           holder.name_input.getText().toString().equals("") ||
           holder.country_input.getText().toString().equals("") ||
           holder.age_input.getText().toString().equals("") ||
           holder.password_input.getText().toString().trim().length()<6){

            notification(context,"Invalid Info");
        }
        else {
          DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
          ref.child("official_users")
             .orderByChild("id")
             .equalTo(holder.id_input.getText().toString())
             .addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(DataSnapshot dataSnapshot) {
               if (dataSnapshot.exists()) {
                   if(flag==0) {
                       notification(context, "Manager ID already exists");
                   }
               }
               else {
                 flag = 1;
                 DatabaseReference dBR = FirebaseDatabase.getInstance().getReference();
                 String key = dBR.push().getKey();
                 User_Info user_info = new User_Info(
                           holder.id_input.getText().toString(),
                           holder.name_input.getText().toString(),
                           holder.country_input.getText().toString(),
                           holder.password_input.getText().toString(),
                           holder.age_input.getText().toString(),2);

                 dBR.child("official_users")
                    .child(key)
                    .setValue(user_info)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        notification(context,"Successful");
                        holder.id_input.getText().clear();
                        holder.name_input.getText().clear();
                        holder.country_input.getText().clear();
                        holder.password_input.getText().clear();
                        holder.age_input.getText().clear();
                    }
                    }).addOnCanceledListener(new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                         notification(context,"Uploading failed");
                    }
                 });
               }
             }
             @Override
             public void onCancelled(DatabaseError databaseError) {
                  notification(context,"Database Error");
             }
          });
        }
        holder.id_input.invalidate();
        holder.name_input.invalidate();
        holder.country_input.invalidate();
        holder.age_input.invalidate();
        holder.password_input.invalidate();
    }

    @Override
    public int getItemCount() {
        return conInfo.length;
    }

    private void notification(Context context,String s){
        Toast.makeText(context,s,Toast.LENGTH_LONG).show();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        EditText id_input;
        EditText name_input;
        EditText country_input;
        EditText age_input;
        EditText password_input;
        Button submit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            id_input = itemView.findViewById(R.id.owner_cont4_input1);
            name_input = itemView.findViewById(R.id.owner_cont4_input2);
            country_input = itemView.findViewById(R.id.owner_cont4_input3);
            age_input = itemView.findViewById(R.id.owner_cont4_input4);
            password_input = itemView.findViewById(R.id.owner_cont4_input5);
            submit = itemView.findViewById(R.id.owner_cont4_btn);
        }
    }
}
