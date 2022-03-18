package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Searchresult extends AppCompatActivity {

    Toast mytoast3;
    Toast mytoast2;
    Toast mytoast;

    int mychecker[];
EditText searchText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.searchresult);

        searchText = findViewById(R.id.editText7se);

        mytoast3 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);

        mytoast.setText("Please fill up input fields !");
        mytoast.show();
        mychecker = new int[1];
        mychecker[0] = 0;


    }

    public void confirm_home6(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }

    public void confirm_lostfndpage(View view) {
        Intent in = new Intent(this, MainActivity.class);
        startActivity(in);
        finish();
    }

    public void searching(View view) {

        if(searchText.getText().toString().trim().length() > 0) {

            Search_Data sd = new Search_Data(searchText.getText().toString(),
                                       mytoast,mytoast2,mychecker);

            Search_DB sdb = new Search_DB(sd);

            Thread tt = new Thread(sdb);
            tt.start();

            Thread match = new Thread(){
                public void run() {
                    try {
                        Thread.sleep(4000);
                        if(mychecker[0]==1){
                            Intent in = new Intent(getBaseContext(),Login.class);
                            startActivity(in);
                            mychecker[0]=0;
                        }
                        else{
                            Thread.sleep(4000);
                            if(mychecker[0]==1){
                                Intent in = new Intent(getBaseContext(),Login.class);
                                startActivity(in);
                                mychecker[0]=0;
                            }
                            else{
                                mychecker[0]=0;
                                mytoast3.setText("Network issues !!");
                                mytoast3.show();
                            }
                        }
                    }
                    catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            match.start();


        }
        else{
            mytoast.setText("Check your info again!");
            mytoast.show();
        }

    }
}
