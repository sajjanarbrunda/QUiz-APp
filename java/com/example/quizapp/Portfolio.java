package com.example.quizapp;

import android.os.Bundle;
import android.webkit.WebView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Portfolio extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.portfolio);


        WebView browser = (WebView)findViewById(R.id.webview);
        browser.loadUrl("http://mehost.xp3.biz/brunda/");
    }
}
