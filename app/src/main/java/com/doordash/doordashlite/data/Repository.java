package com.doordash.doordashlite.data;

import java.util.List;

import rx.Observable;


public class Repository {

    private DataSource remoteDataSource;

    public Repository(DataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public Observable<List<Restaurant>> getRestaurants(int start, int end) {

        Observable<List<Restaurant>> observable;

        observable = remoteDataSource.getRestaurants(start, end);

        return observable;
    }

    public Observable<Restaurant> getRestaurantData(int restaurantId) {

        Observable<Restaurant> observable;

        observable = remoteDataSource.getRestaurantData(restaurantId);

        return observable;
    }

    public Observable<TokenResponse> getTokenData(String username, String password) {

        Observable<TokenResponse> observable;

        observable = remoteDataSource.getTokenData(username, password);

        return observable;
    }
}
