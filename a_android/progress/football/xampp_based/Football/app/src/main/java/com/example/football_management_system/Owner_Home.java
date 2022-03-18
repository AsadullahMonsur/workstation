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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Owner_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener {

    TextView owner_cont1_id;
    TextView owner_cont1_name;
    TextView owner_cont1_country;
    EditText owner_cont1_input;
    Button owner_cont1_btn;
    ArrayList<Person_Info> person_list;

    EditText owner_cont2_objective;
    EditText owner_cont2_manager_id;
    Button owner_cont2_done;

    EditText owner_cont3_manager_id;
    EditText owner_cont3_manager_income;
    EditText owner_cont3_manager_bonus;
    Button owner_cont3_manager_done;

    EditText owner_cont3_player_id;
    EditText owner_cont3_player_income;
    EditText owner_cont3_player_bonus;
    Button owner_cont3_player_done;

    EditText owner_cont4_input1;
    EditText owner_cont4_input2;
    EditText owner_cont4_input3;
    EditText owner_cont4_input4;
    EditText owner_cont4_input5;
    Button owner_cont4_btn;

    EditText owner_cont5_input1;
    EditText owner_cont5_input2;
    EditText owner_cont5_input3;
    EditText owner_cont5_input4;
    EditText owner_cont5_input5;
    EditText owner_cont5_input6;

    Button owner_cont5_btn;

    ArrayList<Money_And_Honey> optionList;
    List<String> owner_cont6_list;
    List<String> owner_cont7_list;

    ArrayList<String> managerlist;
    ArrayList<String> playerlist;


    ArrayList<String> add_manager_list;
    ArrayList<String> add_player_list;

    GridView gv_o6;
    GridView gv_o7;

    RelativeLayout layout;
    RelativeLayout layout1;
    RelativeLayout layout2;
    RelativeLayout layout3;
    RelativeLayout layout4;
    RelativeLayout layout5;
    RelativeLayout layout6;
    RelativeLayout layout7;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle dtog;

    static String uid;

    Toast mytoast;
    Toast mytoast1;
    Toast mytoast2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.owner_home);

        Intent in = getIntent();
        uid = in.getStringExtra("us_id");

        mytoast = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast1 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);

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

        owner_cont1_id      = findViewById(R.id.owner_cont1_id);
        owner_cont1_name    = findViewById(R.id.owner_cont1_name);
        owner_cont1_country = findViewById(R.id.owner_cont1_country);
        owner_cont1_input = findViewById(R.id.owner_cont1_password);
        owner_cont1_btn = findViewById(R.id.owner_cont1_confirm_change);
        owner_cont1_btn.setOnClickListener(this);

        owner_cont2_objective   = findViewById(R.id.owner_cont2_objective);
        owner_cont2_manager_id  = findViewById(R.id.owner_cont2_manager_id);
        owner_cont2_done        = findViewById(R.id.owner_cont2_btn);
        owner_cont2_done.setOnClickListener(this);

        owner_cont3_manager_income   = findViewById(R.id.owner_cont3_manager_income);
        owner_cont3_manager_bonus    = findViewById(R.id.owner_cont3_manager_bonus);
        owner_cont3_manager_id       = findViewById(R.id.owner_cont3_manager_id);
        owner_cont3_manager_done     = findViewById(R.id.owner_cont3_manager_done);
        owner_cont3_manager_done.setOnClickListener(this);

        owner_cont3_player_income   = findViewById(R.id.owner_cont3_player_income);
        owner_cont3_player_bonus    = findViewById(R.id.owner_cont3_player_bonus);
        owner_cont3_player_id       = findViewById(R.id.owner_cont3_player_id);
        owner_cont3_player_done     = findViewById(R.id.owner_cont3_player_done);
        owner_cont3_player_done.setOnClickListener(this);


        owner_cont4_input1 = findViewById(R.id.owner_cont4_input1);
        owner_cont4_input2 = findViewById(R.id.owner_cont4_input2);
        owner_cont4_input3 = findViewById(R.id.owner_cont4_input3);
        owner_cont4_input4 = findViewById(R.id.owner_cont4_input4);
        owner_cont4_input5 = findViewById(R.id.owner_cont4_input5);
        owner_cont4_btn = findViewById(R.id.owner_cont4_btn);
        owner_cont4_btn.setOnClickListener(this);

        owner_cont5_input1 = findViewById(R.id.owner_cont5_input1);
        owner_cont5_input2 = findViewById(R.id.owner_cont5_input2);
        owner_cont5_input3 = findViewById(R.id.owner_cont5_input3);
        owner_cont5_input4 = findViewById(R.id.owner_cont5_input4);
        owner_cont5_input5 = findViewById(R.id.owner_cont5_input5);
        owner_cont5_input6 = findViewById(R.id.owner_cont5_input6);
        owner_cont5_btn = findViewById(R.id.owner_cont5_btn);
        owner_cont5_btn.setOnClickListener(this);

        gv_o6 =  findViewById(R.id.owner_cont6_gv);
        gv_o7 =  findViewById(R.id.owner_cont7_gv);


        NavigationView nv = findViewById(R.id.nav_owner_layout);
        nv.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.owner_drawer);
        dtog = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(dtog);
        dtog.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        person_list        = new ArrayList<Person_Info>();
        optionList         = new ArrayList<Money_And_Honey>();

        managerlist = new ArrayList<String>();
        playerlist = new ArrayList<String>();

        add_manager_list = new ArrayList<String>();
        add_player_list  = new ArrayList<String>();

        call_owner_details();

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
            bring_manager_list(1,managerlist);
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

           bring_player_list(2,playerlist);
            }

        else if(menuItem.getItemId()==R.id.owner_log_out){
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


    private void call_owner_details() {
        bring_person_Info(1,uid,person_list);
        bring_manager_list(1,managerlist);
        bring_player_list(2,playerlist);

    }

    private  void bring_manager_list(int flag,ArrayList<String> list){

        Bring_Managers_Players_DB bmp = new Bring_Managers_Players_DB(flag,list);
        Thread tt = new Thread(bmp);
        tt.start();

            Thread updateUi = new Thread(){
                public void run() {
                    try{
                        Thread.sleep(4500);
                        handler4.sendEmptyMessage(0);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        Log.d("failed","exception");
                    }
                }
            };
            updateUi.start();
    }

    private  void bring_player_list(int flag,ArrayList<String> list){

        Bring_Managers_Players_DB bmp = new Bring_Managers_Players_DB(flag,list);
        Thread tt = new Thread(bmp);
        tt.start();

            Thread updateUi = new Thread() {
                public void run() {
                    try {
                        Thread.sleep(4500);
                        handler5.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("failed", "exception");
                    }
                }
            };
            updateUi.start();

    }

    private void push_set_options(int flag,String ud,ArrayList oList){

        Set_Matters_DB smdb = new Set_Matters_DB(flag,ud,oList,mytoast2);

         Thread tt = new Thread(smdb);
         tt.start();

        if(flag==1){
         Thread updateUi = new Thread(){
            public void run() {
                try{
                    Thread.sleep(1000);
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
        if(flag==2) {
            Thread updateUi = new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        handler2.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("failed", "exception");
                    }
                }
            };
            updateUi.start();
        }

        if(flag==3) {
            Thread updateUi = new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        handler3.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("failed", "exception");
                    }
                }
            };
            updateUi.start();
        }
}


    private void bring_person_Info(int flag,String ud,ArrayList pList) {
        Bring_Personal_Info_DB bpi = new Bring_Personal_Info_DB(flag, ud, pList);
        Thread tt = new Thread(bpi);
        tt.start();

            Thread updateUi = new Thread() {
                public void run() {
                    try {
                        Thread.sleep(4500);
                        handler.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("failed", "exception");
                    }
                }
            };
            updateUi.start();

    }

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String id = "empty";
            String name = "empty";
            String country = "empty";

            Person_Info pi = person_list.get(0);

            id = pi.getPerson_id();
            name = pi.getPerson_name();
            country = pi.getPerson_country();


            owner_cont1_id.setText(id);
            owner_cont1_name.setText(name);
            owner_cont1_country.setText(country);
            person_list.clear();

        }
    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.owner_cont1_confirm_change){

            if((owner_cont1_input.getText().toString()).equals("")){
                mytoast.setText("Invalid Input");
                mytoast.show();
            }
            else{
                Reset_Password rp = new Reset_Password(1,uid,owner_cont1_input.getText().toString(),mytoast2);
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
        if( v.getId()== R.id.owner_cont2_btn) {

            String a = owner_cont2_objective.getText().toString();
            String b = owner_cont2_manager_id.getText().toString();

            Money_And_Honey mh = new Money_And_Honey(a,b);
            optionList.add(mh);

            push_set_options(1, uid, optionList);
        }
        if( v.getId()== R.id.owner_cont3_manager_done) {

            String a = owner_cont3_manager_id.getText().toString();
            String b = owner_cont3_manager_income.getText().toString();
            String c = owner_cont3_manager_bonus.getText().toString();

            Money_And_Honey mh = new Money_And_Honey(a,b,c);
            optionList.add(mh);

            push_set_options(2, uid, optionList);
        }
        if( v.getId()==R.id.owner_cont3_player_done) {

            String a = owner_cont3_player_id.getText().toString();
            String b = owner_cont3_player_income.getText().toString();
            String c = owner_cont3_player_bonus.getText().toString();

            Money_And_Honey mh = new Money_And_Honey(0,a,b,c);
            optionList.add(mh);

            push_set_options(3, uid, optionList);
        }

        if( v.getId()==R.id.owner_cont4_btn) {

            String a = owner_cont4_input1.getText().toString();
            String b = owner_cont4_input2.getText().toString();
            String c = owner_cont4_input3.getText().toString();
            String d = owner_cont4_input4.getText().toString();
            String e = owner_cont4_input5.getText().toString();

            add_manager_list.add(a);
            add_manager_list.add(b);
            add_manager_list.add(c);
            add_manager_list.add(d);
            add_manager_list.add(e);

            add_player_manager_options(1,add_manager_list);
        }
        if( v.getId()==R.id.owner_cont5_btn) {

            String a = owner_cont5_input1.getText().toString();
            String b = owner_cont5_input2.getText().toString();
            String c = owner_cont5_input3.getText().toString();
            String d = owner_cont5_input4.getText().toString();
            String e = owner_cont5_input5.getText().toString();
            String f = owner_cont5_input6.getText().toString();

            add_player_list.add(a);
            add_player_list.add(b);
            add_player_list.add(c);
            add_player_list.add(d);
            add_player_list.add(e);
            add_player_list.add(f);

            add_player_manager_options(2,add_player_list);
        }
    }

    private void add_player_manager_options(int flag, ArrayList<String> list){

        Add_Manager_Players_DB amp = new Add_Manager_Players_DB(flag,list,mytoast2);
        Thread tt = new Thread(amp);
        tt.start();

        if(flag==1){
            Thread updateUi = new Thread(){
                public void run() {
                    try{
                        Thread.sleep(1000);
                        handler6.sendEmptyMessage(0);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        Log.d("failed","exception");
                    }
                }
            };
            updateUi.start();
        }
        if(flag==2) {
            Thread updateUi = new Thread() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        handler7.sendEmptyMessage(0);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Log.d("failed", "exception");
                    }
                }
            };
            updateUi.start();
        }

    }

    private Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            owner_cont2_objective.getText().clear();
            owner_cont2_manager_id.getText().clear();
            //optionList.clear();
        }
    };
    private Handler handler2 = new Handler() {
        public void handleMessage(Message msg) {
            owner_cont3_manager_id.getText().clear();
            owner_cont3_manager_income.getText().clear();
            owner_cont3_manager_bonus.getText().clear();
            //optionList.clear();
        }
    };
    private Handler handler3 = new Handler() {
        public void handleMessage(Message msg) {

            owner_cont3_player_id.getText().clear();
            owner_cont3_player_income.getText().clear();
            owner_cont3_player_bonus.getText().clear();
            //optionList.clear();
        }
    };

    private Handler handler4 = new Handler() {
        public void handleMessage(Message msg) {

            List<String> how = new ArrayList<String>(managerlist);

            ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                 (getBaseContext(),android.R.layout.simple_list_item_1,how);

            gv_o6.setAdapter(gridViewArrayAdapter);
            managerlist.clear();

        }
    };
    private Handler handler5 = new Handler() {
        public void handleMessage(Message msg) {

            List<String> how = new ArrayList<String>(playerlist);
            ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                    (getBaseContext(),android.R.layout.simple_list_item_1,how);

            gv_o7.setAdapter(gridViewArrayAdapter);
            playerlist.clear();
        }
    };

    private Handler handler6 = new Handler() {
        public void handleMessage(Message msg) {
            owner_cont4_input1.getText().clear();
             owner_cont4_input2.getText().clear();
             owner_cont4_input3.getText().clear();
             owner_cont4_input4.getText().clear();
             owner_cont4_input5.getText().clear();
         //    add_manager_list.clear();

        }
    };
    private Handler handler7 = new Handler() {
        public void handleMessage(Message msg) {

            owner_cont5_input1.getText().clear();
            owner_cont5_input2.getText().clear();
            owner_cont5_input3.getText().clear();
            owner_cont5_input4.getText().clear();
            owner_cont5_input5.getText().clear();
            owner_cont5_input6.getText().clear();
           // add_player_list.clear();
        }
    };

    private Handler handler8 = new Handler() {
        public void handleMessage(Message msg) {

            owner_cont1_input.getText().clear();

        }
    };
}
