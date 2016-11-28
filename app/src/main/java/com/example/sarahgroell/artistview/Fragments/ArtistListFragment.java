package com.example.sarahgroell.artistview.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sarahgroell.artistview.Adapter.RecyclerViewAdapter;
import com.example.sarahgroell.artistview.Data.Api.RestClient;
import com.example.sarahgroell.artistview.Data.Api.RetrofitClient;
import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.MusicProvider.ArtistsManager;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogo on 11/8/16.
 */

public class ArtistListFragment extends Fragment {

    private RecyclerView recyclerView;
    RecyclerViewAdapter adapter;
    ArrayList<Artist> artistData = new ArrayList<>();
    private boolean loadingData = false;
    RestClient restClient = RetrofitClient.getService();
    ArtistsManager artistsManager = ArtistsManager.getInstance();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.artist_show_list, container,false);

        adapter = new RecyclerViewAdapter(artistData);
        recyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView);
        recyclerView.setAdapter(adapter);
        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(this.getContext(), this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1);
        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(loadingData)
                    return;

                int visibleItemCount = mLayoutManager.getChildCount();
                int totalItemCount = mLayoutManager.getItemCount();
                int pastVisibleItems = ((LinearLayoutManager)mLayoutManager).findFirstVisibleItemPosition();


                //Did we reached the end ?

                if (pastVisibleItems + visibleItemCount >= totalItemCount) {
                    loadData();
                }
            }
        });

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        if(artistData.size() == 0)
            loadData();

    }

    private void loadData(){

        loadingData = true;
        List<Artist> artists = artistsManager.getAll();

        if(artists == null) return;

        for(Artist artist : artists){
            restClient.getArtistInfo(artist, new RestClient.OnResponce() {
                @Override
                public <T> void OnSuccess(T response) {
                    artistData.add((Artist) response);
                    adapter.notifyDataSetChanged();
                    loadingData = false;
                }

                @Override
                public <T> void OnFailure(T failure) {
                    Log.d("Retrofit", failure.toString().toString());
                    Toast.makeText(getContext(),"Gosh ! Something went wrong ! Check Console",Toast.LENGTH_LONG);
                    loadingData = false;
                }
            });
        }


    }

    public static Fragment newInstance() {
        return new ArtistListFragment();
    }
}
