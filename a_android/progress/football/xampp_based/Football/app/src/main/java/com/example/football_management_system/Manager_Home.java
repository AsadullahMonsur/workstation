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

public class Manager_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    RelativeLayout layout;
    RelativeLayout layout1;
    RelativeLayout layout2;
    RelativeLayout layout3;
    RelativeLayout layout4;
    RelativeLayout layout5;

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle dtog;

    TextView manager_cont1_id;
    TextView manager_cont1_name;
    TextView manager_cont1_country;
    EditText manager_cont1_input;
    Button manager_cont1_btn;

    TextView manager_cont2_income;
    TextView manager_cont2_bonus;

    EditText manager_cont3_player_id;
    EditText manager_cont3_player_point;
    Button manager_cont3_btn;

    EditText manager_cont4_goal_match_id;
    EditText manager_cont4_goal_player_id;
    EditText manager_cont4_goal_num;
    EditText manager_cont4_goal_match_time;
    Button manager_con4_btn1;

    EditText manager_cont4_card_match_id;
    EditText manager_cont4_card_player_id;
    EditText manager_cont4_card_type;
    EditText manager_cont4_card_match_time;
    Button manager_con4_btn2;

    EditText manager_cont4_injury_match_id;
    EditText manager_cont4_injury_player_id;
    EditText manager_cont4_injury_time;
    Button manager_con4_btn3;

    EditText manager_cont4_set_match_id;
    EditText manager_cont4_set_venu;
    Button manager_cont4_btn4;

    ArrayList<Person_Info> person_list;
    ArrayList<Money_And_Honey> optionList;
    ArrayList<Income> myincome;

    ArrayList<String> m_con5_match_list;
    ArrayList<String> m_con5_goal_list;
    ArrayList<String> m_con5_card_list;
    ArrayList<String> m_con5_injury_list;
    GridView m_con5_gv1;
    GridView m_con5_gv2;
    GridView m_con5_gv3;
    GridView m_con5_gv4;

    static String uid;

    Toast mytoast;
    Toast mytoast1;
    Toast mytoast2;
    Toast mytoast3;
    Toast mytoast4;
    Toast mytoast5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_home);

        Intent in = getIntent();
        uid = in.getStringExtra("us_id");


        mytoast = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast1 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast3 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast4 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast5 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);


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


        manager_cont1_id      = findViewById(R.id.manager_cont1_id);
        manager_cont1_name    = findViewById(R.id.manager_cont1_name);
        manager_cont1_country = findViewById(R.id.manager_cont1_country);
        manager_cont1_input = findViewById(R.id.manager_cont1_password);
        manager_cont1_btn = findViewById(R.id.manager_confirm_change);
        manager_cont1_btn.setOnClickListener(this);

        manager_cont2_income = findViewById(R.id.manager_cont2_income);
        manager_cont2_bonus  = findViewById(R.id.manager_cont2_bonus);

        manager_cont3_player_id    = findViewById(R.id.manager_cont3_player_id);
        manager_cont3_player_point = findViewById(R.id.manager_cont3_player_points);
        manager_cont3_btn          = findViewById(R.id.manager_cont3_btn);
        manager_cont3_btn.setOnClickListener(this);

        manager_cont4_goal_match_id   = findViewById(R.id.manager_cont4_input1);
        manager_cont4_goal_player_id  = findViewById(R.id.manager_cont4_input2);
        manager_cont4_goal_num        = findViewById(R.id.manager_cont4_input3);
        manager_cont4_goal_match_time = findViewById(R.id.manager_cont4_input4);
        manager_con4_btn1             = findViewById(R.id.manager_cont4_btn1);
        manager_con4_btn1.setOnClickListener(this);

        manager_cont4_card_match_id    = findViewById(R.id.manager_cont4_input5);
        manager_cont4_card_player_id   = findViewById(R.id.manager_cont4_input6);
        manager_cont4_card_type        = findViewById(R.id.manager_cont4_input7);
        manager_cont4_card_match_time  = findViewById(R.id.manager_cont4_input8);
        manager_con4_btn2              = findViewById(R.id.manager_cont4_btn2);
        manager_con4_btn2.setOnClickListener(this);

        manager_cont4_injury_match_id  = findViewById(R.id.manager_cont4_input9);
        manager_cont4_injury_player_id = findViewById(R.id.manager_cont4_input10);
        manager_cont4_injury_time      = findViewById(R.id.manager_cont4_input11);
        manager_con4_btn3              = findViewById(R.id.manager_cont4_btn3);
        manager_con4_btn3.setOnClickListener(this);

        manager_cont4_set_match_id  = findViewById(R.id.manager_cont4_input12);
        manager_cont4_set_venu      = findViewById(R.id.manager_cont4_input13);
        manager_cont4_btn4          = findViewById(R.id.manager_cont4_btn4);
        manager_cont4_btn4.setOnClickListener(this);

        m_con5_match_list = new ArrayList<String>();
        m_con5_goal_list = new ArrayList<String>();
        m_con5_card_list = new ArrayList<String>();
        m_con5_injury_list = new ArrayList<String>();

        m_con5_gv1 = findViewById(R.id.manager_cont5_gv1);
        m_con5_gv2 = findViewById(R.id.manager_cont5_gv2);
        m_con5_gv3 = findViewById(R.id.manager_cont5_gv3);
        m_con5_gv4 = findViewById(R.id.manager_cont5_gv4);

        NavigationView nv = findViewById(R.id.nav_manager_layout);
        nv.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.manager_drawer);
        dtog = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(dtog);
        dtog.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        person_list = new ArrayList<Person_Info>();
        optionList  = new ArrayList<Money_And_Honey>();
        myincome    = new ArrayList<Income>();

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

        if(menuItem.getItemId()==R.id.manager_profile){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
        }
        else if(menuItem.getItemId()==R.id.manager_view_income){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
        }
        else if(menuItem.getItemId()==R.id.manager_set_morale){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.VISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.INVISIBLE);
        }
        else if(menuItem.getItemId()==R.id.manager_current_match){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.VISIBLE);
            layout5.setVisibility(View.INVISIBLE);
        }
        else if(menuItem.getItemId()==R.id.manager_view_match){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            layout4.setVisibility(View.INVISIBLE);
            layout5.setVisibility(View.VISIBLE);

            view_match_info(1,m_con5_match_list,m_con5_goal_list,
                            m_con5_card_list,m_con5_injury_list);
        }

        else if(menuItem.getItemId()==R.id.manager_log_out){
            sendToLogin();
        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.manager_confirm_change){

            if((manager_cont1_input.getText().toString()).equals("")){
                mytoast.setText("Invalid Input");
                mytoast.show();
            }
            else{
                Reset_Password rp = new Reset_Password(2,uid,manager_cont1_input.getText().toString(),mytoast2);
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

        if( v.getId()== R.id.manager_cont3_btn) {

            String b = manager_cont3_player_point.getText().toString();
            String a = manager_cont3_player_id.getText().toString();

            Money_And_Honey mh = new Money_And_Honey(0,0,0,b,a);
            optionList.add(mh);

            push_set_options(4, uid, optionList);
        }

        if(v.getId()== R.id.manager_cont4_btn1){
        String a = manager_cont4_goal_match_id.getText().toString();
        String b = manager_cont4_goal_player_id.getText().toString();
        String c = manager_cont4_goal_num.getText().toString();
        String d  = manager_cont4_goal_match_time.getText().toString();

        set_current_match(1,a,b,c,d);
        }

        if(v.getId()== R.id.manager_cont4_btn2){
        String a = manager_cont4_card_match_id.getText().toString();
        String b = manager_cont4_card_player_id.getText().toString();
        String c = manager_cont4_card_type.getText().toString();
        String d = manager_cont4_card_match_time.getText().toString();
            set_current_match(2,a,b,c,d);
        }

        if(v.getId()== R.id.manager_cont4_btn3){
        String a = manager_cont4_injury_match_id.getText().toString();
        String b = manager_cont4_injury_player_id.getText().toString();
        String c = manager_cont4_injury_time.getText().toString();
            set_current_match(3,a,b,c,"");
        }
        if(v.getId()== R.id.manager_cont4_btn4){
        String a = manager_cont4_set_match_id.getText().toString();
        String b = manager_cont4_set_venu.getText().toString();
            set_current_match(4,a,b,"","");
        }

    }

    private void call_viewer_details(){
        bring_person_Info(2,uid,person_list);
        bring_person_income(1,uid,myincome);
        view_match_info(1,m_con5_match_list,m_con5_goal_list,
                m_con5_card_list,m_con5_injury_list);
    }


    private  void view_match_info(int flag,ArrayList<String> list1,
            ArrayList<String> list2,ArrayList<String>list3,ArrayList<String>list4){

        View_Match_DB vmdb = new View_Match_DB(flag,list1,list2,list3,list4);
        Thread tt = new Thread(vmdb);
        tt.start();

        Thread updateUi = new Thread() {
            public void run() {
                try {
                    Thread.sleep(4500);
                    handler7.sendEmptyMessage(0);
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d("failed", "exception");
                }
            }
        };
        updateUi.start();

    }


    private  void set_current_match(int flag, String a, String b,String c, String d){

        if(flag==1){
            Current_Match_DB cmdb = new Current_Match_DB(uid,a,b,c,d,flag,mytoast2);
            Thread tt = new Thread(cmdb);
            tt.start();

                        Thread updateUi = new Thread(){
                            public void run() {
                                try{
                                    Thread.sleep(1000);
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
        if(flag==2){
            Current_Match_DB cmdb = new Current_Match_DB(uid,a,b,c,d,flag,mytoast3,0);
            Thread tt = new Thread(cmdb);
            tt.start();
            Thread updateUi = new Thread(){
                public void run() {
                    try{
                        Thread.sleep(1000);
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
        if(flag==3){
            Current_Match_DB cmdb = new Current_Match_DB(uid,a,b,c,flag,mytoast4);
            Thread tt = new Thread(cmdb);
            tt.start();

            Thread updateUi = new Thread(){
                public void run() {
                    try{
                        Thread.sleep(1000);
                        handler5.sendEmptyMessage(0);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        Log.d("failed","exception");
                    }
                }
            };
            updateUi.start();
        }
        if(flag==4){
            Current_Match_DB cmdb = new Current_Match_DB(uid,a,b,flag,mytoast5);
            Thread tt = new Thread(cmdb);
            tt.start();
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

    }
    private void sendToLogin() {

        Intent in = new Intent(this,MainActivity.class);
        //in.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(in);
        finish();
    }


    private void bring_person_Info(int flag,String ud,ArrayList pList){
        Bring_Personal_Info_DB bpi = new Bring_Personal_Info_DB(flag,ud,pList);
        Thread tt = new Thread(bpi);
        tt.start();

        Thread updateUi = new Thread(){
            public void run() {
                try{
                    Thread.sleep(4500);
                    handler.sendEmptyMessage(0);
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

    private void push_set_options(int flag,String ud,ArrayList oList){

        Set_Matters_DB smdb = new Set_Matters_DB(flag,ud,oList,mytoast2);

        Thread tt = new Thread(smdb);
        tt.start();

        if(flag==4){
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


            manager_cont1_id.setText(id);
            manager_cont1_name.setText(name);
            manager_cont1_country.setText(country);
            person_list.clear();

        }
    };


    private Handler handler1 = new Handler() {
        public void handleMessage(Message msg) {
            manager_cont3_player_id.getText().clear();
            manager_cont3_player_point.getText().clear();
            optionList.clear();
        }
    };


    private Handler handler2 = new Handler() {
        public void handleMessage(Message msg) {
            String income  = "empty";
            String bonus   = "empty";

            Income pi = myincome.get(0);

            income = pi.getIncome();
            bonus = pi.getBonus();

            manager_cont2_income.setText(income);
            manager_cont2_bonus.setText(bonus);

            myincome.clear();

        }
    };

    private Handler handler3 = new Handler() {
        public void handleMessage(Message msg) {
            manager_cont4_goal_match_id.getText().clear();
            manager_cont4_goal_player_id.getText().clear();
            manager_cont4_goal_num.getText().clear();
            manager_cont4_goal_match_time.getText().clear();
        }
    };

    private Handler handler4 = new Handler() {
        public void handleMessage(Message msg) {
            manager_cont4_card_match_id.getText().clear();
            manager_cont4_card_player_id.getText().clear();
            manager_cont4_card_type.getText().clear();
            manager_cont4_card_match_time.getText().clear();
        }
    };
    private Handler handler5 = new Handler() {
        public void handleMessage(Message msg) {
            manager_cont4_injury_match_id.getText().clear();
            manager_cont4_injury_player_id.getText().clear();
            manager_cont4_injury_time.getText().clear();
        }
    };
    private Handler handler6 = new Handler() {
        public void handleMessage(Message msg) {
            manager_cont4_set_match_id.getText().clear();
            manager_cont4_set_venu.getText().clear();
        }
    };

    private Handler handler7 = new Handler() {
        public void handleMessage(Message msg) {

            List<String> how1 = new ArrayList<String>(m_con5_match_list);
            ArrayAdapter<String> gridViewArrayAdapter1 = new ArrayAdapter<String>
                    (getBaseContext(),android.R.layout.simple_list_item_1,how1);

            m_con5_gv1.setAdapter(gridViewArrayAdapter1);
            m_con5_match_list.clear();

            List<String> how2 = new ArrayList<String>(m_con5_goal_list);
            ArrayAdapter<String> gridViewArrayAdapter2 = new ArrayAdapter<String>
                    (getBaseContext(),android.R.layout.simple_list_item_1,how2);

            m_con5_gv2.setAdapter(gridViewArrayAdapter2);
            m_con5_goal_list.clear();

            List<String> how3 = new ArrayList<String>(m_con5_card_list);
            ArrayAdapter<String> gridViewArrayAdapter3 = new ArrayAdapter<String>
                    (getBaseContext(),android.R.layout.simple_list_item_1,how3);

            m_con5_gv3.setAdapter(gridViewArrayAdapter3);
            m_con5_card_list.clear();

            List<String> how4 = new ArrayList<String>(m_con5_injury_list);
            ArrayAdapter<String> gridViewArrayAdapter4 = new ArrayAdapter<String>
                    (getBaseContext(),android.R.layout.simple_list_item_1,how4);

            m_con5_gv4.setAdapter(gridViewArrayAdapter4);
            m_con5_injury_list.clear();
        }
    };

    private Handler handler8 = new Handler() {
        public void handleMessage(Message msg) {
            manager_cont1_input.getText().clear();
        }
    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
