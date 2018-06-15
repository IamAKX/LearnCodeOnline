package com.akash.applications.learncodeonline.Model;

/**
 * Created by Akash on 14/6/18.
 */

public class ThumbnailModel {
    String name;
    int imageID;

    public ThumbnailModel(String name, int imageID) {
        this.name = name;
        this.imageID = imageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }
}
