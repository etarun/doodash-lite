package com.doordash.doordashlite.ui.restaurantDetails;

import com.doordash.doordashlite.dagger.AppComponent;
import com.doordash.doordashlite.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(
        dependencies = {AppComponent.class},
        modules = {RestaurantDetailsModule.class}
)
public interface RestaurantDetailsComponent {
    void inject(RestaurantDetailActivity restaurantDetailActivity);
}
