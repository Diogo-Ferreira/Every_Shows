package com.example.sarahgroell.artistview.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.sarahgroell.artistview.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by aurelie.debrot on 20.12.2016.
 */

public class MapFragment extends Fragment implements OnMapReadyCallback{

    public SupportMapFragment mapFragment;
    private FragmentActivity myContext;
    private GoogleMap mMap;

    @Override
    public void onMapReady(GoogleMap googleMap) {
        Log.d("onMapReady", "onMapReady: ");
        mMap = googleMap;
        LatLng zurich = new LatLng(21,57);
        mMap.addMarker(new MarkerOptions().position(zurich).title("Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zurich));
        //marker.setVisible(true);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentManager fragmentManager = myContext.getSupportFragmentManager();
        mapFragment = (SupportMapFragment)fragmentManager.findFragmentById(R.id.map);
        ViewGroup.LayoutParams params = mapFragment.getView().getLayoutParams();
        params.height = 200;
        mapFragment.getView().setLayoutParams(params);
        mapFragment.getMapAsync(this);
        Log.d("getMapAsync", "onCreateView: ");
        MapsInitializer.initialize(myContext);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity)context;
        super.onAttach(context);
    }
}
