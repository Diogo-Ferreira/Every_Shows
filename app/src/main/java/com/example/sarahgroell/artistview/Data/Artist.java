package com.example.sarahgroell.artistview.Data;

import java.util.ArrayList;
import java.util.List;

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


    public String toString(){
        return name;


    public static List<Artist> fake (){
        List<Artist> fakeArtists = new ArrayList<Artist>();
        fakeArtists.add(new Artist("Green Day"));
        fakeArtists.add(new Artist("Adele"));
        fakeArtists.add(new Artist("System of a Down"));
        return fakeArtists;

    }
}
