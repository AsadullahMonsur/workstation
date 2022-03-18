package com.example.myapplicationttst;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Foundpage extends AppCompatActivity {
    EditText prod;
    EditText loc;
    EditText imei;


    Toast mytoast;
    Toast mytoast2;
    Toast mytoast3;

    static int myChecker[];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.foundpage);

        prod=findViewById(R.id.editTextPD);;
        loc=findViewById(R.id.editText2Loc);;
        imei=findViewById(R.id.editText3imei);;

        mytoast3 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast2 = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);
        mytoast = Toast.makeText(getApplicationContext(),null, Toast.LENGTH_LONG);

        mytoast.setText("Please fill up input fields !");
        mytoast.show();
        myChecker = new int[1];
        myChecker[0] = 0;

    }

    public void confirm_home3(View view) {
        Intent in = new Intent(this, Postlogin.class);
        startActivity(in);
        finish();
    }

    public void found_search(View view) {

        if(prod.getText().toString().trim().length() > 0 &&
                loc.getText().toString().trim().length() > 0 &&
                imei.getText().toString().trim().length() > 0)
        {

            Found_Page_Data fpd = new Found_Page_Data(prod.getText().toString(),loc.getText().toString(),imei.getText().toString(),mytoast,mytoast2,myChecker);

            Found_Page_DB fpdb = new Found_Page_DB(fpd);

            Thread tt = new Thread(fpdb);
            tt.start();

            Thread match = new Thread(){
                public void run() {
                    try {
                        Thread.sleep(4000);
                        if(myChecker[0]==1){
                            Intent in = new Intent(getBaseContext(),Searchresult.class);
                            startActivity(in);
                            myChecker[0]=0;
                        }
                        else{
                            Thread.sleep(4000);
                            if(myChecker[0]==1){
                                Intent in = new Intent(getBaseContext(),Searchresult.class);
                                startActivity(in);
                                myChecker[0]=0;
                            }
                            else{
                                myChecker[0]=0;
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
