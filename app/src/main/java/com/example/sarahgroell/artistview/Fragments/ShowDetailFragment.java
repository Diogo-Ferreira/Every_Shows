package com.example.sarahgroell.artistview.Fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.sarahgroell.artistview.Data.Show;
import com.example.sarahgroell.artistview.R;
import com.facebook.drawee.view.SimpleDraweeView;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * Created by aurelie.debrot on 23.11.2016.
 */

public class ShowDetailFragment extends Fragment implements OnMapReadyCallback{

    public Show show; //donner les infos
    public TextView artistName;
    public TextView location;
    public TextView date;
    public TextView place;
    public SimpleDraweeView imageArtist;
    public SimpleDraweeView imageLocation;
    public SupportMapFragment mapFragment;

    private FragmentActivity myContext;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_show_page,container,false);

        artistName = (TextView)view.findViewById(R.id.artistName);
        location = (TextView)view.findViewById(R.id.location);
        date = (TextView)view.findViewById(R.id.date);
        place = (TextView)view.findViewById(R.id.place);
        imageArtist = (SimpleDraweeView)view.findViewById(R.id.artist);
        imageLocation = (SimpleDraweeView)view.findViewById(R.id.imageHall);

        FragmentManager fragmentManager = myContext.getSupportFragmentManager();
        mapFragment = (SupportMapFragment)fragmentManager.findFragmentById(R.id.map);

        Bundle bundle = this.getArguments();
        if(bundle != null){
            show = bundle.getParcelable("show");
            System.out.println(show.toString());
            artistName.setText(show.artist.name);
            location.setText(show.place);
            date.setText(show.date);
            place.setText(show.place);
            imageArtist.setImageURI(show.artist.imageCover);
            imageLocation.setImageURI(show.imageCover);
        }
        //Cette ligne cause une FATAL EXCEPTION. Apparemment la référence est nulle.
        mapFragment.getMapAsync(this);

        return view;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions().position(new LatLng(0,0)).title("Marker"));
    }

    /*@Override
    public void onAttach(Activity activity) {
        myContext=(FragmentActivity) activity;
        super.onAttach(activity);
    }*/

    @Override
    public void onAttach(Context context) {
        myContext = (FragmentActivity)context;
        super.onAttach(context);
    }
    //@Override
   /* public void onCreate(@Nullable Bundle savedInstanceState) {
        Bundle bundle = this.getArguments();
        if(bundle != null){
            show = bundle.getParcelable("show");
            System.out.println(show.toString());
            artistName.setText(show.artist.name);
            location.setText(show.place);
            date.setText(show.date);
        }

        super.onCreate(savedInstanceState);
    }*/
}
