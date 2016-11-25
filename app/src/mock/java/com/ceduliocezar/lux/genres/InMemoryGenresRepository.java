package com.ceduliocezar.lux.genres;

import android.support.annotation.VisibleForTesting;

import com.ceduliocezar.lux.data.genre.Genre;
import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.genre.GenresServiceApi;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by cedulio on 03/08/2016.
 */
public class InMemoryGenresRepository implements GenresRepository {

    private final GenresServiceApi genresServiceApi;

    @VisibleForTesting
    List<Genre> cachedGenres;

    public InMemoryGenresRepository(GenresServiceApi genresServiceApi) {
        this.genresServiceApi = genresServiceApi;
    }

    @Override
    public void getGenres(final LoadGenresCallback callback) {
        checkNotNull(callback);

        if (cachedGenres == null) {
            genresServiceApi.getGenres(new GenresServiceApi.GenresServiceCallback<List<Genre>>() {
                @Override
                public void onLoaded(List<Genre> load) {
                    cachedGenres = load;
                    callback.onLoadGenres(cachedGenres);
                }

                @Override
                public void onError(Throwable t) {
                    callback.onError(t);
                }
            });
        } else {
            callback.onLoadGenres(cachedGenres);
        }
    }
}
