package com.ceduliocezar.lux.movie.detail;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;
import android.widget.Toast;

import com.ceduliocezar.lux.Injection;
import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.custom.ui.DividerItemDecoration;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.util.EspressoResourceIdling;

import java.util.ArrayList;
import java.util.List;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View, VideoAdapter.VideoAdapterListener {

    public static final String MOVIE_ID_PARAM = "MOVIE_ID_PARAM";

    private int movieId = 0;
    private Movie movie;
    private MovieDetailPresenter userActionsListener;
    private List<Video> videos = new ArrayList<>();
    private TextView tvOverview;
    private Toolbar toolbar;
    private RecyclerView recyclerViewVideos;
    private LinearLayoutManager layoutManager;
    private VideoAdapter videosAdapter;
    private TextView tvMovieYear;


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
                this,
                Injection.providesVideosRepository(this),
                Injection.providesMoviesRepository(this));
    }

    private void initViews() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvOverview = (TextView) findViewById(R.id.movie_overview);

        tvMovieYear = (TextView) findViewById(R.id.movie_year);

        recyclerViewVideos = (RecyclerView) findViewById(R.id.videos_recycler);
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
        // TODO: 25/11/16  
    }

    @Override
    public void hideLoading() {
        // TODO: 25/11/16  
    }

    @Override
    public void showVideos(List<Video> videos) {
        this.videos = videos;

        recyclerViewVideos.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewVideos.setLayoutManager(layoutManager);

        videosAdapter = new VideoAdapter(videos, this, Injection.providesThumbnailProvider());
        recyclerViewVideos.setAdapter(videosAdapter);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST);
        recyclerViewVideos.addItemDecoration(dividerItemDecoration);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingVideos() {
        // TODO: 25/11/16  
    }

    @Override
    public void hideLoadingVideos() {
        // TODO: 25/11/16  
    }

    @Override
    public void showMovie(Movie movie) {
        this.movie = movie;

        this.tvOverview.setText(movie.getOverview());
        this.toolbar.setTitle(movie.getTitle());
        this.setSupportActionBar(toolbar);
        this.tvMovieYear.setText(formatReleaseYear(movie));
    }

    private String formatReleaseYear(Movie movie) {

        if (movie.getReleaseDate().isEmpty()) {
            return "";
        }

        return movie.getReleaseDate().substring(0, 4);
    }

    @Override
    public void hideContainerVideos() {
        // TODO: 25/11/16  
    }

    @Override
    public void watchYoutubeVideo(Video video) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + video.getKey()));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + video.getKey()));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }


    @Override
    public void onClickVideo(Video video) {
        userActionsListener.userClickedVideo(video);
    }
}
