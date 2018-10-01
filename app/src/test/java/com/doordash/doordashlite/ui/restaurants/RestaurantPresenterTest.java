package com.doordash.doordashlite.ui.restaurants;

import android.content.Context;

import com.doordash.doordashlite.data.Repository;
import com.doordash.doordashlite.data.Restaurant;
import com.doordash.doordashlite.ui.restaurants.RestaurantsContract;
import com.doordash.doordashlite.ui.restaurants.RestaurantsPresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import rx.Observable;
import rx.Scheduler;
import rx.Subscription;
import rx.android.plugins.RxAndroidPlugins;
import rx.android.plugins.RxAndroidSchedulersHook;
import rx.schedulers.Schedulers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by tarun on 9/30/18.
 */

@RunWith(MockitoJUnitRunner.class)
public class RestaurantPresenterTest {

    @Mock
    private RestaurantsContract.View mockView;

    @Mock
    private Repository mockDataRepository;

    @Mock
    private List<Restaurant> restaurantsData;

    @Mock
    private Restaurant restaurant;

    private RestaurantsContract.Presenter presenter;

    @Before
    public void setup() {
        presenter = new RestaurantsPresenter(mockView, mockDataRepository);
        RxAndroidPlugins.getInstance().registerSchedulersHook(new RxAndroidSchedulersHook() {
            @Override
            public Scheduler getMainThreadScheduler() {
                return Schedulers.immediate();
            }
        });
    }

    @Test
    public void getRestaurants_testCallback() {
        int start = 0;
        int end = 50;
        when(mockDataRepository.getRestaurants(eq(start), eq(end)))
                .thenReturn(Observable.just(restaurantsData));

        presenter.getRestaurants();

        verify(mockView).setProgressBar(true);
        verify(mockDataRepository).getRestaurants(eq(start), eq(end));

        verify(mockView).showRestaurants(restaurantsData);
        verify(mockView, times(1)).setProgressBar(false);
    }

    @Test
    public void getRestaurantData_testCallback() {
        int resId = 9;
        when(mockDataRepository.getRestaurantData(eq(resId)))
                .thenReturn(Observable.just(restaurant));

        presenter.getRestaurantData(resId);

        verify(mockView).setProgressBar(true);
        verify(mockView).navigateToRestaurantDetails(restaurant);
        verify(mockView, times(1)).setProgressBar(false);
    }

}
