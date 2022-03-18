package com.example.football_management_system.owner;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.football_management_system.MainActivity;
import com.example.football_management_system.R;
import com.example.football_management_system.accessories.Profile_Update_Adapter;
import com.example.football_management_system.accessories.User_Info;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Owner_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    private RecyclerView recyclerView_01;
    private Profile_Update_Adapter adapter_01;

    private RecyclerView recyclerView_02;
    private Owner_Set_Objectives_Adapter adapter_02;

    private RecyclerView recyclerView_03;
    private Owner_Set_Income_Adapter adapter_03;

    private RecyclerView recyclerView_04;
    private Owner_Add_Manager_Adapter adapter_04;

    private RecyclerView recyclerView_05;
    private Owner_Add_Player_Adapter adapter_05;

    private RecyclerView recyclerView_06;
    private Owner_Bring_Manager_Adapter adapter_06;

    private RecyclerView recyclerView_07;
    private Owner_Bring_Player_Adapter adapter_07;

    private RelativeLayout layout;
    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;
    private RelativeLayout layout4;
    private RelativeLayout layout5;
    private RelativeLayout layout6;
    private RelativeLayout layout7;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle dtog;

    private String uid;
    private User_Info []data_01;
    private ArrayList<User_Info> data_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_home);
        data_01 = new User_Info[1];
        data_list = new ArrayList<User_Info>();

        Intent in = getIntent();
        uid = in.getStringExtra("identifier");

        layout = findViewById(R.id.oc);
        layout1 = findViewById(R.id.oc1);
        layout2 = findViewById(R.id.oc2);
        layout3 = findViewById(R.id.oc3);
        layout4 = findViewById(R.id.oc4);
        layout5 = findViewById(R.id.oc5);
        layout6 = findViewById(R.id.oc6);
        layout7 = findViewById(R.id.oc7);

        layout.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);
        layout4.setVisibility(View.INVISIBLE);
        layout5.setVisibility(View.INVISIBLE);
        layout6.setVisibility(View.INVISIBLE);
        layout7.setVisibility(View.INVISIBLE);

        recyclerView_01 = findViewById(R.id.owner_cont1_port_list);
        recyclerView_02 = findViewById(R.id.owner_cont2_port_list);
        recyclerView_03 = findViewById(R.id.owner_cont3_port_list);
        recyclerView_04 = findViewById(R.id.owner_cont4_port_list);
        recyclerView_05 = findViewById(R.id.owner_cont5_port_list);
        recyclerView_06 = findViewById(R.id.owner_cont6_port_list);
        recyclerView_07 = findViewById(R.id.owner_cont7_port_list);

        NavigationView nv = findViewById(R.id.nav_owner_layout);
        nv.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.owner_drawer);
        dtog = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(dtog);
        dtog.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
     if(dtog.onOptionsItemSelected(item)){
         return true;
     }
        return  super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        if(menuItem.getItemId()==R.id.owner_profile){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            layout6.setVisibility(View.INVISIBLE);
            layout7.setVisibility(View.INVISIBLE);
            bring_person_Info();
        }
        else if(menuItem.getItemId()==R.id.owner_set_objectives){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            layout6.setVisibility(View.INVISIBLE);
            layout7.setVisibility(View.INVISIBLE);
            set_objectives();
        }
        else if(menuItem.getItemId()==R.id.owner_set_income){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            layout6.setVisibility(View.INVISIBLE);
            layout7.setVisibility(View.INVISIBLE);
            set_income();
        }
        else if(menuItem.getItemId()==R.id.owner_add_manager){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            layout6.setVisibility(View.INVISIBLE);
            layout7.setVisibility(View.INVISIBLE);
            add_manager();
        }
        else if(menuItem.getItemId()==R.id.owner_add_players){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.VISIBLE);
            layout6.setVisibility(View.INVISIBLE);
            layout7.setVisibility(View.INVISIBLE);
            add_player();
        }
        else if(menuItem.getItemId()==R.id.owner_view_manager){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            layout6.setVisibility(View.VISIBLE);
            layout7.setVisibility(View.INVISIBLE);
            bring_manager_list();
        }
        else if(menuItem.getItemId()==R.id.owner_view_players){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            layout6.setVisibility(View.INVISIBLE);
            layout7.setVisibility(View.VISIBLE);
            bring_player_list();
        }

        else if(menuItem.getItemId()==R.id.owner_log_out){
            sendToLogin();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void sendToLogin() {

        Intent in = new Intent(this, MainActivity.class);
        in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }


    private  void bring_manager_list(){
        data_list.clear();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("official_users")
           .orderByChild("flag")
           .equalTo(2)
           .addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.exists()){
                       for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                           User_Info info = dataSnapshot.getValue(User_Info.class);
                           data_list.add(info);
                       }
                   }
                   else{
                       notification("Error Occurred");
                   }

                   User_Info [] data;
                   if (data_list.size()<=0){
                       data = new User_Info[]{new User_Info("empty", "empty",
                               "empty", "empty", 2)};
                       notification("No item found");
                   }
                   else {
                       data = new User_Info[data_list.size()];
                       data = data_list.toArray(data);
                   }

                   adapter_06 = new Owner_Bring_Manager_Adapter(getBaseContext(),data);
                   recyclerView_06.setHasFixedSize(true);
                   recyclerView_06.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                   recyclerView_06.setAdapter(adapter_06);
                   recyclerView_06.setNestedScrollingEnabled(false);
                   adapter_06.notifyDataSetChanged();
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {
                   notification("Database Error Occurred");
               }
           });

    }

    private  void bring_player_list(){
        data_list.clear();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("official_users")
                .orderByChild("flag")
                .equalTo(3)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                User_Info info = dataSnapshot.getValue(User_Info.class);
                                data_list.add(info);
                            }
                        }
                        else{
                            notification("Error Occurred");
                        }

                        User_Info [] data;
                        if (data_list.size()<=0){
                            data = new User_Info[]{new User_Info("empty", "empty",
                                    "empty", "empty", 2)};
                            notification("No item found");
                        }
                        else {
                            data = new User_Info[data_list.size()];
                            data = data_list.toArray(data);
                        }

                        adapter_07 = new Owner_Bring_Player_Adapter(getBaseContext(),data);
                        recyclerView_07.setHasFixedSize(true);
                        recyclerView_07.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        recyclerView_07.setAdapter(adapter_07);
                        recyclerView_07.setNestedScrollingEnabled(false);
                        adapter_07.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        notification("Database Error Occurred");
                    }
                });
    }

    private void add_player(){
        String data[] ={"empty"};
        adapter_05 = new Owner_Add_Player_Adapter(this,data);
        recyclerView_05.setHasFixedSize(true);
        recyclerView_05.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_05.setAdapter(adapter_05);
        recyclerView_05.setNestedScrollingEnabled(false);
        adapter_05.notifyDataSetChanged();
    }

    private void add_manager(){
        String data[] ={"empty"};
        adapter_04 = new Owner_Add_Manager_Adapter(this,data);
        recyclerView_04.setHasFixedSize(true);
        recyclerView_04.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_04.setAdapter(adapter_04);
        recyclerView_04.setNestedScrollingEnabled(false);
        adapter_04.notifyDataSetChanged();
    }

    private void set_income(){
        String data[] ={"empty"};
        adapter_03 = new Owner_Set_Income_Adapter(this,data);
        recyclerView_03.setHasFixedSize(true);
        recyclerView_03.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_03.setAdapter(adapter_03);
        recyclerView_03.setNestedScrollingEnabled(false);
        adapter_03.notifyDataSetChanged();
    }

    private void set_objectives(){
        String data[] ={"empty"};
        adapter_02 = new Owner_Set_Objectives_Adapter(this,data);
        recyclerView_02.setHasFixedSize(true);
        recyclerView_02.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_02.setAdapter(adapter_02);
        recyclerView_02.setNestedScrollingEnabled(false);
        adapter_02.notifyDataSetChanged();
    }

    private void bring_person_Info() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("official_users")
           .orderByChild("id")
           .equalTo(uid)
           .addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){
                 for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    User_Info info = dataSnapshot.getValue(User_Info.class);
                    data_01[0] = info;
                 }
               }
               else{
                  notification("Error Occurred");
               }

               adapter_01 = new Profile_Update_Adapter(getBaseContext(),data_01);
               recyclerView_01.setHasFixedSize(true);
               recyclerView_01.setLayoutManager(new LinearLayoutManager(getBaseContext()));
               recyclerView_01.setAdapter(adapter_01);
               recyclerView_01.setNestedScrollingEnabled(false);
               adapter_01.notifyDataSetChanged();
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 notification("Database Error Occurred");
               }
           });

    }

    private User_Info[] list_to_array(ArrayList<User_Info> data_list){
        // manual conversion
        User_Info data[] = new User_Info[data_list.size()];
        int counter = 0;
        while(counter<data_list.size()){
            data[counter] = data_list.get(counter);
            counter++;
        }

        return data;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {

    }

    private void notification(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }

}
