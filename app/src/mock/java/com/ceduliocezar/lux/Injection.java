package com.ceduliocezar.lux;

import android.content.Context;

import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.poster.PosterProvider;
import com.ceduliocezar.lux.data.thumbnail.ThumbnailProvider;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.ceduliocezar.lux.genres.FakeGenresServiceApiImpl;
import com.ceduliocezar.lux.genres.InMemoryGenresRepository;
import com.ceduliocezar.lux.movies.FakeMoviesServiceApiImpl;
import com.ceduliocezar.lux.movies.InMemoryMoviesRepository;
import com.ceduliocezar.lux.poster.AssetPosterProvider;
import com.ceduliocezar.lux.thumbnail.AssetThumbnailProvider;
import com.ceduliocezar.lux.video.FakeVideosServiceApiImpl;
import com.ceduliocezar.lux.video.InMemoryVideosRepository;

/**
 * Created by cedulio on 03/08/2016.
 */
public class Injection {


    public static VideosRepository providesVideosRepository(Context context) {
        return new InMemoryVideosRepository(new FakeVideosServiceApiImpl(context));
    }

    public static GenresRepository providesGenreRepository(Context context) {
        return new InMemoryGenresRepository(new FakeGenresServiceApiImpl());
    }

    public static MoviesRepository providesMoviesRepository(Context context) {
        return new InMemoryMoviesRepository(new FakeMoviesServiceApiImpl());
    }

    public static PosterProvider providesPosterProvider() {
        return new AssetPosterProvider();
    }

    public static ThumbnailProvider providesThumbnailProvider(){
        return new AssetThumbnailProvider();
    }
}
