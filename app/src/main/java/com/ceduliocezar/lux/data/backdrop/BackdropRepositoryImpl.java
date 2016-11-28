package com.ceduliocezar.lux.data.backdrop;

import java.util.List;

/**
 * Created by cedulio on 27/11/16.
 */

public class BackdropRepositoryImpl implements BackdropRepository {


    private final BackdropServiceApi backdropServiceApi;

    public BackdropRepositoryImpl(BackdropServiceApi backdropServiceApi) {
        this.backdropServiceApi = backdropServiceApi;
    }

    @Override
    public void getBackdrops(int movieId, final LoadBackdropCallback callback) {

        backdropServiceApi.getBackdrops(movieId, new BackdropServiceApi.BackdropServiceApiCallback<List<Backdrop>>() {
            @Override
            public void onLoad(List<Backdrop> load) {
                callback.onLoadBackdrops(load);
            }

            @Override
            public void errorLoading(Throwable t) {
                callback.errorLoadingBackdrops(t);
            }
        });


    }
}
