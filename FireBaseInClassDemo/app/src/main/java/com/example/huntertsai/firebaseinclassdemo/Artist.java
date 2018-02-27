package com.example.huntertsai.firebaseinclassdemo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by huntertsai on 2018-02-26.
 */

public class Artist {
    private String id, name, genre;

    public Artist(){

    }


    public Artist(String id, String name, String genre) {
        this.id = id;
        this.name = name;
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
