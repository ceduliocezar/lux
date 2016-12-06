package com.ceduliocezar.lux.injection;

import com.ceduliocezar.lux.data.backdrop.AssetBackdropImageProvider;
import com.ceduliocezar.lux.data.backdrop.BackdropImageProvider;
import com.ceduliocezar.lux.data.backdrop.BackdropServiceApi;
import com.ceduliocezar.lux.data.backdrop.FakeBackdropServiceApi;
import com.ceduliocezar.lux.data.genre.GenresServiceApi;
import com.ceduliocezar.lux.data.genres.FakeGenresServiceApiImpl;
import com.ceduliocezar.lux.data.movie.MoviesServiceApi;
import com.ceduliocezar.lux.data.movies.FakeMoviesServiceApiImpl;
import com.ceduliocezar.lux.data.poster.AssetPosterProvider;
import com.ceduliocezar.lux.data.poster.PosterProvider;
import com.ceduliocezar.lux.data.thumbnail.AssetThumbnailProvider;
import com.ceduliocezar.lux.data.thumbnail.ThumbnailProvider;
import com.ceduliocezar.lux.data.video.FakeVideosServiceApiImpl;
import com.ceduliocezar.lux.data.video.VideoServiceApi;

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
    BackdropServiceApi providesBackdropServiceApi() {
        return new FakeBackdropServiceApi();
    }


    @Provides
    @Singleton
    GenresServiceApi providesGenreServiceApi() {
        return new FakeGenresServiceApiImpl();
    }

    @Provides
    @Singleton
    VideoServiceApi providesVideoServiceApi() {
        return new FakeVideosServiceApiImpl();
    }

    @Provides
    @Singleton
    MoviesServiceApi providesMovieServiceApi() {
        return new FakeMoviesServiceApiImpl();
    }

    @Provides
    @Singleton
    ThumbnailProvider providesThumbnailprovider() {
        return new AssetThumbnailProvider();
    }

    @Provides
    @Singleton
    PosterProvider providesPosterProvider() {
        return new AssetPosterProvider();
    }

    @Provides
    @Singleton
    BackdropImageProvider providesBackdropImageProvider() {
        return new AssetBackdropImageProvider();
    }

}