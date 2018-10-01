package com.doordash.doordashlite.ui.restaurantDetails;

import com.doordash.doordashlite.data.Restaurant;


class RestaurantDetailContract {

    interface View {

        void showErrorMessage();

        void setProgressBar(boolean show);

        void showRestaurantImage(String url);

        void setResName(String name);

        void setResPhone(String phone);

        void setResDeliveryFee(String deliveryFee);
    }

    interface Presenter {
        void onCreate(Restaurant restaurant);
    }
}
