package com.ceduliocezar.lux.data.backdrop;

import android.content.Context;

import com.ceduliocezar.lux.data.cloud.MovieDBRESTApi;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cedulio on 27/11/16.
 */

public class BackdropServiceApiEndpoint implements BackdropServiceApi {


    private final MovieDBRESTApi service;
    private Context context;

    @Inject
    public BackdropServiceApiEndpoint(Context context, MovieDBRESTApi service) {
        this.service = service;
        this.context = context;
    }

    @Override
    public void getBackdrops(int movieId, final BackdropServiceApiCallback<List<Backdrop>> callback) {

        Call<BackdropTransport> call = service.getMovieImages(movieId);

        call.enqueue(new Callback<BackdropTransport>() {
            @Override
            public void onResponse(Call<BackdropTransport> call, Response<BackdropTransport> response) {
                if (response.isSuccessful()) {
                    callback.onLoad(response.body().getBackdrops());
                } else {
                    try {
                        String error = response.errorBody().string();
                        callback.errorLoading(new Exception(error));
                    } catch (Exception e) {
                        callback.errorLoading(new Exception());
                    }
                }
            }

            @Override
            public void onFailure(Call<BackdropTransport> call, Throwable t) {
                t.printStackTrace();
                callback.errorLoading(t);
            }
        });

    }
}
