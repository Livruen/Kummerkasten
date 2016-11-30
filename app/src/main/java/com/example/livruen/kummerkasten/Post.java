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
    private final String IMAGE = "featured_media";

    private String id;
    private String title;
    private String content;
    private String image;


    public Post(JSONObject object) {

        try {
            this.id = object.optString(ID);
            this.title = object.getJSONObject(TITLE).optString(RENDERED);
            this.content = object.getJSONObject(CONTENT).optString(RENDERED);
            this.image = object.optString(IMAGE);

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

    public String getImage() {
        return image;
    }
}
