package com.akash.applications.learncodeonline.Model;

/**
 * Created by Akash on 14/6/18.
 */

public class YouTubeThumbnailModel {
    String url,name;
    int imageId;

    public YouTubeThumbnailModel(String name, int imageId,String url) {
        this.url = url;
        this.name = name;
        this.imageId = imageId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }
}
