package com.doordash.doordashlite;

import android.app.Application;

import com.doordash.doordashlite.dagger.AppComponent;
import com.doordash.doordashlite.dagger.AppModule;
import com.doordash.doordashlite.dagger.DaggerAppComponent;
import com.doordash.doordashlite.retrofit.RetrofitModule;


public class MainDoorDashApplication extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .retrofitModule(new RetrofitModule())
                .build();
    }

    public AppComponent getApplicationComponent() {
        return appComponent;
    }
}
