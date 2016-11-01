package com.example.sarahgroell.artistview;

import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.sarahgroell.artistview.Adapter.RecyclerViewAdapter;
import com.example.sarahgroell.artistview.Data.Artist;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_main);
        final ArrayList<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Avril Lavigne","http://www.billboard.com/files/media/avril-lavigne-press-650b.jpg"));
        artists.add(new Artist("In This Moment","http://img2-ak.lst.fm/i/u/arO/f0cbd8b54866452d921a88fe48ab5082"));
        artists.add(new Artist("Lindsey Stirling","https://i.ytimg.com/vi/aE2GCa-_nyU/maxresdefault.jpg"));
        artists.add(new Artist("Adele","http://paroles-et-traduction.com/wp-content/uploads/2016/02/adele1.jpg"));
        artists.add(new Artist("Jain","http://grungecake.com/wp-content/uploads/2016/05/jain-grungecake-thumbnail.jpg"));

        recyclerView = (RecyclerView) findViewById(R.id.RecyclerView);
        final RecyclerViewAdapter adapter = new RecyclerViewAdapter(artists);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(this, this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1));
        adapter.notifyDataSetChanged();
    }
    @Override
    public void onConfigurationChanged(Configuration newConfig)
    {
        recyclerView.setLayoutManager(new GridLayoutManager(this, newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE ? 2 : 1));
        super.onConfigurationChanged(newConfig);
    }


}
