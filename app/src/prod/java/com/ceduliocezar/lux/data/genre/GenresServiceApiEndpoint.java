package com.ceduliocezar.lux.data.genre;

import android.content.Context;

import com.ceduliocezar.lux.data.cloud.MovieDBRESTApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cedulio on 25/11/16.
 */

public class GenresServiceApiEndpoint implements GenresServiceApi {

    private final Context context;
    private final MovieDBRESTApi service;

    @Inject
    public GenresServiceApiEndpoint(Context context, MovieDBRESTApi service) {
        this.context = context.getApplicationContext();
        this.service = service;
    }

    @Override
    public void getGenres(final GenresServiceCallback<List<Genre>> callback) {

        Call<GenreTransport> response = service.getGenres();

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
