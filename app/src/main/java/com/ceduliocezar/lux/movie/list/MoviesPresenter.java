package com.ceduliocezar.lux.movie.list;

import android.support.annotation.NonNull;

import com.ceduliocezar.lux.data.genre.Genre;
import com.ceduliocezar.lux.data.genre.GenresRepository;
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
    private final GenresRepository genresRepository;

    public MoviesPresenter(@NonNull MoviesRepository moviesRepository,
                           @NonNull GenresRepository genresRepository,
                           @NonNull MoviesContract.View moviesView) {

        this.moviesRepository = checkNotNull(moviesRepository, "movies repository can not be null");
        this.moviesView = checkNotNull(moviesView, "movies view can not be null");
        this.genresRepository = checkNotNull(genresRepository, "genres repository can not be null");

    }

    @Override
    public void loadMovies() {
        moviesView.showActivityIndicator();

        EspressoResourceIdling.increment();

        moviesRepository.getMovies(FIRST_PAGE, new MoviesRepository.LoadMoviesCallback() {
            @Override
            public void onLoadMovies(List<Movie> movies, int currentPage, int maxPage) {
                EspressoResourceIdling.decrement();

                moviesView.hideActivityIndicator();

                if (movies.isEmpty()) {
                    moviesView.showNoMoviesFoundView();
                } else {
                    moviesView.showMovies(movies, currentPage, maxPage);
                }
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
        moviesView.showPageLoad();
        moviesRepository.getMovies(page, new MoviesRepository.LoadMoviesCallback() {
            @Override
            public void onLoadMovies(List<Movie> movies, int currentPage, int maxPage) {
                moviesView.hidePageLoad();
                moviesView.appendPage(movies, currentPage);
            }

            @Override
            public void onErrorLoadingMovies(Throwable e) {
                moviesView.hidePageLoad();
                moviesView.showError(e);
            }
        });
    }

    @Override
    public void loadGenres() {

        EspressoResourceIdling.increment();
        genresRepository.getGenres(new GenresRepository.LoadGenresCallback() {
            @Override
            public void onLoadGenres(List<Genre> genres) {
                EspressoResourceIdling.decrement();
                moviesView.onLoadGenres(genres);
            }

            @Override
            public void onError(Throwable t) {
                EspressoResourceIdling.decrement();
                moviesView.showError(t);
            }
        });
    }
}
