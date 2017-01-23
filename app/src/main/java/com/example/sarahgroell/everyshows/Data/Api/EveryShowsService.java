package com.example.sarahgroell.everyshows.Data.Api;

import com.example.sarahgroell.everyshows.Data.Artist;
import com.example.sarahgroell.everyshows.Data.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by diogo on 11/23/16.
 *
 * Les fonctions correspondent aux routes du webservice.
 * Voir la doc de retrofit
 */

public interface EveryShowsService {

    /**
     * Récupération des concerts d'un artiste
     * @param artist
     * @param lat
     * @param longitude
     * @return callable qui retourne une list de shows
     */
    @GET("shows/{artist}/{lat}/{long}")
    Call<List<Show>> listShows(@Path("artist") String artist,@Path("lat") String lat, @Path("long") String longitude);

    /**
     * Récupération des informations d'un artiste
     * @param artist
     * @return callable qui retourne un artiste
     */
    @GET("artist/{artist}")
    Call<Artist> getArtistInfo(@Path("artist") String artist);
}
