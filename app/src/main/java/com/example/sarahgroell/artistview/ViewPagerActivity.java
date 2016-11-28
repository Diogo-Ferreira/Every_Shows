package com.example.sarahgroell.artistview;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sarahgroell.artistview.Adapter.SectionsPagerAdapter;
import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.MusicProvider.LocalMusicProvider;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;


public class ViewPagerActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;


    private static boolean frescoInitialized = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(!frescoInitialized) {
            Fresco.initialize(this);
            frescoInitialized = true;
        }
        setContentView(R.layout.view_pager_layout);


        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());


        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        try {
            LocalMusicProvider localMusicProvider = new LocalMusicProvider(this);
            ArrayList<Artist> localArtists = localMusicProvider.getArtist();
            Log.d("ARTISTS FOUNDED : ",localArtists.toString());
        }catch (SecurityException e){
            Log.d("Exception : ",e.getMessage());
        }


    }

}
