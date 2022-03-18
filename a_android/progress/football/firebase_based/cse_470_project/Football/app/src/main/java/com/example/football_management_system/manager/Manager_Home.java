package com.example.football_management_system.manager;

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
import com.example.football_management_system.accessories.Card_Info;
import com.example.football_management_system.accessories.Goal_Info;
import com.example.football_management_system.accessories.Income;
import com.example.football_management_system.accessories.Injury_Info;
import com.example.football_management_system.accessories.Match_Info;
import com.example.football_management_system.accessories.Profile_Update_Adapter;
import com.example.football_management_system.accessories.User_Info;
import com.example.football_management_system.accessories.View_Team_Statistics;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Manager_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    private RelativeLayout layout;
    private RelativeLayout layout1;
    private RelativeLayout layout2;
    private RelativeLayout layout3;
    private RelativeLayout layout4;
    private RelativeLayout layout5;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle dtog;

    private RecyclerView recyclerView_01;
    private Profile_Update_Adapter adapter_01;

    private RecyclerView recyclerView_02;
    private Manager_View_Income_Adaptor adapter_02;

    private RecyclerView recyclerView_03;
    private Manager_Set_Points_Adapter adapter_03;

    private RecyclerView recyclerView_04;
    private Manager_Set_Current_Match_Adapter adapter_04;

    private RecyclerView recyclerView_05;
    private Manager_View_Matches_Adapter adapter_05;

    private String uid;

    private User_Info []data_01;
    private Income []data_02;
    private ArrayList<View_Team_Statistics> data_list_05;

    private long startTime = System.currentTimeMillis();
    ArrayList<Goal_Info> goal_info_list = new ArrayList<Goal_Info>();
    ArrayList<Injury_Info> injury_info_list = new ArrayList<Injury_Info>();
    ArrayList<Card_Info> card_info_list = new ArrayList<Card_Info>();

    private int signal_01 = 0;
    private int signal_02 = 0;
    private int signal_03 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_home);
        data_01 = new User_Info[1];
        data_02 = new Income[1];

        data_list_05 = new ArrayList<View_Team_Statistics>();

        Intent in = getIntent();
        uid = in.getStringExtra("identifier");

        layout = findViewById(R.id.mc);
        layout1 = findViewById(R.id.mc1);
        layout2 = findViewById(R.id.mc2);
        layout3 = findViewById(R.id.mc3);
        layout4 = findViewById(R.id.mc4);
        layout5 = findViewById(R.id.mc5);

        layout.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);
        layout4.setVisibility(View.INVISIBLE);
        layout5.setVisibility(View.INVISIBLE);

        recyclerView_01 = findViewById(R.id.manager_cont1_port_list);
        recyclerView_02 = findViewById(R.id.manager_cont2_port_list);
        recyclerView_03 = findViewById(R.id.manager_cont3_port_list);
        recyclerView_04 = findViewById(R.id.manager_cont4_port_list);
        recyclerView_05 = findViewById(R.id.manager_cont5_port_list);

        NavigationView nv = findViewById(R.id.nav_manager_layout);
        nv.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.manager_drawer);
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

        if(menuItem.getItemId()==R.id.manager_profile){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            bring_person_Info();
        }
        else if(menuItem.getItemId()==R.id.manager_view_income){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            bring_person_income();
        }
        else if(menuItem.getItemId()==R.id.manager_set_morale){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            set_morale();
        }
        else if(menuItem.getItemId()==R.id.manager_current_match){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.INVISIBLE);
            set_current_match();
        }
        else if(menuItem.getItemId()==R.id.manager_view_match) {
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.VISIBLE);
            bring_matches_view();
        }

        else if(menuItem.getItemId()==R.id.manager_log_out){
            sendToLogin();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onClick(View v) {

    }

    private void sendToLogin() {

        Intent in = new Intent(this, MainActivity.class);
        //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }

    private void bring_matches_view(){
        goal_info_list.clear();
        card_info_list.clear();
        injury_info_list.clear();
        data_list_05.clear();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("manager_section")
           .child("matches")
           .addValueEventListener(new ValueEventListener() {
             @Override
             public void onDataChange(@NonNull DataSnapshot snapshot) {
               if(snapshot.exists()){
                 for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                   Match_Info info = dataSnapshot.getValue(Match_Info.class);
                    blue_hook_01(info);
//                    blue_hook_02(info);
//                    blue_hook_03(info);

//                            View_Team_Statistics vts = card_blaster(info,goal_info_list,injury_info_list,card_info_list);
//                     View_Team_Statistics vts = card_blaster(info,blue_hook_01(info),blue_hook_02(info),blue_hook_03(info));
//                     data_list_05.add(vts);
                 }
               }
               else{
                 notification("No info found");
               }
             }

             @Override
             public void onCancelled(@NonNull DatabaseError error) {
                 notification("Database Error Occurred");
             }
           });
    }

    private View_Team_Statistics card_blaster(Match_Info info, ArrayList<Goal_Info> goal_info, ArrayList<Injury_Info> injury_info, ArrayList<Card_Info> card_info) {
      String list_of_scorer = "";
      String goal_time = "";
      int goal_counter = 0;
      while (goal_counter<goal_info.size()){
          Goal_Info gf = goal_info.get(goal_counter);
          list_of_scorer = list_of_scorer+","+gf.getPlayer_id();
          goal_time = goal_time+","+gf.getGoal_time();
          goal_counter++;
      }

      String list_of_got_injured = "";
      String injured_time = "";
      int injury_counter = 0;
        while (injury_counter<injury_info.size()){
            Injury_Info inj = injury_info.get(injury_counter);
            list_of_got_injured = list_of_got_injured+","+inj.getPlayer_id();
            injured_time = injured_time+","+inj.getInjury_time();
            injury_counter++;
      }
      String list_of_got_card = "";
      String card_time = "";
      String card_type = "";

      int card_counter = 0;
        while (card_counter<card_info.size()){
          Card_Info carIn = card_info.get(card_counter);
          list_of_got_card = list_of_got_card+","+carIn.getPlayer_id();
          card_time = card_time+","+carIn.getCard_time();
          card_type = card_type+","+carIn.getCard_type();
          card_counter++;
      }

      View_Team_Statistics vts = new View_Team_Statistics(
              info.getMatch_id(),list_of_scorer,goal_time,list_of_got_injured,
              injured_time,list_of_got_card,card_time,card_type
      );

        return vts;
    }


    private ArrayList<Card_Info> blue_hook_03(Match_Info info) {
//    ArrayList<Goal_Info> goal_info_list = new ArrayList<Goal_Info>();
//    ArrayList<Injury_Info> injury_info_list = new ArrayList<Injury_Info>();
//    ArrayList<Card_Info> card_info_list = new ArrayList<Card_Info>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("manager_section")
                .child("card_info")
                .orderByChild("match_id")
                .equalTo(info.getMatch_id())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot ds : snapshot.getChildren()){
                                Card_Info carIn = ds.getValue(Card_Info.class);
                                card_info_list.add(carIn);
                                signal_03 = 1;
                            }
                            View_Team_Statistics vts = card_blaster(info,goal_info_list,injury_info_list,card_info_list);
                            data_list_05.add(vts);
                        }
                        else{
                            Card_Info carIn = new Card_Info("empty",
                                    "empty","empty",
                                    "empty","empty");

                            card_info_list.add(carIn);
                            signal_03 = 1;
                            View_Team_Statistics vts = card_blaster(info,goal_info_list,injury_info_list,card_info_list);
                            data_list_05.add(vts);
                        }

                        View_Team_Statistics vts[];
                        if(data_list_05.size()<=0){
                            vts = new View_Team_Statistics[]{
                                    new View_Team_Statistics()};
                            notification("No Info Found");
                        }
                        else {
                            vts = new View_Team_Statistics[data_list_05.size()];
                            vts = data_list_05.toArray(vts);
                            notification("Updating");
                        }

                        adapter_05 = new Manager_View_Matches_Adapter(getBaseContext(),vts);
                        recyclerView_05.setHasFixedSize(true);
                        recyclerView_05.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        recyclerView_05.setAdapter(adapter_05);
                        recyclerView_05.setNestedScrollingEnabled(false);
                        adapter_05.notifyDataSetChanged();

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//        while (true){
//            if(card_info_list.size()>0){
//                signal_03 = 1;
//                break;
//            }
//            long estimatedTime = System.currentTimeMillis() - startTime;
//            if(estimatedTime>=7000){
//               break;
//            }
//        }
        return card_info_list;
    }

    private ArrayList<Injury_Info> blue_hook_02(Match_Info info) {
//    ArrayList<Goal_Info> goal_info_list = new ArrayList<Goal_Info>();
//    ArrayList<Injury_Info> injury_info_list = new ArrayList<Injury_Info>();
//    ArrayList<Card_Info> card_info_list = new ArrayList<Card_Info>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("manager_section")
                .child("injury_info")
                .orderByChild("match_id")
                .equalTo(info.getMatch_id())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot ds : snapshot.getChildren()){
                                Injury_Info inf = ds.getValue(Injury_Info.class);
                                injury_info_list.add(inf);
                                signal_02 = 1;
                            }
                            blue_hook_03(info);
                        }
                        else{
                            Injury_Info inf = new Injury_Info("empty",
                                    "empty","empty",
                                    "empty");

                            injury_info_list.add(inf);
                            signal_02 = 1;
                            blue_hook_03(info);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

//        while (true){
//            if(injury_info_list.size()>0){
//                signal_02 = 1;
//                break;
//            }
//            long estimatedTime = System.currentTimeMillis() - startTime;
//            if(estimatedTime>=7000){
//                break;
//            }
//        }

        return injury_info_list;
    }

    private ArrayList<Goal_Info> blue_hook_01(Match_Info info) {
//   ArrayList<Goal_Info> goal_info_list = new ArrayList<Goal_Info>();
//    ArrayList<Injury_Info> injury_info_list = new ArrayList<Injury_Info>();
//    ArrayList<Card_Info> card_info_list = new ArrayList<Card_Info>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.child("manager_section")
           .child("goal_info")
           .orderByChild("match_id")
           .equalTo(info.getMatch_id())
           .addValueEventListener(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   if(snapshot.exists()){
                     for(DataSnapshot ds : snapshot.getChildren()){
                        Goal_Info gf = ds.getValue(Goal_Info.class);
                        goal_info_list.add(gf);
                        signal_01 = 1;
                     }
                     blue_hook_02(info);
                   }
                   else{
                      Goal_Info gf = new Goal_Info("empty",
                              "empty","empty",
                              "empty","empty");

                      goal_info_list.add(gf);
                      signal_01 = 1;
                      blue_hook_02(info);
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });

//        while (true){
//            if(goal_info_list.size()>0){
//                signal_01 = 1;
//                break;
//            }
//            long estimatedTime = System.currentTimeMillis() - startTime;
//            if(estimatedTime>=7000){
//                break;
//            }
//        }
        return goal_info_list;
    }

    private  void set_current_match(){
        String data_04[] = new String[]{"abcd"};
        adapter_04 = new Manager_Set_Current_Match_Adapter(this,data_04);
        recyclerView_04.setHasFixedSize(true);
        recyclerView_04.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_04.setAdapter(adapter_04);
        recyclerView_04.setNestedScrollingEnabled(false);
        adapter_04.notifyDataSetChanged();
    }

    private void set_morale() {
        Points []data_03 = new Points[]{
                    new Points("empty","empty","empty")};
        
        adapter_03 = new Manager_Set_Points_Adapter(this,data_03);
        recyclerView_03.setHasFixedSize(true);
        recyclerView_03.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_03.setAdapter(adapter_03);
        recyclerView_03.setNestedScrollingEnabled(false);
        adapter_03.notifyDataSetChanged();
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

                adapter_02 = new Manager_View_Income_Adaptor(getBaseContext(),data_02);
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
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private void notification(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
