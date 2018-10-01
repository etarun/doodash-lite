package com.doordash.doordashlite.ui.restaurants;

import com.doordash.doordashlite.dagger.AppComponent;
import com.doordash.doordashlite.scope.FragmentScope;

import dagger.Component;

@FragmentScope
@Component(
        dependencies = {AppComponent.class},
        modules = {RestaurantsModule.class}
)
public interface RestaurantsComponent {

    void inject(RestaurantsFragment restaurantsFragment);
}
