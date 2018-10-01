package com.doordash.doordashlite.retrofit;

import com.doordash.doordashlite.data.Restaurant;

import java.util.List;
import java.util.Map;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import rx.Observable;

public interface APIInterface {

    @GET("/v2/restaurant/")
    Observable<List<Restaurant>> getAllRestaurants(@QueryMap Map<String, String> queryMap);

    @GET("/v2/restaurant/{id}")
    Observable<Restaurant> getRestaurantData(@Path(value = "id", encoded = true) int id);
}
