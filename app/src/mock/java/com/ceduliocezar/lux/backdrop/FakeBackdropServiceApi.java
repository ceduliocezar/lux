package com.ceduliocezar.lux.backdrop;

import com.ceduliocezar.lux.data.backdrop.Backdrop;
import com.ceduliocezar.lux.data.backdrop.BackdropServiceApi;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cedulio on 27/11/16.
 */

public class FakeBackdropServiceApi implements BackdropServiceApi {


    private static final String FAKE_BACKDROP_ASSETS_PATH = "file:///android_asset/poster_test.jpg";
    private static final String FAKE_ISO_6391 = "FAKE_ISO_6391";

    @Override
    public void getBackdrops(int movieId, BackdropServiceApiCallback<List<Backdrop>> callback) {

        List<Backdrop> backdrops = new ArrayList<>();

        for (int i = 0; i < 50; i++) {
            backdrops.add(createFakeBackdrop(i));
        }

        callback.onLoad(backdrops);
    }

    private Backdrop createFakeBackdrop(int i) {

        Backdrop backdrop = new Backdrop();

        backdrop.setAspectRatio(Double.valueOf(i));
        backdrop.setFilePath(FAKE_BACKDROP_ASSETS_PATH);
        backdrop.setHeight(500.0);
        backdrop.setIso6391(FAKE_ISO_6391);
        backdrop.setVoteAverage(Double.valueOf(i));
        backdrop.setVoteCount(i);
        backdrop.setWidth(500 * i);

        return backdrop;
    }

}
