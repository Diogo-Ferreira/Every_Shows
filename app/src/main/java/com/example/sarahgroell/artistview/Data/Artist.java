package com.example.sarahgroell.artistview.Data;

/**
 * Created by sarah.groell on 01.11.2016.
 */

public class Artist {
    public String name;

    public Artist(String name, String imageCover) {
        this.name = name;
        this.imageCover = imageCover;
    }

    public String imageCover;

    public Artist(String name) {
        this.name = name;
    }
}
