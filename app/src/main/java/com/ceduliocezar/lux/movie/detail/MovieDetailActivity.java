package com.ceduliocezar.lux.movie.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.util.EspressoResourceIdling;

public class MovieDetailActivity extends AppCompatActivity {

    private static final String MOVIE_PARAM = "MOVIE_PARAM";
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        movie = (Movie) getIntent().getExtras().getSerializable(MOVIE_PARAM);

        final ImageView imageView = (ImageView) findViewById(R.id.movie_image);

//        final RequestCreator requestCreator = Picasso.with(this).load("http://image.tmdb.org/t/p/w500" + movie.getPosterPath());
//
//        requestCreator.into(imageView, new Callback() {
//            @Override
//            public void onSuccess() {
//                Palette.PaletteAsyncListener paletteListener = new Palette.PaletteAsyncListener() {
//                    public void onGenerated(Palette palette) {
//                        changeToolbarColor(palette);
//                    }
//                };
//
//                Bitmap myBitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
//                if (myBitmap != null && !myBitmap.isRecycled()) {
//                    Palette.from(myBitmap).generate(paletteListener);
//                }
//            }
//
//            @Override
//            public void onError() {
//
//            }
//        });


        TextView tvOverview = (TextView) findViewById(R.id.movie_overview);
        tvOverview.setText(movie.getOverview());

    }

    private void changeToolbarColor(Palette palette) {

        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        appBarLayout.setBackgroundColor(palette.getDarkMutedColor(0));
    }

    public static Intent getIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);

        Bundle bundle=new Bundle();

        bundle.putSerializable(MOVIE_PARAM, movie);

        intent.putExtras(bundle);
        return intent;
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoResourceIdling.getIdlingResource();
    }
}
