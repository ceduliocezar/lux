package com.ceduliocezar.lux.movie.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.ceduliocezar.lux.Injection;
import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.util.EspressoResourceIdling;

import java.util.ArrayList;
import java.util.List;

import static com.ceduliocezar.lux.R.id.toolbar;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    public static final String MOVIE_ID_PARAM = "MOVIE_ID_PARAM";

    private int movieId = 0;
    private Movie movie;
    private MovieDetailPresenter userActionsListener;
    private List<Video> videos = new ArrayList<>();
    private TextView tvOverview;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        loadParams();
        initUserActionListener();
        initViews();

        loadMovie();
        loadVideos();
    }

    private void loadVideos() {
        userActionsListener.loadVideos(movieId);
    }

    private void loadMovie() {
        userActionsListener.loadMovie(movieId);
    }

    private void initUserActionListener() {
        this.userActionsListener = new MovieDetailPresenter(this,
                Injection.providesVideosRepository(this),
                Injection.providesMoviesRepository(this));
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
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


//        final ImageView imageView = (ImageView) findViewById(R.id.movie_image);
//
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


        tvOverview = (TextView) findViewById(R.id.movie_overview);
    }

    private void loadParams() {
        movieId = getIntent().getExtras().getInt(MOVIE_ID_PARAM);
    }

//    private void changeToolbarColor(Palette palette) {
//
//        CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
//        appBarLayout.setBackgroundColor(palette.getDarkMutedColor(0));
//    }

    public static Intent getIntent(Context context, int movieId) {
        Intent intent = new Intent(context, MovieDetailActivity.class);

        Bundle bundle = new Bundle();

        bundle.putInt(MOVIE_ID_PARAM, movieId);

        intent.putExtras(bundle);
        return intent;
    }

    @VisibleForTesting
    public IdlingResource getCountingIdlingResource() {
        return EspressoResourceIdling.getIdlingResource();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showVideos(List<Video> videos) {
        this.videos = videos;
    }

    @Override
    public void showError(Throwable t) {
        Toast.makeText(this, Log.getStackTraceString(t), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingVideos() {

    }

    @Override
    public void hideLoadingVideos() {

    }

    @Override
    public void showMovie(Movie movie) {
        this.movie = movie;
        tvOverview.setText(movie.getOverview());
        toolbar.setTitle(movie.getTitle());
    }

}
