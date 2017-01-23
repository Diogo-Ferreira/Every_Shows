package com.example.sarahgroell.everyshows.Data;

import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.RequiresApi;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by aurelie.debrot on 08.11.2016.
 */

public class Show implements Parcelable, Comparable<Show>{

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
    //Constructeur avec un Parcel
    public Show(Parcel in){
        this.place = in.readString();
        this.date = in.readString();
        this.artist.name = in.readString();
        this.imageCover = in.readString();
        this.artist.imageCover = in.readString();
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
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(place);
        dest.writeString(date);
        dest.writeString(artist.name);
        dest.writeString(imageCover);
        dest.writeString(artist.imageCover);
    }

    public static final Parcelable.Creator<Show> CREATOR = new Parcelable.Creator<Show>(){
        @Override
        public Show createFromParcel(Parcel source) {
            return new Show(source);
        }

        @Override
        public Show[] newArray(int size) {
            return new Show[size];
        }
    };


    @Override
    public boolean equals(Object obj) {

        if(obj instanceof Show){
            return ((Show)obj).artist.name == this.artist.name && ((Show)obj).date == this.date;
        }

        return super.equals(obj);
    }
}

