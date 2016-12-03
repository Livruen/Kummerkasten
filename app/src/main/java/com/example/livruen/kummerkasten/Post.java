package com.example.livruen.kummerkasten;

import android.text.Html;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by livruen on 30.11.16.
 */
public class Post {

    private final String ID = "id";
    private final String TITLE = "title";
    private final String EXCERPT = "excerpt";
    private final String CONTENT = "content";
    private final String RENDERED = "rendered";
    private final String IMAGE = "featured_media";

    private String id;
    private String title;
    private String excerpt;
    private String content;
    private String image;


    public Post(JSONObject object) {

        try {
            this.id = object.optString(ID);
            this.title = object.getJSONObject(TITLE).optString(RENDERED);
            this.excerpt =  Html.fromHtml(object.getJSONObject(EXCERPT).optString(RENDERED)).toString();
            this.content = Html.fromHtml(object.getJSONObject(CONTENT).optString(RENDERED)).toString();
            Log.d("comtent", content);
            //TODO
            this.image =  object.getJSONObject("_links").getJSONArray("self").getJSONObject(0).optString("href");

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public String getContent() { return content; }

    public String getImage() {
        return image;
    }
}
