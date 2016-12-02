package com.example.livruen.kummerkasten;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by livruen on 02.12.16.
 */
public class Beratungsstellen extends AppCompatActivity {


    public static final String HEADER = "document.getElementsByTagName('header')[0].style.display=\"none\";";
    public static final String HEADER_IMAGE = "document.getElementsByClassName('header-image')[0].style.display=\"none\";";
    public static final String ENTRY_HEADER = "document.getElementsByClassName('entry-header')[0].style.display=\"none\";";
    public static final String PANEL = "document.getElementsByClassName('panel-row-style')[0].style.display=\"none\";";
    public static final String FOOTER = "document.getElementsById('sidebar-footer.footer-widgets.widget-area')[0].style.display=\"none !important\"  ;";
    public static final String COLOFON = "document.getElementsByClassName('site-footer')[0].style.display=\"none\"  ;";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        String siteName = "http://study.mipsol.com/beratungsstellen/";
        setContentView(R.layout.beratungsstellen);

        final WebView webView = (WebView) findViewById(R.id.webView_beratungsstellen);


        // Enable Javascript
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                webView.loadUrl("javascript:(function() { " +
                        HEADER + HEADER_IMAGE + ENTRY_HEADER + FOOTER + COLOFON +
                        "})()");
            }
        });
        // https://jsoup.org/
        webView.loadUrl(siteName);
    }
}
