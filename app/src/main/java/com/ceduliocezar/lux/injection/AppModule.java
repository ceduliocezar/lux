package com.ceduliocezar.lux.injection;

import android.app.Application;
import android.content.Context;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ceduliocezar on 05/12/16.
 */


@Module
public class AppModule {

    Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Provides
    @Named("applicationContext")
    public Context providesApplication() {
        return application;
    }
}
