package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView welcome , A_T_B;
    ImageView imageView;
    EditText name ;
    Button play;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        welcome = findViewById(R.id.tv_well_come);
        A_T_B = findViewById(R.id.tv_allthebest);
        imageView = findViewById(R.id.img_all_the_best);
        name = findViewById(R.id.ed_name);
        play = findViewById(R.id.btn_play);

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(name.getText().toString().trim().length() == 0) {
                    name.setError("enter valid name");
                    name.requestFocus();

                }
                else{


                    Intent dashboardIntent = new Intent(MainActivity.this, Dashboard.class);
                    startActivity(dashboardIntent);
                }
            }
        });


    }
}