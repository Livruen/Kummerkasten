package com.example.livruen.kummerkasten;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class PostActivity extends AppCompatActivity {
private WebView webView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        webView = (WebView) findViewById(R.id.webView_posts);

        String content = getIntent().getStringExtra("content");
        webView.setHorizontalScrollBarEnabled(false);

        webView.loadData(content,  "text/html; charset=UTF-8", null);
    }
}
