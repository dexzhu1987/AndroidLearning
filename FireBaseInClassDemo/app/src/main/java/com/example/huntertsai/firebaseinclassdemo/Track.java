package com.example.huntertsai.firebaseinclassdemo;

/**
 * Created by dexunzhu on 2018-02-27.
 */

public class Track {
    private String title;
    private String rating;
    private String trackId;

    public Track() {
    }

    public Track(String title, String rating, String trackId) {
        this.title = title;
        this.rating = rating;
        this.trackId = trackId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }
}
