package com.ceduliocezar.lux.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ceduliocezar.lux.movie.list.MoviesFragment;

/**
 * Created by cedulio on 05/06/16.
 */
public class HomeFragmentAdapter extends FragmentPagerAdapter {

    public HomeFragmentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MoviesFragment.newInstance();
    }

    @Override
    public int getCount() {
        return 1;
    }
}