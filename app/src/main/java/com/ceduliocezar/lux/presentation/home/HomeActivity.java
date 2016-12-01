package com.ceduliocezar.lux.presentation.home;

import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.presentation.util.EspressoResourceIdling;

public class HomeActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final int FEED_POSITION = 1;
    private HomeFragmentAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        mSectionsPagerAdapter = new HomeFragmentAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(FEED_POSITION);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoResourceIdling.getIdlingResource();
    }

}
