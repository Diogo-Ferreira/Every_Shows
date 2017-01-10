package com.example.sarahgroell.artistview.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarah.groell on 01.11.2016.
 */

public class Artist implements Parcelable{
    public String name;
    public ArrayList<Artist> similarArtists;
    public ArrayList<Show> nextShows;
    public String infos;
    public String imageCover;

    public Artist(String name, String imageCover) {
        this.name = name;
        this.imageCover = imageCover;
    }

    public Artist(String name, String imageCover, String infos) {
        this.name = name;
        this.imageCover = imageCover;
        this.infos = infos;
    }

    public void setInfos(String infos){
        this.infos = infos;
    }

    public Artist(String name) {
        this.name = name;
    }

    public String toString() {
        return name + " // " + infos;
    }

    public static List<Artist> fake() {
        List<Artist> fakeArtists = new ArrayList<Artist>();
        fakeArtists.add(new Artist("Green Day"));
        fakeArtists.add(new Artist("Adele"));
        fakeArtists.add(new Artist("System of a Down"));
        fakeArtists.add(new Artist("Jain"));
        fakeArtists.add(new Artist("Avril Lavigne"));
        return fakeArtists;
    }
    public static List<Show> fakeShows(){
        List<Show> fakeShows = new ArrayList<Show>();
        fakeShows.add(new Show("Delémont","drawable/hallenstadion.jpg","09.07.1994"));
        fakeShows.add(new Show("Neuchâtel","drawable/hallenstadion.jpg","10.07.1994"));
        fakeShows.add(new Show("Lausanne","drawable/hallenstadion.jpg","11.07.1994"));
        fakeShows.add(new Show("Genève","drawable/hallenstadion.jpg","12.07.1994"));
        fakeShows.add(new Show("Ouagadougou","drawable/hallenstadion.jpg","13.07.1994"));

        return fakeShows;
    }
    public void loadFakeSimilarArtist(){
        similarArtists = new ArrayList<Artist>();
        similarArtists.addAll(fake());

    }

    public void loadFakeNextShows(){
        nextShows = new ArrayList<Show>();
        nextShows.addAll(fakeShows());
    }

    protected Artist(Parcel in) {
        name = in.readString();
        imageCover = in.readString();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(imageCover);
    }

    public static final Creator<Artist> CREATOR = new Creator<Artist>() {
        @Override
        public Artist createFromParcel(Parcel in) {
            return new Artist(in);
        }

        @Override
        public Artist[] newArray(int size) {
            return new Artist[size];
        }
    };
}
