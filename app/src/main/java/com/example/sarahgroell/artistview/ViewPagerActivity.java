package com.example.sarahgroell.artistview;

import android.os.Build;
import android.os.Bundle;
<<<<<<< HEAD
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerTabStrip;
=======
>>>>>>> ce82ac6087d0bad971d55f9264a5ffb1bbe86a4a
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.sarahgroell.artistview.Adapter.SectionsPagerAdapter;
<<<<<<< HEAD
import com.example.sarahgroell.artistview.Data.Artist;
import com.example.sarahgroell.artistview.MusicProvider.LocalMusicProvider;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
=======
import com.example.sarahgroell.artistview.Data.Api.EveryShowsService;
import com.example.sarahgroell.artistview.Data.Show;
import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
>>>>>>> ce82ac6087d0bad971d55f9264a5ffb1bbe86a4a


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

    }


}
