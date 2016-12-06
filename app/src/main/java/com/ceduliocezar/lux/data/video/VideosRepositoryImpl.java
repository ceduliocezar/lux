package com.ceduliocezar.lux.data.video;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by cedulio on 26/11/16.
 */

public class VideosRepositoryImpl implements VideosRepository {

    private final VideoServiceApi videoServiceApi;

    @Inject
    public VideosRepositoryImpl(VideoServiceApi videoServiceApi) {
        this.videoServiceApi = videoServiceApi;
    }

    @Override
    public void getMovies(int movieId, final LoadVideosCallback callback) {
        videoServiceApi.getVideos(movieId, new VideoServiceApi.VideosServiceCallback() {
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
