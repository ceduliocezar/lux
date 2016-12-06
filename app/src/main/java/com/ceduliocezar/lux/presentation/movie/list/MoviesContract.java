package com.ceduliocezar.lux.presentation.movie.list;

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
        void showError(Throwable e);
        void appendPage(List<Movie> movies, int currentPage);
        void showGenres(List<Genre> genres);
        void showNoMoviesFoundView();
    }

    public interface UserActionsListener{
        void loadMovies();
        void loadPage(int page);
        void loadGenres();
        void setView(MoviesContract.View view);
    }
}
