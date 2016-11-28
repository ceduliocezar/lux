package com.ceduliocezar.lux;

import android.content.Context;

import com.ceduliocezar.lux.backdrop.BackdropImageProviderImpl;
import com.ceduliocezar.lux.backdrop.BackdropServiceApiEndpoint;
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
import com.ceduliocezar.lux.genre.GenresServiceApiEndpoint;
import com.ceduliocezar.lux.movie.detail.MovieDetailActivity;
import com.ceduliocezar.lux.movies.MoviesServiceApiEndpoint;
import com.ceduliocezar.lux.poster.PosterProviderImpl;
import com.ceduliocezar.lux.thumbnail.YoutubeThumbnailProvider;
import com.ceduliocezar.lux.videos.VideoServiceApiEndpoint;

/**
 * Created by cedulio on 27/10/16.
 */

public class Injection {

    public static GenresRepository providesGenreRepository(Context context) {
        return new GenresRepositoryImpl(new GenresServiceApiEndpoint(context));
    }

    public static MoviesRepository providesMoviesRepository(Context context) {
        return new MoviesRepositoryImpl(new MoviesServiceApiEndpoint(context));
    }

    public static PosterProvider providesPosterProvider() {
        return new PosterProviderImpl();
    }

    public static ThumbnailProvider providesThumbnailProvider() {
        return new YoutubeThumbnailProvider();
    }

    public static VideosRepository providesVideosRepository(Context context) {
        return new VideosRepositoryImpl(new VideoServiceApiEndpoint(context));
    }

    public static BackdropRepository providesBackdropsRepository(Context context) {
        return new BackdropRepositoryImpl(new BackdropServiceApiEndpoint(context));
    }

    public static BackdropImageProvider providesBackdropImageProvider(MovieDetailActivity movieDetailActivity) {
        return new BackdropImageProviderImpl();
    }
}
