package com.ceduliocezar.lux;

import android.content.Context;

import com.ceduliocezar.lux.backdrop.AssetBackdropImageProvider;
import com.ceduliocezar.lux.backdrop.FakeBackdropServiceApi;
import com.ceduliocezar.lux.data.backdrop.BackdropImageProvider;
import com.ceduliocezar.lux.data.backdrop.BackdropRepository;
import com.ceduliocezar.lux.data.backdrop.BackdropRepositoryImpl;
import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.genre.GenresRepositoryImpl;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.movie.MoviesRepositoryImpl;
import com.ceduliocezar.lux.data.poster.PosterProvider;
import com.ceduliocezar.lux.data.thumbnail.ThumbnailProvider;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.ceduliocezar.lux.data.video.VideosRepositoryImpl;
import com.ceduliocezar.lux.genres.FakeGenresServiceApiImpl;
import com.ceduliocezar.lux.movies.FakeMoviesServiceApiImpl;
import com.ceduliocezar.lux.poster.AssetPosterProvider;
import com.ceduliocezar.lux.thumbnail.AssetThumbnailProvider;
import com.ceduliocezar.lux.video.FakeVideosServiceApiImpl;

/**
 * Created by cedulio on 03/08/2016.
 */
public class Injection {


    public static BackdropRepository providesBackdropsRepository(Context context) {
        return new BackdropRepositoryImpl(new FakeBackdropServiceApi());
    }

    public static BackdropImageProvider providesBackdropImageProvider(Context context) {
        return new AssetBackdropImageProvider();
    }

    public static VideosRepository providesVideosRepository(Context context) {
        return new VideosRepositoryImpl(new FakeVideosServiceApiImpl(context));
    }

    public static GenresRepository providesGenreRepository(Context context) {
        return new GenresRepositoryImpl(new FakeGenresServiceApiImpl());
    }

    public static MoviesRepository providesMoviesRepository(Context context) {
        return new MoviesRepositoryImpl(new FakeMoviesServiceApiImpl());
    }

    public static PosterProvider providesPosterProvider() {
        return new AssetPosterProvider();
    }

    public static ThumbnailProvider providesThumbnailProvider() {
        return new AssetThumbnailProvider();
    }
}
