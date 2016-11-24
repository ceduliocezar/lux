package com.ceduliocezar.lux;

import android.content.Context;

import com.ceduliocezar.lux.data.FakeGenresServiceApiImpl;
import com.ceduliocezar.lux.data.InMemoryGenresRepository;
import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.poster.PosterHandler;
import com.ceduliocezar.lux.movies.FakeMoviesServiceApiImpl;
import com.ceduliocezar.lux.movies.InMemoryMoviesRepository;
import com.ceduliocezar.lux.poster.AssetPosterHandler;

/**
 * Created by cedulio on 03/08/2016.
 */
public class Injection {


    public static GenresRepository providesGenreRepository() {
        return new InMemoryGenresRepository(new FakeGenresServiceApiImpl());
    }

    public static MoviesRepository providesMoviesRepository(Context context) {
        return new InMemoryMoviesRepository(new FakeMoviesServiceApiImpl());
    }

    public static PosterHandler providesPosterHandler() {
        return new AssetPosterHandler();
    }
}
