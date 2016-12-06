package com.ceduliocezar.lux.injection;

import com.ceduliocezar.lux.presentation.movie.detail.MovieDetailActivity;
import com.ceduliocezar.lux.presentation.movie.list.MoviesFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ceduliocezar on 05/12/16.
 */

@Singleton
@Component(modules = {AppModule.class, DataModule.class, PresentationModule.class,
        RepositoryModule.class, NetModule.class})
public interface AppComponent {

    void inject(MovieDetailActivity activity);

    void inject(MoviesFragment moviesFragment);
}
