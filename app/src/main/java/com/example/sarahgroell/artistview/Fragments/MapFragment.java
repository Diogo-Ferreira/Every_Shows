package com.example.sarahgroell.artistview.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarahgroell.artistview.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
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
        mMap = googleMap;
        LatLng zurich = new LatLng(47.38,8.52);
        Marker marker = mMap.addMarker(new MarkerOptions().position(zurich).title("Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(zurich));
        marker.setVisible(true);
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
        MapsInitializer.initialize(myContext);

        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity)context;
        super.onAttach(context);
    }
}
