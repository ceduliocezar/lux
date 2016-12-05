package com.ceduliocezar.lux;

import android.app.Application;

import com.ceduliocezar.lux.injection.AppComponent;
import com.ceduliocezar.lux.injection.AppModule;
import com.ceduliocezar.lux.injection.DaggerAppComponent;
import com.ceduliocezar.lux.injection.DataModule;

/**
 * Created by ceduliocezar on 05/12/16.
 */

public class LuxApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder()
                .dataModule(new DataModule())
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
