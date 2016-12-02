package com.example.livruen.kummerkasten;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by livruen on 22.11.16.
 */
public class Betreuer extends AppCompatActivity {


    public static final String HEADER = "document.getElementsByTagName('header')[0].style.display=\"none\";";
    public static final String HEADER_IMAGE = "document.getElementsByClassName('header-image')[0].style.display=\"none\";";
    public static final String ENTRY_HEADER = "document.getElementsByClassName('entry-header')[0].style.display=\"none\";";
    public static final String PANEL = "document.getElementsByClassName('panel-row-style')[0].style.display=\"none\";";
    public static final String FOOTER = "document.getElementsById('sidebar-footer.footer-widgets.widget-area')[0].style.display=\"none !important\"  ;";
    public static final String COLOFON = "document.getElementsByClassName('site-footer')[0].style.display=\"none\"  ;";

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
                        HEADER + HEADER_IMAGE + ENTRY_HEADER + PANEL + FOOTER + COLOFON+
                        "})()");
            }
        });
        // https://jsoup.org/
        webView.loadUrl(siteName);
    }

}
