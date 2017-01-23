package com.example.sarahgroell.everyshows.MusicProvider;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.example.sarahgroell.everyshows.Data.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogo on 11/28/16.
 * Gère les différents sources d'artistes (Locale, Tests, Spotify, Youtube... (à l'avenir))
 */

public class ArtistsManager {
    ArrayList<ArtistProvider> providers = new ArrayList<>();
    ArrayList<Artist> artists = new ArrayList<>();
    private int selector = 0;
    private int chunkSize = 3;

    private static ArtistsManager instance = new ArtistsManager();

    public static ArtistsManager getInstance(){
        return instance;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void load(Context context){
        providers.clear();
        artists.clear();
        providers.add(new TestMusicProvider());
        providers.add(new LocalMusicProvider((Activity) context));
        for(ArtistProvider artistProvider : providers){
            artists.addAll(artistProvider.getArtist());
        }
    }

    public void reset(){
        selector = 0;
    }

    public List<Artist> getAll(){
        return  artists;
    }

    /**
     * Fetch un certains nombre d'artistes
     * @return
     */
    public List<Artist> fetch(){

        List<Artist> out;
        if(selector + chunkSize < artists.size()){
            out = artists.subList(selector,selector+chunkSize);
        }else if (selector < artists.size()){
            out = artists.subList(selector,artists.size()-1);
        }else{
            return null;
        }


        selector += chunkSize;
        return out;
    }
}
