package com.example.sarahgroell.artistview.Data;

import java.util.Date;

/**
 * Created by aurelie.debrot on 08.11.2016.
 */

public class Show {
    public Artist artist;
    public String place;
    public Date date;
    public String imageCover;

    public Show(Artist a, String p, Date d, String i){
        this.artist = a;
        this.place = p;
        this.date = d;
        this.imageCover = i;

    }
    public Show(String p, String image){
        this.place = p;
        this.imageCover = image;
    }
}
