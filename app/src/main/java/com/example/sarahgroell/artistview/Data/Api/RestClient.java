package com.example.sarahgroell.artistview.Data.Api;

import android.location.Location;

import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;

/**
 * This class make the rest client more generic (Means we could use something else than retrofit)
 */

public interface RestClient {

    void getShows(Artist artist, Location location, OnResponce response);
    void getArtistInfo(Artist artist,OnResponce response);
    void getShow(Show show,OnResponce response);


    interface OnResponce{
        <T> void OnSuccess(T response);
        <T> void OnFailure(T failure);
    }
}



