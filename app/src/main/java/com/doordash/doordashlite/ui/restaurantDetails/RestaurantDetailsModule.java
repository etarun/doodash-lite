package com.doordash.doordashlite.ui.restaurantDetails;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tarun on 9/29/18.
 */

@Module
public class RestaurantDetailsModule {

    RestaurantDetailActivity restaurantDetailActivity;

    public RestaurantDetailsModule(RestaurantDetailActivity restaurantDetailActivity) {
        this.restaurantDetailActivity = restaurantDetailActivity;
    }

    @Provides
    @ActivityScope
    public RestaurantDetailContract.View provideRestaurantView() {
        return this.restaurantDetailActivity;
    }

    @Provides
    @ActivityScope
    public RestaurantDetailContract.Presenter provideRestaurantPresenter(RestaurantDetailContract.View view, Repository repository) {
        return new RestaurantDetailsPresenter(view, repository);
    }
}
