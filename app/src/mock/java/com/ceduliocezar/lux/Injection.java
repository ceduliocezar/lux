package com.ceduliocezar.lux;

import com.ceduliocezar.lux.data.FakeGenresServiceApiImpl;
import com.ceduliocezar.lux.data.GenresRepository;
import com.ceduliocezar.lux.data.InMemoryGenresRepository;
import com.ceduliocezar.lux.movies.FakeMoviesServiceApiImpl;
import com.ceduliocezar.lux.movies.InMemoryMoviesRepository;
import com.ceduliocezar.lux.movie.list.MoviesRepository;

/**
 * Created by cedulio on 03/08/2016.
 */
public class Injection {


    public static GenresRepository providesGenreRepository(){
        return new InMemoryGenresRepository(new FakeGenresServiceApiImpl());
    }

    public static MoviesRepository providesMoviesRepository() {
        return new InMemoryMoviesRepository(new FakeMoviesServiceApiImpl());
    }
}
