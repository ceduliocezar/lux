package com.ceduliocezar.lux.video;

import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.data.video.VideoServiceApi;
import com.ceduliocezar.lux.data.video.VideosRepository;

import java.util.List;

/**
 * Created by cedulio on 25/11/16.
 */

public class InMemoryVideosRepository implements VideosRepository {

    private final VideoServiceApi serviceApi;

    public InMemoryVideosRepository(VideoServiceApi serviceApi) {
        this.serviceApi = serviceApi;
    }

    @Override
    public void getMovies(int movieId, final LoadVideosCallback callback) {
        serviceApi.getVideos(movieId, new VideoServiceApi.VideosServiceCallback() {
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
