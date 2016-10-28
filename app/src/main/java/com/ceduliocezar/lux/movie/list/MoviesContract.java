package com.ceduliocezar.lux.movie.list;

import android.support.annotation.NonNull;

import com.ceduliocezar.lux.data.Movie;

import java.util.List;

/**
 * Created by cedulio on 08/08/2016.
 */
public class MoviesContract {

    interface View{
        void showActivityIndicator();
        void hideActivityIndicator();
        void showLazyLoad();
        void hideLazyLoad();
        void showMovies(List<Movie> movies, int currentPage, int maxPage);
        void showMovieDetailUi(Integer movieId);
        void showError(Throwable e);

        void appendPage(List<Movie> movies, int currentPage);
    }

    interface UserActionsListener{
        void loadMovies(boolean forceUpdate);
        void loadPage(int page);
        void openMovieDetail(@NonNull Movie movie);
    }
}
