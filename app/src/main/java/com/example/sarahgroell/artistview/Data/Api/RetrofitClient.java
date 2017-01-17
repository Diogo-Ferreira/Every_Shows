package com.example.sarahgroell.artistview.Data.Api;

import android.location.Location;
import android.util.Log;

import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;

import java.util.ArrayList;
import java.util.Date;
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

    private List<Show> showsCache = new ArrayList<>();
    private Date showsCacheDate;


    private RetrofitClient(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.MINUTES)
                .readTimeout(10, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://157.26.83.72:4567")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

         service = retrofit.create(EveryShowsService.class);
    }



    public static RetrofitClient getService(){

        return instance;
    }

    @Override
    public void getShows(Artist artist, Location location, final OnResponce finalResponse) {


        /*if(showsCache.size() > 0 && showsCacheDate != null){
            long duration  = new Date().getTime() - showsCacheDate.getTime();
            long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);

            if(diffInMinutes <= 10){
                finalResponse.OnSuccess(showsCache);
                return;
            }
        }*/

        double latitude = 0;
        double longitude = 0;

        if(location != null){
            latitude = location.getLatitude();
            longitude = location.getLongitude();
        }

        Call<List<Show>> call = service.listShows(artist.name, String.valueOf(latitude),String.valueOf(longitude));

        call.enqueue(new Callback<List<Show>>() {
            @Override
            public void onResponse(Call<List<Show>> call, Response<List<Show>> response) {
                finalResponse.OnSuccess(response.body());
                showsCache = response.body();
                showsCacheDate = new Date();
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
