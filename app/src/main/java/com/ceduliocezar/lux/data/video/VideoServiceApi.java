package com.ceduliocezar.lux.data.video;

import java.util.List;

/**
 * Created by TECBMCCS on 25/11/16.
 */

public interface VideoServiceApi {


    interface VideosServiceCallback {
        void onLoaded(List<Video> videos);

        void onError(Throwable e);
    }

    void getVideos(int movieId, VideosServiceCallback callback);

}





