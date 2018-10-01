package com.doordash.doordashlite.ui.restaurants;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.data.Restaurant;
import com.doordash.doordashlite.mvp.BasePresenter;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * The Presenter that fetches restaurant data by calling {@link Repository} at the request of
 * its View "{@link RestaurantsContract.View}", and then delivers the data back to
 * its View.
 * The presenter also calls other relevant methods of its View such as for
 * showing/hiding progress bar.
 */
public class RestaurantsPresenter extends BasePresenter<RestaurantsContract.View> implements RestaurantsContract.Presenter {

    private Repository dataRepository;

    //To destroy Subscriptions IfAny.
    @Override
    public void onViewActive(RestaurantsContract.View view) {

    }

    public RestaurantsPresenter(RestaurantsContract.View view, Repository dataRepository) {
        this.view = view;
        this.dataRepository = dataRepository;
    }

    @Override
    public void getRestaurants() {
        view.setProgressBar(true);
        Observable<List<Restaurant>> restaurantsData = dataRepository.getRestaurants(0, 50);
        restaurantsData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restaurants -> {
                    view.showRestaurants(restaurants);
                    view.setProgressBar(false);
                }, throwable -> {
                    view.showErrorMessage();
                    view.setProgressBar(false);
                });
    }

    @Override
    public void getRestaurantData(int restaurantId) {
        view.setProgressBar(true);
        Observable<Restaurant> restaurantData = dataRepository.getRestaurantData(restaurantId);
        restaurantData.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(restaurant -> {
                    view.setProgressBar(false);
                    view.navigateToRestaurantDetails(restaurant);
                }, throwable -> {
                    view.setProgressBar(false);
                    view.showErrorMessage();
                });
    }
}
