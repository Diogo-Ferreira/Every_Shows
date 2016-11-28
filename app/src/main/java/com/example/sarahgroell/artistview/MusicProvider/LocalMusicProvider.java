package com.example.sarahgroell.artistview.MusicProvider;

import android.Manifest;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;

import com.example.sarahgroell.artistview.Data.Artist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sarah.groell on 23.11.2016.
 */

public class LocalMusicProvider implements ArtistProvider {
    public Activity activity;

    @RequiresApi(api = Build.VERSION_CODES.M)
    public LocalMusicProvider(Activity activity){
        this.activity = activity;
        int permissionCheck = activity.checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            throw new SecurityException("Permission not granted");
        }
    }

    @Override
    public ArrayList<Artist> getArtist() {
        ArrayList<Artist> artists = new ArrayList<Artist>();

        ContentResolver musicResolver = activity.getContentResolver();
        Uri sMusicUri = MediaStore.Audio.Artists.EXTERNAL_CONTENT_URI;
        Cursor music = musicResolver.query(sMusicUri,null,null,null,null);

        while(music.moveToNext()){
            int x = music.getColumnIndex(MediaStore.Audio.Artists.ARTIST);
            String artistName = music.getString(x);
            artists.add(new Artist(artistName));
        }
        return artists;
    }
}
