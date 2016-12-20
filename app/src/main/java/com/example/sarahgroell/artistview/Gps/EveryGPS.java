package com.example.sarahgroell.artistview.Gps;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by diogo on 12/20/16.
 */

public class EveryGPS {

    private static EveryGPS instance;

    private LocationManager locationManager;

    private Context context;

    private EveryGPS(Context context){
        this.context = context;
        locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public Location getLocation(){
        int permissionCheck = context.checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION);

        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            throw new SecurityException("Permission not granted");
        }

        return locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

    }

    public static EveryGPS getInstance(Context context){

        if(instance == null)
            instance = new EveryGPS(context);

        return instance;
    }
}
