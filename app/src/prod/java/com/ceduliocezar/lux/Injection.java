package com.ceduliocezar.lux;

import android.content.Context;

import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.poster.PosterProvider;
import com.ceduliocezar.lux.data.thumbnail.ThumbnailProvider;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.ceduliocezar.lux.genre.GenresRepositoryImpl;
import com.ceduliocezar.lux.movies.MoviesRepositoryImpl;
import com.ceduliocezar.lux.poster.PosterProviderImpl;
import com.ceduliocezar.lux.thumbnail.YoutubeThumbnailProvider;
import com.ceduliocezar.lux.videos.VideosRepositoryImpl;

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

    public static PosterProvider providesPosterProvider() {
        return new PosterProviderImpl();
    }

    public static ThumbnailProvider providesThumbnailProvider() {
        return new YoutubeThumbnailProvider();
    }

    public static VideosRepository providesVideosRepository(Context context) {
        return new VideosRepositoryImpl(context);
    }
}
