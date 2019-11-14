package com.doordash.doordashlite.loginActivityComponent;

import com.doordash.doordashlite.dagger.AppComponent;
import com.doordash.doordashlite.mainActivityComponent.MainActivityModule;
import com.doordash.doordashlite.scope.ActivityScope;

import dagger.Component;

/**
 * Created by tarun on 11/9/18.
 */

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {LoginActivityModule.class}
)
public interface LoginActivityComponent {

    void inject(LoginActivity loginActivity);
}
