package com.example.sarahgroell.artistview.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by sarah.groell on 29.11.2016.
 */

public class ArtistDetailFragment extends Fragment {
    private Artist artist;
    SimpleDraweeView image;
    TextView name;


    @Override
    public void onStart() {
        super.onStart();
        artist = new Artist("Diogo Ferreira","http://diogoferreira.ch/new/res/me.png");
        image.setImageURI(artist.imageCover);
        name.setText(artist.name);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_artist_page,container,false);
        image = (SimpleDraweeView)view.findViewById(R.id.artistImage);
        name = (TextView) view.findViewById(R.id.artistName);

        return view;
    }
}
