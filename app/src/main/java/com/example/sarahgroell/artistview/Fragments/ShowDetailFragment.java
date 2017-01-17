package com.example.sarahgroell.artistview.Fragments;


import android.app.Activity;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aurelie.debrot on 23.11.2016.
 */

public class ShowDetailFragment extends Fragment implements OnMapReadyCallback{

    public Show show; //donner les infos
    public TextView artistName;
    //public TextView location;
    public TextView date;
    public TextView place;
    public SimpleDraweeView imageArtist;
    public SimpleDraweeView imageLocation;
    //Map
    public SupportMapFragment mapFragment;
    private FragmentActivity myContext;
    private GoogleMap mMap;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_show_page,container,false);

        artistName = (TextView)view.findViewById(R.id.artistName);
        //location = (TextView)view.findViewById(R.id.location);
        date = (TextView)view.findViewById(R.id.date);
        place = (TextView)view.findViewById(R.id.place);
        imageArtist = (SimpleDraweeView)view.findViewById(R.id.artist);
        imageLocation = (SimpleDraweeView)view.findViewById(R.id.imageHall);


        Bundle bundle = this.getArguments();
        if(bundle != null){
            show = bundle.getParcelable("show");
            System.out.println(show.toString());
            artistName.setText(show.artist.name);
            //location.setText(show.place);
            date.setText(show.date);
            place.setText(show.place);
            imageArtist.setImageURI(show.artist.imageCover);
            imageLocation.setImageURI(show.imageCover);
        }

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Log.d("getMapAsync", "onCreateView: ");
        MapsInitializer.initialize(getContext());


    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Geocoder geocoder = new Geocoder(getContext());
        List<Address> addresses = new ArrayList<>();
        try {
            addresses = geocoder.getFromLocationName(place.getText().toString(), 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(addresses.size() > 0) {
            double latitude= addresses.get(0).getLatitude();
            double longitude= addresses.get(0).getLongitude();
            LatLng city = new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(city).title("Marker").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(city,7));
        }

    }
}
