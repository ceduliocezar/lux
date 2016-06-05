package com.ceduliocezar.lux.menu;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.ceduliocezar.lux.R;

public class MenuActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private SectionsAdapter mSectionsPagerAdapter;

    private ViewPager mViewPager;
    private View profileMarker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        profileMarker = findViewById(R.id.profile_marker);

        mSectionsPagerAdapter = new SectionsAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);
        mViewPager.addOnPageChangeListener(this);
        mViewPager.post(new Runnable() {
            @Override
            public void run() {
                mViewPager.setCurrentItem(1);
            }
        });
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    private void showProfileMarker() {
        profileMarker.animate()
                .translationX(0);
    }

    private void hideProfileMarker() {
        profileMarker.animate()
                .translationX(profileMarker.getWidth()*-1);
    }

    @Override
    public void onPageSelected(int position) {
        if(position == 0){
            hideProfileMarker();
        }else {
            showProfileMarker();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
