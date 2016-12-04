package com.example.livruen.kummerkasten;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by livruen on 22.11.16.
 */
public class Kummerkasten extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String siteName = "http://study.mipsol.com/kontakt/";
        setContentView(R.layout.kummerkasten);

        final WebView webView = (WebView) findViewById(R.id.webView_kummerkasten);


        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:(function() { " +
                        HTMLparser.HEADER.getValue() + HTMLparser.HEADER_IMAGE.getValue() + HTMLparser.ENTRY_HEADER.getValue() + HTMLparser.FOOTER.getValue() + HTMLparser.COLOFON.getValue() + HTMLparser.CONTENT.getValue() +
                        "})()");
            }
        });
        // https://jsoup.org/
        webView.loadUrl(siteName);
    }
}
