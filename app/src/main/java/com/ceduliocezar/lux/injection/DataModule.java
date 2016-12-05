package com.ceduliocezar.lux.injection;

import com.ceduliocezar.lux.backdrop.FakeBackdropServiceApi;
import com.ceduliocezar.lux.data.backdrop.BackdropRepository;
import com.ceduliocezar.lux.data.backdrop.BackdropRepositoryImpl;
import com.ceduliocezar.lux.data.backdrop.BackdropServiceApi;
import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.genre.GenresRepositoryImpl;
import com.ceduliocezar.lux.data.genre.GenresServiceApi;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.movie.MoviesRepositoryImpl;
import com.ceduliocezar.lux.data.movie.MoviesServiceApi;
import com.ceduliocezar.lux.data.video.VideoServiceApi;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.ceduliocezar.lux.data.video.VideosRepositoryImpl;
import com.ceduliocezar.lux.genres.FakeGenresServiceApiImpl;
import com.ceduliocezar.lux.movies.FakeMoviesServiceApiImpl;
import com.ceduliocezar.lux.video.FakeVideosServiceApiImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ceduliocezar on 05/12/16.
 */

@Module
public class DataModule {


    @Provides
    @Singleton
    BackdropRepository providesBackdropRepository(BackdropServiceApi backdropServiceApi) {
        return new BackdropRepositoryImpl(backdropServiceApi);
    }

    @Provides
    @Singleton
    BackdropServiceApi providesBackdropServiceApi() {
        return new FakeBackdropServiceApi();
    }

    @Provides
    @Singleton
    GenresRepository providesGenresRepository(GenresServiceApi genreServiceApi) {
        return new GenresRepositoryImpl(genreServiceApi);
    }

    @Provides
    @Singleton
    GenresServiceApi providesGenreServiceApi() {
        return new FakeGenresServiceApiImpl();
    }

    @Provides
    @Singleton
    MoviesRepository providesMovieRepository(MoviesServiceApi moviesServiceApi) {
        return new MoviesRepositoryImpl(moviesServiceApi);
    }

    @Provides
    @Singleton
    MoviesServiceApi providesMovieServiceApi() {
        return new FakeMoviesServiceApiImpl();
    }

    @Provides
    @Singleton
    VideosRepository providesVideoRepository(VideoServiceApi videoServiceApi) {
        return new VideosRepositoryImpl(videoServiceApi);
    }

    @Provides
    @Singleton
    VideoServiceApi providesVideoServiceApi() {
        return new FakeVideosServiceApiImpl();
    }

}
