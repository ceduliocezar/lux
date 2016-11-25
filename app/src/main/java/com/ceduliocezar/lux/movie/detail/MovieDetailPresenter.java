package com.ceduliocezar.lux.movie.detail;

import android.content.Context;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.ceduliocezar.lux.util.EspressoResourceIdling;

import java.util.List;

/**
 * Created by TECBMCCS on 25/11/16.
 */

public class MovieDetailPresenter implements MovieDetailContract.UserActionsListener {
    private final MovieDetailContract.View view;
    private final VideosRepository videosRepository;
    private final MoviesRepository moviesRepository;
    private final Context context;

    public MovieDetailPresenter(MovieDetailContract.View view,
                                Context context,
                                VideosRepository videosRepository,
                                MoviesRepository moviesRepository) {
        this.view = view;
        this.videosRepository = videosRepository;
        this.moviesRepository = moviesRepository;
        this.context = context;
    }

    @Override
    public void loadMovie(int movieId) {
        EspressoResourceIdling.increment();
        view.showLoading();
        moviesRepository.getMovie(movieId, new MoviesRepository.LoadMovieCallback() {
            @Override
            public void onLoadMovie(Movie movie) {
                EspressoResourceIdling.decrement();
                view.hideLoading();

                if (movie != null) {
                    view.showMovie(movie);
                } else {
                    view.showError(context.getString(R.string.error_unable_to_load_movie));
                }
            }

            @Override
            public void onErrorLoadingMovie(Throwable t) {
                EspressoResourceIdling.decrement();
                view.hideLoading();
                view.showError(t.getMessage());
            }
        });
    }

    @Override
    public void loadVideos(int movieId) {

        EspressoResourceIdling.increment();

        view.showLoadingVideos();

        videosRepository.getMovies(movieId, new VideosRepository.LoadVideosCallback() {
            @Override
            public void onLoadVideos(List<Video> videos) {
                EspressoResourceIdling.decrement();
                view.hideLoadingVideos();

                if (videos == null || videos.isEmpty()) {
                    view.hideContainerVideos();
                } else {
                    view.showVideos(videos);
                }
            }

            @Override
            public void errorLoadingVideos(Throwable e) {
                EspressoResourceIdling.decrement();
                view.hideLoadingVideos();
                view.showError(e.getMessage());
            }
        });
    }

}
