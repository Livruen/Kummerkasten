package com.example.livruen.kummerkasten;

import android.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import android.app.ProgressDialog;

import okhttp3.OkHttpClient;
import okhttp3.Response;

public class KummerkastenActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , OnPostListener{


    ProgressDialog progressDialog;
    String restURL;
    ListView listView;
    PostAdapter adapter;
    ArrayList<Post> posts = new ArrayList<>();
    RestOperation restOperation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kummerkasten);

        //showProgressDialog();

        listView = (ListView) findViewById(R.id.listView);


        adapter = new PostAdapter(getApplicationContext(), R.layout.post);


        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Post post = posts.get(position);

                Intent intent = new Intent(getApplicationContext(), PostActivity.class);
                intent.putExtra("content", post.getContent());

                startActivity(intent);
            }
        });


        // Set WP Webside
        restOperation = new RestOperation(this);
        restURL = "http://study.mipsol.com/wp-json/wp/v2/posts/?filter[category_name]=news";
        restOperation.execute(restURL);


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

    private void showProgressDialog() {
        progressDialog = new ProgressDialog(KummerkastenActivity.this);
        progressDialog.setMessage("Loading...");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.show();
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
//
//        if (id == R.id.nav_news) {
//            fragmentManager.beginTransaction().replace(R.id.webView, new News()).commit();
//            restURL = "http://study.mipsol.com/wp-json/wp/v2/posts/?filter[category_name]=news";
//            restOperation.execute(restURL);
//        }

//        } else if (id == R.id.nav_einrichtungen) {
//            fragmentManager.beginTransaction().replace(R.id.webView, new Einrichtungen()).commit();
//            new RestOperation().execute(restURL);
//
//        } else if (id == R.id.nav_veranstaltungen) {
//            fragmentManager.beginTransaction().replace(R.id.webView, new Veranstaltungen()).commit();
//            new RestOperation().execute(restURL);

            if (id == R.id.nav_betreuer) {
        //    fragmentManager.beginTransaction().replace(R.id.webView_betreuer, new Betreuer()).commit();
                startActivity(new Intent(getApplicationContext(), Betreuer.class));
            } else if (id == R.id.nav_beratungsstellen) {
                startActivity(new Intent(getApplicationContext(), Beratungsstellen.class));
            } else if (id == R.id.nav_einrichtungen) {
                startActivity(new Intent(getApplicationContext(), Einrichtungen.class));
            } else if (id == R.id.nav_news) {
                startActivity(new Intent(getApplicationContext(), KummerkastenActivity.class));
            } else if (id == R.id.nav_kummerkasten) {
                startActivity(new Intent(getApplicationContext(), Kummerkasten.class));
            }else if (id == R.id.nav_veranstaltungen) {
                startActivity(new Intent(getApplicationContext(), Veranstaltungen.class));

            }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onPost(JSONArray jsonArray) {
        for (int i = 0; i< jsonArray.length(); i++) {
            JSONObject obj = jsonArray.optJSONObject(i);
            Post post = new Post(obj);
            posts.add(post);
        }
        adapter.addAll(posts);
        adapter.notifyDataSetChanged();
    }




    public class RestOperation extends AsyncTask<String, Void, JSONArray> {

        public OnPostListener listner;

        public RestOperation(OnPostListener listner) {
            this.listner = listner;
        }

        @Override
        protected JSONArray doInBackground(String... params) {

            String url = params[0];
            OkHttpClient client = new OkHttpClient();
            okhttp3.Request.Builder builder = new okhttp3.Request.Builder();
            okhttp3.Request request = builder.url(url).build();

            try {
                Response response = client.newCall(request).execute();
                String json = response.body().string();

                JSONArray jsonArray = new JSONArray(json);

                return jsonArray;

            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(JSONArray jsonArray) {
            super.onPostExecute(jsonArray);

            if(jsonArray == null){
                return;
            }

            if(listner != null) {
                listner.onPost(jsonArray);
            }
        }
    }

    public class PostAdapter extends ArrayAdapter<Post> {

        private int resource;

        public PostAdapter(Context context, int resource) {

            super(context, resource);
            this.resource = resource;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Convert view -> reuse

            if(convertView == null){
                LayoutInflater inflater = LayoutInflater.from(getContext());
                convertView = inflater.inflate(resource, null);
            }

            Post post = getItem(position);
            // Binding data
            TextView title = (TextView) convertView.findViewById(R.id.title);
            TextView excerpt = (TextView) convertView.findViewById(R.id.excerpt);
            ImageView imageView = (ImageView) convertView.findViewById(R.id.imageView);

            title.setText(post.getTitle());
            excerpt.setText(post.getExcerpt());

           // String imgURL = post.getImage();
          //  Glide.with(getContext()).load(imgURL).into(imageView);

            return convertView;
        }
    }
}
