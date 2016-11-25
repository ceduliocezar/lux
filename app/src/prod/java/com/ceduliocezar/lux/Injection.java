package com.ceduliocezar.lux;

import android.content.Context;

import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.poster.PosterHandler;
import com.ceduliocezar.lux.genre.GenresRepositoryImpl;
import com.ceduliocezar.lux.movies.MoviesRepositoryImpl;
import com.ceduliocezar.lux.poster.PosterHandlerImpl;

/**
 * Created by cedulio on 27/10/16.
 */

public class Injection {

    public static GenresRepository providesGenreRepository(Context context) {
        return new GenresRepositoryImpl(context);
    }

    public static MoviesRepository providesMoviesRepository(Context context) {
        return new MoviesRepositoryImpl(context);
    }

    public static PosterHandler providesPosterHandler() {
        return new PosterHandlerImpl();
    }
}
