package com.ceduliocezar.lux.data;

import android.support.annotation.NonNull;
import android.support.annotation.VisibleForTesting;

import java.util.List;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by cedulio on 03/08/2016.
 */
public class InMemoryGenresRepository implements  GenresRepository {

    private final GenresServiceApi genresServiceApi;

    @VisibleForTesting
    List<Genre> cachedGenres;

    public InMemoryGenresRepository(GenresServiceApi genresServiceApi){
        this.genresServiceApi =  genresServiceApi;
    }

    @Override
    public void getAllGenres(@NonNull final LoadGenresCallback callback) {
        checkNotNull(callback);

        if(cachedGenres ==  null){
            genresServiceApi.getGenres(new GenresServiceApi.GenresServiceCallback<List<Genre>>() {
                @Override
                public void onLoaded(List<Genre> load) {
                    cachedGenres = load;
                    callback.onLoadGenres(cachedGenres);
                }
            });
        }else {
            callback.onLoadGenres(cachedGenres);
        }
    }

    @Override
    public void saveGenreAsFavorite(Genre genre) {
        genresServiceApi.saveGenreAsFavorite(genre);
    }

    @Override
    public void removeGenreAsFavorite(Genre genre) {
        genresServiceApi.removeGenreAsFavorite(genre);
    }
}
