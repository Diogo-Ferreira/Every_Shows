package com.example.sarahgroell.artistview.Data.Api;

import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by diogo on 11/23/16.
 */

public interface EveryShowsService {
    @GET("shows/{artist}/{lat}/{long}")
    Call<List<Show>> listShows(@Path("artist") String artist,@Path("lat") String lat, @Path("long") String longitude);

    @GET("artist/{artist}")
    Call<Artist> getArtistInfo(@Path("artist") String artist);
}
