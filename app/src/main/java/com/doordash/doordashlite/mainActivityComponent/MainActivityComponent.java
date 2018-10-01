package com.doordash.doordashlite.mainActivityComponent;

import com.doordash.doordashlite.dagger.AppComponent;
import com.doordash.doordashlite.scope.ActivityScope;

import dagger.Component;


@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {MainActivityModule.class}
)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);
}
