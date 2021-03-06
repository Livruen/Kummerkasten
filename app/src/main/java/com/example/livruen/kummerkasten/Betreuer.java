package com.example.livruen.kummerkasten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by livruen on 22.11.16.
 */
public class Betreuer extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String siteName = "http://study.mipsol.com/betreuer/";
        setContentView(R.layout.betreuer);

       final WebView webView = (WebView) findViewById(R.id.webView_betreuer);



        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:(function() { " +
                        HTMLparser.HEADER.getValue() + HTMLparser.HEADER_IMAGE.getValue() + HTMLparser.ENTRY_HEADER.getValue() + HTMLparser.PANEL.getValue() + HTMLparser.FOOTER.getValue() + HTMLparser.COLOFON.getValue() +
                        "})()");
            }
        });
        // https://jsoup.org/
        webView.loadUrl(siteName);
    }

}
