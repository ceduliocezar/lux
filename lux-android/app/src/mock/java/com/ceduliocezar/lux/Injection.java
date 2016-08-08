package com.ceduliocezar.lux;

import com.ceduliocezar.lux.data.FakeGenresServiceApiImpl;
import com.ceduliocezar.lux.data.GenresRepository;
import com.ceduliocezar.lux.data.InMemoryGenresRepository;

/**
 * Created by cedulio on 03/08/2016.
 */
public class Injection {


    public static GenresRepository providesGenreRepository(){
        return new InMemoryGenresRepository(new FakeGenresServiceApiImpl());
    }
}
