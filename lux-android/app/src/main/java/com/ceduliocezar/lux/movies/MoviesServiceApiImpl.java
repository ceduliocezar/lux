package com.ceduliocezar.lux.movies;

import android.content.Context;

import com.ceduliocezar.lux.data.Movie;

import java.io.IOException;
import java.util.List;

/**
 * Created by cedulio on 08/08/2016.
 */
public class MoviesServiceApiImpl implements MoviesServiceApi {


    private final MoviesServiceApiEndpoint moviesEndpoint;

    public MoviesServiceApiImpl(Context context) {
        this.moviesEndpoint = new MoviesServiceApiEndpoint(context);
    }

    @Override
    public void getMovies(int page, MoviesServiceCallback<List<Movie>> callback) {

        try {
            callback.onLoaded(moviesEndpoint.loadMovies(page), page, page);
        } catch (IOException e) {
            callback.onError(e);
        }
    }
}
