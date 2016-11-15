package com.example.sarahgroell.artistview.Data;

import java.util.Date;

/**
 * Created by aurelie.debrot on 08.11.2016.
 */

public class Show {
    public Artist artist;
    public String place;
    public String date;
    public String imageCover;

    public Show(Artist a, String p, String d, String i){
        this.artist = a;
        this.place = p;
        this.date = d;
        this.imageCover = i;

    }
    public Show(String p, String image){
        this.place = p;
        this.imageCover = image;
    }
    public Show(String p, String i,String d ){
        this.place = p;
        this.imageCover = i;
        this.date = d;
    }
}
