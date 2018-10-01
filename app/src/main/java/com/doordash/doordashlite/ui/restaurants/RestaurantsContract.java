package com.doordash.doordashlite.ui.restaurants;

import com.doordash.doordashlite.data.Restaurant;
import com.doordash.doordashlite.mvp.IBasePresenter;
import com.doordash.doordashlite.mvp.IBaseView;

import java.util.List;


public class RestaurantsContract {

    public interface View extends IBaseView {

        void showRestaurants(List<Restaurant> restaurants);

        void showErrorMessage();

        void setProgressBar(boolean show);

        void navigateToRestaurantDetails(Restaurant restaurant);
    }

    public interface Presenter extends IBasePresenter<View> {

        void getRestaurants();

        void getRestaurantData(int restaurantId);
    }
}
