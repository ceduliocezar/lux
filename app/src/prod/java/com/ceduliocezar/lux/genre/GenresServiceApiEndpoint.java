package com.ceduliocezar.lux.genre;

import android.content.Context;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.MovieAPIFactory;
import com.ceduliocezar.lux.data.MovieDBRESTApi;
import com.ceduliocezar.lux.data.genre.Genre;
import com.ceduliocezar.lux.data.genre.GenreTransport;
import com.ceduliocezar.lux.data.genre.GenresServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TECBMCCS on 25/11/16.
 */

public class GenresServiceApiEndpoint implements GenresServiceApi {

    private final Context context;

    public GenresServiceApiEndpoint(Context context) {
        this.context = context.getApplicationContext();
    }

    @Override
    public void getGenres(final GenresServiceCallback<List<Genre>> callback) {

        MovieDBRESTApi service = MovieAPIFactory.create(context);

        Call<GenreTransport> response = service.getGenres(context.getString(R.string.MOVIE_DB_API_KEY));

        response.enqueue(new Callback<GenreTransport>() {
            @Override
            public void onResponse(Call<GenreTransport> call, Response<GenreTransport> response) {

                if (response.isSuccessful()) {
                    callback.onLoaded(response.body().getGenres());
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
            public void onFailure(Call<GenreTransport> call, Throwable t) {
                callback.onError(t);
            }
        });
    }
}
