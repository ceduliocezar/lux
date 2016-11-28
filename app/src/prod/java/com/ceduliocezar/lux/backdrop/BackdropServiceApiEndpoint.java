package com.ceduliocezar.lux.backdrop;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.MovieAPIFactory;
import com.ceduliocezar.lux.data.MovieDBRESTApi;
import com.ceduliocezar.lux.data.backdrop.Backdrop;
import com.ceduliocezar.lux.data.backdrop.BackdropServiceApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cedulio on 27/11/16.
 */

public class BackdropServiceApiEndpoint implements BackdropServiceApi {


    private final MovieDBRESTApi service;
    private Context context;

    public BackdropServiceApiEndpoint(Context context) {
        this.service = MovieAPIFactory.create(context);
        this.context = context;
    }

    @Override
    public void getBackdrops(int movieId, final BackdropServiceApiCallback<List<Backdrop>> callback) {

        Call<ImagesTransport> call = service.getMovieImages(movieId, getAPIKey());

        call.enqueue(new Callback<ImagesTransport>() {
            @Override
            public void onResponse(Call<ImagesTransport> call, Response<ImagesTransport> response) {
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
            public void onFailure(Call<ImagesTransport> call, Throwable t) {
                t.printStackTrace();
                callback.errorLoading(t);
            }
        });

    }

    @NonNull
    private String getAPIKey() {
        return context.getString(R.string.MOVIE_DB_API_KEY);
    }
}
