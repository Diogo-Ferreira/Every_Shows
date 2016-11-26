package com.example.sarahgroell.artistview.Data.Api;

import com.example.sarahgroell.artistview.Data.Show;

import java.util.List;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by diogo on 11/23/16.
 */

public interface EveryShowsService {
    @GET("shows/{artists}")
    Call<List<Show>> listShows(@Path("artists") String artists);
}
