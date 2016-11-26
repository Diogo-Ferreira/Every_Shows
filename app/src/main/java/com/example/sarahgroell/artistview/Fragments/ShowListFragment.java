package com.example.sarahgroell.artistview.Fragments;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sarahgroell.artistview.Adapter.RecyclerViewShowAdapter;
import com.example.sarahgroell.artistview.Data.Api.RestClient;
import com.example.sarahgroell.artistview.Data.Api.RetrofitClient;
import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by diogo on 11/8/16.
 */

public class ShowListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewShowAdapter showAdapter;
    ArrayList<Show> showData = new ArrayList<>();
    RestClient restClient = RetrofitClient.getService();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_show_list,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView);

        showAdapter = new RecyclerViewShowAdapter(showData);
        recyclerView.setAdapter(showAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), this.getContext().getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE ? 1 : 2));

        Log.d("Show","OnCreateView");
        return v;
    }

    public static ShowListFragment newInstance() {

        ShowListFragment fragment = new ShowListFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        loadData();


        Log.d("Show","OnStart");
    }

    private void loadData(){
        ArrayList<Artist> artists = (ArrayList<Artist>) Artist.fake();

        restClient.getShows(artists, new RestClient.OnResponce() {
            @Override
            public <T> void OnSuccess(T response) {
                showData.addAll((List<? extends Show>) response);
                showAdapter.notifyDataSetChanged();
            }

            @Override
            public <T> void OnFailure(T failure) {
                Log.d("Retrofit", failure.toString().toString());
                Toast.makeText(getContext(),"Gosh ! Something went wrong ! Check Console",Toast.LENGTH_LONG);
            }
        });
    }
}
