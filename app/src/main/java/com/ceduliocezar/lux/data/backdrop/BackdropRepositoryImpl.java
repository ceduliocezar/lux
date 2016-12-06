package com.ceduliocezar.lux.data.backdrop;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 27/11/16.
 */

public class BackdropRepositoryImpl implements BackdropRepository {


    private final BackdropServiceApi backdropServiceApi;

    @Inject
    public BackdropRepositoryImpl(@NonNull BackdropServiceApi backdropServiceApi) {
        this.backdropServiceApi = checkNotNull(backdropServiceApi, "backdrop service can not be null");
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
