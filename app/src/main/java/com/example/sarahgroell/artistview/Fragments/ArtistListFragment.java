package com.example.sarahgroell.artistview.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarahgroell.artistview.Adapter.RecyclerViewAdapter;
import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

/**
 * Created by diogo on 11/8/16.
 */

public class ArtistListFragment extends Fragment {

    private RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<Artist> artistData = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.artist_show_list, container,false);

        adapter = new RecyclerViewAdapter(artistData);
        recyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1));

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        artistData.add(new Artist("Avril Lavigne","http://www.billboard.com/files/media/avril-lavigne-press-650b.jpg"));
        artistData.add(new Artist("In This Moment","http://img2-ak.lst.fm/i/u/arO/f0cbd8b54866452d921a88fe48ab5082"));
        artistData.add(new Artist("Lindsey Stirling","http://www.billboard.com/files/styles/article_main_image/public/media/lindsey-stirling-sundance-2015-billboard-650.jpg"));
        artistData.add(new Artist("Adele","http://paroles-et-traduction.com/wp-content/uploads/2016/02/adele1.jpg"));

    }

    public static Fragment newInstance() {
        return new ArtistListFragment();
    }
}
