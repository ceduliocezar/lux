package com.ceduliocezar.lux.movie.detail;

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

    public MovieDetailPresenter(MovieDetailContract.View view,
                                VideosRepository videosRepository,
                                MoviesRepository moviesRepository) {
        this.view = view;
        this.videosRepository = videosRepository;
        this.moviesRepository = moviesRepository;
    }

    @Override
    public void loadMovie(int movieId){
        EspressoResourceIdling.increment();
        view.showLoading();
        moviesRepository.getMovie(movieId, new MoviesRepository.LoadMovieCallback() {
            @Override
            public void onLoadMovie(Movie movie) {
                EspressoResourceIdling.decrement();
                view.hideLoading();
                view.showMovie(movie);
            }

            @Override
            public void onErrorLoadingMovie(Throwable t) {
                EspressoResourceIdling.decrement();
                view.hideLoading();
                view.showError(t);
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
                view.showVideos(videos);
            }

            @Override
            public void errorLoadingVideos(Throwable e) {
                EspressoResourceIdling.decrement();
                view.hideLoadingVideos();
                view.showError(e);
            }
        });
    }

}
