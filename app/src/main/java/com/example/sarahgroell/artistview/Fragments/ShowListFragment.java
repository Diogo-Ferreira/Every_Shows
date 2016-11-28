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

import com.example.sarahgroell.artistview.Adapter.RecyclerViewShowAdapter;
import com.example.sarahgroell.artistview.Data.Api.RestClient;
import com.example.sarahgroell.artistview.Data.Api.RetrofitClient;
import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.Listener.IShowListener;
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
    private boolean loadingData = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_show_list,container,false);
        recyclerView = (RecyclerView) v.findViewById(R.id.RecyclerView);

        showAdapter = new RecyclerViewShowAdapter(showData);

        recyclerView.setAdapter(showAdapter);

        final RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(
                this.getContext(),
                this.getContext().
                        getResources().
                        getConfiguration().
                        orientation != Configuration.ORIENTATION_LANDSCAPE ? 1 : 2
        );

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

        if(showData.size() == 0)
            loadData();



        Log.d("Show","OnStart");
        showAdapter.setListener(new IShowListener() {
            @Override
            public void onClickShow(String name) {
                Log.d("from anonyme",name);
                //On devrait lancer le fragment ici.
            }
        });
    }

    private void loadData(){

        loadingData = true;
        ArrayList<Artist> artists = (ArrayList<Artist>) Artist.fake();

        restClient.getShows(artists.subList(0,1), new RestClient.OnResponce() {
            @Override
            public <T> void OnSuccess(T response) {
                showData.addAll((List<Show>) response);
                showAdapter.notifyDataSetChanged();
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
