package com.example.sarahgroell.artistview.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.sarahgroell.artistview.Fragments.ArtistListFragment;
import com.example.sarahgroell.artistview.Fragments.PlaceholderFragment;
import com.example.sarahgroell.artistview.Fragments.ShowListFragment;


/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    public SectionsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ShowListFragment.newInstance();
            case 1:
                return ArtistListFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 3 total pages.
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SHOWS";
            case 1:
                return "ARTISTS";
        }
        return null;
    }
}
