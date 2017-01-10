package com.example.sarahgroell.artistview.Data.Api;

import android.util.Log;

import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;

import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by diogo on 11/26/16.
 */

public class RetrofitClient implements RestClient{

    private static RetrofitClient instance = new RetrofitClient();

    private EveryShowsService service;


    private RetrofitClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://46.101.161.176:4567")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

         service = retrofit.create(EveryShowsService.class);
    }



    public static RetrofitClient getService(){

        return instance;
    }

    @Override
    public void getShows(List<Artist> artists, final OnResponce finalResponse) {

        String artistsGlued = "";

        //Api can batch multiple users, sperated by comas
        for(Artist a : artists)
            artistsGlued += a.name+",";


        Call<List<Show>> call = service.listShows(artistsGlued);

        call.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                finalResponse.OnSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Show>> call, Throwable t) {
                finalResponse.OnFailure(t);
            }
        });
    }

    @Override
    public void getArtistInfo(Artist artist, final OnResponce finalResponse) {
        Call<Artist> call = service.getArtistInfo(artist.name);

        call.enqueue(new Callback<Artist>() {
            @Override
            public void onResponse(Call<Artist> call, Response<Artist> response) {
                finalResponse.OnSuccess(response.body());
            }

            @Override
            public void onFailure(Call<Artist> call, Throwable t) {
                finalResponse.OnFailure(t);
            }
        });
    }


    @Override
    public void getShow(Show show, OnResponce responce) {

    }
}
