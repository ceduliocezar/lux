package com.ceduliocezar.lux.movie.list;

import android.support.annotation.NonNull;

import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.util.EspressoResourceIdling;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 08/08/2016.
 */
public class MoviesPresenter implements MoviesContract.UserActionsListener {


    private static final int FIRST_PAGE = 1;
    private final MoviesRepository moviesRepository;
    private final MoviesContract.View moviesView;

    public MoviesPresenter(@NonNull MoviesRepository moviesRepository, @NonNull MoviesContract.View
            moviesView) {
        this.moviesRepository = moviesRepository;
        this.moviesView = moviesView;
    }

    @Override
    public void loadMovies(boolean forceUpdate) {
        moviesView.showActivityIndicator();

        EspressoResourceIdling.increment();

        moviesRepository.getMovies(FIRST_PAGE, new MoviesRepository.LoadMoviesCallback() {
            @Override
            public void onLoadMovies(List<Movie> movies, int currentPage, int maxPage) {
                EspressoResourceIdling.decrement();

                moviesView.hideActivityIndicator();
                moviesView.showMovies(movies, currentPage, maxPage);
            }

            @Override
            public void onErrorLoadingMovies(Throwable e) {
                EspressoResourceIdling.decrement();

                moviesView.hideActivityIndicator();
                moviesView.showError(e);
            }
        });
    }

    @Override
    public void loadPage(int page) {
        moviesView.showLazyLoad();
        moviesRepository.getMovies(page, new MoviesRepository.LoadMoviesCallback() {
            @Override
            public void onLoadMovies(List<Movie> movies, int currentPage, int maxPage) {
                moviesView.hideLazyLoad();
                moviesView.appendPage(movies, currentPage);
            }

            @Override
            public void onErrorLoadingMovies(Throwable e) {
                moviesView.hideLazyLoad();
                moviesView.showError(e);
            }
        });
    }

    @Override
    public void openMovieDetail(@NonNull Movie movie) {
        checkNotNull(movie, "movie cannot be null");

        moviesView.showMovieDetailUi(movie.getId());
    }
}
