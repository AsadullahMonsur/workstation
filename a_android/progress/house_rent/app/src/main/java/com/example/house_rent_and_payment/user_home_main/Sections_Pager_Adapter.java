package com.example.house_rent_and_payment.user_home_main;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import com.example.house_rent_and_payment.R;

public class Sections_Pager_Adapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] tab_titles_default  = new int[]{R.string.cont0_tab1, R.string.cont0_tab2,R.string.cont0_tab3};
    @StringRes
    private static final int[] tab_titles_profile = new int[]{R.string.cont1_tab1, R.string.cont1_tab2};
    @StringRes
    private static final int[] tab_titles_payment = new int[]{R.string.cont2_tab1, R.string.cont2_tab2, R.string.cont2_tab3};
    @StringRes
    private static final int[] tab_titles_messages = new int[]{R.string.cont3_tab1, R.string.cont3_tab2, R.string.cont3_tab3};
    @StringRes
    private static final int[] tab_titles_news_notifications = new int[]{R.string.cont4_tab1, R.string.cont4_tab2, R.string.cont4_tab3};
    @StringRes
    private static final int[] tab_titles_my_apartment = new int[]{R.string.cont5_tab1, R.string.cont5_tab2};
    @StringRes
    private static final int[] tab_titles_my_rent = new int[]{R.string.cont6_tab1, R.string.cont6_tab2, R.string.cont6_tab3};
    @StringRes
    private static final int[] tab_titles_available_rent = new int[]{R.string.cont7_tab1};

    static int detect_main_content = 0;
    private final Context mContext;

    public Sections_Pager_Adapter(Context context, FragmentManager fm,int detect_main_content) {
        super(fm);
        mContext = context;
        this.detect_main_content = detect_main_content;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a Placeholder_Fragment (defined as a static inner class below).
        return Placeholder_Fragment.newInstance(position + 1);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(detect_main_content==1){
            return mContext.getResources().getString(tab_titles_profile[position]);
        }
        else if(detect_main_content==2){
            return mContext.getResources().getString(tab_titles_payment[position]);
        }
        else if(detect_main_content==3){
            return mContext.getResources().getString(tab_titles_messages[position]);
        }
        else if(detect_main_content==4){
            return mContext.getResources().getString(tab_titles_news_notifications[position]);
        }
        else if(detect_main_content==5){
        return mContext.getResources().getString(tab_titles_my_apartment[position]);
        }
        else if(detect_main_content==6){
        return mContext.getResources().getString(tab_titles_my_rent[position]);
        }
        else if(detect_main_content==7){
        return mContext.getResources().getString(tab_titles_available_rent[position]);
        }
        return mContext.getResources().getString(tab_titles_default[position]);
    }

    @Override
    public int getCount() {
        // Show 3 total pages.

        if(detect_main_content==1){
            return 2;
          }
        else if(detect_main_content==2){
            return 3;
          }
        else if(detect_main_content==3){
            return 3;
          }
        else if(detect_main_content==4){
            return 3;
        }
        else if(detect_main_content==5){
            return 2;
        }
        else if(detect_main_content==6){
            return 3;
        }
        else if(detect_main_content==7){
            return 1;
        }
        return 3;
    }
}