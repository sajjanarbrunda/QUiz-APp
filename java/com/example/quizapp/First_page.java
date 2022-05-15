package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class First_page extends AppCompatActivity {
    TextView wellcome;
    Button myportflio, myapp;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_page);
        wellcome = (TextView)findViewById(R.id.tv_welcome);
        myportflio =(Button)findViewById(R.id.btn_myportfolio);
        myapp=(Button)findViewById(R.id.btn_myApp);


        myportflio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myprotoflioIntent = new Intent(First_page.this , Portfolio.class);
                startActivity(myprotoflioIntent);
            }
        });

        myapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myappIntent = new Intent(First_page.this , Flash.class );
                startActivity(myappIntent);
            }
        });



    }
}
