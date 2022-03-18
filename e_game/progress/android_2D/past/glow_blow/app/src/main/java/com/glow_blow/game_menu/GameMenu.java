package com.glow_blow.game_menu;

import android.animation.ObjectAnimator;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.glow_blow.R;

import java.util.ArrayList;

public class GameMenu extends AppCompatActivity {
    private ImageView left_rotate;
    private ImageView right_rotate;

    private ImageView ring;
    private ImageView options;

    private float angle_tracker = 0.0f;
    private int option_tracker = 0;
    private ArrayList<Integer> options_img_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_menu);
        show_notification("Game Menu Loaded");

        left_rotate = findViewById(R.id.game_menu_left);
        right_rotate = findViewById(R.id.game_menu_right);

        ring = findViewById(R.id.game_menu_ring_img);
        options = findViewById(R.id.game_menu_options_img);

        options_img_list = new ArrayList<Integer>();
        options_img_list.add(R.mipmap.glow_blow_menu_options_foreground);
        options_img_list.add(R.mipmap.glow_blow_menu_options_01_foreground);
        options_img_list.add(R.mipmap.glow_blow_menu_options_02_foreground);
        options_img_list.add(R.mipmap.glow_blow_menu_options_03_foreground);
        options_img_list.add(R.mipmap.glow_blow_menu_options_04_foreground);
        options_img_list.add(R.mipmap.glow_blow_menu_options_05_foreground);
        options_img_list.add(R.mipmap.glow_blow_menu_options_06_foreground);
        options_img_list.add(R.mipmap.glow_blow_menu_options_07_foreground);

        configure_rotation(left_rotate,ring,-1,45);
        configure_rotation(right_rotate,ring,1,45);
    }

    private void configure_rotation(View rotate_by, View rotate_to,
                                    int direction, float angle) {
        rotate_by.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float past = angle_tracker;
                angle_tracker = angle_tracker + (direction*angle);
                ObjectAnimator.ofFloat(rotate_to, "rotation",
                        past, angle_tracker).start();

                if(direction==1){
                    option_tracker = option_tracker - 1;
                }
                else if(direction==-1){
                    option_tracker = option_tracker + 1;
                }

                if(option_tracker>7){
                    option_tracker = 0;
                }
                else if(option_tracker<0){
                    option_tracker = 7;
                }

                options.setImageResource(options_img_list.get(option_tracker));
            }
        });
    }

    private void show_notification(String s){
        Toast.makeText(this,s,Toast.LENGTH_SHORT).show();
    }
    private void show_notification_long(String s){
        Toast.makeText(this,s,Toast.LENGTH_LONG).show();
    }
}
