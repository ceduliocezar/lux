package com.ceduliocezar.lux.presentation.movie.detail;

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
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ceduliocezar.lux.Injection;
import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.backdrop.Backdrop;
import com.ceduliocezar.lux.data.backdrop.BackdropImageProvider;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.presentation.custom.ui.DividerItemDecoration;
import com.ceduliocezar.lux.presentation.util.EspressoResourceIdling;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.ceduliocezar.lux.Injection.providesBackdropImageProvider;

public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View,
        VideoAdapter.VideoAdapterListener,
        BackdropAdapter.BackdropAdapterListener {

    public static final String MOVIE_ID_PARAM = "MOVIE_ID_PARAM";

    @BindView(R.id.movie_overview)
    TextView tvOverview;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.videos_recycler)
    RecyclerView recyclerViewVideos;

    @BindView(R.id.movie_year)
    TextView tvMovieYear;

    @BindView(R.id.loading_videos)
    View loadingVideos;

    @BindView(R.id.loading_images)
    View loadingBackdropsView;

    @BindView(R.id.images_recycler)
    RecyclerView recyclerBackdropsImages;

    @BindView(R.id.movie_title)
    TextView tvMovieTitle;
    @BindView(R.id.movie_image)
    ImageView movieImageToolbar;

    private int movieId = 0;

    private VideoAdapter videosAdapter;
    private BackdropAdapter backdropsAdapter;
    private Movie movie;
    private List<Backdrop> backdrops;
    private MovieDetailContract.UserActionsListener userActionsListener;
    private List<Video> videos = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        ButterKnife.bind(this);

        initToolbar();

        loadParams();
        initUserActionListener();

        loadBackdrops();
        loadMovie();
        loadVideos();
    }

    private void loadBackdrops() {
        userActionsListener.loadBackdrops(movieId);
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
                Injection.providesMoviesRepository(this),
                Injection.providesBackdropsRepository(this));
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
    }

    private void loadParams() {
        movieId = getIntent().getExtras().getInt(MOVIE_ID_PARAM);
    }

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
        // TODO: 27/11/16  
    }

    @Override
    public void hideLoading() {
        // TODO: 27/11/16  
    }

    @Override
    public void showVideos(List<Video> videos) {
        this.videos = videos;

        videosAdapter = new VideoAdapter(videos, this, Injection.providesThumbnailProvider());

        recyclerViewVideos.setAdapter(videosAdapter);
        recyclerViewVideos.setHasFixedSize(true);
        recyclerViewVideos.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewVideos.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoadingVideos() {
        loadingVideos.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingVideos() {
        loadingVideos.setVisibility(View.GONE);
    }

    @Override
    public void showMovie(Movie movie) {
        this.movie = movie;

        this.tvOverview.setText(movie.getOverview());
        this.tvMovieYear.setText(formatReleaseYear(movie));
        this.tvMovieTitle.setText(movie.getOriginalTitle());
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

        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("http://www.youtube.com/watch?v=" + video.getKey()));
            startActivity(webIntent);
        }
    }

    @Override
    public void showLoadingBackdrops() {
        loadingBackdropsView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingBackdrops() {
        loadingBackdropsView.setVisibility(View.GONE);
    }

    @Override
    public void showBackdrops(List<Backdrop> backdrops) {
        this.backdrops = backdrops;

        backdropsAdapter = new BackdropAdapter(this.backdrops, this, providesBackdropImageProvider(this));


        recyclerBackdropsImages.setAdapter(backdropsAdapter);

        recyclerBackdropsImages.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerBackdropsImages.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL_LIST));

        if (!backdrops.isEmpty()) {
            showMovieImageToolbar(backdrops.get(0));
        }
    }

    @Override
    public void showFullScreenBackdrop(Backdrop backdrop) {
        // TODO: 02/12/16
    }

    private void showMovieImageToolbar(Backdrop backdrop) {
        BackdropImageProvider backdropImageProvider = Injection.providesBackdropImageProvider(this);

        backdropImageProvider.load(movieImageToolbar, backdrop, this);
    }

    @Override
    public void onClickVideo(Video video) {
        userActionsListener.userClickedVideo(video);
    }

    @Override
    public void onClickBackdrop(Backdrop backdrop) {
        userActionsListener.userClickedBackdrop(backdrop);
    }
}
