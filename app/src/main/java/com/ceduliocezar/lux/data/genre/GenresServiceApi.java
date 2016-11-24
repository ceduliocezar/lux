package com.ceduliocezar.lux.data.genre;

import java.util.List;

/**
 * Created by cedulio on 03/08/2016.
 */
public interface GenresServiceApi {

    interface GenresServiceCallback<T>{
        void onLoaded(T load);
    }

    void getGenres(GenresServiceCallback<List<Genre>> callback);

    void saveGenreAsFavorite(Genre genre);

    void removeGenreAsFavorite(Genre genre);
}
