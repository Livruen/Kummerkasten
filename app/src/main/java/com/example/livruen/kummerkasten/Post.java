package com.example.livruen.kummerkasten;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by livruen on 30.11.16.
 */
public class Post {

    private final String ID = "id";
    private final String TITLE = "title";
    private final String CONTENT = "content";
    private final String RENDERED = "rendered";

    private String id;
    private String title;
    private String content;


    public Post(JSONObject object) {

        try {
            this.id = object.getString(ID);
            this.title = object.getJSONObject(TITLE).getString(RENDERED);
            this.content = object.getJSONObject(CONTENT).getString(RENDERED);

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

    public String getContent() {
        return content;
    }
}
