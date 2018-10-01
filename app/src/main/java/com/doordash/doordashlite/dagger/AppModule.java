package com.doordash.doordashlite.dagger;

import com.doordash.doordashlite.MainDoorDashApplication;
import com.doordash.doordashlite.retrofit.RetrofitModule;
import com.doordash.doordashlite.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;


@Module(includes = RetrofitModule.class)
public class AppModule {
    private MainDoorDashApplication baseApplication;

    public AppModule(MainDoorDashApplication baseApplication) {
        this.baseApplication = baseApplication;
    }

    @Provides
    @ApplicationScope
    public MainDoorDashApplication provideApplication() {
        return baseApplication;
    }
}
