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

import com.example.sarahgroell.artistview.Adapter.RecyclerViewShowAdapter;
import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

/**
 * Created by diogo on 11/8/16.
 */

public class ShowListFragment extends Fragment {

    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_show_list,container,false);

        return v;
    }

    public static ShowListFragment newInstance() {

        ShowListFragment fragment = new ShowListFragment();
        return fragment;
    }

    @Override
    public void onStart() {
        super.onStart();

        Fresco.initialize(this.getContext());
        final ArrayList<Show> shows = new ArrayList<>();
        shows.add(new Show("Hallenstadion", "http://www.hallenstadion.ch/media/uploads/headers/432343/images/header-frontseite-club-konzert-29.01.15.jpg?w=1650&c=1650x550"));
        shows.add(new Show("Stade de Suisse","https://i.ytimg.com/vi/gRkyk9iyjmM/maxresdefault.jpg"));
        shows.add(new Show("Greenfield Festival","http://musictour.eu/data//uploads/media/halls/2342/0e077adc167f8ebfd7cda248e2ab8f2c.jpg"));
        shows.add(new Show("Wacken Open Air","https://www.fest300.com/system/images/W1siZiIsIjIwMTQvMDQvMjAvMTYvNDEvMzgvNDEyL1dhY2tlbl9PcGVuX0Fpcl9XYWNrZW5fT3Blbl9BaXJfMjguanBnIl1d/Wacken_Open_Air_Wacken_Open_Air%20-%2028.jpg"));

        recyclerView = (RecyclerView) getView().findViewById(R.id.RecyclerView);
        final RecyclerViewShowAdapter showAdapter = new RecyclerViewShowAdapter(shows);

        recyclerView.setAdapter(showAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this.getContext(), this.getContext().getResources().getConfiguration().orientation != Configuration.ORIENTATION_LANDSCAPE ? 1 : 2));
        showAdapter.notifyDataSetChanged();
    }
}
