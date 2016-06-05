package com.ceduliocezar.lux.menu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ceduliocezar.lux.menu.genre.MoviesByGenreFragment;
import com.ceduliocezar.lux.menu.profile.ProfileFragment;

/**
 * Created by cedulio on 05/06/16.
 */
public class SectionsAdapter extends FragmentPagerAdapter {

    public SectionsAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {


        if (position == 0) {
            return ProfileFragment.newInstance();
        } else if (position == 1) {
            return SectionFragment.newInstance(position + 1);
        } else if (position == 2) {
            return MoviesByGenreFragment.newInstance();
        }

        return SectionFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return 3;
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