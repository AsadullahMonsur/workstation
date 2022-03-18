package com.example.football_management_system;

import android.content.Context;
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

public class Viewer_Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle dtog;
    Context context;

    RelativeLayout layout;
    RelativeLayout layout1;
    RelativeLayout layout2;
    RelativeLayout layout3;

    TextView viewer_cont1_id;
    TextView viewer_cont1_name;
    TextView viewer_cont1_email;
    EditText viewer_cont1_input;
    Button viewer_cont1_btn;

    ArrayList<Person_Info> person_list_viewer;
    ArrayList<String> playerlist;
    ArrayList<String> match_list;

    GridView viewer_con2_gv;
    GridView viewer_con3_gv;

    static String user_mail;

    Toast mytoast;
    Toast mytoast2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewer_home);

        Intent in = getIntent();
        user_mail = in.getStringExtra("user_mail");

        context = getApplicationContext();
        layout = findViewById(R.id.vc);
        layout1 = findViewById(R.id.vc1);
        layout2 = findViewById(R.id.vc2);
        layout3 = findViewById(R.id.vc3);

        layout.setVisibility(View.VISIBLE);
        layout1.setVisibility(View.INVISIBLE);
        layout2.setVisibility(View.INVISIBLE);
        layout3.setVisibility(View.INVISIBLE);


        viewer_con2_gv = findViewById(R.id.viewer_cont2_gv1);
        viewer_con3_gv = findViewById(R.id.viewer_cont3_gv);

        NavigationView nv = findViewById(R.id.nav_viewer_layout);
        nv.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.viewer_drawer);
        dtog = new ActionBarDrawerToggle(this,drawerLayout,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(dtog);
        dtog.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewer_cont1_id    = findViewById(R.id.viewer_cont1_id);
        viewer_cont1_name  = findViewById(R.id.viewer_cont1_name);
        viewer_cont1_email = findViewById(R.id.viewer_cont1_email);
        viewer_cont1_input = findViewById(R.id.viewer_cont1_password);
        viewer_cont1_btn  = findViewById(R.id.viewer_confirm_change);
        viewer_cont1_btn.setOnClickListener(this);

        person_list_viewer = new ArrayList<Person_Info>();
        playerlist = new ArrayList<String>();
        match_list = new ArrayList<String>();

        mytoast = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null,Toast.LENGTH_LONG);

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

        if(menuItem.getItemId()==R.id.viewer_profile){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.VISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.INVISIBLE);
        }
        else if(menuItem.getItemId()==R.id.viewer_show_team){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.VISIBLE);
            layout3.setVisibility(View.INVISIBLE);
            bring_player_list(3,playerlist);
        }
        else if(menuItem.getItemId()==R.id.viewer_match_history){
            layout.setVisibility(View.INVISIBLE);
            layout1.setVisibility(View.INVISIBLE);
            layout2.setVisibility(View.INVISIBLE);
            layout3.setVisibility(View.VISIBLE);
        }

        else if(menuItem.getItemId()==R.id.viewer_log_out){
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
        bring_player_list(3,playerlist);
        bring_person_Info(4,user_mail,person_list_viewer);
        bring_match_info(2,match_list);
    }

    private void bring_match_info(int flag,ArrayList<String> List){
         View_Match_DB  vmdb = new View_Match_DB(flag,List);
        Thread tt = new Thread(vmdb);
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

    private void bring_person_Info(int flag,String um,ArrayList<Person_Info> pList){
        Bring_Personal_Info_DB bpi = new Bring_Personal_Info_DB(flag,um,pList);
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

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            String fname = "empty";
            String lname = "empty";
            String email = "empty";

            Person_Info pi = person_list_viewer.get(0);

            fname = pi.getPerson_fname();
            lname = pi.getPerson_lname();
            email = pi.getPerson_mail();


            viewer_cont1_id.setText(fname);
            viewer_cont1_name.setText(lname);
            viewer_cont1_email.setText(email);
           // person_list_viewer.clear();

        }
    };

    private Handler handler5 = new Handler() {
        public void handleMessage(Message msg) {

            List<String> how = new ArrayList<String>(playerlist);
            ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                    (getBaseContext(),android.R.layout.simple_list_item_1,how);

            viewer_con2_gv.setAdapter(gridViewArrayAdapter);
            playerlist.clear();
        }
    };

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

    private Handler handler3 = new Handler() {
        public void handleMessage(Message msg) {

            List<String> how = new ArrayList<String>(match_list);
            ArrayAdapter<String> gridViewArrayAdapter = new ArrayAdapter<String>
                    (getBaseContext(),android.R.layout.simple_list_item_1,how);

            viewer_con3_gv.setAdapter(gridViewArrayAdapter);
            match_list.clear();
        }
    };

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.viewer_confirm_change){

            if((viewer_cont1_input.getText().toString()).equals("")){
                mytoast.setText("Invalid Input");
                mytoast.show();
            }
            else{
                Reset_Password rp = new Reset_Password(4,user_mail,viewer_cont1_input.getText().toString(),mytoast2);
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


    private Handler handler8 = new Handler() {
        public void handleMessage(Message msg) {
            viewer_cont1_input.getText().clear();
        }
    };
}
