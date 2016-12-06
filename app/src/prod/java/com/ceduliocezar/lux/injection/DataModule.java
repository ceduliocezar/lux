package com.ceduliocezar.lux.injection;

import android.content.Context;

import com.ceduliocezar.lux.data.backdrop.BackdropImageProvider;
import com.ceduliocezar.lux.data.backdrop.BackdropImageProviderImpl;
import com.ceduliocezar.lux.data.backdrop.BackdropServiceApi;
import com.ceduliocezar.lux.data.backdrop.BackdropServiceApiEndpoint;
import com.ceduliocezar.lux.data.cloud.MovieDBRESTApi;
import com.ceduliocezar.lux.data.genre.GenresServiceApi;
import com.ceduliocezar.lux.data.genre.GenresServiceApiEndpoint;
import com.ceduliocezar.lux.data.movie.MoviesServiceApi;
import com.ceduliocezar.lux.data.movies.MoviesServiceApiEndpoint;
import com.ceduliocezar.lux.data.poster.PosterProvider;
import com.ceduliocezar.lux.data.poster.PosterProviderImpl;
import com.ceduliocezar.lux.data.thumbnail.ThumbnailProvider;
import com.ceduliocezar.lux.data.thumbnail.YoutubeThumbnailProvider;
import com.ceduliocezar.lux.data.video.VideoServiceApi;
import com.ceduliocezar.lux.data.videos.VideoServiceApiEndpoint;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ceduliocezar on 06/12/16.
 */


@Module
public class DataModule {

    @Provides
    @Singleton
    BackdropServiceApi providesBackdropServiceApi(@Named("applicationContext") Context context, MovieDBRESTApi service) {
        return new BackdropServiceApiEndpoint(context, service);
    }


    @Provides
    @Singleton
    GenresServiceApi providesGenreServiceApi(@Named("applicationContext") Context context, MovieDBRESTApi service) {
        return new GenresServiceApiEndpoint(context, service);
    }

    @Provides
    @Singleton
    VideoServiceApi providesVideoServiceApi(@Named("applicationContext") Context context, MovieDBRESTApi service) {
        return new VideoServiceApiEndpoint(context, service);
    }

    @Provides
    @Singleton
    MoviesServiceApi providesMovieServiceApi(@Named("applicationContext") Context context,
                                             MovieDBRESTApi service) {
        return new MoviesServiceApiEndpoint(context,service);
    }

    @Provides
    @Singleton
    ThumbnailProvider providesThumbnailprovider() {
        return new YoutubeThumbnailProvider();
    }

    @Provides
    @Singleton
    PosterProvider providesPosterProvider() {
        return new PosterProviderImpl();
    }

    @Provides
    @Singleton
    BackdropImageProvider providesBackdropImageProvider() {
        return new BackdropImageProviderImpl();
    }
}
