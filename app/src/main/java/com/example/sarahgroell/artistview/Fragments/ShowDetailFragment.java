package com.example.sarahgroell.artistview.Fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.sarahgroell.artistview.R;

/**
 * Created by aurelie.debrot on 23.11.2016.
 */

public class ShowDetailFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_show_page,container,false);
        return view;
    }
}
