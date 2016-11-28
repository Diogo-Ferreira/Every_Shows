package com.example.sarahgroell.artistview.MusicProvider;

import com.example.sarahgroell.artistview.Data.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarah.groell on 23.11.2016.
 */

public class TestMusicProvider implements ArtistProvider{
    @Override
    public ArrayList<Artist> getArtist() {
        ArrayList<Artist> artists = new ArrayList<Artist>();
        artists.add(new Artist("Lindsey Stirling","https://i.ytimg.com/vi/aE2GCa-_nyU/maxresdefault.jpg"));
        artists.add(new Artist("Adele","http://paroles-et-traduction.com/wp-content/uploads/2016/02/adele1.jpg"));
        artists.add(new Artist("Jain","http://grungecake.com/wp-content/uploads/2016/05/jain-grungecake-thumbnail.jpg"));

        return artists;
    }
}
