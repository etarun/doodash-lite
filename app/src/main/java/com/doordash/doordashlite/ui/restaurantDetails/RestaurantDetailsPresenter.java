package com.doordash.doordashlite.ui.restaurantDetails;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.data.Restaurant;
import com.doordash.doordashlite.mvp.BasePresenter;

public class RestaurantDetailsPresenter extends BasePresenter<RestaurantDetailContract.View> implements RestaurantDetailContract.Presenter {

    private Repository dataRepository;


    @Override
    public void onViewActive(RestaurantDetailContract.View view) {

    }

    public RestaurantDetailsPresenter(RestaurantDetailContract.View view, Repository dataRepository) {
        this.view = view;
        this.dataRepository = dataRepository;
    }


    @Override
    public void onCreate(Restaurant restaurant) {
        if (restaurant == null) {
            view.showErrorMessage();
            return;
        }
        if (restaurant.getImageURL() != null) {
            view.showRestaurantImage(restaurant.getImageURL());
        }
        if (restaurant.getPhone() != null) {
            view.setResPhone(restaurant.getPhone());
        }
        if (restaurant.getDeliveryFee() != null) {
            view.setResDeliveryFee(restaurant.getDeliveryFee());
        }
        if (restaurant.getName() != null) {
            view.setResName(restaurant.getName());
        }
    }
}
