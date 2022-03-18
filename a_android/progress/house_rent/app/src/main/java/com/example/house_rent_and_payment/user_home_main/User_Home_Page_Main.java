package com.example.house_rent_and_payment.user_home_main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import com.example.house_rent_and_payment.R;
import com.example.house_rent_and_payment.user_authentication.Sign_In_Page;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class User_Home_Page_Main extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        View.OnClickListener{

    private static final int REQUEST_LOCATION = 1;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle dtog;

    Sections_Pager_Adapter sectionsPagerAdapter;
    ViewPager viewPager;
    TabLayout tabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_a_user_home_main_layout);

        Toolbar main_toolbar_id = (Toolbar) findViewById(R.id.main_toolbar_id);
        setSupportActionBar(main_toolbar_id);

        NavigationView nv = findViewById(R.id.b_a_user_home_main_nav);
        nv.setItemIconTintList(null);
        nv.setNavigationItemSelectedListener(this);

        drawerLayout = findViewById(R.id.b_user_main_drawer);
        dtog = new ActionBarDrawerToggle(this,drawerLayout,main_toolbar_id,R.string.nav_open,R.string.nav_close);

        drawerLayout.addDrawerListener(dtog);
        dtog.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),0);
        viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        int id = menuItem.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.b_user_home_main_btn) {
            sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),0);
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            return true;
        }
        else if (id == R.id.c_user_profile_main_btn) {
            sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),1);
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            return true;
        }
        else if (id == R.id.d_payment_main_btn) {
            sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),2);
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            return true;
        }
        else if (id == R.id.e_messages_main_btn) {
            sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),3);
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            return true;
        }
        else if (id == R.id.f_news_notify_main_btn) {
            sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),4);
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            return true;
        }
        else if (id == R.id.g_my_apartment_main_btn) {
            sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),5);
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            return true;
        }
        else if (id == R.id.h_my_rent_main_btn) {
            sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),6);
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            return true;
        }
        else if (id == R.id.i_available_rent_main_btn) {
            sectionsPagerAdapter = new Sections_Pager_Adapter(this, getSupportFragmentManager(),7);
            viewPager = findViewById(R.id.view_pager);
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs = findViewById(R.id.tabs);
            tabs.setupWithViewPager(viewPager);
            return true;
        }
        else if (id == R.id.j_user_log_out_btn) {
            FirebaseAuth.getInstance().signOut();
            finish();
            return true;
        }
        return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.extra_facilities, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_selection) {
            return true;
        }
        else if (id == R.id.action_selection) {
            return true;
        }
        else if (id == R.id.action_selection) {
            return true;
        }
        else if (id == R.id.action_selection) {
            return true;
        }
        else if (id == R.id.action_selection) {
            return true;
        }
        else if (id == R.id.action_selection) {
            return true;
        }
        else if (id == R.id.action_selection) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Toast.makeText(this, "image root home", Toast.LENGTH_SHORT).show();
         }
}
