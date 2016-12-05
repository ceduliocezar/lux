package com.ceduliocezar.lux.video;

import android.support.annotation.NonNull;

import com.ceduliocezar.lux.data.video.Video;
import com.ceduliocezar.lux.data.video.VideoServiceApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cedulio on 25/11/16.
 */

public class FakeVideosServiceApiImpl implements VideoServiceApi {

    public FakeVideosServiceApiImpl() {
    }


    @Override
    public void getVideos(int movieId, VideosServiceCallback callback) {

        List<Video> videos = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            Video video = createFakeVideo(i);

            videos.add(video);
        }

        callback.onLoaded(videos);
    }

    @NonNull
    private Video createFakeVideo(int i) {
        Video video = new Video();
        video.setId(String.valueOf(i));
        video.setName("Video " + i);
        video.setSite("Site " + i);
        video.setKey("Key " + i);
        video.setSize(i);
        video.setType("Type:" + i);
        return video;
    }
}
