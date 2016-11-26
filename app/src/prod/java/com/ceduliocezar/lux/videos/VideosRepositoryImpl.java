package com.ceduliocezar.lux.videos;

import android.content.Context;

import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.data.video.VideoServiceApi;
import com.ceduliocezar.lux.data.video.VideosRepository;

import java.util.List;

/**
 * Created by cedulio on 26/11/16.
 */

public class VideosRepositoryImpl implements VideosRepository {

    private final VideoServiceApiEndpoint serviceEndpoit;

    public VideosRepositoryImpl(Context context) {
        this.serviceEndpoit = new VideoServiceApiEndpoint(context);
    }

    @Override
    public void getMovies(int movieId, final LoadVideosCallback callback) {
        serviceEndpoit.getVideos(movieId, new VideoServiceApi.VideosServiceCallback() {
            @Override
            public void onLoaded(List<Video> videos) {
                callback.onLoadVideos(videos);
            }

            @Override
            public void onError(Throwable e) {
                callback.errorLoadingVideos(e);
            }
        });
    }
}
