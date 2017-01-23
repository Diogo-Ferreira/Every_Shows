package com.example.sarahgroell.everyshows.Data.Api;

import android.location.Location;

import com.example.sarahgroell.everyshows.Data.Artist;
import com.example.sarahgroell.everyshows.Data.Show;

/**
 * Cette classe permet de définir un client REST du point de vie du client mobile everyshows,
 * pour toutes les implémentations possibles (Ex. retrofit).
 */

public interface RestClient {

    //Méthodes que chaque client devrait implémenter pour consommer le service REST ES
    void getShows(Artist artist, Location location, OnResponse response);
    void getArtistInfo(Artist artist,OnResponse response);
    void getShow(Show show,OnResponse response);


    // Reponse du webservice
    interface OnResponse {
        <T> void OnSuccess(T response);
        <T> void OnFailure(T failure);
    }
}



