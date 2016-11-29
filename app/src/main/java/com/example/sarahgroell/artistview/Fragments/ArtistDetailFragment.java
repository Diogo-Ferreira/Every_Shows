package com.example.sarahgroell.artistview.Fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarahgroell.artistview.R;

/**
 * Created by sarah.groell on 29.11.2016.
 */

public class ArtistDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_artist_page,container,false);
        return view;
    }
}
