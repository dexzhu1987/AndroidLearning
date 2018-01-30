package com.bignerdranch.android.movieview;

import java.util.UUID;

/**
 * Created by dexunzhu on 2018-01-29.
 */

public class Movie {
    private UUID mUUID;
    private int image;
    private String description;

    public Movie(){
        mUUID = UUID.randomUUID();
    }

    public UUID getUUID() {
        return mUUID;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return image + ": " + description;
    }
}
