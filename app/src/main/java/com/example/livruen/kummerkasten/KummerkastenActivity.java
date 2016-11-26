package com.example.livruen.kummerkasten;

import android.app.FragmentManager;
import android.content.ClipData;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class KummerkastenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kummerkasten);

        // Set WP Webside
        String restURL = "http://study.mipsol.com";
        //setView(restURL);



        new RestOperation().execute(restURL);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void setView(String URL) {
        WebView view = (WebView) this.findViewById(R.id.webView);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(URL);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.kummerkasten, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();


        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_news) {
            fragmentManager.beginTransaction().replace(R.id.webView, new News()).commit();
        } else if (id == R.id.nav_einrichtungen) {
            fragmentManager.beginTransaction().replace(R.id.webView, new Einrichtungen()).commit();
        } else if (id == R.id.nav_veranstaltungen) {
            fragmentManager.beginTransaction().replace(R.id.webView, new Veranstaltungen()).commit();
        } else if (id == R.id.nav_kummerkasten) {
            fragmentManager.beginTransaction().replace(R.id.webView, new Kummerkasten()).commit();
        } else if (id == R.id.nav_betreuer) {
            fragmentManager.beginTransaction().replace(R.id.webView, new Betreuer()).commit();
            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public class RestOperation extends AsyncTask<String, Void, Void> {

        TextView textView = (TextView) findViewById(R.id.id_label2);
        String data;
        String content;

        @Override
        protected Void doInBackground(String... params) {

            URL url = null;
            HttpURLConnection urlConnection = null;

            try {
                url = new URL(params[0]);

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setDoOutput(true);

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                 content =  readStream(in);



            } catch (IOException e) {
                e.printStackTrace();

            } finally {
                urlConnection.disconnect();
            }

            return null;
        }

        private String readStream(InputStream in) {

            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            String line = "";
            StringBuilder builder = new StringBuilder();

            try {
                while ((line = reader.readLine()) != null){
                    builder.append(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return builder.toString();
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            String output = "";
            JSONObject jsonResponse;

            try {
                jsonResponse = new JSONObject(content);

                JSONArray jsonArray = jsonResponse.optJSONArray("study");

                for(int i = 0; i < jsonArray.length(); i++) {
                    JSONObject child = jsonArray.getJSONObject(i);

                    String id = child.getString("id");

                    output += id + " ";
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            textView.setText(output);
            //  textView.setText(content);


        }
    }
}
