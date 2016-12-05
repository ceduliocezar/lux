package com.ceduliocezar.lux.injection;

import android.content.Context;

import com.ceduliocezar.lux.data.backdrop.BackdropRepository;
import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.movie.MoviesRepository;
import com.ceduliocezar.lux.data.video.VideosRepository;
import com.ceduliocezar.lux.presentation.movie.detail.MovieDetailContract;
import com.ceduliocezar.lux.presentation.movie.detail.MovieDetailPresenter;
import com.ceduliocezar.lux.presentation.movie.list.MoviesContract;
import com.ceduliocezar.lux.presentation.movie.list.MoviesPresenter;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by TECBMCCS on 05/12/16.
 */


@Module
public class PresentationModule {

    @Provides
    MovieDetailContract.UserActionsListener providesMovieDetailLister(
            @Named("applicationContext") Context context,
            VideosRepository videosRepository,
            MoviesRepository moviesRepository,
            BackdropRepository backdropRepository) {

        return new MovieDetailPresenter(context, videosRepository, moviesRepository,
                backdropRepository);
    }

    @Provides
    MoviesContract.UserActionsListener providesMoviesActionListener(
            MoviesRepository moviesRepository, GenresRepository genresRepository) {

        return new MoviesPresenter(moviesRepository, genresRepository);
    }
}
