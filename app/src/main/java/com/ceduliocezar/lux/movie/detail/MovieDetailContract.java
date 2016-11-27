package com.ceduliocezar.lux.movie.detail;

import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.video.Video;

import java.util.List;

/**
 * Created by cedulio on 25/11/16.
 */

public class MovieDetailContract {

    interface View {

        void showLoading();

        void hideLoading();

        void showVideos(List<Video> videos);

        void showError(String message);

        void showLoadingVideos();

        void hideLoadingVideos();

        void showMovie(Movie movie);

        void hideContainerVideos();

        void watchYoutubeVideo(Video video);
    }

    interface UserActionsListener {
        void loadVideos(int movieId);

        void loadMovie(int movieId);
    }
}
