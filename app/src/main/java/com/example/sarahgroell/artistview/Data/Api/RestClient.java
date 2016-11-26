package com.example.sarahgroell.artistview.Data.Api;

import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;

import java.util.List;

/**
 * This class make the rest client more generic (Means we could use something else than retrofit)
 */

public interface RestClient {

    void getShows(List<Artist> artists,OnResponce response);
    void getArtistsInfo(List<Artist> artists,OnResponce response);
    void getShow(Show show,OnResponce response);


    interface OnResponce{
        <T> void OnSuccess(T response);
        <T> void OnFailure(T failure);
    }
}



