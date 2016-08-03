package com.ceduliocezar.lux.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ceduliocezar.lux.home.movies.MoviesFragment;
import com.ceduliocezar.lux.home.profile.ProfileFragment;

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
            return ProfileFragment.newInstance();
        } else {
            return MoviesFragment.newInstance();
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "SECTION 1";
            case 1:
                return "SECTION 2";
            case 2:
                return "SECTION 3";
        }
        return null;
    }
}