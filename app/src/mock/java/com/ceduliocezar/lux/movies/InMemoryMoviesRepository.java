package com.ceduliocezar.lux.movies;

import android.support.annotation.NonNull;

import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.movie.MoviesServiceApi;

import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 08/08/2016.
 */
public class InMemoryMoviesRepository implements MoviesRepository {

    private MoviesServiceApi moviesServiceApi;

    public InMemoryMoviesRepository(@NonNull MoviesServiceApi moviesServiceApi) {
        this.moviesServiceApi = moviesServiceApi;
    }

    @Override
    public void getMovie(int movieId, final LoadMovieCallback callback) {
        moviesServiceApi.getMovie(movieId, new MoviesServiceApi.MovieServiceCallback() {
            @Override
            public void onLoadMovie(Movie movie) {
                callback.onLoadMovie(movie);
            }

            @Override
            public void errorLoadingMovie(Throwable t) {
                callback.onErrorLoadingMovie(t);
            }
        });
    }

    @Override
    public void getMovies(final int pageIndex, @NonNull final LoadMoviesCallback callback) {
        checkNotNull(callback);

        moviesServiceApi.getMovies(pageIndex, new MoviesServiceApi.MoviesServiceCallback<List<Movie>>() {
            @Override
            public void onLoaded(List<Movie> load, int page, int maxPage) {
                callback.onLoadMovies(load, pageIndex, maxPage);
            }

            @Override
            public void onError(Throwable e) {
                callback.onErrorLoadingMovies(e);
            }
        });

    }
}