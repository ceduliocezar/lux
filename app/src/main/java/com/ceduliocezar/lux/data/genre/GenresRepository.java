package com.ceduliocezar.lux.data.genre;

import java.util.List;

/**
 * Created by cedulio on 03/08/2016.
 */
public interface GenresRepository {

    interface LoadGenresCallback{
        void onLoadGenres(List<Genre> genres);
    }

    void getAllGenres(LoadGenresCallback callback);
    void saveGenreAsFavorite(Genre genre);
    void removeGenreAsFavorite(Genre genre);
}
