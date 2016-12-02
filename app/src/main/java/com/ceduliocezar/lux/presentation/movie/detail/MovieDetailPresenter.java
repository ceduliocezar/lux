package com.ceduliocezar.lux.presentation.movie.detail;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.backdrop.Backdrop;
import com.ceduliocezar.lux.data.backdrop.BackdropRepository;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.ceduliocezar.lux.presentation.util.EspressoResourceIdling;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 25/11/16.
 */

public class MovieDetailPresenter implements MovieDetailContract.UserActionsListener {
    private final MovieDetailContract.View view;
    private final VideosRepository videosRepository;
    private final MoviesRepository moviesRepository;
    private final Context context;
    private final BackdropRepository backdropRepository;

    public MovieDetailPresenter(@NonNull MovieDetailContract.View view,
                                @NonNull Context context,
                                @NonNull VideosRepository videosRepository,
                                @NonNull MoviesRepository moviesRepository,
                                @NonNull BackdropRepository backdropRepository) {

        this.view = checkNotNull(view, "movie detail view can not be null");
        this.videosRepository = checkNotNull(videosRepository, "videos repository can not be null");
        this.moviesRepository = checkNotNull(moviesRepository, "movies repository can not be null");
        this.backdropRepository = checkNotNull(backdropRepository, "backdrop repository can not be null");
        this.context = checkNotNull(context, "context can not be null");
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
    public void loadBackdrops(int movieId) {

        EspressoResourceIdling.increment();

        view.showLoadingBackdrops();

        backdropRepository.getBackdrops(movieId, new BackdropRepository.LoadBackdropCallback() {
            @Override
            public void onLoadBackdrops(List<Backdrop> backdrops) {
                EspressoResourceIdling.decrement();

                view.hideLoadingBackdrops();
                view.showBackdrops(backdrops);
            }

            @Override
            public void errorLoadingBackdrops(Throwable t) {
                EspressoResourceIdling.decrement();

                view.hideLoadingBackdrops();
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

    public void userClickedVideo(@NonNull Video video) {
        checkNotNull(video, "video can not be null");
        view.watchYoutubeVideo(video);
    }

    @Override
    public void userClickedBackdrop(Backdrop backdrop) {
        checkNotNull(backdrop, "backdrop can not be null.");
        view.showFullScreenBackdrop(backdrop);
    }
}
