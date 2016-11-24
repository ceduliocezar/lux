package com.ceduliocezar.lux.movies;

import android.content.Context;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.movie.Movie;
import com.ceduliocezar.lux.data.movie.MovieAPIFactory;
import com.ceduliocezar.lux.data.movie.MovieDBRESTApi;
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

    public MoviesServiceApiEndpoint(Context applicationContext) {
        this.context = applicationContext.getApplicationContext();
    }

    @Override
    public void getMovies(final int page, final MoviesServiceCallback<List<Movie>> callback) {


        MovieDBRESTApi service = MovieAPIFactory.create(context);

        Call<MovieTransport> response = service.orderByPopularity(context.getString(R.string.MOVIE_DB_API_KEY), page);
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
}
