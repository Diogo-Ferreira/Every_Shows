package com.example.sarahgroell.artistview.Fragments;

import android.annotation.TargetApi;
import android.content.res.Configuration;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.util.SortedListAdapterCallback;
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
import com.example.sarahgroell.artistview.MusicProvider.ArtistsManager;
import com.example.sarahgroell.artistview.R;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
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

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onStart() {
        super.onStart();


        artistsManager.load(this.getContext());

        if(showData.size() == 0) {
            if(avi != null) avi.show();
            loadData();
        }



        Log.d("Show","OnStart");
        showAdapter.setListener(new IShowListener() {
            @Override
            public void onClickShow(Show show) {
                Log.d("from anonyme",show.toString());
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

    private void loadData(){

        loadingData = true;
        List<Artist> artists = artistsManager.fetch();

        if(artists == null) return;

        for(int i = 0; i< artists.size(); i++){
            restClient.getShows(artists.subList(i,i+1), new RestClient.OnResponce() {
                @TargetApi(Build.VERSION_CODES.N)
                @Override
                public <T> void OnSuccess(T response) {
                    showData.addAll((List<Show>) response);
                    Collections.sort(showData);
                    showAdapter.notifyDataSetChanged();
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
}
