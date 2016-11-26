package com.ceduliocezar.lux.movie.list;

import com.ceduliocezar.lux.data.genre.Genre;
import com.ceduliocezar.lux.data.movie.Movie;

import java.util.List;

/**
 * Created by cedulio on 08/08/2016.
 */
public class MoviesContract {

    interface View{
        void showActivityIndicator();
        void hideActivityIndicator();
        void showPageLoad();
        void hidePageLoad();
        void showMovies(List<Movie> movies, int currentPage, int maxPage);
        void showMovieDetailUi(Integer movieId);
        void showError(Throwable e);

        void appendPage(List<Movie> movies, int currentPage);

        void onLoadGenres(List<Genre> genres);

        void showNoMoviesFoundView();
    }

    interface UserActionsListener{
        void loadMovies();
        void loadPage(int page);
        void loadGenres();
    }
}
