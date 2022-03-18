package com.example.football_management_system;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Player_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{


    RelativeLayout layout;
    RelativeLayout layout1;
    RelativeLayout layout2;
    RelativeLayout layout3;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle dtog;

    TextView player_cont1_id;
    TextView player_cont1_name;
    TextView player_cont1_country;
    EditText player_cont1_input;
    Button player_cont1_btn;

    TextView player_cont2_income;
    TextView player_cont2_bonus;

    TextView player_cont3_goals;

    ArrayList<Person_Info> person_list;
    ArrayList<Income> myincome;
    ArrayList<String> mygoals;

    static String uid;

    Toast mytoast;
    Toast mytoast1;
    Toast mytoast2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.player_home);


        Intent in = getIntent();
        uid = in.getStringExtra("us_id");


        mytoast = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast1 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);

        layout = findViewById(R.id.pc);
        layout1 = findViewById(R.id.pc1);
        layout2 = findViewById(R.id.pc2);
        layout3 = findViewById(R.id.pc3);

        layout.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);

        player_cont1_id      = findViewById(R.id.player_cont1_id);
        player_cont1_name    = findViewById(R.id.player_cont1_name);
        player_cont1_country = findViewById(R.id.player_cont1_country);
        player_cont1_input = findViewById(R.id.player_cont1_password);
        player_cont1_btn = findViewById(R.id.player_confirm_change);
        player_cont1_btn.setOnClickListener(this);

        player_cont2_bonus  = findViewById(R.id.player_cont2_bonus);
        player_cont2_income = findViewById(R.id.player_cont2_income);

        player_cont3_goals = findViewById(R.id.player_cont3_goals);

        NavigationView nv = findViewById(R.id.nav_player_layout);
        nv.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.player_drawer);
        dtog = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(dtog);
        dtog.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        person_list  = new ArrayList<Person_Info>();
        myincome     = new ArrayList<Income>();
        mygoals      = new ArrayList<String>();
        call_viewer_details();

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
      }
        else if(menuItem.getItemId()==R.id.player_view_income){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.INVISIBLE);
        }
        else if(menuItem.getItemId()==R.id.player_goals){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.VISIBLE);
        }

        else if(menuItem.getItemId()==R.id.player_log_out){
            sendToLogin();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    private void sendToLogin() {

        Intent in = new Intent(this,MainActivity.class);
        //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }


    private void call_viewer_details(){
        bring_person_Info(3,uid,person_list);
        bring_person_income(2,uid,myincome);
        bring_goals(uid,mygoals);
    }


    private void bring_goals(String ud,ArrayList<String> goals){
        Bring_Personal_Info_DB  bpi = new Bring_Personal_Info_DB(5,ud,goals,0);

        Thread tt = new Thread(bpi);
        tt.start();

        Thread updateUi = new Thread(){
            public void run() {
                try{
                    Thread.sleep(4500);
                    handler3.sendEmptyMessage(0);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.d("failed","exception");
                }
            }
        };
        updateUi.start();

    }

    private void bring_person_income(int flag, String ud, ArrayList<Income> income){

        Income_DB idb = new Income_DB(flag,ud,income);
        Thread tt = new Thread(idb);
        tt.start();

        Thread updateUi = new Thread(){
            public void run() {
                try{
                    Thread.sleep(4500);
                    handler2.sendEmptyMessage(0);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.d("failed","exception");
                }
            }
        };
        updateUi.start();


    }


    private void bring_person_Info(int flag,String ud,ArrayList pList){
        Bring_Personal_Info_DB bpi = new Bring_Personal_Info_DB(flag,ud,pList);
        Thread tt = new Thread(bpi);
        tt.start();

        Thread updateUi = new Thread(){
            public void run() {
                try{
                    Thread.sleep(4500);
                    handler1.sendEmptyMessage(0);
                }
                catch (Exception e){
                    e.printStackTrace();
                    Log.d("failed","exception");
                }
            }
        };
        updateUi.start();
    }

    private Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            String id      = "empty";
            String name    = "empty";
            String country = "empty";

            Person_Info pi = person_list.get(0);

            id = pi.getPerson_id();
            name = pi.getPerson_name();
            country = pi.getPerson_country();


            player_cont1_id.setText(id);
            player_cont1_name.setText(name);
            player_cont1_country.setText(country);
            person_list.clear();

        }
    };

    private Handler handler2 = new Handler() {
        public void handleMessage(Message msg) {
            String income  = "empty";
            String bonus   = "empty";

            Income pi = myincome.get(0);

            income = pi.getIncome();
            bonus = pi.getBonus();

            player_cont2_income.setText(income);
            player_cont2_bonus.setText(bonus);

            myincome.clear();

        }
    };

    private Handler handler3 = new Handler() {
        public void handleMessage(Message msg) {
            String goals  = "empty";

            String pi = mygoals.get(0);

            player_cont3_goals.setText(pi);
            mygoals.clear();

        }
    };

    private Handler handler8 = new Handler() {
        public void handleMessage(Message msg) {
            player_cont1_input.getText().clear();
        }
    };
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.player_confirm_change){

            if((player_cont1_input.getText().toString()).equals("")){
                mytoast.setText("Invalid Input");
                mytoast.show();
            }
            else{
                Reset_Password rp = new Reset_Password(3,uid,player_cont1_input.getText().toString(),mytoast2);
                Thread tt = new Thread(rp);
                tt.start();

                Thread updateUi = new Thread(){
                    public void run() {
                        try{
                            Thread.sleep(1000);
                            handler8.sendEmptyMessage(0);
                        }
                        catch (Exception e){
                            e.printStackTrace();
                            Log.d("failed","exception");
                        }
                    }
                };
                updateUi.start();
            }
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
