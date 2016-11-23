package com.example.sarahgroell.artistview;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.example.sarahgroell.artistview.Adapter.RecyclerViewShowAdapter;
import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.Fragments.ShowDetailFragment;
import com.example.sarahgroell.artistview.Listener.IShowListener;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class ShowListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_show_list);
        final ArrayList<Show> shows = new ArrayList<>();
        shows.add(new Show("Hallenstadion", "http://www.hallenstadion.ch/media/uploads/headers/432343/images/header-frontseite-club-konzert-29.01.15.jpg?w=1650&c=1650x550"));
        shows.add(new Show("Stade de Suisse","https://i.ytimg.com/vi/gRkyk9iyjmM/maxresdefault.jpg"));
        shows.add(new Show("Greenfield Festival","http://musictour.eu/data//uploads/media/halls/2342/0e077adc167f8ebfd7cda248e2ab8f2c.jpg"));
        shows.add(new Show("Wacken Open Air","https://www.fest300.com/system/images/W1siZiIsIjIwMTQvMDQvMjAvMTYvNDEvMzgvNDEyL1dhY2tlbl9PcGVuX0Fpcl9XYWNrZW5fT3Blbl9BaXJfMjguanBnIl1d/Wacken_Open_Air_Wacken_Open_Air%20-%2028.jpg"));

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        final RecyclerViewShowAdapter showAdapter = new RecyclerViewShowAdapter(shows);

        recyclerView.setAdapter(showAdapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1));
        showAdapter.notifyDataSetChanged();

    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        recyclerView.setLayoutManager(new GridLayoutManager(this, newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1));
        super.onConfigurationChanged(newConfig);
    }

}
