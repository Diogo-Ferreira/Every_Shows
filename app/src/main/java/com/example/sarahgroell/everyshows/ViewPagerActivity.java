package com.example.sarahgroell.everyshows;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.sarahgroell.everyshows.Adapter.SectionsPagerAdapter;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Activité principale de l'application,
 * chaque vue sera un fragment incorporé dans chaque activité.
 */
public class ViewPagerActivity extends AppCompatActivity {

    private SectionsPagerAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    int requestCode;

    private static boolean frescoInitialized = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!frescoInitialized) {
            Fresco.initialize(this);
            frescoInitialized = true;
        }


        setContentView(R.layout.view_pager_layout);

        // Permissions pour le GPS, et la lecture des artistes présents dans le mobile
        ActivityCompat.requestPermissions(
                this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_COARSE_LOCATION,Manifest.permission.ACCESS_FINE_LOCATION}, requestCode);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(this.requestCode == requestCode){
            if ((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                init();
            }else if((grantResults.length > 0) && (grantResults[0] == PackageManager.PERMISSION_DENIED)){
                Toast.makeText(this,"We need that permission to access your artists", Toast.LENGTH_LONG).show();
            }
        }
    }

}
