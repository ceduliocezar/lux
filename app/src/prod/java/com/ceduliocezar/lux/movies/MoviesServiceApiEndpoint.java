package com.ceduliocezar.lux.data.movies;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.cloud.MovieAPIFactory;
import com.ceduliocezar.lux.data.cloud.MovieDBRESTApi;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MovieTransport;
import com.ceduliocezar.lux.data.movie.MoviesServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cedulio on 08/08/2016.
 */
public class MoviesServiceApiEndpoint implements MoviesServiceApi {

    private final Context context;
    private final MovieDBRESTApi service;

    public MoviesServiceApiEndpoint(Context applicationContext) {
        this.context = applicationContext.getApplicationContext();
        this.service = MovieAPIFactory.create(context);
    }

    @Override
    public void getMovies(final int page, final MoviesServiceCallback<List<Movie>> callback) {

        Call<MovieTransport> response = service.orderByPopularity(getAPIKey(), page);
        response.enqueue(new Callback<MovieTransport>() {
            @Override
            public void onResponse(Call<MovieTransport> call, Response<MovieTransport> response) {

                if (response.isSuccessful()) {
                    callback.onLoaded(response.body().getResults(), page, response.body()
                            .getTotalPages());
                } else {
                    try {
                        String error = response.errorBody().string();
                        callback.onError(new Exception(error));
                    } catch (Exception e) {
                        callback.onError(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieTransport> call, Throwable t) {
                callback.onError(t);
            }
        });

    }

    @Override
    public void getMovie(int movieId, final MovieServiceCallback callback) {
        Call<Movie> call = service.getMovie(movieId, getAPIKey());

        call.enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.isSuccessful()) {
                    callback.onLoadMovie(response.body());
                } else {
                    try {
                        String error = response.errorBody().string();
                        callback.errorLoadingMovie(new Exception(error));
                    } catch (Exception e) {
                        callback.errorLoadingMovie(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
                callback.errorLoadingMovie(t);
            }
        });
    }

    @NonNull
    private String getAPIKey() {
        return context.getString(R.string.MOVIE_DB_API_KEY);
    }
}
