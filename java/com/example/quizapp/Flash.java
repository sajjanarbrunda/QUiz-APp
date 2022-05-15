package com.example.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import androidx.annotation.ContentView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Flash  extends AppCompatActivity {
    private static final int SPLASH_TIME_OUT = 1500;
    ImageView imageView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.flash);
        imageView = findViewById(R.id.img_quiztime);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent flashIntent = new Intent(Flash.this , MainActivity.class);
                startActivity(flashIntent);
                finish();
            }
        },SPLASH_TIME_OUT);

    }
}
