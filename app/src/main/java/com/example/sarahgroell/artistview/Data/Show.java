package com.example.sarahgroell.artistview.Data;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by aurelie.debrot on 08.11.2016.
 */

public class Show implements Comparable<Show>{

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


    @Override
    public String toString() {
        return place + " " + date + " " + artist.name;
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public int compareTo(Show o) {
        SimpleDateFormat format = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date date1 = format.parse(o.date);
            Date date2 = format.parse(this.date);
            return date2.compareTo(date1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
