package com.example.football_management_system.player;

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
import com.example.football_management_system.accessories.Goal;
import com.example.football_management_system.accessories.Income;
import com.example.football_management_system.accessories.Profile_Update_Adapter;
import com.example.football_management_system.accessories.User_Info;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Player_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    private RelativeLayout layout;
    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle dtog;

    private RecyclerView recyclerView_01;
    private Profile_Update_Adapter adapter_01;

    private RecyclerView recyclerView_02;
    private Player_View_Income_Adapter adapter_02;

    private RecyclerView recyclerView_03;
    private Player_View_Goal_Adapter adapter_03;

    private String uid;

    private User_Info []data_01;
    private Income []data_02;
    private int []data_03;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_home);
        data_01 = new User_Info[1];
        data_02 = new Income[1];
        data_03 = new int[1];

        Intent in = getIntent();
        uid = in.getStringExtra("identifier");

        layout = findViewById(R.id.pc);
        layout1 = findViewById(R.id.pc1);
        layout2 = findViewById(R.id.pc2);
        layout3 = findViewById(R.id.pc3);

        layout.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);

        recyclerView_01 = findViewById(R.id.player_cont1_port_list);
        recyclerView_02 = findViewById(R.id.player_cont2_port_list);
        recyclerView_03 = findViewById(R.id.player_cont3_port_list);

        NavigationView nv = findViewById(R.id.nav_player_layout);
        nv.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.player_drawer);
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

        if(menuItem.getItemId()==R.id.player_profile){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            bring_person_Info();
      }
        else if(menuItem.getItemId()==R.id.player_view_income){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            bring_person_income();
        }
        else if(menuItem.getItemId()==R.id.player_goals){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.VISIBLE);
            bring_goal_info();
        }

        else if(menuItem.getItemId()==R.id.player_log_out){
            sendToLogin();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void sendToLogin() {

        Intent in = new Intent(this, MainActivity.class);
        //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }

    private void bring_goal_info(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("player_section")
                .child("goal")
                .orderByChild("player_id")
                .equalTo(uid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                Goal goal = dataSnapshot.getValue(Goal.class);
                                data_03[0] = goal.getGoal();
                            }
                        }
                        else{
                            notification("No Info found");
                            data_03[0] = 0;
                        }

                        adapter_03 = new Player_View_Goal_Adapter(getBaseContext(),data_03);
                        recyclerView_03.setHasFixedSize(true);
                        recyclerView_03.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        recyclerView_03.setAdapter(adapter_03);
                        recyclerView_03.setNestedScrollingEnabled(false);
                        adapter_03.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        notification("Database Error Occurred");
                    }
                });
    }

    private void bring_person_income(){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("owner_section")
                .child("income")
                .orderByChild("id")
                .equalTo(uid)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                                Income info = dataSnapshot.getValue(Income.class);
                                data_02[0] = info;
                            }
                        }
                        else{
                            notification("No info found");
                            Income info = new Income("empty","empty",
                                                "empty","empty");
                            data_02[0] = info;
                        }

                        adapter_02 = new Player_View_Income_Adapter(getBaseContext(),data_02);
                        recyclerView_02.setHasFixedSize(true);
                        recyclerView_02.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        recyclerView_02.setAdapter(adapter_02);
                        recyclerView_02.setNestedScrollingEnabled(false);
                        adapter_02.notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        notification("Database Error Occurred");
                    }
                });
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

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void notification(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
