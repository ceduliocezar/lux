package com.ceduliocezar.lux;

import android.app.Application;

import com.ceduliocezar.lux.injection.AppComponent;
import com.ceduliocezar.lux.injection.AppModule;
import com.ceduliocezar.lux.injection.DaggerAppComponent;

/**
 * Created by ceduliocezar on 05/12/16.
 */

public class LuxApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = createComponent();
    }

    protected AppComponent createComponent() {
        return DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }


    public AppComponent getAppComponent() {
        return appComponent;
    }
}
