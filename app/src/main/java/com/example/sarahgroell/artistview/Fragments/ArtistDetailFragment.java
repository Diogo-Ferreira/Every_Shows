package com.example.sarahgroell.artistview.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarahgroell.artistview.Adapter.SimilarArtistAdapter;
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
    TextView biographie;
    RecyclerView similarRecyclerView;
    SimilarArtistAdapter similarArtistAdapter;


    @Override
    public void onStart() {
        super.onStart();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_artist_page,container,false);
        image = (SimpleDraweeView)view.findViewById(R.id.artistImage);
        name = (TextView) view.findViewById(R.id.artistName);
        biographie = (TextView) view.findViewById(R.id.biographieArtist);


        Bundle bundle = this.getArguments();
        if(bundle != null){
            artist = bundle.getParcelable("artist");
            artist.loadFakeSimilarArtist();
            artist.setInfos("Voici les diverses informations liées à l'artiste. Ce texte est un texte par défaut.");

            similarArtistAdapter = new SimilarArtistAdapter(artist.similarArtists);
            similarRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerArtists);
            similarRecyclerView.setLayoutManager(new LinearLayoutManager(this.getContext(),LinearLayoutManager.HORIZONTAL,false));
            similarRecyclerView.setAdapter(similarArtistAdapter);

            System.out.println(artist.toString());

            name.setText(artist.name);
            image.setImageURI(artist.imageCover);
            biographie.setText(artist.infos);
        }

        return view;
    }
}
