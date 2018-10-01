package com.doordash.doordashlite.dagger;

import com.doordash.doordashlite.BaseActivity;
import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.retrofit.RetrofitModule;
import com.doordash.doordashlite.scope.ApplicationScope;

import dagger.Component;

/**
 * Dagger App Component with Application Scope
 */

@ApplicationScope
@Component(modules = {AppModule.class, RetrofitModule.class})
public interface AppComponent {
    Repository getDataRepository();

    void inject(BaseActivity baseActivity);
}
