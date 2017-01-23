package com.example.sarahgroell.everyshows.Fragments;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
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

import com.example.sarahgroell.everyshows.Adapter.RecyclerViewShowAdapter;
import com.example.sarahgroell.everyshows.Data.Api.RestClient;
import com.example.sarahgroell.everyshows.Data.Api.RetrofitClient;
import com.example.sarahgroell.everyshows.Data.Artist;
import com.example.sarahgroell.everyshows.Data.Show;
import com.example.sarahgroell.everyshows.Gps.EveryGPS;
import com.example.sarahgroell.everyshows.Listener.IShowListener;
import com.example.sarahgroell.everyshows.MusicProvider.ArtistsManager;
import com.example.sarahgroell.everyshows.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by diogo on 11/8/16.
 */

public class ShowListFragment extends Fragment {

    RecyclerView recyclerView;
    RecyclerViewShowAdapter showAdapter;
    ArrayList<Show> showData = new ArrayList<>();
    RestClient restClient = RetrofitClient.getService();
    boolean loadingData = false;
    ArtistsManager artistsManager = ArtistsManager.getInstance();
    AVLoadingIndicatorView avi;
    EveryGPS everyGPS;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_show_list,container,false);

        avi = (AVLoadingIndicatorView) v.findViewById(R.id.avi);

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


        everyGPS = EveryGPS.getInstance(this.getContext());

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

                    // Pour ne pas gêner le thread UI
                    getActivity().runOnUiThread(new Runnable() {
                        @TargetApi(Build.VERSION_CODES.M)
                        @Override
                        public void run() {
                            try{
                                loadData();
                            }catch (Exception e){
                            }
                        }
                    });

                }
            }
        });

        return v;
    }

    public static ShowListFragment newInstance() {
        ShowListFragment fragment = new ShowListFragment();
        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();

        artistsManager.load(this.getContext());

        if(avi != null) avi.show();
        loadData();

        showAdapter.setListener(new IShowListener() {
            @Override
            public void onClickShow(Show show) {
                ShowDetailFragment fragment = new ShowDetailFragment();
                Bundle bundle = new Bundle();
                bundle.putParcelable("show",show);
                fragment.setArguments(bundle);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.frameLayout,fragment).addToBackStack(null);
                transaction.commit();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void loadData(){

        loadingData = true;
        List<Artist> artists = artistsManager.fetch();

        if(artists == null) return;

        Location location = everyGPS.getLocation();

        for(Artist artist : artists){
            restClient.getShows(artist,location, new RestClient.OnResponse() {
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
                        showAdapter.notifyDataSetChanged();
                        if(avi != null) avi.hide();
                        recyclerView.setVisibility(View.VISIBLE);
                    }

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
}
