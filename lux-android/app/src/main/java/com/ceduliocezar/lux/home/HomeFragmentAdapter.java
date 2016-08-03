package com.ceduliocezar.lux.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ceduliocezar.lux.movies.MoviesFragment;
import com.ceduliocezar.lux.settings.SettingsFragment;

/**
 * Created by cedulio on 05/06/16.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0) {
            return SettingsFragment.newInstance();
        } else {
            return MoviesFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}