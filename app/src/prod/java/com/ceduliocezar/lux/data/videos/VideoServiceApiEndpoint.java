package com.ceduliocezar.lux.data.videos;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ceduliocezar.lux.R;
import com.ceduliocezar.lux.data.cloud.MovieDBRESTApi;
import com.ceduliocezar.lux.data.video.VideoServiceApi;
import com.ceduliocezar.lux.data.video.VideosTransport;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by cedulio on 26/11/16.
 */

public class VideoServiceApiEndpoint implements VideoServiceApi {

    private final Context context;
    private final MovieDBRESTApi service;

    @Inject
    public VideoServiceApiEndpoint(Context context, MovieDBRESTApi service) {
        this.context = context;
        this.service = service;
    }


    @Override
    public void getVideos(int movieId, final VideosServiceCallback callback) {


        Call<VideosTransport> call = service.getVideos(movieId);

        call.enqueue(new Callback<VideosTransport>() {
            @Override
            public void onResponse(Call<VideosTransport> call, Response<VideosTransport> response) {
                if (response.isSuccessful()) {
                    callback.onLoaded(response.body().getResults());
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
            public void onFailure(Call<VideosTransport> call, Throwable t) {
                callback.onError(t);
            }
        });
    }

    @NonNull
    private String getAPIKey() {
        return context.getString(R.string.MOVIE_DB_API_KEY);
    }
}
