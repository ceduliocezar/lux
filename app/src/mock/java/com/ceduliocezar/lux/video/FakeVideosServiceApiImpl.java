package com.ceduliocezar.lux.video;

import android.content.Context;

import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.data.video.VideoServiceApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by TECBMCCS on 25/11/16.
 */

public class FakeVideosServiceApiImpl implements VideoServiceApi {

    private final Context context;

    public FakeVideosServiceApiImpl(Context context) {
        this.context = context;
    }


    @Override
    public void getVideos(int movieId, VideosServiceCallback callback) {

        List<Video> videos = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Video video = new Video();
            video.setId(String.valueOf(i));
            video.setName("Video " + i);
            video.setSite("Site " + i);
            video.setKey("Key " + i);
            video.setSize(i);
            video.setType("Type:" + i);

            videos.add(video);
        }

        callback.onLoaded(videos);
    }
}
