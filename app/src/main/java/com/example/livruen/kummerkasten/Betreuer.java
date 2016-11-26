package com.example.livruen.kummerkasten;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
public class Betreuer extends Fragment {

    private static final String TAG_ID = "id";

    View myView;
    static JSONObject jObj = null;
    static String json = "";
    JSONArray user = null;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.betreuer, container, false);


//        HttpURLConnection connection = null;
//
        String siteName = "http://study.mipsol.com/betreuer/";
//        URL url = null;
//
//        TextView textView =  (TextView) view.findViewById(R.id.id_label);
//
//
//        try {
//            url = new URL(siteName);
//            connection = (HttpURLConnection) url.openConnection();
//            connection.setDoInput(true);
//            connection.setConnectTimeout(20 * 1000);
//            connection.setReadTimeout(20 * 1000);
//
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK){
//                InputStream stream = connection.getInputStream();
//
//                BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
//
//                StringBuilder buffer = new StringBuilder();
//
//                String line = "";
//                while ((line = reader.readLine()) != null) {
//                    buffer.append(line);
//                }
//                stream.close();
//                json = buffer.toString();
//
//                try {
//                    jObj = new JSONObject(json);
//                    user = jObj.getJSONArray(TAG_ID);
//
//                    JSONObject c = user.getJSONObject(0);
//
//                    // Storing  JSON item in a Variable
//                    String id = c.getString(TAG_ID);
//
//                    textView.setText(id);
//
//
//                } catch (JSONException e) {
//                    Log.e("JSON Parser", "Error parsing data " + e.toString());
//                }
//            }
//
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            connection.disconnect();
//        }
//

//user = json.getJSONArray(TAG_USER);




        WebView mWebView = (WebView) view.findViewById(R.id.webView);

        mWebView.loadUrl(siteName);

        // Enable Javascript
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        // Force links and redirects to open in the WebView instead of in a browser
        mWebView.setWebViewClient(new WebViewClient());

        return view;
    }

}
