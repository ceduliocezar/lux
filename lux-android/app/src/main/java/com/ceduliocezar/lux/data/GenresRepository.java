package com.ceduliocezar.lux.data;

import java.util.List;

/**
 * Created by TECBMCCS on 03/08/2016.
 */
public interface GenresRepository {

    interface LoadGenresCallback{
        void onLoadGenres(List<Genre> genres);
    }

    void getGenres(LoadGenresCallback callback);
    void selectGenreAsFavorite(Genre genre);
    void deSelectGenreAsFavorite(Genre genre);
}
