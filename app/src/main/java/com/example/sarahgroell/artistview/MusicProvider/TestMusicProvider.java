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
        artists.add(new Artist("Lindsey Stirling"));
        artists.add(new Artist("Adele"));
        artists.add(new Artist("Jain"));
        artists.add(new Artist("Green Day"));
        artists.add(new Artist("System of a down"));
        artists.add(new Artist("In this moment"));
        artists.add(new Artist("Eminem"));
        artists.add(new Artist("Muse"));
        artists.add(new Artist("Bastille"));

        return artists;
    }
}
