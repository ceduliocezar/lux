package com.ceduliocezar.lux.injection;

import com.ceduliocezar.lux.data.backdrop.BackdropRepository;
import com.ceduliocezar.lux.data.backdrop.BackdropServiceApi;
import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.genre.GenresServiceApi;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.movie.MoviesServiceApi;
import com.ceduliocezar.lux.data.video.VideoServiceApi;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.ceduliocezar.lux.presentation.movie.detail.MovieDetailActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ceduliocezar on 05/12/16.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class, PresentationModule.class})
public interface AppComponent {

    void inject(MovieDetailActivity activity);

    VideosRepository movieRepository();

    VideoServiceApi videoServiceApi();

    MoviesServiceApi moviesServiceApi();

    MoviesRepository moviesRepository();

    GenresRepository genresRepository();

    GenresServiceApi genresServiceApi();

    BackdropRepository backdropRepository();

    BackdropServiceApi backdropServiceApi();
}
