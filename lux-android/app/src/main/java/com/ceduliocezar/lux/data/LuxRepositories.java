package com.ceduliocezar.lux.data;

import android.support.annotation.NonNull;

import static com.google.gson.internal.$Gson$Preconditions.checkNotNull;

/**
 * Created by cedulio on 03/08/2016.
 */
public class LuxRepositories {

    private static GenresRepository genresRepository = null;

    private LuxRepositories(){

    }

    public synchronized static GenresRepository getInMemoryRepoInstance(@NonNull GenresServiceApi genresServiceApi) {
        checkNotNull(genresServiceApi);
        if (null == genresRepository) {
            genresRepository = new InMemoryGenresRepository(genresServiceApi);
        }
        return genresRepository;
    }
}
