package com.doordash.doordashlite.ui.restaurants;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.scope.FragmentScope;

import dagger.Module;
import dagger.Provides;

@Module
public class RestaurantsModule {

    RestaurantsContract.View view;

    public RestaurantsModule(RestaurantsContract.View view) {
        this.view = view;
    }


    @Provides
    @FragmentScope
    public RestaurantsContract.View providePhotosView() {
        return this.view;
    }

    @Provides
    @FragmentScope
    public RestaurantsContract.Presenter providePhotosPresenter(RestaurantsContract.View view, Repository repository) {
        return new RestaurantsPresenter(view, repository);
    }
}
