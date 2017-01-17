package com.example.sarahgroell.artistview.Fragments;

import android.annotation.TargetApi;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sarahgroell.artistview.Adapter.NextShowArtistAdapter;
import com.example.sarahgroell.artistview.Adapter.SimilarArtistAdapter;
import com.example.sarahgroell.artistview.Data.Api.RestClient;
import com.example.sarahgroell.artistview.Data.Api.RetrofitClient;
import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.Gps.EveryGPS;
import com.example.sarahgroell.artistview.Listener.IArtistListener;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by sarah.groell on 29.11.2016.
 */

public class ArtistDetailFragment extends Fragment {
    private Artist artist;
    SimpleDraweeView image;
    TextView name;
    TextView biographie;

    ArrayList<Show> showData = new ArrayList<>();

    RecyclerView similarRecyclerView;
    RecyclerView nextShowsRecyclerView;
    SimilarArtistAdapter similarArtistAdapter;
    NextShowArtistAdapter nextShowArtistAdapter;
    RestClient restClient = RetrofitClient.getService();

    EveryGPS everyGPS;

    AVLoadingIndicatorView avi;





    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();

        if(avi != null) avi.show();

        loadSimilarArtist();
        loadNextShows();


         similarArtistAdapter.setListener(new IArtistListener() {
          @Override
          public void onClickArtist(Artist artist) {
              Log.d("from anonyme",artist.toString());
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

    private void loadSimilarArtist(){
        //Récupération des infos de l'artiste
        restClient.getArtistInfo(artist,new RestClient.OnResponce(){
            @Override
            public <T> void OnSuccess(T response) {
                Artist a = (Artist) response;
                Log.d("vagin : ",a.toString());
                artist = a;

            }
            @Override
            public <T> void OnFailure(T failure) {

            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadNextShows(){

        Location location = everyGPS.getLocation();

        restClient.getShows(artist,location, new RestClient.OnResponce() {
            @TargetApi(Build.VERSION_CODES.N)
            @Override
            public <T> void OnSuccess(T response) {
                if(response != null){
                    for(Show show : (List<Show>)response){
                        if(!showData.contains(show)){
                            showData.add(show);
                        }
                    }
                    Collections.sort(showData);
                    nextShowArtistAdapter.notifyDataSetChanged();
                    if(avi != null) avi.hide();
                    nextShowsRecyclerView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public <T> void OnFailure(T failure) {
                Log.d("Retrofit", failure.toString().toString());
                Toast.makeText(getContext(),"Gosh ! Something went wrong ! Check Console",Toast.LENGTH_LONG).show();
            }
        });


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_artist_page,container,false);

        avi = (AVLoadingIndicatorView) view.findViewById(R.id.avi);

        image = (SimpleDraweeView)view.findViewById(R.id.artistImage);
        name = (TextView) view.findViewById(R.id.artistName);
        biographie = (TextView) view.findViewById(R.id.biographieArtist);


        everyGPS = EveryGPS.getInstance(this.getContext());


        Bundle bundle = this.getArguments();
        if(bundle != null){
            artist = bundle.getParcelable("artist");

            similarArtistAdapter = new SimilarArtistAdapter(artist.similarArtists);
            similarRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerArtists);
            similarRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));
            similarRecyclerView.setAdapter(similarArtistAdapter);

            nextShowArtistAdapter = new NextShowArtistAdapter(showData);
            nextShowsRecyclerView = (RecyclerView) view.findViewById(R.id.nextShows);
            nextShowsRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL, false));
            nextShowsRecyclerView.setAdapter(nextShowArtistAdapter);

            name.setText(artist.name);
            image.setImageURI(artist.imageCover);
            biographie.setText(artist.info);
        }

        return view;
    }
}
