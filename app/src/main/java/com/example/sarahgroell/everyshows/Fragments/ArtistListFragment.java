package com.example.sarahgroell.everyshows.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sarahgroell.everyshows.Adapter.ArtistListAdapter;
import com.example.sarahgroell.everyshows.Data.Api.RestClient;
import com.example.sarahgroell.everyshows.Data.Api.RetrofitClient;
import com.example.sarahgroell.everyshows.Data.Artist;
import com.example.sarahgroell.everyshows.Listener.IArtistListener;
import com.example.sarahgroell.everyshows.MusicProvider.ArtistsManager;
import com.example.sarahgroell.everyshows.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogo on 11/8/16.
 */

public class ArtistListFragment extends Fragment {

    private RecyclerView recyclerView;
    ArtistListAdapter adapter;
    ArrayList<Artist> artistData = new ArrayList<>();
    private boolean loadingData = false;
    RestClient restClient = RetrofitClient.getService();
    ArtistsManager artistsManager = ArtistsManager.getInstance();
    AVLoadingIndicatorView avi;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.artist_show_list, container,false);
        avi = (AVLoadingIndicatorView) v.findViewById(R.id.avi);
        adapter = new ArtistListAdapter(artistData);
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

                //Est-ce qu'on a atteint la fin ?
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

        if(artistData.size() == 0) {
            if(avi != null) avi.show();
            loadData();
        }


      adapter.setListener(new IArtistListener() {
          @Override
          public void onClickArtist(Artist artist) {
              ArtistDetailFragment fragment = new ArtistDetailFragment();
              Bundle bundle = new Bundle();
              bundle.putParcelable("artist",artist);
              fragment.setArguments(bundle);
              FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
              transaction.add(R.id.frameLayout,fragment).addToBackStack(null).setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
              transaction.commit();
          }


      });

    }

    private void loadData(){

        loadingData = true;
        List<Artist> artists = artistsManager.getAll();

        if(artists == null) return;

        for(Artist artist : artists){
            restClient.getArtistInfo(artist, new RestClient.OnResponse() {
                @Override
                public <T> void OnSuccess(T response) {

                    Artist artist = (Artist) response;

                    // Ne pas ajouter des artistes déjà présents
                    if(!artistData.contains(artist))
                        artistData.add((Artist) response);

                    adapter.notifyDataSetChanged();
                    if(avi != null) avi.hide();
                    recyclerView.setVisibility(View.VISIBLE);
                    loadingData = false;
                }

                @Override
                public <T> void OnFailure(T failure) {
                    Log.d("Retrofit", failure.toString().toString());
                    Toast.makeText(getContext(),"Gosh ! Something went wrong ! Check Console",Toast.LENGTH_LONG).show();
                    loadingData = false;
                }
            });
        }


    }

    public static Fragment newInstance() {
        return new ArtistListFragment();
    }
}
